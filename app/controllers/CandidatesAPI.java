package controllers;

import java.util.List;

import com.google.gson.JsonElement;

import models.Candidate;
import play.mvc.Controller;
import utils.JsonParsers;

public class CandidatesAPI extends Controller
{
  public static void candidates()
  {
    List<Candidate> candidates = Candidate.findAll();
    renderJSON(JsonParsers.candidate2Json(candidates));
  }
  
  public static void candidate(Long id)
  {
    Candidate candidate = Candidate.findById(id);
    if (candidate == null)
    {
      notFound();
    }
    else
    {
      renderJSON(JsonParsers.candidate2Json(candidate));
    }
  }
  
  public static void createCandidate(JsonElement body)
  {
    Candidate candidate = JsonParsers.json2Candidate(body.toString());
    candidate.save();
    renderJSON(JsonParsers.candidate2Json(candidate));
  }
  
  public static void deleteCandidate(Long id)
  {
    Candidate candidate = Candidate.findById(id);
    if (candidate == null)
    {
      notFound();
    }
    else
    {
      candidate.delete();
      renderText("Seccess");
    }
  }

}
