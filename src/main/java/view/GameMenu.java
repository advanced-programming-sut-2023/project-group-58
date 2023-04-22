package view;

import controller.MapMenuController;
import model.Tile;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameMenu {

    Tile[][] map;
    public void run(){
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            map = (new MapMenuController()).setUpMap();
        }
    }
}
