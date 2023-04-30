package instruction;

public enum Mode {
  DIRECT,
  INDIRECT,
  ;

  public static String hexCode(String mode) {
    return String.format("%02X", Mode.valueOf(mode).ordinal());
  }

  public static String binaryCode(String mode) {
    return String.format("%04d", Mode.valueOf(mode).ordinal());
  }
}
