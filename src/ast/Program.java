package ast;

import component.Lex;

public class Program implements Node {

  private Lex lex;
  private Header header;
  private CodeSegment codeSegment;

  public Program(Lex lex) {
    this.lex = lex;
    this.header = new Header(lex);
    this.codeSegment = new CodeSegment(lex);
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
    this.header.print();
    this.codeSegment.print();
  }
}