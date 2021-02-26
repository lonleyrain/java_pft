package ru.stqa.pft.sandbox;

public class MySecondProgram {

  public static void main(String[] args) {

    Point p1 = new Point(1.0,1.0);
    Point p2 = new Point(4.0,5.0);

    System.out.println(distance(p1, p2));
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }
}
