package controller;

import model.User;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ShowProfileController {
    private User user;

    public ShowProfileController(User user) {
        this.user = user;
    }
    public void showRank(){
        ArrayList<User> users = User.getUsers();
        Collections.sort(users, new Comparator<User>() {
            public int compare(User a, User b) {
                return b.getHighScore() - a.getHighScore();
            }
        });
        Collections.sort(users, new Comparator<User>() {
            public int compare(User a, User b) {
                if (a.getHighScore() == b.getHighScore())
                    return a.getUsername().compareTo(b.getUsername());
                return 0;
            }
        });
        for (int i =0; i<users.size(); i++){
            if (users.get(i).getUsername().equals(user.getUsername())) {
                System.out.println("Your rank is "+(i+1));
                break;
            }
        }
    }
    public void showScore(){
        System.out.println("Your highest score is "+ user.getHighScore());
    }
    public void showSlogan(){
        if (user.getSlogan()==null) System.out.println("Your slogan is empty");
        else System.out.println("Your slogan is : "+ user.getSlogan());
    }
    public void showDisplay() throws NoSuchAlgorithmException {
        System.out.println("username : " + user.getUsername());
        System.out.println("nickname :" +user.getNickname());
        System.out.println("email : " + user.getEmail());
        System.out.println("highest score : "+ user.getHighScore());
        if (user.getSlogan() == null) System.out.println("slogan : ");
        else System.out.println("slogan : "+ user.getSlogan());
        System.out.println("your rank : "+ getRank());
    }
    private int getRank(){
        ArrayList<User> users = User.getUsers();
        for (int i =0 ; i<users.size();i++){
            if (users.get(i).getHighScore()==-1) {
                users.remove(i);
                break;
            }
        }
        Collections.sort(users, new Comparator<User>() {
            public int compare(User a, User b) {
                return b.getHighScore() - a.getHighScore();
            }
        });
        Collections.sort(users, new Comparator<User>() {
            public int compare(User a, User b) {
                if (a.getHighScore() == b.getHighScore())
                    return a.getUsername().compareTo(b.getUsername());
                return 0;
            }
        });
        for (int i =0; i<users.size(); i++){
            if (users.get(i).getUsername().equals(user.getUsername())) {
                return (i+1);
            }
        }
        return 0;
    }
}
