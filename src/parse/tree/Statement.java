package parse.tree;

import util.Lex;
public class Statement implements Node {

  private Lex lex;

  public Statement(Lex lex) {
    this.lex = lex;
  }

  private String operator;
  private String operand;

  @Override
  public String parse() {
    String[] tokens = lex.getTokens();

    operator = tokens[0];
    operand = tokens.length > 1 ? tokens[1] : null;

    return operator;
  }

  @Override
  public void print() {
    System.out.println(operator + " " + operand);
  }

  public String getOperator() {
    return operator;
  }

  public String getOperand() {
    return operand;
  }
}