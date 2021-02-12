import java.io.IOException;
import java.text.ParseException;

public class Main {

  public static final String USAGE = "To start the program, you need to pass 3 arguments:\n" +
      "- the absolute path of the file;\n" +
      "- start date in the format \" yyyy-MM-dd'T'kk: mm:ss\";\n" +
      "- the end date of the work in the format \"yyyy-MM-dd't'kk: mm:ss\".";

  public static void main(String[] args) throws ParseException, IOException {
    if (args.length != 3) {
      System.out.println(USAGE);
    }
    Reader fastFoodAmongPrograms = new Reader(args[1], args[2], args[0]);
    fastFoodAmongPrograms.expectFile();
  }

}