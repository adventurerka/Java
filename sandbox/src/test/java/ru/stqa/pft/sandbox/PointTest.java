package ru.stqa.pft.sandbox;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test (enabled = false)
  public void testArea() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,4);
    Assert.assertEquals( p1.distance (p2),4.0);//true
    Assert.assertEquals( p1.distance (p2),3.0);//false
  }
}
