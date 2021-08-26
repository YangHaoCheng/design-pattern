package yhc.java.algorithm.base.mytry;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyTest {
    public static void main(String[] args) throws ParseException {
        int[] a = {1,2,3,4,5};
        int[] a1 = {0,0,0,0};
        String pattern = "YYYY-MM-dd'T'HH:mm:ssZZ";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateTimeFormatter parser2 = ISODateTimeFormat.dateTimeNoMillis();
//        String format = df.format("2018-07-18T19:28:13Z");
//
//        df.setTimeZone(TimeZone.getTimeZone("UAT"));
//        long parse = Date.parse("2018-07-18T19:28:13Z");
        System.out.println(parser2.parseDateTime("2018-07-18T19:28:13Z"));
    }
}
