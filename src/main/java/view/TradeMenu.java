package view;

import controller.gameMenuControllers.TradeMenuController;
import model.User;
import view.enums.Commands;

import java.util.regex.Matcher;

public class TradeMenu {
    private User currentUser;

    public TradeMenu(User currentUser) {
        this.currentUser = currentUser;
    }

    public void run() {
        TradeMenuController tradeMenuController = new TradeMenuController(currentUser);
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.matches("back")) return;
            else if ((matcher = Commands.getMatcher(command, Commands.TRADE))!=null){
                tradeMenuController.newTradeRequest(matcher.group("data"));
            }
            else if (command.matches("\\s*trade\\s+list\\s*")){
                tradeMenuController.showTradeList();
            }
            else if ((matcher = Commands.getMatcher(command, Commands.ACCEPT_TRADE))!=null){
                tradeMenuController.newTradeRequest(matcher.group("data"));
            }
            else if (command.matches("\\s*trade\\s+history\\s*")){
                tradeMenuController.showTradeHistory();
            }
            else
                System.out.println("invalid command");
        }
    }

}
