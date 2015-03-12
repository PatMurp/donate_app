package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import play.db.jpa.Model;

@SuppressWarnings("serial")
@Entity
public class User extends Model
{
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  public String age;
  public String constituency;
  public String address;
  
  @OneToMany(cascade = CascadeType.ALL)
  public List<Donation> donations = new ArrayList<Donation>();
  
  /*@OneToMany(mappedBy="from", cascade = CascadeType.ALL)
  public List<Donation> donations = new ArrayList<Donation>();*/
  
  public User()
  {}
  
  public User(String firstName, String lastName, String email, String password, 
      String age, String constituency, String address)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.age= age;
    this.constituency = constituency;
    this.address = address;
    
  } 
  
  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }  
  
  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }  
}