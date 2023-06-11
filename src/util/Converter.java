package util;

public class Converter {

  public static String decimalToBinary(String Decimal, int length) {
    int decimal = Integer.parseInt(Decimal);
    StringBuilder binary = new StringBuilder();
    for (int i = length-1; i >= 0; i--) {
      int bit = (decimal >> i) & 1;
      binary.append(bit);
    }
    return binary.toString();
  }

  public static String binaryToHex(String binary) {
    StringBuilder hex = new StringBuilder();
    for (int i = 0; i < binary.length(); i += 4) {
      String binaryString = binary.substring(i, i + 4);
      int decimal = Integer.parseInt(binaryString, 2);
      hex.append(Integer.toHexString(decimal));
    }
    return hex.toString();
  }
}
