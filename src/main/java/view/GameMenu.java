package view;

import controller.MapMenuController;
import model.Tile;

import java.util.ArrayList;
import model.Map;
import model.User;

import java.util.regex.Matcher;

public class GameMenu {

    Map map;
    private User currentUser;
    public void run(){
        map = (new MapMenuController()).setUpMap();
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if(command.matches("map menu"))
                new MapMenu(this.map).run();
            else if(command.matches("shop menu")){
                ShopMenu shopMenu = new ShopMenu(currentUser);
                shopMenu.run();
            }
            else if (command.matches("trade menu")){
                TradeMenu tradeMenu = new TradeMenu(currentUser);
                tradeMenu.run();
            }
            else
                System.out.println("invalid command");
        }
    }
}
