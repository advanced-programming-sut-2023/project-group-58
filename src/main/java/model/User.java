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
    private int highScore = 0;
    private static ArrayList<User> users = new ArrayList<>();

    public User(String Username,
                String Password,
                String Nickname,
                String Email,
                String Slogan,
                int SecurityQuestion,
                String SecurityAnswer,
                int HighScore) {
        username = Username;
        password = Password;
        nickname = Nickname;
        email = Email;
        slogan = Slogan;
        securityQuestion = SecurityQuestion;
        securityAnswer = SecurityAnswer;
        highScore = HighScore;
    }

    public void addUserToArrayList() {
        User.users.add(this);
    }
}