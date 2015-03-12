import org.junit.*;

import models.Admin;
import play.test.*;


public class AdminTest extends UnitTest
{
  private Admin a1, a2, a3;
  
  @Before
  public void setup()
  {
    a1 = new Admin("admin1", "secret1");
    a2 = new Admin("admin2", "secret2");
    a3 = new Admin("admin3", "secret3");
  }
  
  @After
  public void teardown()
  {
    a1.delete();
    a2.delete();
    a3.delete();
  }
  
  @Test
  public void testCreate()
  {
    Admin a = Admin.findByuserName("admin1");
    assertNotNull(a);
    assertEquals("admin1", a.userName);
    Admin b = Admin.findByuserName("admin2");
    assertNotNull(b);
    assertEquals("admin2", b.userName);
    Admin c = Admin.findByuserName("admin3");
    assertNotNull(c);
    assertEquals("admin3", c.userName);
  }
  
  @Test
  public void testNotThere()
  {
    Admin a = Admin.findByuserName("admin4");
    assertNull(a);
  }

}
