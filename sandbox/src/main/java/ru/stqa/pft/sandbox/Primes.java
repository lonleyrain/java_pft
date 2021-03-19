package ru.stqa.pft.sandbox;


public class Primes {

  public static boolean isPrime(int n) {
// i ++ означает увеличение на единицу и называется инкремент
    for (int i = 2; i < n; i ++) {
// процентом обозначается остаток от деления, т.о. проверяем, чему равен остаток от деления числа n на  счетчик i
      if (n % i == 0) {
        return false;
      }
    }
    return true;

  }


  public static boolean isPrimeFast(int n) {

    int m = (int) Math.sqrt(n); // преобразуем double в int
// i ++ означает увеличение на единицу и называется инкремент
    for (int i = 2; i < m; i ++) {
// процентом обозначается остаток от деления, т.о. проверяем, чему равен остаток от деления числа n на  счетчик i
      if (n % i == 0) {
        return false;
      }
    }
    return true;

  }

  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (i < n && n % i !=0 ) {
      i++;
    }

    return i == n;

  }


  public static boolean isPrime(long n) {
// i ++ означает увеличение на единицу и называется инкремент
    for (long i = 2; i < n; i ++) {
// процентом обозначается остаток от деления, т.о. проверяем, чему равен остаток от деления числа n на  счетчик i
      if (n % i == 0) {
        return false;
      }
    }
    return true;

  }



}
