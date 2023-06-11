package parse.tree;

import parse.symbol.SymbolType;
import util.Lex;
import parse.symbol.SymbolEntity;
import parse.symbol.SymbolTable;

public class DataSegment {

  private Lex lex;
  private SymbolTable symbolTable;
  private int index;

  public DataSegment(Lex lex, SymbolTable symbolTable) {
    this.lex = lex;
    this.symbolTable = symbolTable;
    this.index = 0;
  }

  public String parse() {
    String token = lex.getToken();

    while (!token.equals(".code")) {
      SymbolEntity symbolEntity = new SymbolEntity(token, SymbolType.DATA, index);
      symbolTable.add(symbolEntity);
      token = lex.getToken();
      index+=4;
    }

    return token;
  }
}
