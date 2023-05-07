package controller.gameMenuControllers;

import controller.CommonController;
import model.Resource;
import model.ResourceEnum;
import model.User;
import model.TradeItem;
import view.enums.ShopControllerOut;

import java.util.ArrayList;

public class TradeMenuController {
    private static ArrayList<TradeItem> trades;
    private TradeItem newRequset;
    private User currentUser;

    public TradeMenuController(User currentUser) {
        this.currentUser = currentUser;
    }
    public ShopControllerOut extractTradeFields(String data) {
        if(CommonController.dataExtractor(data, "((?<!\\S)-t\\s+(?<wantedPart>([^-]+)(?<!\\s))").length() == 0 ||
           CommonController.dataExtractor(data, "((?<!\\S)-a\\s+(?<wantedPart>(\\d+)(?<!\\s))").length() == 0 ||
           CommonController.dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\\d+)(?<!\\s))").length() == 0 ||
           CommonController.dataExtractor(data, "((?<!\\S)-m\\s+(?<wantedPart>([^-]+)(?<!\\s))").length() == 0)
            return ShopControllerOut.INVALID_INPUT_FORMAT;

        String item     = CommonController.dataExtractor(data, "((?<!\\S)-i\\s+(?<wantedPart>([^-]+)(?<!\\s))").trim();
        int amount      = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-a\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim());
        int price       = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim());
        String message  = CommonController.dataExtractor(data, "((?<!\\S)-m\\s+(?<wantedPart>([^-]+)(?<!\\s))").trim();
        ResourceEnum type = resourceFinder(item);
        this.merchandise = new Resource(resourceItem,amount);
        return ShopControllerOut.SUCCESS;
    }

    public void newTradeRequest(String data) {

    }
    public void showTradeList() {

    }
    public void showTradeHistory() {

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public static ArrayList<TradeItem> getTrades() {
        return trades;
    }
}
