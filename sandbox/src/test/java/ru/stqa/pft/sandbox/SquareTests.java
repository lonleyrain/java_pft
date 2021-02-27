package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

  @Test

  /*у тестового метода никакого возвращаемого результата нет и параметров тоже нет*/

  public void testArea(){

    Square s = new Square(5); //создаем новый объект класса Square (квадрат) со стороной 5

    /*вычисляется площадь квадрата с помощью метода area, который принадлежит классу Square
    и с помоью ассерт проверяется (сравнивается) вычисленное значение на равенство 25*/

    Assert.assertEquals(s.area(), 25.0);


  }
}
