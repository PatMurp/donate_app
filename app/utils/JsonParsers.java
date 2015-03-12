package utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import models.Admin;
import models.Candidate;
import models.Donation;
import models.User;

public class JsonParsers
{
  public static JSONSerializer userSerializer     = new JSONSerializer().exclude("class")
                                                                        .exclude("persistent")
                                                                        .exclude("entityId")
                                                                        .include("donations");
  public static JSONSerializer donationSerializer = new JSONSerializer().exclude("class")
                                                                        .exclude("persistent")
                                                                        .exclude("entityId"); 
  
  public static JSONSerializer adminSerializer = new JSONSerializer().exclude("class")
                                                                     .exclude("persistent")
                                                                     .exclude("entityId");
  
  public static JSONSerializer candidateSerializer     = new JSONSerializer().exclude("class")
                                                                             .exclude("persistent")
                                                                             .exclude("entityId")
                                                                             .include("donations");
  public static User json2User(String json)
  {
    return new JSONDeserializer<User>().deserialize(json, User.class); 
  }
  
  
  public static List<User> json2Users(String json)
  {
    return new JSONDeserializer<ArrayList<User>>().use("values", User.class).deserialize(json);
  }
  
 
  public static String user2Json(Object obj)
  {
    return userSerializer.serialize(obj);
  }
  
  public static List<User> users2Json(String json)
  {
    return new JSONDeserializer<ArrayList<User>>().use("values", User.class).deserialize(json);
  } 
  
 
    
 
  public static Donation json2Donation(String json)
  {
    return  new JSONDeserializer<Donation>().deserialize(json, Donation.class);    
  }
  
  public static String donation2Json(Object obj)
  {
    return donationSerializer.serialize(obj);
  }  
  
  public static List<Donation> json2Donations(String json)
  {
    return new JSONDeserializer<ArrayList<Donation>>().use("values", Donation.class).deserialize(json);
  }  
  
  // admin JsonParsers
  public static Admin json2Admin(String json)
  {
    return new JSONDeserializer<Admin>().deserialize(json, Admin.class);
  }
  
  public static List<Admin> json2Admins(String json)
  {
    return new JSONDeserializer<ArrayList<Admin>>().use("values", Admin.class).deserialize(json);
  }
  
  public static String admin2Json(Object obj)
  {
    return adminSerializer.serialize(obj);
  }
  
  public static List<Admin> admin2Json(String json)
  {
    return new JSONDeserializer<ArrayList<Admin>>().use("values", Admin.class).deserialize(json);
  }
  
  // candidate JsonParsers
  public static Candidate json2Candidate(String json)
  {
    return new JSONDeserializer<Candidate>().deserialize(json,Candidate.class);
  }
  
  public static List<Candidate> json2Candidates(String json)
  {
    return new JSONDeserializer<ArrayList<Candidate>>().use("values", Candidate.class).deserialize(json);
  }
  
  public static String candidate2Json(Object obj)
  {
    return candidateSerializer.serialize(obj);
  }
  
  public static List<Candidate> candidates2Json(String json)
  {
    return new JSONDeserializer<ArrayList<Candidate>>().use("values", Candidate.class).deserialize(json);
  }
}
