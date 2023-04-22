package view;

import controller.gameMenuControllers.LobbyController;
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
            else if (command.matches("profile menu")) {
                System.out.println("You are in the profile menu");
                ProfileMenu profileMenu = new ProfileMenu(user);
                profileMenu.run();
            }
            else if ((command.matches("start game"))){
                System.out.println("You entered lobby!\nPlease enter 1-7 username to start game, then enter \"ok\""+
                        "\nYou can remove a username by entering \"remove <username>\"\nYou can return to main menu by entering return.");
                LobbyController lobbyController = new LobbyController(user);
                while (true) {
                    command = ScanMatch.getScanner().nextLine();
                    if (command.matches("return")) break;
                    else if (command.matches("ok")){
                        if (lobbyController.startGame()) {
                            System.out.println("game started");
                            GameMenu gameMenu = new GameMenu();
                            gameMenu.run();
                            break;
                        }
                        else {
                            System.out.println("count of player must be 2-8");
                        }
                    }
                    else if ((matcher = Commands.getMatcher(command, Commands.REMOVE_USER))!=null){
                        lobbyController.removeUser(matcher.group("username"));
                    }
                    else if (command.matches("show players")){
                        lobbyController.showUsers();
                    }
                    else {
                        lobbyController.getUser(command);
                    }
                }
            }
            else System.out.println("invalid command");
        }
    }
}
