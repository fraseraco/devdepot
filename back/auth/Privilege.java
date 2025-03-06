import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    //Default constructor
    public Privilege(){
        this.name = "";
    }

    //Full parameter constructor
    public Privilege(String name){
        this.name = name;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setRoles(Collection<Role> newRoles){
        this.roles.addAll(newRoles);
    }

    //Getters
    public String getName(){
        return this.name;
    }
    public Collection<Role> getRoles(){
        return this.roles;
    }
}
