package view;

import controller.gameMenuControllers.ShopMenuController;
import model.User;

import java.util.regex.Matcher;

public class ShopMenu {
    private User currentUser;
    public ShopMenu(User currentUser){
        this.currentUser = currentUser;
    }
    public void run(){
        ShopMenuController shopMenuController = new ShopMenuController(currentUser);
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.matches("back")) return;
            else if (command.matches("^\\s*show\\s+price\\s*list\\s*$")){
                shopMenuController.showPriceList();
            }
            else if ((matcher = Commands.getMatcher(command, Commands.BUY_SHOP))!=null){
                shopMenuController.buy(matcher.group("data"));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SELL_SHOP))!=null){
                shopMenuController.buy(matcher.group("data"));
            }
            else
                System.out.println("invalid command");
        }
    }
}
