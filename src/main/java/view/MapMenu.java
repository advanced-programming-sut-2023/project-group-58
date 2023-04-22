package view;

import controller.MapMenuController;
import model.Map;

import java.util.regex.Matcher;

public class MapMenu {
    Map map;
    MapMenuController mapMenuController;

    public MapMenu(Map map) {
        this.map = map;
        mapMenuController = new MapMenuController();
        mapMenuController.selectedMap = map;
    }

    public void run(){
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.equals("exit")){
                System.out.println("Your are in the game menu");
                return;
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SET_TEXTURE))!=null){
                System.out.println(mapMenuController.setTextureForTheWholeMap(map,matcher.group("data")));
                this.map = mapMenuController.getSelectedMap();
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_MAP))!=null){
                mapMenuController.showMap(matcher.group("data"));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.MOVE_MAP))!=null){
                mapMenuController.moveMap(matcher.group("data"));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.CLEAR))!=null){
                System.out.println(mapMenuController.clearTile(matcher.group("data")));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_DETAIL))!=null){
                mapMenuController.showDetail(matcher.group("data"));
            }
            else
                System.out.println("invalid command");
        }
    }
}
