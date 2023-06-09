import java.util.Scanner;

public class Program {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int count = scanner.nextInt();

    for (int i = 0; i < count; i++) {
      Student student = new Student();
      student.average();
    }
  }
}

class Student {
  private static int kor = 60;
  private static int eng = 70;

  private int sum() {
    return this.kor + this.eng;
  }

  public void average() {
    int result = sum() / 2;
    System.out.println(result);
  }
}