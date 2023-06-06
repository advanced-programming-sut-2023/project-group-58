package controller;

import model.User;
import view.enums.ProfisterControllerOut;

public class LoginMenuControl {
    public ProfisterControllerOut checkUsername(String username){
        if (username.length() == 0) return ProfisterControllerOut.EMPTY_FIELDS;
        if (username.matches(".*[\\W+].*")) return ProfisterControllerOut.USERNAME_INVALID_FORMAT;
        if (isUsernameOrEmailAlreadyTaken(username)) {
            return ProfisterControllerOut.SUGGESTING_USERNAME;
        }
        return null;
    }
    private boolean isUsernameOrEmailAlreadyTaken(String data){
        for (User user : User.getUsers()){
            if (user.getUsername().equals(data)) return true;
            else if(user.getEmail().equals(data)) return true;
        }
        return false;
    }
}
