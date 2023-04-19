package ast;

import component.Lex;
import java.util.ArrayList;
import java.util.List;

public class Header implements Node {

  private Lex lex;
  private List<Declaration> declarations;

  public Header(Lex lex) {
    this.lex = lex;
    declarations = new ArrayList<>();
  }

  @Override
  public String parse() {
    String token = lex.getToken();

    while (!token.equals(".code")) {
      Declaration declaration = new Declaration(token, Integer.parseInt(lex.getToken()));
      this.declarations.add(declaration);
      token = lex.getToken();
    }

    return token;
  }

  @Override
  public void print() {
    for (Declaration declaration : declarations) {
      System.out.println(declaration.getVariableName() + " " + declaration.getSize());
    }
  }
}
