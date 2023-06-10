package instruction;

import util.Converter;

public enum Operator {
  HALT,
  LOADA,
  LOADC,
  STORE,
  ADDA,
  ADDC,
  SUB,
  DIV,
  JUMP,
  BZ,
  PUSH,
  POP,
  OUT,
  ;

  public static String binaryCode(String operator) {
    int index = Operator.valueOf(operator.toUpperCase()).ordinal();
    return Converter.decimalToBinary(String.valueOf(index));
  }
}

