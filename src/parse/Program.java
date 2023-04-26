package parse;

import util.Lex;
import parse.tree.CodeSegment;
import parse.tree.Node;
import parse.symbol.SymbolTable;

public class Program implements Node {

  private Lex lex;
  private Header header;
  private CodeSegment codeSegment;

  public Program(Lex lex, SymbolTable symbolTable) {
    this.lex = lex;
    this.header = new Header(lex, symbolTable);
    this.codeSegment = new CodeSegment(lex, symbolTable);
  }

  @Override
  public String parse() {
    String token = lex.getToken();

    if (token.equals(".header")) {
      token = this.header.parse();
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
