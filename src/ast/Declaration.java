package ast;

public class Declaration {
  private String variableName;
  private int size;

  Declaration(String variableName, int size) {
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