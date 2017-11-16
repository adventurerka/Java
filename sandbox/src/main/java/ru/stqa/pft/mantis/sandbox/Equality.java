package ru.stqa.pft.mantis.sandbox;

public class Equality {
  public static void main (String args []){
    String s1 = "firefox";
    String s2 = "fire"+"fox";// другое место в памяти с тем же текстом//new String(s1)

    System.out.println(s1==s2);//сравниваются ссылки
    System.out.println(s1.equals(s2));//сравнивается текст
  }
}
