package view;

import controller.ChangeProfileController;
import controller.ShowProfileController;
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
        ShowProfileController show;
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
            else if ((matcher = Commands.getMatcher(command, Commands.EMAIL_CHANGE))!=null) {
                changer = new ChangeProfileController(user, matcher.group("email"));
                changer.changeEmail();
            }
            else if ((matcher = Commands.getMatcher(command, Commands.CHANGE_SLOGAN))!=null){
                changer = new ChangeProfileController(user, matcher.group("slogan"));
                changer.changeSlogan();
            }
            else if (command.matches("\\s*Profile\\s+remove\\s+slogan\\s*")){
                changer =new ChangeProfileController(user);
                changer.removeSlogan();
            }
            else if (command.matches("\\s*profile\\s+display\\s+highscore\\s*")){
                show = new ShowProfileController(user);
                show.showScore();
            }
            else if (command.matches("\\s*profile\\s+display\\s+rank\\s*")){
                show = new ShowProfileController(user);
                show.showRank();
            }
            else if (command.matches("\\s*profile\\s+display\\s+slogan\\s*")){
                show = new ShowProfileController(user);
                show.showSlogan();
            }
            else if (command.matches("\\s*profile\\s+display\\s*")){
                show = new ShowProfileController(user);
                show.showDisplay();
            }
            else System.out.println("invalid command");
        }
    }
}
