package controller;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import model.User;
import view.ScanMatch;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordReset {
    private String inputUsername;
    private User user;
    private String newPassword;
    public PasswordReset(String inputUsername) throws NoSuchAlgorithmException {
        this.inputUsername = inputUsername;
        if (User.getUsers().size() == 0) {
            LoginMenuController.extractUserData();
        }
        resetPassword();
    }
    private void resetPassword() throws NoSuchAlgorithmException {
        if (!userExist()){
            System.out.println("Username not found!");
            return;
        }
        askQuestion();
        scanPassword(true);
        scanPassword(false);
        newPassword = encryptPassword(newPassword);
        user.setPassword(newPassword);

        System.out.println("Your password reset successfully");
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
    private void askQuestion(){
        String question;
        if (user.getSecurityQuestion()==1) question = "What is your father’s name?";
        else if (user.getSecurityQuestion()==2) question = "What was your first pet’s name?";
        else question = "What is your mother’s last name?";
        System.out.println(question);
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            if (command.equals(user.getSecurityAnswer())) return;
            else System.out.println("Your answer is wrong. Please enter another answer.");
        }
    }
    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }
    private void scanPassword(boolean sign){
        if (sign) System.out.println("Please enter new password");
        else System.out.println("Please re-enter new password");
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            if (newPassword == null || sign) {
                newPassword = command;
                if (checkPasswordFormat(newPassword)) break;
            }
            else if (!sign){
                if (newPassword.equals(command)) break;
                else System.out.println("Please re-enter password correctly");
            }
        }
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
}
