package parse.tree;

import util.Lex;
import java.util.Arrays;
public class Statement implements Node {

  private Lex lex;

  public Statement(Lex lex) {
    this.lex = lex;
  }

  private String operator;
  private String[] operand;

  @Override
  public String parse() {
    String[] tokens = lex.getTokens();

    operator = tokens[0];
    operand = Arrays.copyOfRange(tokens, 1, tokens.length);

    return operator;
  }

  @Override
  public void print() {
    System.out.println(operator + " " + String.join(" ", operand));
  }

  public String getOperator() {
    return operator;
  }

  public String getOperand1() {
    if (operand.length >= 1) {
      return operand[0];
    } else {
      return null;
    }
  }

  public String getOperand2() {
    if (operand.length >= 2) {
      return operand[1];
    } else {
      return null;
    }
  }
}