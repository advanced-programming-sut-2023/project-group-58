package view.controls;

import controller.LoginMenuController;
import controller.gameMenuControllers.TradeMenuController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Governance;
import model.ResourceEnum;
import model.TradeItem;
import model.User;
import view.GetStyle;
import view.LoginMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradeMenuControl implements Initializable {
    public ScrollPane scrollPane;
    private HashMap<Label , TradeItem> linkedRecentTrades = new HashMap<>();
    private TradeMenuController tradeMenuController;
    private static User currentUser;
    public Label currentGold;
    public Label message1, message2, message3, message4, message5, message6, message7;
    public Separator sep1, sep2, sep3, sep4, sep5, sep6, sep7;
    private Label tradeItem = new Label();
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
        if(scrollPane != null) {
            try {
                loadPlayers();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

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
        openAddress("/FXML/tradeItem.fxml");
    }

    public void backToTrade() throws IOException {
        openAddress("/FXML/tradeMenu.fxml");
    }

    public void newRequest() throws IOException {
        openAddress("/FXML/newTrade.fxml");
    }

    private void loadPlayers() throws FileNotFoundException {
        LoginMenuController.extractUserData();
        VBox vBox = new VBox();
        Label label;
        Separator separator;
        vBox.setStyle("-fx-background-color: transparent");
        vBox.setSpacing(5);
        label = new Label("Choose a user to trade with:");
        label.setStyle("-fx-font-family: x fantasy; -fx-text-fill: EEE2BBFF; -fx-padding: 45 0 0 30; -fx-font-weight: bold; -fx-font-size: 15");
        vBox.getChildren().add(label);
        for (User user : User.getUsers()) {
            if(currentUser.getUsername().equals(user.getUsername())) continue;
            label = new Label();
            label.getStylesheets().add(GetStyle.class.getResource("/CSS/shopAndTrade.css").toExternalForm());
            label.getStyleClass().add("message");
            label.setStyle("-fx-padding: 25 0 0 45");
            label.maxWidth(960);
            label.setText("::uSeRnAme : " + user.getUsername() + "     ::nIcKnaME : " + user.getNickname());
            label.setOnMouseClicked(mouseEvent -> newRequestWithUser(user));
            separator = new Separator();
            separator.getStylesheets().add(GetStyle.class.getResource("/CSS/shopAndTrade.css").toExternalForm());
            separator.getStyleClass().add("my-separator-class");
            separator.setStyle("-fx-padding: 5 0 0 47");
            separator.setScaleX(1.05);
            vBox.getChildren().addAll(label,separator);
        }
        scrollPane.setContent(vBox);
    }

    private void newRequestWithUser(User accepter) {
        //حواست باشه کاربری بک حتما باید فرق کنه
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: transparent");
        Label label = new Label("Trade Reqest");
        label.setStyle("-fx-font-family: 'Old English Text MT'; -fx-font-size: 30; -fx-text-fill: #fff300; -fx-padding: 45 0 0 400");
        vBox.getChildren().add(label);
        label = new Label("With: " + accepter.getUsername());
        label.setStyle("-fx-font-family: x fantasy; -fx-text-fill: EEE2BBFF; -fx-padding: 15 0 0 450; -fx-font-size: 20");
        vBox.getChildren().add(label);
        label = new Label("Id: " + currentUser.getUsername() + currentUser.getGovernance().getUserTrades().size());
        label.setStyle("-fx-font-family: x fantasy; -fx-text-fill: EEE2BBFF; -fx-padding: 15 0 0 450; -fx-font-size: 20");
        vBox.getChildren().add(label);
        tradeItem.setText("choose item");
        tradeItem.setStyle("-fx-font-family: x fantasy; -fx-text-fill: EEE2BBFF; -fx-padding: 15 0 0 450; -fx-font-size: 20");


        HBox hBox = new HBox();
        ListView listView = new ListView<>();
        listView.setPrefHeight(120);
        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                                    @Override
                                    public ListCell<String> call(ListView<String> list) {
                                        return new ListCell<String>() {
                                            @Override
                                            protected void updateItem(String item, boolean empty) {
                                                super.updateItem(item, empty);

                                                if (item == null || empty) {
                                                    setText(null);
                                                    setStyle("-fx-background-color: transparent;");
                                                } else {
                                                    setText(item);
                                                    int index = getIndex();
                                                    if (index % 2 == 0) {
                                                        setStyle("-fx-background-color: brown; -fx-text-fill: white;");
                                                    } else {
                                                        setStyle("-fx-background-color: white; -fx-text-fill: brown;");
                                                    }
                                                }
                                            }
                                        };
                                    }
                                });




        listView.setStyle("-fx-background-radius: 20; -fx-max-width: 110;");
        for (ResourceEnum resourceEnum : ResourceEnum.values()) {
            if(resourceEnum.equals(ResourceEnum.NULL) || resourceEnum.equals(ResourceEnum.HORSEANDBOW)) continue;
            listView.getItems().add(resourceEnum.getName());
        }
        listView.setOnMouseClicked(event -> {
            String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
            tradeItem.setText(selectedItem);
            listView.setVisible(false);
        });

        tradeItem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                listView.setVisible(!listView.isVisible());
            }
        });


        vBox.getChildren().addAll(tradeItem,listView);
        scrollPane.setContent(vBox);
    }

}
