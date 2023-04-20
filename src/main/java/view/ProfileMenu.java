package view;

import model.User;

import java.util.regex.Matcher;

public class ProfileMenu {
    private User user;

    public ProfileMenu(User user) {
        this.user = user;
    }
    public void run(){
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.equals("back")){
                System.out.println("Your are in the main menu");
                return;
            }
            else System.out.println("invalid command");
        }
    }
}
