package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@SuppressWarnings("serial")
@Entity
public class Candidate extends Model
{
  public String firstName;
  public String lastName;
  public String email;
  public String officeAddress;
  
  @OneToMany(cascade = CascadeType.ALL)
  public List<Donation> donations = new ArrayList<Donation>();
  
  public Candidate()
  {}
  
  public Candidate(String firstName, String lastName, String email, String officeAddress)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.officeAddress = officeAddress;
  }
  
  public static Candidate findByEmail(String email)
  {
    return find("email", email).first();
  }

}
