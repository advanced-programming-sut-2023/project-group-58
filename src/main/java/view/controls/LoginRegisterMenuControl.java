package view.controls;

import controller.RegisterMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.User;
import view.CaptchaMenu;
import view.LoginMenu;
import view.ScanMatch;
import view.enums.Commands;
import view.enums.ProfisterControllerOut;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

public class LoginRegisterMenuControl implements Initializable {

    public TextField username;
    public PasswordField password;
    public TextField nickname;
    public TextField email;
    public Label usernameErrorHandler;
    private RegisterMenuController registerMenuController = new RegisterMenuController();

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

    public ProfisterControllerOut checkUsername(String username) {
        if (username.length() == 0) return ProfisterControllerOut.EMPTY_FIELDS;
        if (username.matches(".*[\\W+].*")) return ProfisterControllerOut.USERNAME_INVALID_FORMAT;
        return ProfisterControllerOut.VALID;
    }

    private boolean isUsernameOrEmailAlreadyTaken(String data) {
        for (User user : User.getUsers()) {
            if (user.getUsername().equals(data)) return true;
            else if (user.getEmail().equals(data)) return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (username != null)
            username.textProperty().addListener((observable, oldText, newText) -> {
                if (checkUsername(username.getText()) != null)
                    usernameErrorHandler.setText(checkUsername(username.getText()).getContent());

                if (checkUsername(username.getText()).equals(ProfisterControllerOut.VALID) || username.getText().equals(""))
                    usernameErrorHandler.setText("");

                String tempResult = registerMenuController.usernameExist(username.getText());
                if (!tempResult.equals(ProfisterControllerOut.VALID.getContent()))
                    usernameErrorHandler.setText(tempResult);
            });
    }
}
