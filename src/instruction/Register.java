package instruction;

public enum Register {
  MAR,
  MBR,
  AC1,
  AC2,
  ;

  public static String hexCode(String register) {
    return Integer.toHexString(Register.valueOf(register).ordinal());
  }

  public static String binaryCode(String register) {
    return Integer.toBinaryString(Register.valueOf(register).ordinal());
  }

  public static boolean contains(String register) {
    try {
      Register.valueOf(register);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
