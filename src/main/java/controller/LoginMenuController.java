package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.User;
import view.*;
import view.enums.LoginControllerOut;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class LoginMenuController {
    private String data;
    private String inputUsername;
    private String inputPassword;
    private int userTryLogin;
    private User user;
    private boolean stayLogin = false;
    private static User userStayLogin;

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    public LoginMenuController(String data) throws IOException {
        this.data = data;
        extractData();
    }

    private void extractData() throws IOException {
        inputUsername = CommonController.dataExtractor(data, "((?<!\\S)-u\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        inputPassword = CommonController.dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        if (CommonController.dataExtractor(data, "--stay-logged-in")!=null){
            stayLogin = true;
        }
    }

    public LoginControllerOut checkForLogin() {
        if (!userExist())
            return LoginControllerOut.USERNAME_NOT_FOUND;
        if (!passwordMatch())
            return LoginControllerOut.PASSWORD_WRONG;
        return LoginControllerOut.VALID;
    }

    public void mainMenuRun() throws NoSuchAlgorithmException, IOException {
        if (stayLogin) userStayLogin = user;
        MainMenu mainMenu = new MainMenu(user);
        mainMenu.run();
    }
    public void mainMenuRunStayed(User user) throws NoSuchAlgorithmException, IOException {
        MainMenu mainMenu = new MainMenu(user);
        mainMenu.run();
    }

    public void giveAnotherShot(int timeOut, String password) {
        long startTime = System.currentTimeMillis()/1000;
        while(true) {
            long timeNow = System.currentTimeMillis()/1000;
            if ((timeNow - startTime) > timeOut)
            {
                inputPassword = password;
                return;
            }
            else {
                //todo: add a message, maybe?
                //System.out.println("You have to wait!");
            }

        }
    }
    public boolean userExist() {

        for (int i = 0; i < User.getUsers().size(); i++) {
            if (User.getUsers().get(i).getUsername().equals(inputUsername)) {
                user = User.getUsers().get(i);
                return true;
            }
        }
        return false;
    }

    public boolean passwordMatch() {
        try {
            inputPassword = encryptPassword(inputPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (user.passwordMatch(inputPassword)) return true;
        return false;
    }

    public static void extractUserData() throws FileNotFoundException {
        //address here is: System.getProperty("user.dir") + "/DataBase/userInfo.json"
        String address = System.getProperty("user.dir") + "/DataBase/userInfo.json";
        if (new FileReader(address)==null) return;
        Gson gson = new Gson();
        JsonArray jsonArray = null;
        try {
            jsonArray = gson.fromJson(new FileReader(address), JsonArray.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            if (i>0) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String password = jsonObject.get("user").getAsJsonObject().get("password").toString().replaceAll("\"", "");
                int securityQuestion = Integer.parseInt(jsonObject.get("user").getAsJsonObject().get("securityQuestion").toString().replaceAll("\"", ""));
                String securityAnswer = jsonObject.get("user").getAsJsonObject().get("securityAnswer").toString().replaceAll("\"", "");
                String nickname = jsonObject.get("user").getAsJsonObject().get("nickname").toString().replaceAll("\"", "");
                String slogan = jsonObject.get("user").getAsJsonObject().get("slogan").toString().replaceAll("\"", "");
                String email = jsonObject.get("user").getAsJsonObject().get("email").toString().replaceAll("\"", "");
                String username = jsonObject.get("user").getAsJsonObject().get("username").toString().replaceAll("\"", "");
                int highScore = Integer.parseInt(jsonObject.get("user").getAsJsonObject().get("highScore").toString().replaceAll("\"", ""));
                User addingUser = new User(username, password, nickname, email, slogan, securityQuestion, securityAnswer, highScore);
                addingUser.addUserToArrayList();
            }
        }
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }

    public static User getUserStayLogin() {
        return userStayLogin;
    }

    public LoginMenuController() {
    }
}