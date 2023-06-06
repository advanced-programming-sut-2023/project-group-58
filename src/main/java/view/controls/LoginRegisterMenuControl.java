package view.controls;

import controller.CommonController;
import controller.RegisterMenuController;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.User;
import view.LoginMenu;
import view.enums.ProfisterControllerOut;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginRegisterMenuControl implements Initializable {

    public TextField username;
    public PasswordField password;
    public TextField nickname;
    public TextField email;
    public Label usernameErrorHandler;
    public Label passwordErrorHandler;
    public CheckBox sloganCheckBox;
    public TextField sloganTextField;
    public Button randomSlogan;
    public ListView listView;
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

                if (checkUsername(username.getText()).equals(ProfisterControllerOut.VALID))
                    usernameErrorHandler.setText("");

                String tempResult = registerMenuController.usernameExist(username.getText());
                if (!tempResult.equals(ProfisterControllerOut.VALID.getContent()))
                    usernameErrorHandler.setText(tempResult);

                if (username.getText().equals(""))
                    usernameErrorHandler.setText("");
            });

        if (password != null)
            password.textProperty().addListener((observable, oldText, newText) -> {
                if (CommonController.checkPasswordFormat(password.getText()) != ProfisterControllerOut.VALID)
                    passwordErrorHandler.setText(CommonController.checkPasswordFormat(password.getText()).getContent());
            });

        if (sloganCheckBox != null)
            sloganCheckBox.setOnAction(event -> {
                sloganTextField.setVisible(sloganCheckBox.isSelected());
                randomSlogan.setVisible(sloganCheckBox.isSelected());
            });

        if(listView != null)
            listView.setOnMouseClicked(event -> {
                String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
                sloganTextField.setText(selectedItem);
            });
    }

    public void chooseRandomSlogan(MouseEvent mouseEvent) throws IOException {
        int pickSlogan = (int) (5 * Math.random());
        String newSlogan = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/DataBase/slogans.txt")).get(pickSlogan);
        while(newSlogan.equals(sloganTextField.getText())) {
            pickSlogan = (int) (5 * Math.random());
            newSlogan = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/DataBase/slogans.txt")).get(pickSlogan);
        }
        sloganTextField.setText(newSlogan);
    }

    public void chooseRandomPassword(MouseEvent mouseEvent) {
       String newPassword = registerMenuController.randomPasswordGenerator();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Random password");
        alert.setContentText("Here's a random one: " + newPassword + "\nWould you like to set it as your password?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            password.setText(newPassword);
        }
    }

    public void showList(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if(listView.isVisible()) {
                listView.setVisible(false);
                return;
            }
            listView.setVisible(true);
            ArrayList<String> slogans = registerMenuController.getTop10Slogans();
            for (String slogan : slogans) {
                listView.getItems().add(slogan);
            }
        }

    }
}
