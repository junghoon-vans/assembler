package instruction;

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

  public static String hexCode(String operator) {
    return String.format("%02X", Operator.valueOf(operator.toUpperCase()).ordinal());
  }

  public static String binaryCode(String operator) {
    return String.format("%04d", Operator.valueOf(operator.toUpperCase()).ordinal());
  }
}

