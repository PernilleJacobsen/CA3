package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users implements Serializable {
  
  private String password;  //Pleeeeease dont store me in plain text
  @Id 
  private String userName;
  @ElementCollection
  List<String> roles = new ArrayList();

    public Users()
    {
    }

    public Users(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }
  
  public Users(String userName, String password,List<String> roles) {
    this.userName = userName;
    this.password = password;
    this.roles = roles;
  }
  
  public void AddRole(String role){
    roles.add(role);
  }
    
  public List<String> getRoles() {
   return roles;
  }
 
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  
 
          
}
