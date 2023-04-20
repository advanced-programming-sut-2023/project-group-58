package view;

import model.User;

import java.util.regex.Matcher;

public class MainMenu {
    private User user;
    public MainMenu (User user){
        this.user = user;
    }
    public void run(){
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.equals("user logout")) {
                System.out.println("You logged out successfully");
                return;
            }
            else if (command.equals("profile menu")) {
                ProfileMenu profileMenu = new ProfileMenu(user);
            }
            else System.out.println("invalid command");
        }
    }
}
