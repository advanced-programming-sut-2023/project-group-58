package view;

import controller.MapMenuController;
import model.Tile;

import java.util.ArrayList;
import model.Map;
import java.util.regex.Matcher;

public class GameMenu {

    Map map;
    public void run(){
        map = (new MapMenuController()).setUpMap();
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if(command.matches("map menu"))
                new MapMenu(this.map).run();
        }
    }
}
