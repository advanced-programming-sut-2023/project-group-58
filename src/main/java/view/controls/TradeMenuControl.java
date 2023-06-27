package view.controls;

import controller.gameMenuControllers.TradeMenuController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Governance;
import model.TradeItem;
import model.User;
import view.LoginMenu;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradeMenuControl implements Initializable {
    private HashMap<Label , TradeItem> linkedRecentTrades = new HashMap<>();
    private TradeMenuController tradeMenuController;
    private static User currentUser;
    public Label currentGold;
    public Label message1, message2, message3, message4, message5, message6, message7;
    public Separator sep1, sep2, sep3, sep4, sep5, sep6, sep7;
    public static void setCurrentUser(User currentUser) {
        TradeMenuControl.currentUser = currentUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(currentGold != null)
            currentGold.setText("" + currentUser.getGovernance().getGold());
        tradeMenuController = new TradeMenuController(currentUser);
        if(message1 != null)
            pairLabelsWithTrades();
    }

    private void pairLabelsWithTrades() {
        String[] messages = tradeMenuController.popup().split("\n");
        for(int i = 0; i < messages.length; i++) {
            switch (i) {
                case 1:
                    message1.setVisible(true);
                    sep1.setVisible(true);
                    message1.setText(messages[i]);
                    linkedRecentTrades.put(message1, tradeFinderByMessage(messages[i]));
                    break;
                case 2:
                    message2.setVisible(true);
                    sep2.setVisible(true);
                    message2.setText(messages[i]);
                    linkedRecentTrades.put(message2, tradeFinderByMessage(messages[i]));
                    break;
                case 3:
                    message3.setVisible(true);
                    sep3.setVisible(true);
                    message3.setText(messages[i]);
                    linkedRecentTrades.put(message3, tradeFinderByMessage(messages[i]));
                    break;
                case 4:
                    message4.setVisible(true);
                    sep4.setVisible(true);
                    message4.setText(messages[i]);
                    linkedRecentTrades.put(message4, tradeFinderByMessage(messages[i]));
                    break;
                case 5:
                    message5.setVisible(true);
                    sep5.setVisible(true);
                    message5.setText(messages[i]);
                    linkedRecentTrades.put(message5, tradeFinderByMessage(messages[i]));
                    break;
                case 6:
                    message6.setVisible(true);
                    sep6.setVisible(true);
                    message6.setText(messages[i]);
                    linkedRecentTrades.put(message6, tradeFinderByMessage(messages[i]));
                    break;
                case 7:
                    message7.setVisible(true);
                    message7.setText(messages[i]);
                    linkedRecentTrades.put(message7, tradeFinderByMessage(messages[i]));
                    break;
                case 8:
                    return;
            }
        }
    }

    private TradeItem tradeFinderByMessage(String message) {
        String regex = " id: (?<wantedPart>.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        String id = matcher.group("wantedPart");
        for (TradeItem trade : Governance.getAllTrades()) {
            if(trade.getId().equals(id))
                return trade;
        }
        return null;
    }

    public void backToGame(MouseEvent mouseEvent) {
        //todo
    }

    public void enterShop(MouseEvent mouseEvent) throws IOException {
        ShopMenuControl.setCurentUser(currentUser);
        openAddress("/FXML/shopMenu.fxml");
    }

    private void openAddress(String address) throws IOException {
        URL url = ShopMenuControl.class.getResource(address);
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        //LoginMenu.getStage().setFullScreen(true);
        LoginMenu.getStage().show();
    }

    public void enterMassage(MouseEvent mouseEvent) throws IOException {
        switch (((Label)mouseEvent.getSource()).idProperty().get()) {
            case "message1" :
                setSeenAndOpenMessage(linkedRecentTrades.get(message1));
                break;
            case "message2" :
                setSeenAndOpenMessage(linkedRecentTrades.get(message2));
                break;
            case "message3" :
                setSeenAndOpenMessage(linkedRecentTrades.get(message3));
                break;
            case "message4" :
                setSeenAndOpenMessage(linkedRecentTrades.get(message4));
                break;
            case "message5" :
                setSeenAndOpenMessage(linkedRecentTrades.get(message5));
                break;
            case "message6" :
                setSeenAndOpenMessage(linkedRecentTrades.get(message6));
                break;
            case "message7" :
                setSeenAndOpenMessage(linkedRecentTrades.get(message7));
                break;
        }
    }

    private void setSeenAndOpenMessage(TradeItem tradeItem) throws IOException {
        if(currentUser.getUsername().equals(tradeItem.getOneWhoRequests().getUsername()))
            tradeItem.setSeenRequester(true);
        else
            tradeItem.setSeenAccepter(true);
        openAddress("/FXML/shopMenu.fxml");
    }
}
