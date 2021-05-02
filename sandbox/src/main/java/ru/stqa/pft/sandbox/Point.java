package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;


  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }




  public double distance(Point that){
    return Math.sqrt((that.x - this.x) * (that.x - this.x) + (that.y - this.y) * (that.y - this.y));
  }

}
