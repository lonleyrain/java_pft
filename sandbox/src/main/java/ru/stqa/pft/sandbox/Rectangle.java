package ru.stqa.pft.sandbox;

public class Rectangle {

  public double a; // длина стороны прямоугольника
  public double b; // ширина стороны прямоугольника

  public Rectangle(double a, double b){
    this.a = a;
    this.b = b;
  }

  public double area(){
    return this.a * this.b;
  }

}
