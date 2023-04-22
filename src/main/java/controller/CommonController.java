package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonController {
    public static String dataExtractor(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) return "";
        return matcher.group("wantedPart");
    }
}
