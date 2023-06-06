package view;
import controller.LoginMenuControl;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class LoginMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        URL url = LoginMenu.class.getResource("/FXML/firstMenu.fxml");
        BorderPane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();



//        BorderPane borderPane = new BorderPane();
//        borderPane.getStylesheets().add(getClass().getResource("/CSS/login.css").toExternalForm());
//        borderPane.resize(1530, 800);
//        ImageView imageView = new ImageView(new Image(getClass().getResource("/Images/login.jpg").toExternalForm(), 1530, 960, false, false));
//        imageView.setPreserveRatio(true);
//        borderPane.getChildren().add(imageView);
//        VBox vBox = new VBox();
//        vBox.setAlignment(Pos.CENTER);
//        vBox.setSpacing(10);
//        addToVbox(vBox);
//
//        //exit button for our use only
//        Button closeButton = new Button("Close");
//        closeButton.setOnAction(event -> {
//            stage.close();
//        });
//        vBox.getChildren().add(closeButton);
//        vBox.getChildren().get(vBox.getChildren().size() - 1).setLayoutY(0);
//        vBox.getChildren().get(vBox.getChildren().size() - 1).setLayoutX(0);
//
//        borderPane.setCenter(vBox);
//
//        imageView.fitWidthProperty().bind(stage.widthProperty());
//        stage.setScene(new Scene(borderPane));
//        stage.setFullScreen(true);
//        stage.show();
    }
    private void addToVbox(VBox vBox){
        LoginMenuControl loginMenuControl = new LoginMenuControl();
        Label label = GetStyle.label("Welcome toStrongHold!");
        TextField username = GetStyle.textField("username");
        username.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> arg0,
                            Boolean oldPropertyValue, Boolean newPropertyValue) {
                        if (newPropertyValue) {
                            username.setText("mine");
                            // Clearing message if any
//                            actiontarget.setText("");
//                            // Hiding the error message
//                            usernameValidator.hide();
                        }
                    }
                });
        TextField nickname = GetStyle.textField("nickname");
        TextField email = GetStyle.textField("email@email.com");
        PasswordField password = GetStyle.passwordField("password");
        PasswordField rePassword = GetStyle.passwordField("re-enter password");
        vBox.getChildren().addAll(label, username);
        vBox.getChildren().get(1).setLayoutY(100);
        vBox.getChildren().get(0).setLayoutY(200);
    }
}
