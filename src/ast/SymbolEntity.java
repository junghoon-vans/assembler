package ast;

public class SymbolEntity {
  private String variableName;
  private int size;

  SymbolEntity(String variableName, int size) {
    this.variableName = variableName;
    this.size = size;
  }

  public String getVariableName() {
    return this.variableName;
  }

  public int getSize() {
    return this.size;
  }
}