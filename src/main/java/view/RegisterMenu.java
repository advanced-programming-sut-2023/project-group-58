package view;

import com.google.gson.Gson;
import controller.RegisterMenuController;

import java.io.*;
import java.util.regex.Matcher;

public class RegisterMenu {

    RegisterMenuController registerMenuController = new RegisterMenuController();

    public void run() throws IOException {
        registerMenuController.setUpSloganDataBase();
        registerMenuController.setUpUserInfo();
        while (true) {
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.equals("exit")) break;
            else if ((matcher = Commands.getMatcher(command, Commands.CREATE_USER)) != null) {
                System.out.println(registerMenuController.createUser(matcher.group("data")));
            }

            else System.out.println("invalid command");
        }
    }
}
