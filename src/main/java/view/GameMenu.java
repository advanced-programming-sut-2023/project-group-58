package view;

import java.io.IOException;

import controller.gameMenuControllers.GameController;
import model.Map;
import model.User;
import model.buildings.Building;
import view.enums.Commands;
import view.enums.GameControllerOut;

import java.util.regex.Matcher;

public class GameMenu {

    Map map;
    private User currentUser;
    private GameController gameController;
    private Building selectedBuilding;

    public GameMenu(User host) {
        this.currentUser = host;
    }
    //todo: reset every governance in the end.
    public void run() throws IOException {
        map = (new MapMenu(null,this.currentUser)).setUpMap();
        gameController = new GameController(this.currentUser,this.map);
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if(command.matches("map menu")) {
                MapMenu mapMenu = new MapMenu(this.map,this.currentUser);
                this.map = mapMenu.map;
                gameController.setSelectedMap(this.map);
            }
            else if(command.matches("shop menu")){
                if(selectedBuilding.getType().getName().equals("market"))
                    new ShopMenu(currentUser).run();
                else System.out.println("You should go to the market first!");
            }
            else if (command.matches("trade menu")){
                new TradeMenu(currentUser).run();
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_POP_FACTORS)) != null) {
                System.out.println(gameController.showPopularityFactors());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_POPULARITY)) != null) {
                System.out.println(gameController.showPopularity());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SET_FOOD_RATE)) != null) {
                System.out.println(gameController.setFoodRate(matcher.group("data")).getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_FOOD_RATE)) != null) {
                System.out.println(gameController.showFoodRate());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_TAX_RATE)) != null) {
                System.out.println(gameController.showTaxRate());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SELECT_BUILDING)) != null) {
                System.out.println(gameController.selectBuilding(matcher.group("data")).getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.DROP_BUILDING)) != null) {
                System.out.println(GameControllerOut.DROP);
            }
            else if ((matcher = Commands.getMatcher(command, Commands.CREATE_UNIT)) != null) {
                System.out.println(gameController.createUnit(matcher.group("data")).getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.REPAIR)) != null) {
                System.out.println(gameController.repair().getContent());
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
