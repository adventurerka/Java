package ru.stqa.pft.mantis.sandbox;

public class Result {
  public static void main(String[] args) {

    Point p1 = new Point(0.0, 0.0);
    Point p2 = new Point(0.0, 4.0);

    System.out.println(p1.toString());
    System.out.println(p2.toString());
    System.out.println("Расстояние между точками равно: "+p1.distance(p2));
  }
}