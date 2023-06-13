package util;

import instruction.Operator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parse.symbol.SymbolEntity;
import parse.symbol.SymbolTable;
import parse.symbol.SymbolType;
import parse.tree.Statement;

public class CodeGenerator {

  private static int CODE_SEGMENT = 0;
  private static int DATA_SEGMENT = 1024;
  private static int STACK_SEGMENT = 3072;

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
    String operand = resolveOperand(statement.getOperand(), symbolTable);
    String opcode = resolveOperator(statement.getOperator());

    String binary = opcode + operand;
    return binaryOutput ? binary : Converter.binaryToHex(binary);
  }

  private String resolveOperator(String operator) {
    return Operator.binaryCode(operator);
  }

  private String resolveOperand(String operand, SymbolTable symbolTable) {

    if (operand.equals("@keyboard")) {
      int operandValue = 2047;
      return Converter.decimalToBinary(String.valueOf(operandValue), 12);
    }

    // constant
    if (operand.contains("#")) {
      return Converter.decimalToBinary(operand.replace("#", ""), 12);
    }

    // code segment
    if (symbolTable.contains(operand, SymbolType.LABEL)) {
      SymbolEntity symbolEntity = symbolTable.get(operand, SymbolType.LABEL);
      return Converter.decimalToBinary(String.valueOf(CODE_SEGMENT + symbolEntity.getValue()), 12);
    }

    // data segment
    if (operand.contains("@")) {
      operand = operand.replace("@", "");
      SymbolEntity symbolEntity = symbolTable.get(operand, SymbolType.DATA);
      return Converter.decimalToBinary(String.valueOf(DATA_SEGMENT + symbolEntity.getValue()), 12);
    }

    // stack segment
    if (operand.equals("[sp]")) {
      return Converter.decimalToBinary(String.valueOf(STACK_SEGMENT), 12);
    }

    Pattern pattern = Pattern.compile("\\[sp-(\\d+)\\]");
    Matcher matcher = pattern.matcher(operand);
    if (matcher.find()) {
      int value = Integer.parseInt(matcher.group(1));
      return Converter.decimalToBinary(String.valueOf(STACK_SEGMENT+value), 12);
    }

    return Converter.decimalToBinary(operand, 12);
  }
}
