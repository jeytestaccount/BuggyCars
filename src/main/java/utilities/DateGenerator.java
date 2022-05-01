package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * This class is used to store all Date related methods
 *
 * author Jey
 */
public class DateGenerator {

    /**
     * This method return current date time in "ddMMyyHHmmssSSS" format
     * @return currentDataTime
     */
    public static String getCurrentDataTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHHmmssSSS");
        Date date = new Date();
        String currentDateTime = formatter.format(date).toString();
        return currentDateTime;
    }
}
