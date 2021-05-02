package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {



  @Test

  public void testDistanceSameCoordinates(){

    Point p1 = new Point(1.0,1.0);
    Point p2 = new Point(1.0,1.0);

    double d = p1.distance(p2);
    System.out.println(d);

    Assert.assertEquals(d,0.0);

  }



  @Test

  public void testDistanceSameX(){

    Point p1 = new Point(1.0,4.0);
    Point p2 = new Point(1.0,5.0);

    double y = p1.y - p2.y;
    double moduleY = Math.abs(y);

    System.out.println(moduleY);

    double d = p1.distance(p2);
    System.out.println(d);

    Assert.assertEquals(moduleY,d);


  }



  @Test

  public void testDistanceSameY(){

    Point p1 = new Point(1.0,0.0);
    Point p2 = new Point(2.0,0.0);

    double x = p1.x - p2.x;
    double moudleX = Math.abs(x);

    System.out.println(moudleX);

    double d = p1.distance(p2);
    System.out.println(d);

    Assert.assertEquals(moudleX,d);


  }


}
