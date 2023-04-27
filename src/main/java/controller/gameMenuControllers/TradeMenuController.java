package controller.gameMenuControllers;

import model.User;
import model.newClasses.Trade;

import java.util.ArrayList;

public class TradeMenuController {
    private static ArrayList<Trade> trades;
    private User currentUser;

    public TradeMenuController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void doTheTrade(String data) {
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

    public static ArrayList<Trade> getTrades() {
        return trades;
    }
}
