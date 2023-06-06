package view.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.LoginMenu;

import java.io.IOException;
import java.net.URL;

public class LoginRegisterMenuControl {

    public TextField username;
    public PasswordField password;

    public void login(MouseEvent mouseEvent) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/loginMenu.fxml");
        BorderPane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        LoginMenu.getStage().setFullScreen(true);
        LoginMenu.getStage().show();
    }

    public void register(MouseEvent mouseEvent) {
    }

    public void exit(MouseEvent mouseEvent) {
        LoginMenu.getStage().close();
    }

    public void forgotpass(MouseEvent mouseEvent) {

    }

    public void back(MouseEvent mouseEvent) throws Exception {
    }
}
