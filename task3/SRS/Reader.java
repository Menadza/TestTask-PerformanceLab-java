import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reader {

  public static SimpleDateFormat logFormat = new SimpleDateFormat("yyyy-MM-dd'Т'kk:mm:ss.SSS'Z'");
  public static SimpleDateFormat argFormat = new SimpleDateFormat("yyyy-MM-dd'Т'kk:mm:ss");

  public static void main(String[] args) throws ParseException {
    Date dateFrom = argFormat.parse(args[1]);
    Date dateTo = argFormat.parse(args[2]);

    Date workDate;
    String line;

    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      reader.readLine();
      WaterBoy vodonos = new WaterBoy(Integer.parseInt(reader.readLine()),
          Integer.parseInt(reader.readLine()));
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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
