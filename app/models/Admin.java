package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@SuppressWarnings("serial")
@Entity
public class Admin extends Model
{
  public String userName;
  public String adminPassword;
  
  public Admin(String userName, String adminPassword)
  {
    this.userName = userName;
    this.adminPassword = adminPassword;
  }
  
  public static Admin findByuserName(String userName)
  {
    return find("userName", userName).first();
  }
  
  public boolean checkPassword(String adminPassword)
  {
    return this.adminPassword.equals(adminPassword);
  }

}
