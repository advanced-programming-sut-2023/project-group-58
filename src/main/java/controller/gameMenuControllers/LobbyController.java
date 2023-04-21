package controller.gameMenuControllers;

import model.Governance;
import model.User;

import java.util.ArrayList;

public class LobbyController {
    private String username;
    private User user;
    private ArrayList<User> players = new ArrayList<>();
    public LobbyController(User user){
        players.add(user);
    }
    public void getUser(String user){
        this.username = user;
        addUser();
    }
    private void addUser(){
        if (!existUser()){
            System.out.println("Username not found");
            return;
        }
        for (int i =0; i<players.size(); i++){
            if (players.get(i).equals(user)) {
                System.out.println("this user already joined lobby");
                return;
            }
        }
        players.add(user);
        System.out.println("user joined lobby");
    }
    private boolean existUser(){
        for (int i=0; i<User.getUsers().size(); i++){
            if (User.getUsers().get(i).getUsername().equals(username)) {
                user = User.getUsers().get(i);
                return true;
            }
        }
        return false;
    }
    public boolean startGame(){
        if (players.size()>1 && players.size()<9){
            Governance.setEmpires(players);
            return true;
        }
        return false;
    }
    public void removeUser(String user){
        username = user;
        for (int i=0; i<players.size(); i++){
            if (players.get(i).getUsername().equals(username)){
                if (i==0) {
                    System.out.println("you cannot remove yourself, because your are owner of lobby");
                    return;
                }
                players.remove(i);
                System.out.println(username+" removed successfully");
                return;
            }
        }
        System.out.println("there isn't any user by entered username in the lobby");
    }
    public void showUsers(){
        for (int i =0; i<players.size(); i++){
            System.out.println((i+1)+". "+players.get(i).getUsername());
        }
    }
}
