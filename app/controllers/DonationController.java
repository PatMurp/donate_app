package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class DonationController extends Controller
{
  
  public static void index()
  {
    List <Candidate> candidates = Candidate.findAll();
    User user = Accounts.getCurrentUser();
    render(user, candidates);
  }
  
  public static void donateTo(Long id)
  {
    Candidate candidate = Candidate.findById(id);
    User user = Accounts.getCurrentUser();
    String prog = getPercentTargetAchieved();
    String progress = prog + "%";
    Logger.info("Donation ctrler : user is " + user.email);
    Logger.info("Donation ctrler : candidate is " + candidate.firstName);
    Logger.info("Donation ctrler : percent target achieved " + progress);
    List<Donation> donations = user.donations;
    session.put("selectedCand", candidate.email);
    render(user, progress, donations, candidate);
  }
  
  public static void donate(int amountDonated, String methodDonated, String email)
  {
    
    User user = Accounts.getCurrentUser();
    Candidate toCandidate = Candidate.findByEmail(session.get("selectedCand"));
    
    Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);
    Donation donation = new Donation(amountDonated, methodDonated);
    user.donations.add(donation);
    user.save();
    toCandidate.donations.add(donation);
    toCandidate.save();
    index();
  }

 

  public static Candidate selectedCandidate(Long id)
  {

    Candidate candidate = Candidate.findById(id);
    return candidate;
  }

  private static long getDonationTarget()
  {
    return 20000;
  }

  public static String getPercentTargetAchieved()
  {
    List<Donation> allDonations = Donation.findAll();
    long total = 0;
    for (Donation donation : allDonations)
    {
      total += donation.amount;
    }
    long target = getDonationTarget();
    long percentachieved = (total * 100 / target);
    String progress = String.valueOf(percentachieved);
    Logger.info("Percent of target achieved (string) " + progress + " percentachieved (long)= " + percentachieved);
    return progress;
  }

  public static void renderReport(Long id)
  {
    User user = Accounts.getCurrentUser();
    //Candidate candidate = Candidate.findByEmail(session.get("selectedCand"));
    //Candidate candidate = Candidate.findById(id);
    List<Donation> donations = user.donations;
    //List<Donation> donationsTo = candidate.donations;
    render(donations);
  }
}
