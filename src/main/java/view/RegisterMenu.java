package view;

import controller.LoginMenuController;
import controller.RegisterMenuController;

import java.io.*;
import java.util.regex.Matcher;

public class RegisterMenu {

    RegisterMenuController registerMenuController = new RegisterMenuController();

    public void run() throws IOException {
        registerMenuController.setUpSloganDataBase();
        registerMenuController.createFileWhenNecessary(System.getProperty("user.dir") + "/DataBase/userInfo.json");
        while (true) {
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.equals("Exit")) break;
            else if ((matcher = Commands.getMatcher(command, Commands.CREATE_USER)) != null) {
                System.out.println(registerMenuController.createUser(matcher.group("data")));
                continue;
            }
            else if ((matcher = Commands.getMatcher(command, Commands.USER_LOGIN))!=null) {
                LoginMenuController loginMenuController = new LoginMenuController(matcher.group("data"));
            }
            else
                System.out.println("invalid command");
        }
    }
}
