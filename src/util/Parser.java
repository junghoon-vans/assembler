package util;

import parse.Program;
import parse.symbol.SymbolTable;

public class Parser {
  private Lex lex;
  private Program program;

  public Parser(Lex lex, SymbolTable symbolTable) {
    this.lex = lex;
    program = new Program(lex, symbolTable);
  }

  public void parse() {
    program.parse();
  }

  public void print() {
    program.print();
  }
}
