package component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lex {
  private Scanner scanner;

  public Lex(String filepath) {
    File file = new File(filepath);
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public String getToken() {
    try {
      return scanner.next();
    } catch (Exception e) {
      return null;
    }
  }

  public void finalize() {
    scanner.close();
  }
}
