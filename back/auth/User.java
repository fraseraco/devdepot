package devdepot.com.demo;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String orders[];
    private boolean enabled;
    private boolean tokenExpired;

    @ManyToMany 
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
    private Collection<Role> roles;

    //Default constructor
    public User(){
      this.firstName = "";
      this.lastName = "";
      this.email = "";
      this.password = "";
      this.enabled = false;
      this.tokenExpired = true;
    }

    //Full parameter constructor
    public User(String fName, String lName, String mail, String pass, boolean enable, boolean token){
      this.firstName = fName;
      this.lastName = lName;
      this.email = mail;
      this.password = pass;
      this.enabled = enable;
      this.tokenExpired = token;
    }
    //Setter methods
    public void setFirstName(String name){
      this.firstName = name;
    }
    public void setLastName(String name){
      this.lastName = name;
    }
    public void setEmail(String email){
      this.email = email;
    }
    public void setPassword(String pass){
      this.password = pass;
    }
    public void setRoles(List<Role> newRoles){
        roles.addAll(newRoles);
    }
    public boolean addOrder(){
      return false;
    }

    //Getter methods
    public String getFirstName(){
      return this.firstName;
    }
    public String getLastName(){
      return this.lastName;
    }
    public String getEmail(){
      return this.email;
    }
    public String getPassword(){
      return this.password;
    }
    public Collection<Role> getRoles(){
      return roles;
    }
    

}
