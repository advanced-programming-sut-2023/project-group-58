package view;
import controller.LoginMenuControl;
import controller.RegisterMenuController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.enums.ProfisterControllerOut;

import java.net.URL;

public class LoginMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
        borderPane.resize(1530, 800);
        ImageView imageView = new ImageView(new Image(getClass().getResource("/image/login.jpg").toExternalForm(), 1530, 960, false, false));
        borderPane.getChildren().add(imageView);
        Stage stage = new Stage();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        addToVbox(vBox);
        borderPane.setCenter(vBox);
        imageView.fitWidthProperty().bind(stage.widthProperty());
        stage.setScene(new Scene(borderPane));
        stage.setFullScreen(true);
        stage.show();
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
                            // Clearing message if any
                            actiontarget.setText("");
                            // Hiding the error message
                            usernameValidator.hide();
                        }
                    }
                });
        TextField nickname = GetStyle.textField("nickname");
        TextField email = GetStyle.textField("email@email.com");
        PasswordField password = GetStyle.passwordField("password");
        PasswordField rePassword = GetStyle.passwordField("re-enter password");
        vBox.getChildren().addAll(label, username);
    }
}
