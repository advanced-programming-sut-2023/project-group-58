package view;

import model.User;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;

public class MainMenu {
    private User user;
    public MainMenu (User user){
        this.user = user;
    }
    public void run() throws NoSuchAlgorithmException {
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.equals("user logout")) {
                System.out.println("You logged out successfully");
                return;
            }
            else if (command.matches("show current menu")) System.out.println("main menu");
            else if (command.equals("profile menu")) {
                System.out.println("You are in the profile menu");
                ProfileMenu profileMenu = new ProfileMenu(user);
                profileMenu.run();
            }
            else System.out.println("invalid command");
        }
    }
}
