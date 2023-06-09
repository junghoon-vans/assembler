package parse;

import java.util.List;
import parse.tree.DataSegment;
import parse.tree.Statement;
import util.Lex;
import parse.tree.CodeSegment;
import parse.tree.Node;
import parse.symbol.SymbolTable;

public class Program implements Node {

  private Lex lex;
  private DataSegment dataSegment;
  private CodeSegment codeSegment;

  public Program(Lex lex, SymbolTable symbolTable, List<Statement> statements) {
    this.lex = lex;
    this.dataSegment = new DataSegment(lex, symbolTable);
    this.codeSegment = new CodeSegment(lex, symbolTable, statements);
  }

  @Override
  public String parse() {
    String token = lex.getToken();

    if (token.equals(".data")) {
      token = this.dataSegment.parse();
    }

    if (token.equals(".code")) {
      token = this.codeSegment.parse();
    }

    return token;
  }

  @Override
  public void print() {
    System.out.println("[Parse Tree]");
    this.codeSegment.print();
  }
}
