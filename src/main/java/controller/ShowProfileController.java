package controller;

import model.User;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import com.google.gson.*;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.Commands;
import view.ScanMatch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println("slogan : "+ user.getSlogan());
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
