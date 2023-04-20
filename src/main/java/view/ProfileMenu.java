package view;

import controller.ChangeProfileController;
import model.User;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;

public class ProfileMenu {
    private User user;

    public ProfileMenu(User user) {
        this.user = user;
    }
    public void run() throws NoSuchAlgorithmException {
        ChangeProfileController changer;
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.equals("back")){
                System.out.println("Your are in the main menu");
                return;
            }
            else if ((matcher = Commands.getMatcher(command, Commands.CHANGE_USERNAME))!=null) {
                changer = new ChangeProfileController(user, matcher.group("username"));
                changer.changeUserName();
            }
            else if ((matcher = Commands.getMatcher(command, Commands.CHANGE_NICKNAME))!=null ){
                changer = new ChangeProfileController(user, matcher.group("nickname"));
                changer.changeNickname();
            }
            else if ((matcher = Commands.getMatcher(command, Commands.CHANGE_PASSWORD))!= null){
                changer = new ChangeProfileController(user, matcher.group("data"));
                changer.changePassword();
            }
            else System.out.println("invalid command");
        }
    }
}
