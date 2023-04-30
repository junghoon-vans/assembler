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
    return Integer.toHexString(Operator.valueOf(operator).ordinal());
  }

  public static String binaryCode(String operator) {
    return Integer.toBinaryString(Operator.valueOf(operator).ordinal());
  }
}

