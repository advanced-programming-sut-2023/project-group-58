package model;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int securityQuestion;
    private String securityAnswer;
    private static ArrayList<User> users = new ArrayList<>();

    public User(String Username,
                String Password,
                String Nickname,
                String Email,
                String Slogan,
                int SecurityQuestion,
                String SecurityAnswer)
    {
        username = Username;
        password = Password;
        nickname = Nickname;
        email = Email;
        slogan = Slogan;
        securityQuestion = SecurityQuestion;
        securityAnswer = SecurityAnswer;
    }

    public void addUserToArrayList() {
        User.users.add(this);
    }
    @Override
    public String toString() {
        return "User [username = " + username + ", password = " + password + ", nickname = " + nickname + ", email = " + email
                + ", slogan = " + slogan + ", securityQuestion = " + securityQuestion + ", securityAnswer = " + securityAnswer+"]";
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public String getUsername() {
        return username;
    }
    public boolean passwordMatch(String password){
        if (this.password.equals(password)) return true;
        return false;
    }

    public int getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
