package controllers;

import java.util.List;

import models.Candidate;
import models.Donation;
import models.User;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;

public class AdminDonate extends Controller
{
  // ensure admin is logged in before any action method is called
  @Before
  static void checkAuthentificationCand()
  {
    if (AdminLogin.isLoggedInAdmin() == false)
    {
      AdminLogin.index();
    }
  }

  public static void index()
  {
    List<Candidate> candidates = Candidate.findAll();
    render(candidates);
  }
  
  public static void donationReport()
  {
    List<User> users = User.findAll();
    List<Donation> donations = Donation.findAll();
    render(users, donations);
  }
  
  // add a candidate
  public static void register (Candidate candidate)
  {
    candidate.save();
    index();
  }
  
  public static void userReport()
  {
    List<User> users = User.findAll();
    render(users);
  }
  
  public static void delete (Long id)
  {
    Candidate candidate = Candidate.findById(id);
    if (candidate != null)
    {
      Logger.info("Trying to delete " + candidate.firstName);
      List<Donation> donations = Donation.findAll();
      for (Donation donation : donations)
      {
        if (candidate.donations.contains(candidate.id));
        {
          candidate.donations.remove(donation);
          candidate.save();
        }
      }
    }
    candidate.delete();
    index();
  }
  
  public static void editCand (Long id)
  {
    Candidate candidate = Candidate.findById(id);
    session.put("editCand", candidate.email);
    render(candidate);
  }
  
  public static void changeCand (Long id ,String firstName, String lastName, String email, 
      String officeAddress)
  {
    //Candidate candidate = Candidate.findById(id);
    Candidate candidate = Candidate.findByEmail(session.get("editCand"));
    if (candidate != null)
    {
      candidate.firstName = firstName;
      candidate.lastName = lastName;
      candidate.email = email;
      candidate.officeAddress = officeAddress;
      candidate.save();
    }
    index();
  }
  
  public static void deleteUser(Long id)
  {
    User user = User.findById(id);
    if (user != null)
    {
      Logger.info("Trying to delete " + user.firstName);
      List<Donation> donations = Donation.findAll();
      for (Donation donation : donations)
      {
        if (user.donations.contains(user.id));
        {
          user.donations.remove(donation);
          user.save();
        }
      }
    }
    user.delete();
    index();
  }

}
