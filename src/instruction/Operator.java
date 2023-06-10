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

  public static String binaryCode(String operator) {
    int index = Operator.valueOf(operator.toUpperCase()).ordinal();
    StringBuilder binaryString = new StringBuilder();

    for (int i = 3; i >= 0; i--) {
      int bit = (index >> i) & 1;
      binaryString.append(bit);
    }

    return binaryString.toString();
  }
}

