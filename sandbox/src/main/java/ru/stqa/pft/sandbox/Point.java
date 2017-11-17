package ru.stqa.pft.sandbox;

public class Point {

  public double p1;
  public double p2;

  public Point(double p1, double p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  public double distance(Point other) {
    double dp1 = other.p1 - this.p1;
    double dp2 = other.p2 - this.p2;
    double distance = Math.sqrt(dp1 * dp1 + dp2 * dp2);
    return distance;
  }

  @Override
  public String toString() {
    return "Point{" +
            "p1=" + p1 +
            ", p2=" + p2 +
            '}';
  }
}

