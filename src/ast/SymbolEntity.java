package ast;

public class SymbolEntity {
  private String variableName;
  private int value;

  SymbolEntity(String variableName, int value) {
    this.variableName = variableName;
    this.value = value;
  }

  public String getVariableName() {
    return this.variableName;
  }

  public int getValue() {
    return this.value;
  }
}