package view.controls;
import controller.MapMenuController;
import controller.gameMenuControllers.GameController;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import model.Map;
import model.Tile;
import model.User;
import model.buildings.Building;
import model.buildings.BuildingEnum;
import view.GetStyle;

import java.util.HashMap;

public class GameControlTest {
    private final int TILE_SIZE = 150;
    private Stage primaryStage;
    private Pane root;
    private User currentPlayer;
    private ImageView buildingImageView = new ImageView();
    private HashMap<ImageView , Building> buildings = new HashMap<>();

    private static final double SCALE_DELTA = 1.1;
    private final Scale scaleTransform = new Scale(1, 1);


    private VBox popularityFactorsBar = new VBox();
    private HBox mainBar = new HBox();
    private VBox changeFactorsBar = new VBox();
    private VBox barBook = new VBox();
    private ImageView barScene = new ImageView();

    private Button[] houses = new Button[4];
    private BuildingType currentSet = BuildingType.FOOD_PROCESSING;

    private int xCenter = 50;
    private int yCenter = 50;
    private Pane[][] panes = new Pane[10][5];
    private HashMap<Pane, Tile> linkedHouses = new HashMap<>();
    private Map map;
    private GameController gameController;

    public void start(Stage primaryStage, User currentPlayer) {
        this.currentPlayer = currentPlayer;
        MapMenuController mapMenuController = new MapMenuController();
        mapMenuController.setUpATemplate();
        map = mapMenuController.selectedMap;
        this.gameController = new GameController(currentPlayer, map);

        Pane root = new Pane();
        root.getTransforms().add(scaleTransform);
        this.primaryStage = primaryStage;
        this.root = root;

        for (int i = 0; i < 50; i++)
            addTile(root, i);

        addBar(root);
        createGamePane(currentSet);

        Label navar2 = new Label();
        navar2.setPrefHeight(633);
        navar2.setPrefWidth(30);
        navar2.setStyle("-fx-background-color: #2a0a0a");
        navar2.setLayoutX(1500);
        navar2.setLayoutY(0);
        root.getChildren().add(navar2);
        
        primaryStage.setScene(new Scene(root, 1530, 800));
        primaryStage.getScene().addEventFilter(ScrollEvent.ANY, this::handleMouseScroll);
        primaryStage.show();
    }

    private void createGamePane(BuildingType currentSet) {

    }

    private void handleMouseScroll(ScrollEvent event) {
        double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
        scaleTransform.setX(scaleTransform.getX() * scaleFactor);
        scaleTransform.setY(scaleTransform.getY() * scaleFactor);
        event.consume();
    }

    private void setBarScene(String address) {
        barScene = new ImageView(new Image(GameMenuControl.class.getResource(address).toExternalForm()));
        barScene.setFitWidth(1530);
        barScene.setPreserveRatio(true);
        barScene.setSmooth(true);
        barScene.setCache(true);
        barScene.setY(567);
        root.getChildren().add(barScene);
    }

    private void addBar(Pane root) {
        setBarScene("/Images/governance.png");
        createBarBook();
        enterMainBar();
        //todo: don't make them go to the last row
    }

    private void createBarBook() {
        //should change
        Label population = new Label(currentPlayer.getGovernance().getUnemployedPopulation() + " / " +
                currentPlayer.getGovernance().getMaximumPopulation());
        population.setStyle("-fx-alignment: center; -fx-font-family: x fantasy; -fx-font-style: italic; -fx-text-fill: #3d3535; -fx-padding: 20 0 0 8; -fx-font-weight: bold; -fx-font-size: 15");
        Label popularity = new Label("" + currentPlayer.getGovernance().getPopularity());
        popularity.setStyle("-fx-alignment: center; -fx-font-family: x fantasy; -fx-font-style: italic; -fx-text-fill: #3d3535; -fx-padding: 0 0 0 23; -fx-font-weight: bold; -fx-font-size: 25");

        Button mask = new Button();
        ImageView masks = new ImageView(new Image(GameControlTest.class.getResource("/Images/masks.png").toExternalForm(), 35, 35, false, false));
        mask.setStyle("-fx-background-color: transparent; -fx-padding: -6 -80 6 80;");
        mask.setGraphic(masks);
        mask.setOnMouseClicked(mouseEvent -> {
            if (popularityFactorsBar != null && popularityFactorsBar.getChildren().size() != 0 && popularityFactorsBar.getChildren().get(0).isVisible())
                enterMainBar();
            else enterPopularityBar();
        });

        Button changeFactorButton = new Button();
        ImageView change = new ImageView(new Image(GameControlTest.class.getResource("/Images/changeFactor.png").toExternalForm(), 25, 25, false, false));
        changeFactorButton.setStyle("-fx-background-color: transparent; -fx-padding: 20 -55 -20 -55;");
        changeFactorButton.setGraphic(change);
        changeFactorButton.setOnMouseClicked(mouseEvent -> {
            System.out.println("88888888888888888888888888");
            if (changeFactorsBar != null && changeFactorsBar.getChildren().size() != 0 && changeFactorsBar.getChildren().get(0).isVisible()) {
                currentPlayer.getGovernance().setFearRate((int) ((Slider) changeFactorsBar.getChildren().get(3)).getValue());
                currentPlayer.getGovernance().setFoodRate((int) ((Slider) changeFactorsBar.getChildren().get(4)).getValue());
                currentPlayer.getGovernance().setTaxRate((int) ((Slider) changeFactorsBar.getChildren().get(5)).getValue());
                enterMainBar();
            } else enterChangeFactorBar();
        });

        barBook.getChildren().addAll(popularity, population, mask, changeFactorButton);
        barBook.setSpacing(-20);
        barBook.setLayoutY(660);
        barBook.setLayoutX(1150);
        root.getChildren().add(barBook);
    }

    private void enterChangeFactorBar() {
        System.out.println("trying to enter");
        if (popularityFactorsBar != null)
            for (Node child : changeFactorsBar.getChildren()) {
                child.setVisible(false);
            }
        if (mainBar != null)
            for (Node child : mainBar.getChildren()) {
                child.setVisible(false);
            }

        boolean first = changeFactorsBar.getChildren().size() == 0;
        barScene.setImage(new Image(GameMenuControl.class.getResource("/Images/governance.png").toExternalForm()));
        System.out.println("here");
        Slider fear = sliderMaker("fear rate:", currentPlayer.getGovernance().getFearRate(), -5, 5, first, 0);
        Slider food = sliderMaker("food rate: ", currentPlayer.getGovernance().getFearRate(), -2, 2, first, 1);
        Slider tax = sliderMaker("tax rate: ", currentPlayer.getGovernance().getFearRate(), -3, 8, first, 2);
        fear.setPadding(new Insets(-95,-125,95,125));
        food.setPadding(new Insets(-95,-125,95,125));
        tax.setPadding(new Insets(-95,-125,95,125));

        changeFactorsBar.setLayoutX(670);
        changeFactorsBar.setLayoutY(650);
        System.out.println("almost");
        if (first) {
            System.out.println("here");
            changeFactorsBar.getChildren().addAll(fear, food, tax);
            root.getChildren().add(changeFactorsBar);
        } else {
            System.out.println("or there");
            changeFactorsBar.getChildren().set(3, fear);
            changeFactorsBar.getChildren().set(4, food);
            changeFactorsBar.getChildren().set(5, tax);
        }
    }

    private Slider sliderMaker(String text, int initialValue, int min, int max, boolean first, int index) {
        Slider slider = new Slider();
        slider.getStylesheets().add(GetStyle.class.getResource("/CSS/game.css").toExternalForm());
        //slider.getStyleClass().add("Label");
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(initialValue);
        slider.setAccessibleText(text);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setPrefWidth(300);
        slider.setPrefHeight(30);
        slider.setMinorTickCount(0);
        slider.setMajorTickUnit(1);
        slider.setSnapToTicks(true);
        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            slider.setValue(Math.round(newVal.doubleValue()));
        });
        Label valueLabel = new Label();
        valueLabel.textProperty().bind(Bindings.format(text + "%.0f", slider.valueProperty()));
        valueLabel.setStyle("-fx-text-fill: #282525; -fx-font-weight: bold; -fx-font-size: 20;");
        if (first)
            changeFactorsBar.getChildren().add(valueLabel);
        else
            changeFactorsBar.getChildren().set(index, valueLabel);
        return slider;
    }

    private void enterPopularityBar() {
        if (changeFactorsBar != null)
            for (Node child : changeFactorsBar.getChildren()) {
                child.setVisible(false);
            }
        if (mainBar != null)
            for (Node child : mainBar.getChildren()) {
                child.setVisible(false);
            }

        boolean first = popularityFactorsBar.getChildren().size() == 0;
        barScene.setImage(new Image(GameMenuControl.class.getResource("/Images/popularityMenu.png").toExternalForm()));

        int previousPopularity = currentPlayer.getGovernance().getPopularity();

        gameController.taxRateEffect();
        int effectedPopularity = currentPlayer.getGovernance().getPopularity();
        Label tax = setFactorLabel(effectedPopularity - previousPopularity);
        tax.setPadding(new Insets(-30, 0, 30, 0));

        gameController.churchEffect();
        effectedPopularity = currentPlayer.getGovernance().getPopularity();
        Label religion = setFactorLabel(effectedPopularity - previousPopularity);
        religion.setPadding(new Insets(-20, -200, 20, 180));

        gameController.foodRateEffect();
        effectedPopularity = currentPlayer.getGovernance().getPopularity();
        Label food = setFactorLabel(effectedPopularity - previousPopularity);
        food.setPadding(new Insets(40, 0, -40, 0));

        gameController.fearRateEffect();
        effectedPopularity = currentPlayer.getGovernance().getPopularity();
        Label fear = setFactorLabel(effectedPopularity - previousPopularity);
        fear.setPadding(new Insets(30, -200, -30, 180));


        int wholeChange = Integer.parseInt(fear.getText()) + Integer.parseInt(religion.getText()) +
                Integer.parseInt(tax.getText()) + Integer.parseInt(food.getText());
        Label whole = new Label("" + wholeChange);
        whole.setPadding(new Insets(-25, -250, 25, 250));
        if (wholeChange > 0) {
            whole.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 30;");
            whole.setGraphic(new ImageView(new Image(GameMenuControl.class.getResource("/Images/green.jpg").toExternalForm(), 45, 45, false, false)));
        } else if (wholeChange < 0) {
            whole.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 30;");
            whole.setGraphic(new ImageView(new Image(GameMenuControl.class.getResource("/Images/red.jpg").toExternalForm(), 45, 45, false, false)));
        } else {
            whole.setStyle("-fx-text-fill: #c7ac00; -fx-font-weight: bold; -fx-font-size: 30;");
            whole.setGraphic(new ImageView(new Image(GameMenuControl.class.getResource("/Images/yellow.jpg").toExternalForm(), 45, 45, false, false)));
        }

        popularityFactorsBar.setLayoutX(700);
        popularityFactorsBar.setLayoutY(600);

        if (first) {
            popularityFactorsBar.getChildren().addAll(food, fear, religion, tax, whole);
            root.getChildren().add(popularityFactorsBar);
        } else {
            popularityFactorsBar.getChildren().set(0, food);
            popularityFactorsBar.getChildren().set(1, fear);
            popularityFactorsBar.getChildren().set(2, religion);
            popularityFactorsBar.getChildren().set(3, tax);
            popularityFactorsBar.getChildren().set(4, whole);
        }
    }

    private Label setFactorLabel(int difference) {
        Label result = new Label("" + difference);
        result.setStyle("-fx-text-fill: #ad7600; -fx-font-weight: bold; -fx-font-size: 20;");
        if (Integer.parseInt(result.getText()) > 0)
            result.setGraphic(new ImageView(new Image(GameMenuControl.class.getResource("/Images/green.jpg").toExternalForm(), 20, 20, false, false)));
        else if (Integer.parseInt(result.getText()) < 0)
            result.setGraphic(new ImageView(new Image(GameMenuControl.class.getResource("/Images/red.jpg").toExternalForm(), 20, 20, false, false)));
        else
            result.setGraphic(new ImageView(new Image(GameMenuControl.class.getResource("/Images/yellow.jpg").toExternalForm(), 20, 20, false, false)));
        currentPlayer.getGovernance().changePopularity(-1 * difference);
        return result;
    }

    private void enterMainBar() {
        if (changeFactorsBar != null)
            for (Node child : changeFactorsBar.getChildren()) {
                child.setVisible(false);
            }
        if (popularityFactorsBar != null)
            for (Node child : popularityFactorsBar.getChildren()) {
                child.setVisible(false);
            }
        barScene.setImage(new Image(GameMenuControl.class.getResource("/Images/governance.png").toExternalForm()));

        boolean first = mainBar.getChildren().size() == 0;

        if(currentSet.equals(BuildingType.FOOD_PROCESSING)) {
            houses[0] = createSourceButton("/Images/buildings/castle/hovel.png", 550, 625,BuildingEnum.HOVEL);
            houses[1] = createSourceButton("/Images/buildings/castle/church.png", 670, 625,BuildingEnum.CHURCH);
            houses[2] = createSourceButton("/Images/buildings/castle/catheral.png", 790, 625,BuildingEnum.CATHEDRAL);
            houses[3] = createSourceButton("/Images/buildings/castle/small stone gatehouse.png", 910, 625,BuildingEnum.SMALL_STONE_GATEHOUSE);
        }

        mainBar.setLayoutX(635);
        mainBar.setLayoutY(640);

        if (first) {
            mainBar.getChildren().addAll(houses[0],houses[1],houses[2],houses[3]);
            root.getChildren().add(mainBar);
        }
        else {
            mainBar.getChildren().set(0, houses[0]);
            mainBar.getChildren().set(1, houses[1]);
            mainBar.getChildren().set(2, houses[2]);
            mainBar.getChildren().set(3, houses[3]);
        }
    }

    private Button createSourceButton(String address, int i, int j, BuildingEnum buildingEnum) {
        Button btn = new Button("");
        btn.setGraphic(new ImageView(new Image(GameControlTest.class.getResource(address).toExternalForm(), 100,
                100, false, false)));
        btn.setStyle("-fx-background-color: transparent");
        btn.setOnMouseEntered(event -> {
            createPicture(event, i, j,address,buildingEnum);
            ((Pane) primaryStage.getScene().getRoot()).getChildren().add(buildingImageView);
        });

        btn.setLayoutX(i);
        btn.setLayoutY(j);
        return btn;
    }

    private void addTile(Pane root, int index) {
        Pane section = new Pane();
        section.setStyle("-fx-border-color: black;");
        section.setPrefHeight(TILE_SIZE);
        section.setPrefWidth(TILE_SIZE);
        root.getChildren().add(section);
        double[] ranges = new double[2];
        ranges[0] = ((index % 10) * TILE_SIZE);
        ranges[1] = (index / 10) * TILE_SIZE;
        panes[index % 10][index / 10] = section;
        int[] coordinates = assignTileToScreen(index);
        linkedHouses.put(section, map.getTile(coordinates[1], coordinates[0]));
        section.getChildren().add(new ImageView(new Image(GameMenuControl.class.getResource("/Images/textures/" + map.getTile(
                coordinates[1], coordinates[0]).getTexture().getName() + ".jpg").toExternalForm(), TILE_SIZE, TILE_SIZE, false
                , false)));
        //todo: set up tile. including texture.
        root.getChildren().get(index).setLayoutX(ranges[0]);
        root.getChildren().get(index).setLayoutY(ranges[1]);
    }

    private int[] assignTileToScreen(int index) {
        int[] ans = new int[2];
        ans[0] = xCenter + (index % 10 - 3);
        ans[1] = yCenter + (index / 10 - 2);
        return ans;
    }

    private void addToTile(int[] index, int say) {

        double[] ranges = new double[2];
        ranges[0] = -550 + 155 * index[0];
        ranges[1] = -605 + 140 * index[1];
        System.out.println("final rang: " + index[0] + " , " + index[1]);
        buildingImageView.setLayoutY(ranges[1]);
        buildingImageView.setLayoutX(ranges[0]);
    }

    private void createPicture(MouseEvent event, int i, int j, String address, BuildingEnum buildingEnum) {

        buildingImageView = new ImageView(new Image(GameMenuControl.class.getResource(address).toExternalForm()));
        buildingImageView.setFitWidth(TILE_SIZE - 10);
        buildingImageView.setPreserveRatio(true);
        buildingImageView.setSmooth(true);
        buildingImageView.setCache(true);

        buildingImageView.setOnMouseDragged(this::onMouseDragged);
        buildingImageView.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onMouseReleased(mouseEvent,buildingEnum);
            }
        });
        buildingImageView.setUserData(new double[]{event.getY() , event.getY() , ((Button)event.getSource()).getLayoutX()
                , ((Button)event.getSource()).getLayoutY()});

        buildingImageView.setX(i);
        buildingImageView.setY(j);
    }

    private void onMouseDragged(MouseEvent event) {
        if (buildingImageView == null)
            return;
        // Update the position of the building while dragging
        double[] initialPosition = (double[]) buildingImageView.getUserData();
        buildingImageView.relocate(event.getScreenX() - initialPosition[0] - TILE_SIZE/2, event.getScreenY() - initialPosition[1] - TILE_SIZE/2);
    }

    private void onMouseReleased(MouseEvent event, BuildingEnum buildingEnum) {
        if (buildingImageView == null) return;
        // System.out.println("this is the mouse coor: " + event.getScreenX() + " , " + event.getScreenY());
        buildingImageView.setOnMouseDragged(null);
        buildingImageView.setOnMouseReleased(null);
        buildings.put(buildingImageView,new Building(buildingEnum,currentPlayer,0,true));
        //buildingImageView.setLayoutY(100);
        //System.out.println("button number: " + say(event));
        //buildingImageView.setLayoutY(-540);
        int[] index = findTheNearestTile(say(event));
        linkedHouses.get(panes[index[0]][index[1]]).getBuildings().add(buildings.get(buildingImageView));
        //todo: do the drop building thing here
        System.out.println("index: " + index[0] + " , " + index[1]);
        addToTile(index, say(event));
        buildingImageView = null;
    }

    private int say(MouseEvent event) {
        if(event == null) return -2;
        for(int i = 0; i < 4; i++) {
            if(houses[i].getLayoutX() == ((double[])((ImageView)event.getSource()).getUserData())[2])
                return i;
        }
        return -1;
    }

    private int[] findTheNearestTile(int number) {
        int[] coor = new int[2];
        double offset = number * 120;
        double currentX = buildingImageView.getLayoutX() - offset;
        double currentY = buildingImageView.getLayoutY();
        //System.out.println(buildingImageView.getX() + " , " + buildingImageView.getLayoutX() + " , " + buildingImageView.getTranslateX());
        if (currentX <= -480)
            coor[0] = 0;
        else {
            if (currentX >= 735)
                coor[0] = 9;
            else
                coor[0] = (int) (((currentX + 480) / TILE_SIZE) % 10 + 1);
        }

        currentY = currentY * -1;
        if (currentY <= 245)
            coor[1] = 3;
        else {
            if (currentY >= 540)
                coor[1] = 0;
            else
                coor[1] = (int) (((540 - currentY) / TILE_SIZE) + 1);
        }
        return coor;
    }
}

