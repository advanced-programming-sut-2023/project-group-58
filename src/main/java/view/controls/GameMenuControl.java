package view.controls;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameMenuControl {
    private ImageView buildingImageView;

    public void start(Stage primaryStage) {

        Button btn = new Button("Click Me");
        btn.setOnMouseEntered(event -> {


            // Create a new building image view
            buildingImageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/hovel.png").toExternalForm()));

            // Add mouse event handlers for dragging and dropping
            buildingImageView.setOnMouseDragged(this::onMouseDragged);
            buildingImageView.setOnMouseReleased(this::onMouseReleased);
            buildingImageView.setUserData(new double[]{event.getX(), event.getY()});

            // Add the building to the pane
            ((AnchorPane) primaryStage.getScene().getRoot()).getChildren().add(buildingImageView);
            buildingImageView.setX(50);
            buildingImageView.setY(700);
        });

        // Set up the scene
        AnchorPane root = new AnchorPane(btn);
        btn.setLayoutX(50);
        btn.setLayoutY(700);
        //buildingImageView.setUserData(new double[]{btn.getTranslateX(), btn.getTranslateY()});
        primaryStage.setScene(new Scene(root, 1530, 800));
        primaryStage.show();
    }

    private void onMousePressed(MouseEvent event) {
        // Save the initial position of the building
        buildingImageView.setUserData(new double[]{event.getX(), event.getY()});
    }

    private void onMouseDragged(MouseEvent event) {
        if(buildingImageView == null) return;
        // Update the position of the building while dragging
        double[] initialPosition = (double[]) buildingImageView.getUserData();
        buildingImageView.relocate(event.getScreenX() - initialPosition[0], event.getScreenY() - initialPosition[1]);
    }

    private void onMouseReleased(MouseEvent event) {
        // Clean up the building when dropped
        //buildingImageView.setOnMousePressed(null);
        if(buildingImageView == null) return;
        buildingImageView.setOnMouseDragged(null);
        buildingImageView.setOnMouseReleased(null);
        buildingImageView = null;
    }

}