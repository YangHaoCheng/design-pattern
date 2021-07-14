package yhc.java.design.pattern.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FoodFactory {

    public static void main(String[] args) throws ParseException {

        String date = "Wed Apr 22 15:03:54 CST 2020";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        Date d = sdf.parse(date);
        sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(d));
    }
}
