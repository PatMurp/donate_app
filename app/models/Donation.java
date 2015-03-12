package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@SuppressWarnings("serial")
@Entity
public class Donation extends Model
{
  public int    amount;
  public String method;
  
  
  public Donation()
  {}
  
  public Donation (int amount, String method)
  {
    this.amount = amount;
    this.method = method;
  }
  

  public String toString()
  {
    return amount + ", " + method;
  }
}
