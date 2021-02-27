package ru.stqa.pft.sandbox;

public class MySecondProgram {

  public static void main(String[] args) {

    Point p1 = new Point(1.0,1.0);
    Point p2 = new Point(4.0,5.0);

    System.out.println( "Расстояние между точками = " + p1.distance(p2) );



  }
}
