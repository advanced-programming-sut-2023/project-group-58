package view;

import controller.MapMenuController;
import model.Map;
import model.User;
import view.enums.Commands;
import view.enums.ProfisterControllerOut;

import java.io.IOException;
import java.util.regex.Matcher;

public class MapMenu {
    Map map;
    User currentPlayer;
    MapMenuController mapMenuController;

    public MapMenu(Map map, User currentPlayer) {
        this.map = map;
        mapMenuController = new MapMenuController();
        mapMenuController.selectedMap = map;
        this.currentPlayer = currentPlayer;
    }

    public void run() throws IOException {
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
                System.out.println(mapMenuController.showMap(matcher.group("data")));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.MOVE_MAP))!=null){
                System.out.println(mapMenuController.moveMap(matcher.group("data")));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.CLEAR))!=null){
                System.out.println(mapMenuController.clearTile(matcher.group("data")));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.DROP_TREE))!=null){
                System.out.println(mapMenuController.dropTree(matcher.group("data")));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.DROP_ROCK))!=null){
                System.out.println(mapMenuController.dropRock(matcher.group("data")));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_DETAIL))!=null){
                System.out.println(mapMenuController.showDetail(matcher.group("data")));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.DROP_BUILDING)) != null) {
                System.out.println(mapMenuController.dropBuilding(matcher.group("data"),currentPlayer).getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.DROP_UNIT)) != null) {
            //    System.out.println(mapMenuController.dropUnit(matcher.group("data"),currentPlayer).getContent());
            }
            else
                System.out.println("invalid command");
        }
    }

    public Map setUpMap() {
        System.out.println("Would you like to choose a template map from archive?\nType y for yes or n for no");
        String answer = ScanMatch.getScanner().nextLine().trim();
        if (answer.equals("y"))
            System.out.println(mapMenuController.setUpATemplate());
        else if (answer.equals("n")) {
            System.out.println("Then custom map shall it be!\nChoose the map scale:");
            for (int i = 1; i < 9; i++)
                System.out.println(i + ". " + i * 100 + " * " + i * 100);
            int givenRange = ScanMatch.getScanner().nextInt() * 100;
            if(givenRange <= 0 || givenRange >= 9) {
                System.out.println(ProfisterControllerOut.INVALID_INPUT_FORMAT);
                return null;
            }
            System.out.println(mapMenuController.setUpACustom(givenRange));
        } else System.out.println("Mission failed: invalid input");
        this.map = mapMenuController.selectedMap;
        return this.map;
    }
}
