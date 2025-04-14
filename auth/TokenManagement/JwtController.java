
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swe.backend.DTOs.UserDto;
import com.swe.backend.Entity.User;




@RestController
@CrossOrigin
public class JwtController {
   @Autowired
   private JwtUserDetailsService userDetailsService;
   @Autowired
   private CustomAuthenticationManager authenticationManager;
   @Autowired
   private TokenManager tokenManager;
   @Autowired
   public List<GrantedAuthority> authorities;


   // Get a JWT Token once user is authenticated, otherwise throw BadCredentialsException
   @PostMapping("/login")
   public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel
      request) throws Exception {
      try {
         authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())); 
      } catch (DisabledException e) {
         throw new Exception("USER_DISABLED", e);
      } catch (BadCredentialsException e) {
         throw new Exception("INVALID_CREDENTIALS", e);
      }
      final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
      final String jwtToken = tokenManager.generateJwtToken(userDetails);
      return ResponseEntity.ok(new JwtResponseModel(jwtToken));
   }

   //For other mappings, just need to use the ValidateToken() method

   @PostMapping("/admin") //FIXME: Placeholder mapping
   public ResponseEntity<JwtResponseModel> checkToken(@RequestBody JwtRequestModel
      request) {
      
   }

}

