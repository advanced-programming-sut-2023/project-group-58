package controller;

import view.enums.ProfisterControllerOut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonController {
    public static String dataExtractor(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) return "";
        return matcher.group("wantedPart");
    }
    public static ProfisterControllerOut checkPasswordFormat(String password){
        if (password.length() < 6)
            return ProfisterControllerOut.SHORT_PASSWORD;
        if (!password.matches(".*[A-Z].*"))
            return ProfisterControllerOut.NOT_CAPITAL_PASSWORD;
        if (!password.matches(".*[a-z].*"))
            return ProfisterControllerOut.NOT_SMALL_PASSWORD;
        if (!password.matches(".*[0-9].*"))
            return ProfisterControllerOut.NOT_NUMBERS_PASSWORD;
        if (!password.matches(".*[^a-zA-Z0-9].*"))
            return ProfisterControllerOut.NOT_SYMBOLS_PASSWORD;
        return ProfisterControllerOut.VALID;
    }
}
