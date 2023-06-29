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
    private final int TILE_SIZE = 150;
    private Stage primaryStage;
    private Pane root;
    ImageView buildingImageView = new ImageView();

    public void start(Stage primaryStage) {
        Pane root = new Pane();
        this.primaryStage = primaryStage;
        this.root = root;

        for (int i = 0; i < 50; i++)
            addTile(root, i);

        createSourceButton("/Images/hovel.png", 100, 200);
        createSourceButton("/Images/hovel.png", 450, 100);

        primaryStage.setScene(new Scene(root, 1530, 800));
        primaryStage.show();
    }

    private void createSourceButton(String address, int i, int j) {
        Button btn = new Button("");
        btn.setGraphic(new ImageView(new Image(GameControlTest.class.getResource(address).toExternalForm())));
        btn.setStyle("-fx-background-color: transparent");
        btn.setOnMouseEntered(event -> {
            createPicture(event,i,j);
            ((Pane) primaryStage.getScene().getRoot()).getChildren().add(buildingImageView);
        });

        root.getChildren().add(btn);
        btn.setLayoutX(i);
        btn.setLayoutY(j);
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

        buildingImageView.setLayoutY(ranges[1] - buildingImageView.getY());
        buildingImageView.setLayoutX(ranges[0] - buildingImageView.getX());
    }

    private void createPicture(MouseEvent event, int i, int j) {

        buildingImageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/hovel.png").toExternalForm()));
        buildingImageView.setFitWidth(TILE_SIZE);
        buildingImageView.setPreserveRatio(true);
        buildingImageView.setSmooth(true);
        buildingImageView.setCache(true);

        buildingImageView.setOnMouseDragged(this::onMouseDragged);
        buildingImageView.setOnMouseReleased(this::onMouseReleased);
        buildingImageView.setUserData(new double[]{event.getX(), event.getY()});

        buildingImageView.setX(i);
        buildingImageView.setY(j);
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
        int index = findTheNearestTile();
        addToTile(index);
        buildingImageView = null;
    }

    private int findTheNearestTile() {
        int[] coor = new int[2];
        double[] offset = (double[])buildingImageView.getUserData();
        double currentX = buildingImageView.getLayoutX();
        double currentY = buildingImageView.getLayoutY();
        //System.out.println(buildingImageView.getX() + " , " + buildingImageView.getLayoutX() + " , " + buildingImageView.getTranslateX());
        if (currentX  <= 20)
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

