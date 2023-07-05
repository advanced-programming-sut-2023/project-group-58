package view.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import view.LoginMenu;
import view.controls.changers.*;

import java.io.IOException;
import java.net.URL;

public class ProfileControl {
    public Label username;
    public Label nickname;
    public Label email;
    public Label slogan;
    public static ProfileControl profileControl;
    public String username1 = "ali";
    public  String nickname1 = "king";
    public  String email1 = "ali@king.com";
    public  String slogan1 = "death is better than life";

    public ProfileControl() {
        profileControl = this;
    }

    @FXML
    public void initialize(){
        username.setText(username1);
        nickname.setText(nickname1);
        email.setText(email1);
        slogan.setText(slogan1);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/mainMenu.fxml");
        BorderPane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        LoginMenu.getStage().show();
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/firstMenu.fxml");
        BorderPane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        LoginMenu.getStage().show();
    }
    public void usernameChange(MouseEvent mouseEvent) throws IOException {

        URL url = LoginMenu.class.getResource("/FXML/changers/username.fxml");
        BorderPane pane = FXMLLoader.load(url);
        UsernameControl.pane = pane;
        UsernameControl.enterCaptcha();
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        LoginMenu.getStage().showAndWait();
        initialize();
    }
    public void passwordChange(MouseEvent mouseEvent) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/changers/password.fxml");
        BorderPane pane = FXMLLoader.load(url);
       Password.pane = pane;
        Password.enterCaptcha();
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        LoginMenu.getStage().showAndWait();
        initialize();
    }
    public void nicknameChange(MouseEvent mouseEvent) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/changers/nickname.fxml");
        BorderPane pane = FXMLLoader.load(url);
        NicknameControl.pane = pane;
        NicknameControl.enterCaptcha();
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        LoginMenu.getStage().showAndWait();
        initialize();
    }
    public void emailChange(MouseEvent mouseEvent) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/changers/email.fxml");
        BorderPane pane = FXMLLoader.load(url);
        EmailControl.pane = pane;
        EmailControl.enterCaptcha();
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        LoginMenu.getStage().showAndWait();
        initialize();
    }
    public void sloganChange(MouseEvent mouseEvent) throws IOException {
        openMenu("/FXML/changers/slogan.fxml");
        URL url = LoginMenu.class.getResource("/FXML/changers/slogan.fxml");
        BorderPane pane = FXMLLoader.load(url);
        SloganControl.pane = pane;
        SloganControl.enterCaptcha();
        Scene scene = new Scene(pane);
        LoginMenu.getStage().setScene(scene);
        LoginMenu.getStage().showAndWait();
        initialize();
    }
    public static void openMenu(String s) throws IOException {

    }
}
