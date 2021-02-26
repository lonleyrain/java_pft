package ru.stqa.pft.sandbox;

public class Square {

  public double l; //длина стороны квадрата

  // конструктор имеет такое же название как класс, и не имеет никакого возвращаемого значения. Перед именем конструктора никакой тип возвращаемого значения не указывается
  public Square(double l){
    this.l = l;
  }

  public  double area(){
    return this.l * this.l;
  }

}

