
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.swe.backend.Entity.Role;

@Service
public class JwtUserDetailsService implements UserDetailsService { 

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override 
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = UserDto.findByUserName(username);

        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(passwordEncoder.encode(user.getPassword()));

            List<Role> roleList = new ArrayList<>(user.getRoles());
            String[] roles = new String[roleList.size()];
            for (int i = 0; i < roleList.size(); i++) {
                roles[i] = roleList.get(i).getName();
            }

            userBuilder.authorities(roles);
        } else {
            throw new UsernameNotFoundException("User does not exist");
        }

        return userBuilder.build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities( //Creates authority list from database roles
    Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.addAll(role.getPrivileges()
                    .stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName()))
                    .collect(Collectors.toList()));
        }
        
        return authorities;
    }
}
