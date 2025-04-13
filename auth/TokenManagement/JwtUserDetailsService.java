
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
}
