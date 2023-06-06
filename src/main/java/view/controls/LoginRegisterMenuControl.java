package view.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import view.LoginMenu;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class LoginRegisterMenuControl {

    public TextField username;
    public PasswordField password;
    public TextField nickname;
    public TextField email;

    public void login() throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/loginMenu.fxml");
        BorderPane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        //LoginMenu.getStage().setFullScreen(true);
        LoginMenu.getStage().show();
    }

    public void register() throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/registerMenu.fxml");
        BorderPane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        //LoginMenu.getStage().setFullScreen(true);
        LoginMenu.getStage().show();
    }

    public void exit(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("Exit");
        alert.setContentText("Are you sure you want to leave?");

        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            javafx.application.Platform.exit();
        }
    }

    public void forgotpass(MouseEvent mouseEvent) {

    }

    public void back(MouseEvent mouseEvent) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/firstMenu.fxml");
        BorderPane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        LoginMenu.getStage().show();
    }

}
