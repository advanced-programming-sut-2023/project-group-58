package controller.gameMenuControllers;

import model.User;

public class ShopMenuController {
    private User currentUser;

    public ShopMenuController(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void showPriceList() {

    }
    public void buy(String data) {

    }
    public void sell(String data) {

    }
}
