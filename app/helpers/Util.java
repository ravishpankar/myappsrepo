package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 5/19/2016.
 */
public class Util {
    private static String sDF = "EEE, d MMM yyyy HH:mm:ss:SSS zzzz";
    private static String sRDF = "MMddyyyyHHmmssSSS";

    public static Date getDate (String sDate) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(sDF);
        return formatter.parse(sDate);
    }
    public static String getDateString (Date date) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(sDF);
        return formatter.format(date);
    }

    public static String getNonReadableDateString (Date date) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(sRDF);
        return formatter.format(date);
    }

    public static Date getDateFromNonReadableString (String sDate) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(sRDF);
        return formatter.parse(sDate);
    }

    public static Date getFirstDateAD () throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("mm/DD/yyyy");
        return formatter.parse("01/01/0001");
    }

    public synchronized static Date getTimeStamp()  throws InterruptedException {
        Date ts = new Date();
        Thread.currentThread().sleep(1);
        return ts;
    }
}
