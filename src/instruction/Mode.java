package instruction;

public enum Mode {
  DIRECT,
  INDIRECT,
  ;

  public static String hexCode(String mode) {
    return Integer.toHexString(Mode.valueOf(mode).ordinal());
  }
}
