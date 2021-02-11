import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExComparison {

  public static String USAGE = "Incorrect arguments: to execute, you need to pass 2 lines for " +
      "comparison, the result will be a string - \"ok\" for a positive result and \"ko\" for " +
      "a negative one. Restart the program.";

  public static void main(String[] args) {
    if (args.length == 2) {
      System.out.println(RegExComparison.checkIt(args[0], args[1]));
    } else {
      System.out.println(USAGE);
    }
  }

  public static String checkIt(final String left, final String right) {
    Pattern pattern = Pattern.compile(right.replace("*", ".*"));
    Matcher matcher = pattern.matcher(left);
    StringBuilder sb = new StringBuilder();
    while (matcher.find()) {
      sb.append(matcher.group());
    }

    return sb.toString().equals(left) ? "OK" : "KO";
  }

}
