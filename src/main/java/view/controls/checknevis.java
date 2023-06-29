package view.controls;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class checknevis{
    private final int GRID_SIZE = 3; // Number of rows/columns in the grid
    private final int PANE_SIZE = 190; // Size of each pane

    Pane[][] panes;
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);

        // Create a 3x3 grid of panes
//        panes = new Pane[GRID_SIZE][GRID_SIZE];
//        for (int row = 0; row < GRID_SIZE; row++) {
//            for (int col = 0; col < GRID_SIZE; col++) {
//                Pane pane = createPane();
//                panes[row][col] = pane;
//                root.getChildren().add(pane);
//                GridPane.setColumnIndex(pane,col);
//                GridPane.setRowIndex(pane,row);
//            }
//        }

        Scene scene = new Scene(root, 1530, 800);
        primaryStage.setScene(scene);
        addButton(primaryStage, root);
        primaryStage.show();
    }

    private Pane createPane() {
        Pane pane = new Pane();
        pane.setPrefSize(PANE_SIZE, PANE_SIZE);
        pane.setStyle("-fx-border-color: black;");
        return pane;
    }

    private ImageView createPicture() {
        Image pictureImage = new Image(GameControlTest.class.getResource("/Images/hovel.png").toExternalForm());
        ImageView picture = new ImageView(pictureImage);
        picture.setFitWidth(PANE_SIZE);
        picture.setFitHeight(PANE_SIZE);
        return picture;
    }

    private Point2D getNearestPaneCenter(ImageView picture, Pane[][] panes) {
        if(picture == null) return null;
        double minDistance = Double.MAX_VALUE;
        Point2D pictureCenter = new Point2D(picture.getFitWidth() / 2, picture.getFitHeight() / 2);
        Point2D nearestPaneCenter = null;

        for (Pane[] paneRow : panes) {
            for (Pane pane : paneRow) {
                Point2D paneCenter = new Point2D(pane.getLayoutX() + pane.getWidth() / 2,
                        pane.getLayoutY() + pane.getHeight() / 2);
                double distance = pictureCenter.distance(paneCenter);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPaneCenter = paneCenter;
                }
            }
        }

        return nearestPaneCenter;
    }

















    ImageView buildingImageView = new ImageView();
    private void addButton(Stage primaryStage, Pane root) {
        Button btn = new Button("Click Me");
        btn.setOnMouseEntered(event -> {


            // Create a new building image view
            buildingImageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/hovel.png").toExternalForm()));

            // Add mouse event handlers for dragging and dropping
            buildingImageView.setOnMouseDragged(this::onMouseDragged);
            buildingImageView.setOnMouseReleased(this::onMouseReleased);
            buildingImageView.setUserData(new double[]{event.getX(), event.getY()});

            // Add the building to the pane
            ((GridPane) primaryStage.getScene().getRoot()).getChildren().add(buildingImageView);
            buildingImageView.setX(50);
            buildingImageView.setY(700);
        });
        btn.setLayoutX(50);
        btn.setLayoutY(100);
        root.getChildren().add(btn);
    }

    private void onMousePressed(MouseEvent event) {
        // Save the initial position of the building
        buildingImageView.setUserData(new double[]{event.getX(), event.getY()});
    }

    private void onMouseDragged(MouseEvent event) {
        if(buildingImageView == null)
            return;
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



        //Point2D nearestPaneCenter = getNearestPaneCenter(buildingImageView, panes);
//        if(nearestPaneCenter == null || buildingImageView == null) return;
//        buildingImageView.setLayoutX(nearestPaneCenter.getX() - buildingImageView.getFitWidth() / 2);
//        buildingImageView.setLayoutY(nearestPaneCenter.getY() - buildingImageView.getFitHeight() / 2);
        buildingImageView = null;

    }







}

