package util;

import java.util.List;
import parse.Program;
import parse.symbol.SymbolTable;
import parse.tree.Statement;

public class Parser {
  private Program program;

  public Parser(Lex lex, SymbolTable symbolTable, List<Statement> statements) {
    program = new Program(lex, symbolTable, statements);
  }

  public void parse() {
    program.parse();
  }

  public void print() {
    program.print();
  }
}
