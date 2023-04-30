package util;

import instruction.Mode;
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

    Mode operand1Mode = getMode(statement.getOperand1());
    Mode operand2Mode = getMode(statement.getOperand2());

    String operand1 = resolveOperand(statement.getOperand1(), symbolTable, operand1Mode, binaryOutput);
    String operand2 = resolveOperand(statement.getOperand2(), symbolTable, operand2Mode, binaryOutput);
    String opcode = resolveOperator(statement.getOperator(), binaryOutput);
    String mode = resolveMode(operand2Mode, binaryOutput);

    sb.append(mode).append(" ").append(opcode);
    if (operand1 != null) {
      sb.append(" ").append(operand1);
    }
    if (operand2 != null) {
      sb.append(" ").append(operand2);
    }

    return sb.toString();
  }

  private Mode getMode(String operand) {
    if (operand == null) {
      return Mode.DIRECT;
    }
    if (Register.contains(operand)) {
      return Mode.INDIRECT;
    }
    return Mode.DIRECT;
  }

  private String resolveMode(Mode mode, boolean binaryOutput) {
    if (binaryOutput) {
      return Mode.binaryCode(mode.toString());
    }
    return Mode.hexCode(mode.toString());
  }

  private String resolveOperator(String operator, boolean binaryOutput) {
    if (binaryOutput) {
      return Operator.binaryCode(operator);
    }
    return Operator.hexCode(operator);
  }

  private String resolveOperand(String operand, SymbolTable symbolTable, Mode mode, boolean binaryOutput) {

    if (operand == null) {
      return null;
    }

    if (mode == Mode.INDIRECT) {
      if (binaryOutput) {
        return Register.binaryCode(operand);
      }
      return Register.hexCode(operand);
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
