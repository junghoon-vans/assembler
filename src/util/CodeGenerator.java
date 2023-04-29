package util;

import instruction.Operator;
import instruction.Register;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import parse.symbol.SymbolEntity;
import parse.symbol.SymbolTable;
import parse.tree.Statement;

public class CodeGenerator {

  private static final String HEX_PREFIX = "0x";
  private File file;

  public CodeGenerator(String filename) {
    this.file = new File(filename);
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void generate(SymbolTable symbolTable, List<Statement> statements) {
    FileWriter fw = null;
    try {
      fw = new FileWriter(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    PrintWriter writer = new PrintWriter(fw);

    for (Statement statement : statements) {
      writer.println(resolve(statement, symbolTable));
    }
    writer.close();
  }

  private String resolve(Statement statement, SymbolTable symbolTable) {
    StringBuilder sb = new StringBuilder();
    String opcode = resolveOperator(statement.getOperator());
    String operand1 = resolveOperand1(statement.getOperand1(), symbolTable);
    String operand2 = resolveOperand2(statement.getOperand2(), symbolTable);

    sb.append(opcode);
    if (operand1 != null) {
      sb.append(" ").append(operand1);
    }
    if (operand2 != null) {
      sb.append(" ").append(operand2);
    }

    return sb.toString();
  }

  private String resolveOperator(String operator) {
    return HEX_PREFIX + Operator.hexCode(operator);
  }

  private String resolveOperand1(String operand, SymbolTable symbolTable) {
    if (operand == null) {
      return null;
    }

    SymbolEntity symbolEntity = symbolTable.get(operand);
    if (symbolEntity != null) {
      return HEX_PREFIX + Integer.toHexString(symbolEntity.getValue());
    }
    return HEX_PREFIX + Register.hexCode(operand);
  }

  private String resolveOperand2(String operand, SymbolTable symbolTable) {
    if (operand == null) {
      return null;
    }

    if (operand.contains("@")) {
      return HEX_PREFIX + symbolTable.get(operand.substring(1)).getValue();
    }

    if (Register.contains(operand)) {
      return HEX_PREFIX + Register.hexCode(operand);
    }

    return HEX_PREFIX + Integer.toHexString(Integer.parseInt(operand));
  }
}
