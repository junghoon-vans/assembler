package ast;

import component.Lex;
import java.util.ArrayList;
import java.util.List;

public class Header implements Node {

  private Lex lex;
  private List<SymbolEntity> symbolEntities;

  public Header(Lex lex) {
    this.lex = lex;
    symbolEntities = new ArrayList<>();
  }

  @Override
  public String parse() {
    String token = lex.getToken();

    while (!token.equals(".code")) {
      SymbolEntity symbolEntity = new SymbolEntity(token, Integer.parseInt(lex.getToken()));
      this.symbolEntities.add(symbolEntity);
      token = lex.getToken();
    }

    return token;
  }

  @Override
  public void print() {
    for (SymbolEntity symbolEntity : symbolEntities) {
      System.out.println(symbolEntity.getVariableName() + " " + symbolEntity.getSize());
    }
  }
}
