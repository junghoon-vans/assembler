package instruction;

public enum Mode {
  DIRECT,
  INDIRECT,
  ;

  public static String hexCode(String mode) {
    return Integer.toHexString(Mode.valueOf(mode).ordinal());
  }

  public static String binaryCode(String mode) {
    return Integer.toBinaryString(Mode.valueOf(mode).ordinal());
  }
}
