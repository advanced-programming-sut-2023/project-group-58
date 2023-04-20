package controller;

import model.User;
import view.ScanMatch;

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
        user.setUsername(dataToChange);

        System.out.println("Username changed to "+dataToChange+" successfully");
    }
    public void changeNickname(){
        user.setNickname(dataToChange);

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

        System.out.println("Your Email changed to "+dataToChange+" successfully");
    }
    public void changeSlogan(){
        user.setSlogan(dataToChange);

        System.out.println("Your slogan changed successfully");
    }
    public void removeSlogan(){
        user.setSlogan(null);

        System.out.println("Your slogan cleared successfully");
    }

}
