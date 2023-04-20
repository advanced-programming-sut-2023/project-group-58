package controller;

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
    public LoginMenuController(String data) throws IOException {
        this.data = data;
        extractData();
        checkForLogin();
    }
    private void extractData() throws IOException {
        inputUsername = dataExtractor(data, "((?<!\\S)-u\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        inputPassword = dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        //System.out.println(inputPassword + " : "+ inputUsername);
        if (User.getUsers().size() == 0) {
            extractFromJson();
        }
    }
    private void checkForLogin(){
        //System.out.println(User.getUsers().size());
        if (!userExist()) {
            System.out.println("Username not found!");
            return;
        }
        if (!passwordMatch()){
            System.out.println("Password is wrong!");
            return;
        }
        System.out.println("user logged in successfully!");
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
    private void extractFromJson() {
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/DataBase/userInfo.json"));
            String line = reader.readLine();
           // System.out.println(line);
            String regex = "\"User \\[username = (?<username>[^\"]+), password = (?<password>[^\"]+), nickname = (?<nickname>[^\"]+), email = (?<email>[^\"]+), slogan = (?<slogan>[^\"]+), securityQuestion = (?<number>\\d+), securityAnswer = (?<answer>[^\"]+)]\"";
            Matcher matcher = Pattern.compile(regex).matcher(line);
            while (matcher.find()){
               // System.out.println(1);
                String name = matcher.group("username");
              //  System.out.println(name);
                String password = matcher.group("password");
                String nickname = matcher.group("nickname");
                String email = matcher.group("email");
                String slogan = matcher.group("slogan");
                int securityQuestion = Integer.parseInt(matcher.group("number"));
                String securityAnswer = matcher.group("answer");
                User addingUser = new User(name, password, nickname, email, slogan, securityQuestion, securityAnswer);
                addingUser.addUserToArrayList();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }
}