package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.Admin;



public class AdminLogin extends Controller
{

  public static void index()
  {
    render();
  }

  public static void authenticate(String userName, String adminPassword)
  {
    Logger.info("Attempting to authenticate with " + userName + ":" + adminPassword);

    Admin admin = Admin.findByuserName(userName);
    if ((admin != null) && (admin.checkPassword(adminPassword) == true))
    {
      Logger.info("Authentication successful of " + admin.userName);
      session.put("logged_in_adminid", admin.id);
      AdminDonate.index();
    }
    else
    {
      Logger.info("Authentication to admin failed");
      Home.index();
    }

  }

  public static Admin getCurrentAdmin()
  {
    String adminId = session.get("logged_in_adminid");
    if (adminId == null)
    {
      return null;
    }
    Admin logged_in_adminid = Admin.findById(Long.parseLong(adminId));
    Logger.info("In AdminLogin controller: Logged is ammin is " + logged_in_adminid.userName);
    return logged_in_adminid;
  }

  // checks to see if admin is logged in
  public static boolean isLoggedInAdmin()
  {
    if (session.contains("logged_in_adminid"))
    {
      return true;
    }
    return false;
  }

}
