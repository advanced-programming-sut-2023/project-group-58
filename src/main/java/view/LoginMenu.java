package view;

import controller.LoginMenuController;
import model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu {

    LoginMenuController logController = new LoginMenuController();
    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        logController.setUpSloganDataBase();
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if (command.equals("Exit")) break;
            else if ((matcher = Commands.getMatcher(command, Commands.CREATE_USER)) != null) {
                logController.createUser(matcher.group("data"),scanner);
            }

        }
    }
}
