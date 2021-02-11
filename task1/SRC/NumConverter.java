/**
 * Solution for task1
 *
 * @author Kondratev D.
 */


public class NumConverter {

  /**
   * To convert from the decimal system, you need to enter 2 lines in the console:
   * - the number to convert (consisting of digits);
   * - dictionary of total characters.
   * To convert from non-decimal notation, you need to enter 3 lines in the console:
   * - the number to convert (consisting of numbers and Latin characters);
   * - dictionary of source characters;
   * - dictionary of total characters.
   */
  public static void main(String[] args) {
    try {
      if (args.length == 2) {
        System.out.println(itoBase(Integer.parseInt(args[0]), args[1]));
      } else if (args.length == 3) {
        System.out.println(itoBase(args[0], args[1], args[2]));
      } else {
        System.out.println(USAGE);
      }
    } catch (Exception e){
      System.out.println(USAGE);
    }
  }

  public static final String USAGE =
      "To convert from the decimal system, you need to enter 2 lines in the console:\n" +
          "- the number to convert (consisting of digits);\n" +
          "- dictionary of total characters.\n" +
          "To convert from non-decimal notation, you need to enter 3 lines in the console:\n" +
          "- the number to convert (consisting of numbers and Latin characters);\n" +
          "- dictionary of source characters;\n" +
          "- dictionary of total characters.";

  /**
   * Accepts a number in the decimal system and returns the number in the number system passed
   * by the param to the method.
   *
   * @param nb   number in the decimal system
   * @param base number system of the result
   */
  public static String itoBase(int nb, String base) {
    char[] numSystemDigits = base.toCharArray();
    StringBuilder divisionResult = new StringBuilder();
    int tempNum = nb;
    while (tempNum > 0) {
      divisionResult.append(numSystemDigits[tempNum % numSystemDigits.length]);
      tempNum /= numSystemDigits.length;
    }
    return divisionResult.reverse().toString();
  }

  /**
   * Accepts a string with number in base number system and returns the number in the number
   * system passed by the param to the method.
   *
   * @param nb      line with number in base number system
   * @param baseSrs number system of the nb
   * @param baseDst number system of the result
   */
  public static String itoBase(String nb, String baseSrs, String baseDst) {
    return itoBase(Integer.parseInt(convertToDec(nb, baseSrs)), baseDst);
  }

  /**
   * Accepts a string with number in base number system and returns the number
   * in the decimal system.
   *
   * @param number      line with number in base number system
   * @param base        number system of the number
   */
  private static String convertToDec(String number, String base) {
    int result = 0;
    for (int i = 0; i < number.length(); i++) {
      result += base.indexOf(number.charAt(i)) * Math.pow(base.length(), number.length() - i - 1);
    }
    return String.valueOf(result);
  }

}