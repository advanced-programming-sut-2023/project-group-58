package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import view.ScanMatch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeProfileController {
    private User user;
    private String dataToChange;
    private String oldPassword;
    private String newPassword;

    public ChangeProfileController(User user, String dataToChange) {
        this.user = user;
        this.dataToChange = dataToChange;
    }

    public ChangeProfileController(User user) {
        this.user = user;
    }

    public void changeUserName(){
        for (int i =0; i< User.getUsers().size(); i++){
            if (User.getUsers().get(i).getUsername().equals(dataToChange)){
                System.out.println("This username is already taken");
                return;
            }
        }
        if (dataToChange.matches(".*[\\W+].*")){
            System.out.println("Username's format is invalid!");
            return;
        }
        changeDetail(user.getUsername(), "username", dataToChange);
        user.setUsername(dataToChange);
        System.out.println("Username changed to "+dataToChange+" successfully");
    }
    public void changeNickname(){
        user.setNickname(dataToChange);
        changeDetail(user.getUsername(), "nickname", dataToChange);
        System.out.println("Nickname changed to \""+dataToChange+"\" successfully");
    }
    public void changePassword() throws NoSuchAlgorithmException {
        oldPassword = dataExtractor(dataToChange, "-o\\s+(?<wantedPart>\\S*)");
        newPassword = dataExtractor(dataToChange, "-n\\s+(?<wantedPart>\\S*)");
        oldPassword = encryptPassword(oldPassword);
        String temp = oldPassword;
        if (!user.passwordMatch(oldPassword)){
            System.out.println("Password is wrong");
            return;
        }
        if (!checkPasswordFormat(newPassword)) return;
        if (newPassword.equals(temp)) {
            System.out.println("Please enter a new password");
            return;
        }
        System.out.println("Please enter your new password again");
        while (true) {
            if (ScanMatch.getScanner().nextLine().equals(newPassword)) break;
            else System.out.println("Please enter your new password again correctly");
        }
        newPassword = encryptPassword(newPassword);
        user.setPassword(newPassword);
        changeDetail(user.getUsername(), "password", newPassword);
        System.out.println("Your password successfully changed");
    }
    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }
    public String dataExtractor(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) return "";
        return matcher.group("wantedPart");
    }
    private boolean checkPasswordFormat(String password){
        if (password.length() < 6) {
            System.out.println("Password is too short!");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password does not include capital letters!");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            System.out.println("Password does not include small letters!");
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            System.out.println("Password does not include numbers!");
            return false;
        }
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            System.out.println("Password does not include symbols!");
            return false;
        }
        return true;
    }
    public void changeEmail(){
        for (int i=0; i<User.getUsers().size(); i++){
            if (User.getUsers().get(i).getEmail().equalsIgnoreCase(dataToChange)) {
                System.out.println("This email address is already used");
                return;
            }
        }
        String regex = "^[\\w|.]+@[\\w|.]+\\.[\\w|.]+$";
        if (! Pattern.compile(regex).matcher(dataToChange).find()) {
            System.out.println("Email format is not valid!");
            return;
        }
        user.setEmail(dataToChange);
        changeDetail(user.getUsername(), "email", dataToChange);
        System.out.println("Your Email changed to "+dataToChange+" successfully");
    }
    public void changeSlogan(){
        user.setSlogan(dataToChange);
        changeDetail(user.getUsername(), "slogan", dataToChange);
        System.out.println("Your slogan changed successfully");
    }
    public void removeSlogan(){
        user.setSlogan(null);
        changeDetail(user.getUsername(), "slogan", "");
        System.out.println("Your slogan cleared successfully");
    }
    private void changeDetail(String username, String toChange, String forChange) {
      //  System.out.println(toChange + ": "+ forChange);
        String userInfoAddress = System.getProperty("user.dir") + "/DataBase/userInfo.json";
        Gson gson = new Gson();
        JsonArray usersList;
        JSONArray newUsersList = new JSONArray();
        try {
            usersList = gson.fromJson(new FileReader(userInfoAddress), JsonArray.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < usersList.size(); i++) {

            boolean isTheOne = false;
            JsonObject jsonObject = usersList.get(i).getAsJsonObject();
            JSONObject eachUserWithKey = new JSONObject();
            JSONObject newUserDetails = new JSONObject();
            if (jsonObject.get("user").getAsJsonObject().get("username").toString().equals("\"" + username + "\""))
                isTheOne = true;
            newUserDetails.put("nickname"           , correctDoubleQuotation(jsonObject.get("user").getAsJsonObject().get("nickname").toString()));
            newUserDetails.put("email"              , correctDoubleQuotation(jsonObject.get("user").getAsJsonObject().get("email").toString()));
            newUserDetails.put("slogan"             , correctDoubleQuotation(jsonObject.get("user").getAsJsonObject().get("slogan").toString()));
            newUserDetails.put("securityQuestion"   , correctDoubleQuotation(jsonObject.get("user").getAsJsonObject().get("securityQuestion").toString()));
            newUserDetails.put("securityAnswer"     , correctDoubleQuotation(jsonObject.get("user").getAsJsonObject().get("securityAnswer").toString()));
            newUserDetails.put("username"           , correctDoubleQuotation(jsonObject.get("user").getAsJsonObject().get("username").toString()));
            newUserDetails.put("highScore"          , correctDoubleQuotation(jsonObject.get("user").getAsJsonObject().get("highScore").toString()));
            newUserDetails.put("password"           , correctDoubleQuotation(jsonObject.get("user").getAsJsonObject().get("password").toString()));
            if(!isTheOne)
                newUserDetails.put(""+toChange           , correctDoubleQuotation(jsonObject.get("user").getAsJsonObject().get(""+toChange).toString()));
            if(isTheOne) {
                newUserDetails.replace(""+toChange       , correctDoubleQuotation(forChange.toString()));
            }
            eachUserWithKey.put("user", newUserDetails);
            newUsersList.add(eachUserWithKey);
        }

        //now, we should add the json array to our file.
        FileWriter file = null;
        try {
            file = new FileWriter(System.getProperty("user.dir") + "/DataBase/userInfo.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            file.write(newUsersList.toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String correctDoubleQuotation(String input) {
        if(input.charAt(0) == '"' && input.charAt(input.length()-1) == '"')
            return input.substring(1,input.length()-1);
        return input;
    }
}
