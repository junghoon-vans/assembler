package parse.symbol;

public class SymbolEntity {
  private String variableName;
  private SymbolType type;
  private int value;

  public SymbolEntity(String variableName, SymbolType type, int value) {
    this.variableName = variableName;
    this.type = type;
    this.value = value;
  }

  public String getVariableName() {
    return this.variableName;
  }

  public int getValue() {
    return this.value;
  }

  public SymbolType getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return this.variableName + " " + this.type + " " + this.value;
  }
}