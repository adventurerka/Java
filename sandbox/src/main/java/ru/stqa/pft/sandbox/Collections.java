package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String args[]) {
    String[] langs = new String[4];
    langs[0] = "Java";
    langs[1] = "C#";
    langs[2] = "Python";
    langs[3] = "PHP";
    for (String l : langs) {
      System.out.println(" " + l);
    }
    for (int i = 0; i < langs.length; i++) {
      System.out.println(" " + langs[i]);
    }

    List languag = new ArrayList<>();
    for (Object l : languag) {
      System.out.println(" " + l);
    }

    List<String> languages = Arrays.asList("Java", "C#");
    for (int i = 0; i < languages.size(); i++) {
      System.out.println(" " + languages.get(i));
    }
    for (String l : languages) {
      System.out.println(" " + l);
    }
  }
}
