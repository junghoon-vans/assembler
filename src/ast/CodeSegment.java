package ast;

import component.Lex;
import java.util.ArrayList;
import java.util.List;

public class CodeSegment implements Node {

  private Lex lex;
  private List<Statement> statements;

  public CodeSegment(Lex lex) {
    this.lex = lex;
    this.statements = new ArrayList<>();
  }

  @Override
  public String parse() {
    Statement statement = new Statement(lex);
    String token = statement.parse();

    while (!token.equals(".end")) {
      this.statements.add(statement);
      statement = new Statement(lex);
      token = statement.parse();
    }

    return token;
  }

  @Override
  public void print() {
    for (Statement statement : this.statements) {
      statement.print();
    }
  }
}
