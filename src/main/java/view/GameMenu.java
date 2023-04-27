package view;

import java.io.IOException;

import controller.gameMenuControllers.GameController;
import model.Map;
import model.User;
import view.enums.Commands;
import view.enums.GameControllerOut;

import java.util.regex.Matcher;

public class GameMenu {

    Map map;
    private User currentUser;
    private GameController gameController = new GameController();

    public GameMenu(User host) {
        this.currentUser = host;
    }
    //todo: reset every governance in the end.
    public void run() throws IOException {
        map = (new MapMenu(null)).setUpMap();
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if(command.matches("map menu"))
                new MapMenu(this.map).run();
            else if(command.matches("shop menu")){
                new ShopMenu(currentUser).run();
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
            else
                System.out.println("invalid command");
        }
    }
}
