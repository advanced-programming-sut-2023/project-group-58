package view;

import com.google.gson.Gson;
import controller.LoginMenuController;
import controller.PasswordReset;
import controller.RegisterMenuController;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;

public class RegisterMenu {

    RegisterMenuController registerMenuController = new RegisterMenuController();

    public void run() throws IOException, NoSuchAlgorithmException {
        registerMenuController.setUpSloganDataBase();
        registerMenuController.setUpUserInfo();
        LoginMenuController.extractUserData();
        while (true) {
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.equals("exit")) break;
            else if ((matcher = Commands.getMatcher(command, Commands.CREATE_USER)) != null) {
                System.out.println(registerMenuController.createUser(matcher.group("data")));
            }
            else if (command.matches("show current menu")) System.out.println("login menu");
            else if ((matcher = Commands.getMatcher(command, Commands.USER_LOGIN))!=null) {
                LoginMenuController loginMenuController = new LoginMenuController(matcher.group("data"));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.PASSWORD_FORGOT))!=null){
                PasswordReset passwordReset = new PasswordReset(matcher.group("username").trim());
            }
            else
                System.out.println("invalid command");
        }
    }
}
