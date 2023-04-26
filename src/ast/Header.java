package ast;

import component.Lex;
import symbol.SymbolEntity;
import symbol.SymbolTable;

public class Header implements Node {

  private Lex lex;
  private SymbolTable symbolTable;

  public Header(Lex lex, SymbolTable symbolTable) {
    this.lex = lex;
    this.symbolTable = symbolTable;
  }

  @Override
  public String parse() {
    String token = lex.getToken();

    while (!token.equals(".code")) {
      SymbolEntity symbolEntity = new SymbolEntity(token, Integer.parseInt(lex.getToken()));
      symbolTable.add(symbolEntity);
      token = lex.getToken();
    }

    return token;
  }

  @Override
  public void print() {
    symbolTable.print();
  }
}
