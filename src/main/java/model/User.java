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
                + ", slogan = " + slogan + ", securityQuestion = " + securityQuestion + ", securityAnswer = " + securityAnswer +"\\n" +"]";
    }
}
