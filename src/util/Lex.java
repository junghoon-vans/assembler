package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Lex {
  private Scanner scanner;
  private List<String> tokens;

  public Lex(String filepath) {
    File file = new File(filepath);
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    tokens = new ArrayList<>();
  }

  public String getToken() {
    try {
      String token = scanner.next().trim();
      tokens.add(token);
      return token;
    } catch (Exception e) {
      return null;
    }
  }

  public String[] getTokens() {
    try {
      String line = scanner.nextLine().trim();

      while (line.isEmpty()) {
        line = scanner.nextLine().trim();
      }

      String[] tokens = line.split(" ");
      for (String token : tokens) {
        this.tokens.add(token);
      }

      return tokens;
    } catch (Exception e) {
      return null;
    }
  }

  public void printTokens() {
    System.out.println("[Tokens]");
    System.out.println(tokens);
  }

  public void finalize() {
    scanner.close();
  }
}
