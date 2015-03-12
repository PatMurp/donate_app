package controllers;

import play.*;
import play.mvc.*;
import models.*;

public class Accounts extends Controller
{
  public static void index()
  {
    render();
  }

  public static void signup()
  {
    render();
  }
  

  public static void register(boolean usCitizen, String firstName, String lastName, String email, String confirmEmail,
      String password, String age, String constituency, String address)
  {
    validation.required(firstName); // first Name required
    validation.required(lastName); // last Name required
    validation.required(email); // email required
    validation.email(email); // valid email
    validation.required(confirmEmail); // confirm email required
    validation.equals(confirmEmail, email); // emailConfirm must match email
    validation.required(password); //  password required
    // password must have at least 8 characters, one uppercase letter, and one numeric character
    validation.match(password, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    validation.required(age); // age required
    validation.match(age, "[0-9]+"); // numeric characters only
    validation.range(age, 16, 120); // minimum age 16 max 120
    validation.required(constituency); // constituency required
    validation.required(address); // address required
    
    // display errors 
    if(validation.hasErrors())
    {
      render("@signup");
    }
    
    Logger.info( firstName + " " + lastName + " " + email + " " + password);
    User user = new User(firstName, lastName, email, password, age, constituency, address);
    
    user.save();
    login();
  }

  public static void login()
  {
    render();
  }

  public static void logout()
  {
    session.clear();
    Home.index();
  }

  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    User user = User.findByEmail(email);
    if ((user != null) && (user.checkPassword(password) == true))
    {
      Logger.info("Successfull authentication of  " + user.firstName + " " + user.lastName);
      session.put("logged_in_userid", user.id);
      DonationController.index();
    }
    else
    {
      Logger.info("Authentication failed");
      login();
    }
  }
  
  public static void editProfile()
  {
    User user = getCurrentUser();
    render(user);
  }
  
  public static void change(String firstName, String lastName, String email, String password,
      String age, String constituency, String address)
  {
    User user = getCurrentUser();
    user.firstName = firstName;
    user.lastName = lastName;
    user.email = email;
    user.age = age;
    user.constituency = constituency;
    user.address = address;
    user.save();
    DonationController.index();
  }

  public static User getCurrentUser()
  {
    String userId = session.get("logged_in_userid");
    if (userId == null)
    {
      index();
    }
    User logged_in_user = User.findById(Long.parseLong(userId));
    Logger.info("In Accounts controller: Logged in user is " + logged_in_user.firstName);
    return logged_in_user;
  }
}