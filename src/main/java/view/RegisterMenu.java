package view;

import controller.LoginMenuController;
import controller.PasswordReset;
import controller.RegisterMenuController;
import view.enums.LoginMenuControllerOut;
import view.enums.ProfisterControllerOut;

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
                System.out.println(createUser(matcher.group("data")).getContent());
            } else if (command.matches("show current menu")) System.out.println("login menu");

            else if ((matcher = Commands.getMatcher(command, Commands.USER_LOGIN)) != null) {
                LoginMenuController loginMenuController = new LoginMenuController(matcher.group("data"));
                LoginMenuControllerOut result = loginMenuController.checkForLogin();
                System.out.println(result.getContent());

                if (result.equals(LoginMenuControllerOut.VALID))
                    loginMenuController.mainMenuRun();

                else if (result.equals(LoginMenuControllerOut.PASSWORD_WRONG)) {
                    int timeOut = 5;
                    while (timeOut <= 320) {
                        System.out.println("You can try again in " + timeOut + " seconds");
                        loginMenuController.giveAnotherShot(timeOut, ScanMatch.getScanner().nextLine());
                        if (loginMenuController.passwordMatch()) {
                            loginMenuController.mainMenuRun();
                            break;
                        }
                        timeOut *= 2;
                    }
                    if (timeOut > 320)
                        System.out.println("Login failed: Password is wrong!");
                } else System.out.println("Login failed");
            } else if ((matcher = Commands.getMatcher(command, Commands.PASSWORD_FORGOT)) != null) {
                PasswordReset passwordReset = new PasswordReset(matcher.group("username").trim());
            }
        }
    }

    private ProfisterControllerOut createUser(String data) throws IOException {
        Matcher temp;
        ProfisterControllerOut result = registerMenuController.validateBeforeCreation(data);
        if (!result.equals(ProfisterControllerOut.VALID)) return result;
        result = registerMenuController.usernameExist();
        if (!result.equals(ProfisterControllerOut.VALID)) {
            System.out.println(result.getContent());
            String respondToChangeUsername = ScanMatch.getScanner().nextLine().trim();
            if (!respondToChangeUsername.equals("y"))
                return ProfisterControllerOut.FAILED;
        }
        result = registerMenuController.handleRandomPassword();
        if (!result.equals(ProfisterControllerOut.VALID)) {
            System.out.println(result.getContent());
            int counter = 9;
            while (true) {
                if (!ScanMatch.getScanner().nextLine().trim().equals(registerMenuController.getPassword()))
                    System.out.println("Wrong. Tries left: " + counter + "\nPlease enter your password: " + registerMenuController.getPassword());
                else break;
                counter--;
                if (counter == 0) return ProfisterControllerOut.FAILED;
            }
        }
        System.out.println("Pick your security question: 1. What is my father’s name? 2. What" +
                "was my first pet’s name? 3. What is my mother’s last name?\n" +
                "Your response should in form:\n" +
                "question pick -q <question-number> -a <answer> -c <answerconfirm>");
        if ((temp = Commands.getMatcher(ScanMatch.getScanner().nextLine(), Commands.SECURITY_QUESTION_PICK)) == null)
            return ProfisterControllerOut.FAILED;
        if (!registerMenuController.getSecurityQuestion(temp).equals(ProfisterControllerOut.VALID))
            return ProfisterControllerOut.FAILED;
        return registerMenuController.createUser();
    }

}
