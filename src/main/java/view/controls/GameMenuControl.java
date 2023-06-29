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
        btn.setOnAction(event -> {
            // Create a new building image view
            buildingImageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/hovel.png").toExternalForm()));

            // Add mouse event handlers for dragging and dropping
            buildingImageView.setOnMousePressed(this::onMousePressed);
            buildingImageView.setOnMouseDragged(this::onMouseDragged);
            buildingImageView.setOnMouseReleased(this::onMouseReleased);

            // Add the building to the pane
            ((AnchorPane) primaryStage.getScene().getRoot()).getChildren().add(buildingImageView);
        });

        // Setup the scene
        AnchorPane root = new AnchorPane(btn);
        primaryStage.setScene(new Scene(root, 1530, 800));
        primaryStage.show();
    }

    private void onMousePressed(MouseEvent event) {
        // Save the initial position of the building
        buildingImageView.setUserData(new double[]{event.getX(), event.getY()});
    }

    private void onMouseDragged(MouseEvent event) {
        // Update the position of the building while dragging
        double[] initialPosition = (double[]) buildingImageView.getUserData();
        buildingImageView.relocate(event.getScreenX() - initialPosition[0], event.getScreenY() - initialPosition[1]);
    }

    private void onMouseReleased(MouseEvent event) {
        // Clean up the building when dropped
        buildingImageView.setOnMousePressed(null);
        buildingImageView.setOnMouseDragged(null);
        buildingImageView.setOnMouseReleased(null);
        buildingImageView = null;
    }

}
