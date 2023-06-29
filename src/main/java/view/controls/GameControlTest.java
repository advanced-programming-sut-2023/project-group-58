package view.controls;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameControlTest {
    private final int GRID_SIZE = 3; // Number of rows/columns in the grid
    private final int TILE_SIZE = 150; // Size of each pane

    Pane[][] panes;

    public void start(Stage primaryStage) {
        Button btn = new Button("Click Me");
        btn.setOnMouseEntered(event -> {

            // Create a new building image view
            buildingImageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/hovel.png").toExternalForm()));
            buildingImageView.setFitWidth(TILE_SIZE);
            buildingImageView.setPreserveRatio(true);
            buildingImageView.setSmooth(true);
            buildingImageView.setCache(true);


            // Add mouse event handlers for dragging and dropping
            buildingImageView.setOnMouseDragged(this::onMouseDragged);
            buildingImageView.setOnMouseReleased(this::onMouseReleased);
            buildingImageView.setUserData(new double[]{event.getX(), event.getY()});

            // Add the building to the pane
            ((Pane) primaryStage.getScene().getRoot()).getChildren().add(buildingImageView);
            buildingImageView.setX(50);
            buildingImageView.setY(700);
        });

        Pane root = new Pane();
        for (int i = 0; i < 50; i++)
            addTile(root, i);

        // Set up the scene
        root.getChildren().add(btn);


        btn.setLayoutX(250);
        btn.setLayoutY(200);
        //buildingImageView.setUserData(new double[]{btn.getTranslateX(), btn.getTranslateY()});
        primaryStage.setScene(new Scene(root, 1530, 800));
        primaryStage.show();
    }

    private void addTile(Pane root, int index) {
        Pane section = new Pane();
        section.setStyle("-fx-border-color: black;");
        section.setPrefHeight(TILE_SIZE);
        section.setPrefWidth(TILE_SIZE);
        root.getChildren().add(section);
        double[] ranges = new double[4];
        ranges[0] = ((index % 10) * TILE_SIZE);
        ranges[1] = ranges[0] + TILE_SIZE;
        ranges[2] = (index / 10) * TILE_SIZE;
        ranges[3] = ranges[2] + TILE_SIZE;
        root.getChildren().get(index).setLayoutX(ranges[0]);
        root.getChildren().get(index).setLayoutY(ranges[2]);
    }

    private void addToTile(int index) {

        double[] ranges = new double[2];
        ranges[0] = ((index % 10) * TILE_SIZE);
        ranges[1] = (index / 10) * TILE_SIZE;

        System.out.println("this is the x: " + buildingImageView.getLayoutX() + " and this is were we put it: " +
                ranges[0]);

        buildingImageView.setLayoutY(ranges[1] - buildingImageView.getY());
        buildingImageView.setLayoutX(ranges[0] - buildingImageView.getX());

    }


    private Pane createPane() {
        Pane pane = new Pane();
        pane.setPrefSize(TILE_SIZE, TILE_SIZE);
        pane.setStyle("-fx-border-color: black;");
        return pane;
    }

    private ImageView createPicture() {
        Image pictureImage = new Image(GameControlTest.class.getResource("/Images/hovel.png").toExternalForm());
        ImageView picture = new ImageView(pictureImage);
        picture.setFitWidth(TILE_SIZE);
        picture.setFitHeight(TILE_SIZE);
        return picture;
    }

    private Point2D getNearestPaneCenter(ImageView picture, Pane[][] panes) {
        if (picture == null) return null;
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

    private void onMouseDragged(MouseEvent event) {
        if (buildingImageView == null)
            return;
        // Update the position of the building while dragging
        double[] initialPosition = (double[]) buildingImageView.getUserData();
        buildingImageView.relocate(event.getScreenX() - initialPosition[0], event.getScreenY() - initialPosition[1]);
    }

    private void onMouseReleased(MouseEvent event) {
        if (buildingImageView == null) return;
        buildingImageView.setOnMouseDragged(null);
        buildingImageView.setOnMouseReleased(null);
        placeBuildingOnATTile();
        buildingImageView = null;
    }

    private void placeBuildingOnATTile() {
        int index = findTheNearestTile();
        System.out.println("index found: " + index);
        addToTile(index);
    }

    private int findTheNearestTile() {
        int[] coor = new int[2];
        double currentX = buildingImageView.getLayoutX();
        double currentY = buildingImageView.getLayoutY();
        if (currentX <= 20)
            coor[0] = 0;
        else {
            if (currentX >= 1225)
                coor[0] = 9;
            else
                coor[0] = (int) (((currentX - 20) / TILE_SIZE) % 10 + 1);
        }

        currentY = currentY * -1;
        if(currentY <= 160)
            coor[1] = 4;
        else {
            if(currentY >= 605)
                coor[1] = 0;
            else
                coor[1] = (int) (((605 - currentY) / TILE_SIZE) + 1);
        }
        return coor[0] + coor[1] * 10;
    }
}

