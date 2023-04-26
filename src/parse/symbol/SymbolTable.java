package parse.symbol;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
  private List<SymbolEntity> symbolEntities;

  public SymbolTable() {
    this.symbolEntities = new ArrayList<SymbolEntity>();
  }

  public void add(SymbolEntity symbolEntity) {
    this.symbolEntities.add(symbolEntity);
  }

  public void print() {
    System.out.println("[Symbol Table]");
    for (SymbolEntity symbolEntity : symbolEntities) {
      System.out.println(symbolEntity.getVariableName() + " " + symbolEntity.getValue());
    }
  }
}
