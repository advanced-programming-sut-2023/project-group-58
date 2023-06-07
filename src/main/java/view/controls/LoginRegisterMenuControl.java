package view.controls;

import controller.CommonController;
import controller.RegisterMenuController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.User;
import view.GetStyle;
import view.LoginMenu;
import view.enums.ProfisterControllerOut;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Button eye;
    public HBox TheHbox;
    public Label nicknameErrorHandler;
    public Label emailErrorHandler;
    public Label sloganErrorHandler;
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

    public void emptyTheOne(TextField main, Label error) {
        if (main != null)
            main.textProperty().addListener((observable, oldText, newText) -> {
                if (main.getText().length() != 0)
                    error.setText("");
            });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        emptyTheOne(username, usernameErrorHandler);
        emptyTheOne(email, emailErrorHandler);
        emptyTheOne(nickname, nicknameErrorHandler);
        emptyTheOne(password, passwordErrorHandler);


        if (sloganCheckBox != null && sloganTextField != null)
            sloganTextField.textProperty().addListener((observable, oldText, newText) -> {
                if (sloganCheckBox.isSelected() || (sloganTextField != null && sloganTextField.getText().length() != 0))
                    sloganErrorHandler.setText("");
            });

        if (nickname != null)
            nickname.textProperty().addListener((observable, oldText, newText) -> {
                if (nickname != null && nickname.getText().length() != 0)
                    nickname.setStyle("-fx-font-size: 25");
                else {
                    assert nickname != null;
                    nickname.setStyle("-fx-font-size: 15");
                }
            });

        if (username != null)
            username.textProperty().addListener((observable, oldText, newText) -> {
                if (checkUsername(username.getText()) != null) {
                    usernameErrorHandler.setText(checkUsername(username.getText()).getContent());
                    usernameErrorHandler.setStyle("-fx-text-fill: orange");
                }

                if (checkUsername(username.getText()).equals(ProfisterControllerOut.VALID))
                    usernameErrorHandler.setText("");

                String tempResult = registerMenuController.usernameExist(username.getText());
                if (!tempResult.equals(ProfisterControllerOut.VALID.getContent())) {
                    usernameErrorHandler.setText(tempResult);
                    usernameErrorHandler.setStyle("-fx-text-fill: orange");
                }

                if (username.getText().equals(""))
                    usernameErrorHandler.setText("");
            });


        if (password != null)
            password.textProperty().addListener((observable, oldText, newText) -> {
                if (CommonController.checkPasswordFormat(password.getText()) != ProfisterControllerOut.VALID)
                    passwordErrorHandler.setText(CommonController.checkPasswordFormat(password.getText()).getContent());
                else
                    passwordErrorHandler.setText("");
            });


//        if (TheHbox != null && TheHbox.getChildren() != null && TheHbox.getChildren().get(3) != null) {
//            if (TheHbox.getChildren().get(3) instanceof TextField) {
//                ((TextField) TheHbox.getChildren().get(3)).textProperty().addListener((observable, oldText, newText) -> {
//                    if (CommonController.checkPasswordFormat(((TextField) TheHbox.getChildren().get(3)).getText()) != ProfisterControllerOut.VALID) {
//                        passwordErrorHandler.setText(CommonController.checkPasswordFormat(((TextField) TheHbox.getChildren().get(3)).getText()).getContent());
//                        passwordErrorHandler.setStyle("-fx-text-fill: orange");
//                    } else
//                        passwordErrorHandler.setText("");
//                });
//            }
//            else {
//                ((PasswordField) TheHbox.getChildren().get(3)).textProperty().addListener((observable, oldText, newText) -> {
//                    if (CommonController.checkPasswordFormat(((PasswordField) TheHbox.getChildren().get(3)).getText()) != ProfisterControllerOut.VALID) {
//                        passwordErrorHandler.setText(CommonController.checkPasswordFormat(((PasswordField) TheHbox.getChildren().get(3)).getText()).getContent());
//                        passwordErrorHandler.setStyle("-fx-text-fill: orange");
//                    } else
//                        passwordErrorHandler.setText("");
//                });
//            }
//        }


        if (TheHbox != null && TheHbox.getChildren() != null && TheHbox.getChildren().get(3) != null) {

                ((TextInputControl) TheHbox.getChildren().get(3)).textProperty().addListener((observable, oldText, newText) -> {
                    password.setText(((TextField) TheHbox.getChildren().get(3)).getText());
                });

        }

        if (sloganCheckBox != null)
            sloganCheckBox.setOnAction(event -> {
                sloganTextField.setVisible(sloganCheckBox.isSelected());
                randomSlogan.setVisible(sloganCheckBox.isSelected());
            });

        if (listView != null)
            listView.setOnMouseClicked(event -> {
                String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
                sloganTextField.setText(selectedItem);
            });

        if (eye != null)
            eye.setOnMouseClicked(event -> {
                if (TheHbox.getChildren().get(3) instanceof PasswordField) {
                    String saving = ((PasswordField) TheHbox.getChildren().get(3)).getText();
                    TheHbox.getChildren().set(3, GetStyle.textField(""));
                    TheHbox.getChildren().get(3).setStyle("-fx-fill: darkred; -fx-prompt-text-fill: darkred");
                    ((TextField) TheHbox.getChildren().get(3)).setText(saving);
                    password.setText(saving);

                } else {
                    String saving = ((TextField) TheHbox.getChildren().get(3)).getText();
                    TheHbox.getChildren().set(3, GetStyle.passwordField(""));
                    ((PasswordField) TheHbox.getChildren().get(3)).setText(saving);
                    password.setText(saving);
                }
            });


    }

    public void chooseRandomSlogan(MouseEvent mouseEvent) throws IOException {
        int pickSlogan = (int) (5 * Math.random());
        String newSlogan = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/DataBase/slogans.txt")).get(pickSlogan);
        while (newSlogan.equals(sloganTextField.getText())) {
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
            if (listView.isVisible()) {
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

    public void validateAndRegister(MouseEvent mouseEvent) {
        boolean dead = false;

        if (username.getText() == null || username.getText().length() == 0) {
            usernameErrorHandler.setText("Username cannot be empty!");
            usernameErrorHandler.setStyle("-fx-text-fill: red");
            dead = true;
        }

        if (password.getText() == null || password.getText().length() == 0) {
            passwordErrorHandler.setText("Password cannot be empty!");
            passwordErrorHandler.setStyle("-fx-text-fill: red");
            dead = true;
        }

        if (nickname.getText() == null || nickname.getText().length() == 0) {
            nicknameErrorHandler.setText("Nickname cannot be empty!");
            nicknameErrorHandler.setStyle("-fx-text-fill: red");
            dead = true;
        }

        if (email.getText() == null || email.getText().length() == 0) {
            emailErrorHandler.setText("Email cannot be empty!");
            emailErrorHandler.setStyle("-fx-text-fill: red");
            dead = true;
        }

        if (sloganCheckBox.getText() != null && sloganCheckBox.isSelected() &&
                (sloganTextField == null || sloganTextField.getText().length() == 0)) {
            sloganErrorHandler.setText("Huh! Now that you checked the slogan box, you HAVE to choose a slogan!");
            sloganErrorHandler.setStyle("-fx-text-fill: red");
            dead = true;
        }

        if (isUsernameOrEmailAlreadyTaken(email.getText())) {
            emailErrorHandler.setText("This email is already used");
            emailErrorHandler.setStyle("-fx-text-fill: red");
            dead = true;
        }

        String regex = "^[\\w|.]+@[\\w|.]+\\.[\\w|.]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.getText());
        if (!matcher.find()) {
            emailErrorHandler.setText("Email format is not correct!");
            emailErrorHandler.setStyle("-fx-text-fill: red");
            dead = true;
        }

        if (dead) return;
        //should now go to the other stuff
    }
}
