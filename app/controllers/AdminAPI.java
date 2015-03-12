package controllers;

import java.util.List;

import models.Admin;
import play.mvc.Controller;
import utils.JsonParsers;

public class AdminAPI extends Controller
{
  public static void admins ()
  {
    List<Admin> admins = Admin.findAll();
    renderJSON(JsonParsers.admin2Json(admins));
  }
  
  public static void admin(Long id)
  {
    Admin admin = Admin.findById(id);
    if (admin ==null)
    {
      notFound();
    }
    else
    {
      renderJSON(JsonParsers.admin2Json(admin));
    }
  }

}
