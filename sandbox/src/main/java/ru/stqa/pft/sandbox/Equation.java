package ru.stqa.pft.sandbox;

public class Equation {

  private double a;
  private double b;
  private double c;

  private int rootNumber; // количество корней уравнения

  public Equation (double a, double b, double c){
    this.a = a;
    this.b = b;
    this.c = c;

    double d = b*b - 4*a*c;

    if (a == 0) {
      if (b == 0) {
        if (c ==0) {
          rootNumber = -1;
        } else {
          rootNumber = 0;
        }
      } else {
        rootNumber = 1;
      }

    } else {
      if (d > 0) {
        rootNumber = 2;
      } else  if (d == 0) {
        rootNumber = 1;
      } else {
        rootNumber = 0;
      }

    }
  }

  public int rootNumber(){
    return rootNumber;
  }
}
