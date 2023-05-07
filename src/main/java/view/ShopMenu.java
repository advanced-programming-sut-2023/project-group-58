package view;

import controller.gameMenuControllers.ShopMenuController;
import model.User;
import view.enums.Commands;
import view.enums.ShopAndTradeControllerOut;

import java.util.regex.Matcher;

public class ShopMenu {
    private User currentUser;

    public ShopMenu(User currentUser) {
        this.currentUser = currentUser;
    }

    public void run() {
        ShopMenuController shopMenuController = new ShopMenuController(currentUser);
        while (true) {
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.matches("back")) return;
            else if (command.matches("^\\s*show\\s+price\\s*list\\s*$"))
                System.out.print(shopMenuController.showPriceList(currentUser));
            else if ((matcher = Commands.getMatcher(command, Commands.BUY_SHOP)) != null)
                doThePurchase(matcher, shopMenuController, currentUser,"buy");
            else if ((matcher = Commands.getMatcher(command, Commands.SELL_SHOP)) != null)
                doThePurchase(matcher, shopMenuController, currentUser,"sell");
            else
                System.out.println("invalid command");
        }
    }

    public static void doThePurchase(Matcher matcher, ShopMenuController shopMenuController, User currentUser, String typeOfDeal) {
        ShopAndTradeControllerOut output;
        if(typeOfDeal.equals("buy")) output = shopMenuController.buy(matcher.group("data"), currentUser);
        else output = shopMenuController.sell(matcher.group("data"), currentUser);
        System.out.println(output.getContent());
        if (output.equals(ShopAndTradeControllerOut.PROMPT_CONFIRMATION_FOR_PURCHASE) && typeOfDeal.equals("buy") ||
            output.equals(ShopAndTradeControllerOut.PROMPT_CONFIRMATION_FOR_SELL) && typeOfDeal.equals("sell")) {
            System.out.println(shopMenuController.getMerchandise().getAmount() + " unit of " +
                    shopMenuController.getMerchandise().getType().getName() + "?\n" +
                    "Type \"yes\" or no");
            String answer = ScanMatch.getScanner().nextLine();
            if (answer.matches("^\\s*yes\\s*$")) {
                if(typeOfDeal.equals("buy"))
                    shopMenuController.purchase();
                else shopMenuController.retail();
            }
            else if (answer.matches("^\\s*no\\s*$"))
                System.out.println(ShopAndTradeControllerOut.ABORT_THE_MISSION);
            else
                System.out.println(ShopAndTradeControllerOut.INVALID_INPUT_FORMAT);
        }
    }
}
