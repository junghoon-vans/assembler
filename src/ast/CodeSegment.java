package ast;

import component.Lex;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Spring;
import symbol.SymbolEntity;
import symbol.SymbolTable;

public class CodeSegment implements Node {

  private Lex lex;
  private List<Statement> statements;
  private SymbolTable symbolTable;

  public CodeSegment(Lex lex, SymbolTable symbolTable) {
    this.lex = lex;
    this.symbolTable = symbolTable;
    this.statements = new ArrayList<>();
  }

  @Override
  public String parse() {
    Statement statement = new Statement(lex);
    String token = statement.parse();

    while (!token.equals(".end")) {
      if (statement.getOperator().contains(":")) {
        SymbolEntity symbolEntity = new SymbolEntity(
            statement.getOperator().replace(":", ""),
            this.statements.size() + 1
        );
        this.symbolTable.add(symbolEntity);
      } else {
        this.statements.add(statement);
      }

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
