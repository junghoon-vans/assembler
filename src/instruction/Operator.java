package instruction;

public enum Operator {
  HALT,
  LDA,
  STA,
  ADD,
  AND,
  JMP,
  BZ,
  NOT,
  MOV,
  SUB,
  GTJ,
  ;

  public static String hexCode(String operator) {
    return String.format("%02X", Operator.valueOf(operator).ordinal());
  }

  public static String binaryCode(String operator) {
    return String.format("%04d", Operator.valueOf(operator).ordinal());
  }
}

