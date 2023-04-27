package controller.gameMenuControllers;

import model.User;

public class GameController {
    private User CurrentUser;

    public User getCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }
}
