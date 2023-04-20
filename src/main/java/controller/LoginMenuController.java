package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.MainMenu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {
    private String data;
    private String inputUsername;
    private String inputPassword;
    private int userTryLogin;
    private User user;
    public LoginMenuController(String data) throws IOException, NoSuchAlgorithmException {
        this.data = data;
        extractData();
        checkForLogin();
    }
    private void extractData() throws IOException {
        inputUsername = dataExtractor(data, "((?<!\\S)-u\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        inputPassword = dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
    }
    private void checkForLogin() throws NoSuchAlgorithmException {
        if (!userExist()) {
            System.out.println("Username not found!");
            return;
        }
        if (!passwordMatch()){
            System.out.println("Password is wrong!");
            return;
        }
        System.out.println("user logged in successfully!");
        System.out.println("You are in the main menu");
        MainMenu mainMenu = new MainMenu(user);
        mainMenu.run();
    }
    private boolean userExist(){

        for (int i =0; i<User.getUsers().size(); i++){
            if (User.getUsers().get(i).getUsername().equals(inputUsername)){
                user = User.getUsers().get(i);
                return true;
            }
        }
        return false;
    }
    private boolean passwordMatch(){
        try {
            inputPassword = encryptPassword(inputPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (user.passwordMatch(inputPassword)) return true;
        return false;
    }
    public String dataExtractor(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if(!matcher.find()) return "";
        return matcher.group("wantedPart");
    }
    public static void extractUserData() {
        //address here is: System.getProperty("user.dir") + "/DataBase/userInfo.json"
        String address = System.getProperty("user.dir") + "/DataBase/userInfo.json";
        Gson gson = new Gson();
        JsonArray jsonArray = null;
        try {
            jsonArray = gson.fromJson(new FileReader(address), JsonArray.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < jsonArray.size(); i++) {
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
    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }
}