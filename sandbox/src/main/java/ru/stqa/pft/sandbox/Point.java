package ru.stqa.pft.sandbox;

public class Point {

  public double x; // координата х для точки
  public double y; // координата y для точки


  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }


  /*когда мы превращаем функцию в метод какого-то объекта -- один из параметров функции исчезнет, это будет объект, в котором вызывается метод и внутри метода он будет доступен под специальным именем this.
  но другие параметры останутся. поэтому если у нас есть функция с двумя параметрами (две точки), то из неё получится метод с одним параметром (одна точка станет объектом, в котором метод вызывается,
  а вторая точка будет передаваться как параметр)
  было
  double d = distance(p1, p2);
  стало
  double p1.distance(p2);

  соответственно декларация функции
public static double distance(Point point1, Point point2) {
и тут вычисляется расстояние между point1 и point2
}
превращается в метод
public double distance(Point that) {
и тут вычисляется расстояние между this (специальная переменная, означает объект, в котором вызван метод) и that (а это просто название параметра)*/

  public double distance(Point that){
    return Math.sqrt((that.x - this.x) * (that.x - this.x) + (that.y - this.y) * (that.y - this.y));
  }

}
