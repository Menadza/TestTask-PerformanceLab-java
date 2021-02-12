import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class inspects the .log file, analyzes the strings, and outputs statistical information.
 *
 * @author Denis Kondratev
 */
public class Reader {
  private final Date dateFrom;
  private final Date dateTo;
  private final String filePath;
  SimpleDateFormat logFormat = new SimpleDateFormat("yyyy-MM-dd'Т'kk:mm:ss.SSS'Z'");
  SimpleDateFormat argFormat = new SimpleDateFormat("yyyy-MM-dd'Т'kk:mm:ss");
  private WaterBoy vodonos;

  /**
   * Constructs Reader with start-end dates and path to file to read.
   *
   * @param dateFrom start work date
   * @param dateTo   end work date
   * @param filePath absolute path to the file to read
   * @throws ParseException if <code>dateTo</code> or <code>dateFrom</code> is wrong
   */
  public Reader(String dateFrom, String dateTo, String filePath) throws ParseException {
    this.dateFrom = argFormat.parse(dateFrom);
    this.dateTo = argFormat.parse(dateTo);
    this.filePath = filePath;
  }

  /**
   * Reads the. log file, based on the inspection of the lines, issues a report - to the command
   * line and to the created result.csv file.
   *
   * @throws ParseException if <code>dateTo</code> or <code>dateFrom</code> is wrong
   * @throws if             the transferred file is not found by the BufferedReader
   */
  public void expectFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      reader.readLine();
      WaterBoy vodonos = new WaterBoy(Integer.parseInt(reader.readLine()),
          Integer.parseInt(reader.readLine()));
      String line;
      Date workDate;
      while ((line = reader.readLine()) != null) {
        workDate = logFormat.parse(line.substring(0, line.indexOf('Z') + 1));
        if (workDate.after(dateFrom) && workDate.before(dateTo)) {
          boolean isScoop = line.contains("scoop");
          if (isScoop) {
            vodonos.scoop(Integer.parseInt(line.substring(line.indexOf("scoop") + 6, line.lastIndexOf('l') - 1)));
          } else {
            vodonos.topUp(Integer.parseInt(line.substring(line.indexOf("top up") + 7, line.lastIndexOf('l') - 1)));
          }
        }
      }
      vodonos.pringReport();
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }


}
