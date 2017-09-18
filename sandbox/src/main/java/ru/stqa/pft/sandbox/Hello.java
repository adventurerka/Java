package ru.stqa.pft.sandbox;

public class Hello {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("people");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной "+s.l+" = "+s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами "+r.a+" и "+r.b+" = "+r.area());

    Point d = new Point (2,0,4,6);
    System.out.println("Расстояние от точки с координатами "+d.p1+" и "+d.p2+" до точки с координатамии "+d.p3+" и "+d.p4+ " = "+d.distance());
  }

  public static void hello(String somebody) {

    System.out.println("Hello "+ somebody);
  }
}