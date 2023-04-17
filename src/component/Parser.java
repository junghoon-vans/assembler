package component;

import ast.Program;

public class Parser {
  private Lex lex;
  private Program program;

  public Parser(Lex lex) {
    this.lex = lex;
    program = new Program(lex);
  }

  public void parse() {
    program.parse();
  }

  public void print() {
    program.print();
  }
}
