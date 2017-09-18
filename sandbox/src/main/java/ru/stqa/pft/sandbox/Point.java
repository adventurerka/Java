package ru.stqa.pft.sandbox;

public class Point {

  public double p1;
  public double p2;
  public double p3;
  public double p4;

  public Point (double p1, double p2, double p3, double p4){
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
    this.p4 = p4;
  }

  public double distance () {
    return Math.sqrt(Math.pow((this.p1-this.p3),2)+Math.pow((this.p2-this.p4),2));
  }
  }

