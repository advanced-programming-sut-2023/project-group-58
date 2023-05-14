package view;

import java.io.IOException;

import controller.gameMenuControllers.GameController;
import model.Governance;
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
                System.out.println("You are in the map menu");
                MapMenu mapMenu = new MapMenu(this.map,this.currentUser);
                mapMenu.run();
                this.map = mapMenu.map;
                gameController.setSelectedMap(this.map);
            }
            else if (command.matches("show current menu")) System.out.println("game menu");
            else if(command.matches("shop menu")){
                if(gameController.getSelectedBuilding() != null && gameController.getSelectedBuilding().getType().getName().equals("market")) {
                    System.out.println("You are in the shop menu");
                    new ShopMenu(currentUser).run();
                }
                else System.out.println("You should go to the market first!");
            }
            else if (command.matches("trade menu")){
                System.out.println("You are in the trade menu");
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
            else if ((matcher = Commands.getMatcher(command, Commands.SET_FEAR_RATE)) != null) {
                System.out.println(gameController.setFearRate(matcher.group("data")).getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_FOOD_LIST)) != null) {
                System.out.print(gameController.showFoodList());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SET_TAX_RATE)) != null) {
                System.out.println(gameController.setTaxRate(matcher.group("data")).getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_FOOD_RATE)) != null) {
                System.out.println(gameController.showFoodRate());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SHOW_TAX_RATE)) != null) {
                System.out.println(gameController.showTaxRate());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SELECT_BUILDING)) != null) {
                System.out.println(gameController.selectBuilding(matcher.group("data")));
            }
            else if ((matcher = Commands.getMatcher(command, Commands.DROP_BUILDING)) != null) {
                System.out.println(GameControllerOut.DROP.getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.CREATE_UNIT)) != null) {
                System.out.println(gameController.createUnit(matcher.group("data")).getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.REPAIR)) != null) {
                System.out.println(gameController.repair().getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SELECT_BUILDING)) != null) {
                System.out.println(gameController.selectUnit(matcher.group("data")).getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.SET_STATE)) != null) {
                System.out.println(gameController.setState(matcher.group("data")).getContent());
            }
            else if ((matcher = Commands.getMatcher(command, Commands.MOVE_UNIT)) != null) {
             //   System.out.println(gameController.moveUnit(matcher.group("data")).getContent());
            }
            else if (command.matches("trade menu")){
                TradeMenu tradeMenu = new TradeMenu(currentUser);
                tradeMenu.run();
            }
            else if ((matcher = Commands.getMatcher(command, Commands.NEXT_TURN)) != null){
                //set target, fight , move , update resources , govern functions lie here
                //soldier's damage should be set according to the fear rate at each turn
                this.currentUser = Governance.getNextPlayer(this.currentUser);
                gameController.setCurrentUser(this.currentUser);
                System.out.println(GameControllerOut.NEXT_TURN.getContent() + this.currentUser.getUsername());
            }
            else
                System.out.println("invalid command");
        }
    }
}
