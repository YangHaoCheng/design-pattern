package yhc.java.design.pattern.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChineseFoodFactory implements Factory {

    @Override
    public String create() {
        return "ChineseFood";
    }

    @Override
    public String create(String ChineseCooker) {
        return "Chinese cuisine";
    }

    public String makeNoodles() {
        return "hot noodles with sesame paste";
    }
}
