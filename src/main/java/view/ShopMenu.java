package view;

import controller.gameMenuControllers.ShopMenuController;
import model.User;
import view.enums.Commands;
import view.enums.ShopControllerOut;

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
                buy(matcher, shopMenuController, currentUser);
            else if ((matcher = Commands.getMatcher(command, Commands.SELL_SHOP)) != null)
                shopMenuController.sell(matcher.group("data"), currentUser);
            else
                System.out.println("invalid command");
        }
    }

    public static void buy(Matcher matcher, ShopMenuController shopMenuController, User currentUser) {
        ShopControllerOut output = shopMenuController.buy(matcher.group("data"), currentUser);
        System.out.println(output.getContent());
        if (!output.equals(ShopControllerOut.PROMPT_CONFIRMATION_FOR_PURCHASE)) {
        } else {
            System.out.println(shopMenuController.getBuyProduct().getAmount() + " unit of " +
                    shopMenuController.getBuyProduct().getType().getName() + "?\n" +
                    "Type \"yes\" or no");
            String answer = ScanMatch.getScanner().nextLine();
            if (answer.matches("^\\s*yes\\s*$"))
                shopMenuController.purchase();
            else if (answer.matches("^\\s*no\\s*$"))
                System.out.println(ShopControllerOut.ABORT_THE_MISSION);
            else
                System.out.println(ShopControllerOut.INVALID_INPUT_FORMAT);
        }
    }
}
