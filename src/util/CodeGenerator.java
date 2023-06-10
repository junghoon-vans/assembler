package util;

import instruction.Operator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import parse.symbol.SymbolEntity;
import parse.symbol.SymbolTable;
import parse.tree.Statement;

public class CodeGenerator {
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

  public void generate(SymbolTable symbolTable, List<Statement> statements, boolean binaryOutput) {
    FileWriter fw;
    try {
      fw = new FileWriter(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    PrintWriter writer = new PrintWriter(fw);

    for (Statement statement : statements) {
      try {
        writer.println(resolve(statement, symbolTable, binaryOutput));
      } catch (IllegalArgumentException e) {
        // alert not defined symbol
        throw new RuntimeException(e);
      }
    }
    writer.close();
  }

  private String resolve(Statement statement, SymbolTable symbolTable, boolean binaryOutput) {
    StringBuilder sb = new StringBuilder();

    String operand = resolveOperand(statement.getOperand(), symbolTable, binaryOutput);
    String opcode = resolveOperator(statement.getOperator(), binaryOutput);

    sb.append(opcode);
    if (operand != null) {
      sb.append(" ").append(operand);
    }

    return sb.toString();
  }

  private String resolveOperator(String operator, boolean binaryOutput) {
    if (binaryOutput) {
      return Operator.binaryCode(operator);
    }
    return Operator.hexCode(operator);
  }

  private String resolveOperand(String operand, SymbolTable symbolTable, boolean binaryOutput) {

    if (operand == null) {
      return null;
    }

    if (operand.contains("@")) {
      operand = operand.substring(1);
    }

    if (symbolTable.contains(operand)) {
      SymbolEntity symbolEntity = symbolTable.get(operand);
      operand = String.valueOf(symbolEntity.getValue());
    }

    if (binaryOutput) {
      return String.format("%04d", Integer.parseInt(operand));
    }
    return String.format("%02X", Integer.parseInt(operand));
  }
}
