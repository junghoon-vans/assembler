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

  public SymbolEntity get(String variableName, SymbolType type) {
    for (SymbolEntity symbolEntity : symbolEntities) {
      if (symbolEntity.getVariableName().equals(variableName) && symbolEntity.getType() == type) {
        return symbolEntity;
      }
    }
    return null;
  }

  public void print() {
    System.out.println("[Symbol Table]");
    for (SymbolEntity symbolEntity : symbolEntities) {
      System.out.println(symbolEntity);
    }
  }

  public boolean contains(String variableName, SymbolType type) {
    for (SymbolEntity symbolEntity : symbolEntities) {
      if (symbolEntity.getVariableName().equals(variableName) && symbolEntity.getType() == type) {
        return true;
      }
    }
    return false;
  }
}
