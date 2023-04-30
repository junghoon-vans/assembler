package instruction;

public enum Register {
  MAR,
  MBR,
  AC1,
  AC2,
  ;

  public static String hexCode(String register) {
    return String.format("%02X", Register.valueOf(register).ordinal());
  }

  public static String binaryCode(String register) {
    return String.format("%04d", Register.valueOf(register).ordinal());
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
