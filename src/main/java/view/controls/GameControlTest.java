package view.controls;

import controller.MapMenuController;
import controller.gameMenuControllers.GameController;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import model.Map;
import model.Tile;
import model.User;
import model.buildings.Building;
import model.buildings.BuildingEnum;
import view.GetStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GameControlTest {
    private final int TILE_SIZE = 150;
    private Stage primaryStage;
    private Pane root;
    private User currentPlayer;
    private ImageView buildingImageView = new ImageView();
    private HashMap<ImageView, Building> buildings = new HashMap<>();

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
    private boolean tilesOccupied = false;
    private boolean isCopyActive = false;
    private boolean isPasteActive = false;
    private List<ImageView> savedImageViews = new ArrayList<>();
    private ArrayList<Building> copiedBuildings = new ArrayList<>();
    private boolean clipboardPaneUp = false;
    private Label navar;
    private int navarIndex = 1;

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

        for (int i = 0; i < 50; i++) {
            addTile(root, i);
        }

        addBar(root);
        createGamePane(currentSet);

        navar = new Label();
        navar.setPrefHeight(633);
        navar.setPrefWidth(60);
        navar.setStyle("-fx-background-color: #2a0a0a");
        navar.setLayoutX(1480);
        navar.setLayoutY(0);
        root.getChildren().add(navar);
        navarIndex = root.getChildren().indexOf(navar);

        joyStick();

        selectArea();

        copySetUp();
        clipBoardSetUp();

        primaryStage.setScene(new Scene(root, 1530, 800));
        primaryStage.getScene().addEventFilter(ScrollEvent.ANY, this::handleMouseScroll);
        primaryStage.show();
    }

    private VBox clipBoardBox = null;

    private void clipBoardSetUp() {
        Button addButton = new Button();
        ImageView imageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/clipboard.png").toExternalForm()));
        imageView.setFitWidth(25);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        addButton.setGraphic(imageView);
        addButton.setLayoutX(1485);
        addButton.setLayoutY(90);

        addButton.setOnMouseClicked(mouseEvent -> {
            if (!clipboardPaneUp) {
                clipBoardBox = extractClipBoard();
                navar.setPrefWidth(160);
                navar.setLayoutX(1380);
            } else {
                if (clipBoardBox != null) root.getChildren().remove(clipBoardBox);
                navar.setPrefWidth(80);
                navar.setLayoutX(1480);
                clipBoardBox = null;
            }
            clipboardPaneUp = !clipboardPaneUp;
        });
        root.getChildren().add(addButton);
    }

    private int counter = 0;
    private boolean imFromClipboard = false;

    private VBox extractClipBoard() {
        if (copiedBuildings == null || copiedBuildings.size() == 0) return null;
        counter = 0;
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        Label label = new Label("clipboard:");
        label.setStyle("-fx-alignment: baseline-left ; -fx-font-family: Garamond; -fx-text-fill: #EEE2BBFF; -fx-font-size: 25; -fx-font-weight: bold");
        vBox.getChildren().add(label);
        ImageView imageView;
        while (counter < 5) {
            if (copiedBuildings.size() - 1 - counter < 0) break;
            imageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/buildings/" +
                            copiedBuildings.get(copiedBuildings.size() - 1 - counter).getType().getName() + ".png")
                    .toExternalForm()));
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setOnMouseClicked(mouseEvent -> {
                if(copiedBuildings == null || copiedBuildings.size() == 0) return;
                int index = (int) Math.floor((mouseEvent.getSceneY() - 180)/90);
                System.out.println("copied number: " + index + " , " + mouseEvent.getScreenY() + " , " + mouseEvent.getY() + " , " + mouseEvent.getSceneY());
                if (copiedBuildings.size() - counter < 0) return;
                Building building = copiedBuildings.get(copiedBuildings.size() - 1 - index);
                copiedBuildings.remove(copiedBuildings.size() - 1 - index);
                copiedBuildings.add(building);
                isPasteActive = true;
                imFromClipboard = true;
            });
            vBox.getChildren().add(imageView);
            counter++;
        }
        vBox.setLayoutX(1400);
        vBox.setLayoutY(180);
        root.getChildren().add(vBox);
        return vBox;
    }

    private void copySetUp() {
        Button addButton = new Button();
        ImageView imageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/copy.png").toExternalForm()));
        imageView.setFitWidth(25);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        addButton.setGraphic(imageView);
        addButton.setLayoutX(1485);
        addButton.setLayoutY(30);
        addButton.setOnAction(event -> {
            isCopyActive = !isCopyActive;
            if (isCopyActive) {
                imageView.setFitWidth(35);
                imageView.setPreserveRatio(true);
                if (imFromClipboard) {
                    isPasteActive = true;
                    imageView.setImage(new Image(GameMenuControl.class.getResource("/Images/paste.png").toExternalForm()));
                    imFromClipboard = false;
                }
            } else {
                if (!imFromClipboard) {
                    isPasteActive = false;
                } else {
                    imFromClipboard = false;
                }
                imageView.setImage(new Image(GameMenuControl.class.getResource("/Images/copy.png").toExternalForm()));
                imageView.setFitWidth(25);
                imageView.setPreserveRatio(true);
                imFromClipboard = false;
            }
        });
        root.getChildren().add(addButton);
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getScreenX() > 1470) return;
            if (event.getScreenY() > 600) return;
            if (isCopyActive) {
                if (!isPasteActive) {
                    getPaneIndex(event.getScreenX(), event.getScreenY(), imageView);
                } else {
                    paste(event.getScreenX(), event.getScreenY());
                    imageView.setImage(new Image(GameMenuControl.class.getResource("/Images/copy.png").toExternalForm()));
                }
            }
        });
    }

    private void paste(double screenX, double screenY) {
        if (screenX > 1470) return;
        if (screenY > 600) return;
        int xIndex = (int) Math.floor(screenX / TILE_SIZE);
        int yIndex = (int) Math.floor(screenY / TILE_SIZE);
        linkedHouses.get(panes[xIndex][yIndex]).getBuildings().add(copiedBuildings.get(copiedBuildings.size() - 1));
        isPasteActive = false;
        copiedBuildings.remove(copiedBuildings.size() - 1);
        indexOfHoveringTilesTogether = -1;
        indexOfHoveringTile = -1;
        indexOfHoveringBuilding = -1;
        for (int i = 0; i < 50; i++)
            addTile(root, i);
        for (int i = 0; i < 50; i++) {
            int[] coordinates = assignTileToScreen(i);
            setUpTile(panes[i % 10][i / 10], map.getTile(coordinates[1], coordinates[0]));
        }
    }

    private void getPaneIndex(double screenX, double screenY, ImageView imageView) {
        if (screenX > 1470) return;
        if (screenY > 600) return;
        int xIndex = (int) Math.floor(screenX / TILE_SIZE);
        int yIndex = (int) Math.floor(screenY / TILE_SIZE);
        System.out.println("this is the pane on which we copy form: " + xIndex + " and " + yIndex);
        Tile selected = linkedHouses.get(panes[xIndex][yIndex]);
        if (selected.getBuildings() == null || selected.getBuildings().size() == 0)
            System.out.println("no building on this tile");
        else
            System.out.println("I found this building: " + selected.getBuildings().get(0).getType());
        if (selected.getBuildings() == null || selected.getBuildings().size() == 0) return;
        copiedBuildings.add(selected.getBuildings().get(selected.getBuildings().size() - 1));
        imageView.setImage(new Image(GameMenuControl.class.getResource("/Images/paste.png").toExternalForm()));
        isPasteActive = true;
    }

    private int indexOfHoveringTilesTogether = -1;

    private void selectArea() {
        Rectangle selectionArea = new Rectangle();
        selectionArea.setFill(Color.TRANSPARENT);
        selectionArea.setStroke(Color.BLUE);
        root.setOnMousePressed(event -> {
            selectionArea.setX(event.getX());
            selectionArea.setY(event.getY());
        });

        root.setOnMouseDragged(event -> {
            if (selectionArea.getY() > 600)
                return;
            if (indexOfHoveringTile >= 0) {
                root.getChildren().remove(indexOfHoveringTile);
                indexOfHoveringTile = -1;
            }
            tilesOccupied = true;
            String infoStr = "";
            double width = event.getX() - selectionArea.getX();
            double height = event.getY() - selectionArea.getY();
            selectionArea.setWidth(width);
            selectionArea.setHeight(height);

            int buildingCounter = 0;
            int unitCounter = 0;
            int minRate = 0;
            int maxRate = 0;
            int sumRate = 0;
            int yourUnits = 0;
            int averageRate = 0;

            for (Node node : root.getChildren()) {
                if (node.getBoundsInParent().intersects(selectionArea.getBoundsInParent())) {
                    if (node instanceof Pane) {
                        Tile tile = findTileByPane((Pane) node);
                        infoStr += "Texture: " + tile.getTexture().getName() + "\n";
                        if (tile.getBuildings() != null) {
                            buildingCounter += tile.getBuildings().size();
                            for (Building building : tile.getBuildings()) {
                                maxRate = Math.max(maxRate, building.getType().getRate());
                                minRate = Math.min(minRate, building.getType().getRate());
                                sumRate += building.getType().getRate();
                            }
                        }
                        if (tile.getPlayersUnits() != null)
                            unitCounter += tile.getPlayersUnits().size();
                        if (tile.findYourUnits(currentPlayer) != null)
                            yourUnits += tile.findYourUnits(currentPlayer).size();
                    }
                }
            }

            averageRate = buildingCounter == 0 ? 0 : sumRate / buildingCounter;
            infoStr += "Building number: " + buildingCounter + "\nUnit number: " + unitCounter + "\nYour units: " + yourUnits
                    + "\nMin rate: " + minRate + "\nMax rate: " + maxRate + "\nAverage rate: " + averageRate;
            Label info = simpleLabelStyler(infoStr);
            info.setStyle("-fx-alignment: center; -fx-font-family: Garamond; -fx-text-fill: #EEE2BBFF; -fx-background-color: rgba(40,37,37,0.38);" +
                    "; -fx-font-size: 25; -fx-font-weight: bold");
            if (indexOfHoveringTilesTogether >= 0)
                root.getChildren().set(indexOfHoveringTilesTogether, info);
            else {
                root.getChildren().add(info);
                indexOfHoveringTilesTogether = root.getChildren().indexOf(info);
            }
        });

        root.setOnMouseReleased(event -> {
            if (indexOfHoveringTilesTogether >= 0) root.getChildren().remove(indexOfHoveringTilesTogether);
            indexOfHoveringTilesTogether = -1;
            tilesOccupied = false;
        });

    }

    private int upBuffer = 0, downBuffer = 0, rightBuffer = 0, leftBuffer = 0;
    private final int lock = 5;

    private void joyStick() {
        Button button = new Button();
        button.setGraphic(new ImageView(new Image(GameMenuControl.class.getResource("/Images/joystick.png").toExternalForm(),
                100, 100, false, false)));
        button.setStyle("-fx-background-color: transparent");
        button.setLayoutX(20);
        button.setLayoutY(500);
        // Store initial coordinates for determination of direction
        AtomicReference<Double> initialX = new AtomicReference<>((double) 0);
        AtomicReference<Double> initialY = new AtomicReference<>((double) 0);

        button.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                initialX.set(event.getSceneX());
                initialY.set(event.getSceneY());
            }
        });

        button.setOnMouseDragged(event -> {
            double offsetX = event.getSceneX() - initialX.get();
            double offsetY = event.getSceneY() - initialY.get();

            // Check for vertical or horizontal drag
            if (Math.abs(offsetX) > Math.abs(offsetY)) {
                if (offsetX > 0) {
                    // Right drag
                    if (++rightBuffer >= lock) {
                        scroll(1, 0);
                        resetBuffers();
                    }
                } else {
                    // Left drag
                    if (++leftBuffer >= lock) {
                        scroll(-1, 0);
                        resetBuffers();
                    }
                }
            } else {
                if (offsetY > 0) {
                    // Down drag
                    if (++downBuffer >= lock) {
                        scroll(0, -1);
                        resetBuffers();
                    }
                } else {
                    // Up drag
                    if (++upBuffer >= lock) {
                        scroll(0, 1);
                        resetBuffers();
                    }
                }
            }
        });
        root.getChildren().add(button);
    }

    private void scroll(int xChange, int yChange) {
        xCenter += xChange;
        yCenter += yChange;
        for (int i = 0; i < 50; i++)
            addTile(root, i);
        for (int i = 0; i < 50; i++) {
            int[] coordinates = assignTileToScreen(i);
            setUpTile(panes[i % 10][i / 10], map.getTile(coordinates[1], coordinates[0]));
        }
    }

    private void resetBuffers() {
        rightBuffer = 0;
        downBuffer = 0;
        upBuffer = 0;
        leftBuffer = 0;
    }

    private void createGamePane(BuildingType currentSet) {

    }

    private void handleMouseScroll(ScrollEvent event) {
        double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
        double change = scaleTransform.getX();
        if (change > 2.5) change = 2.5;
        if (change < 0.5) change = 0.5;
        double secChange = scaleTransform.getY();
        if (secChange > 2.4) secChange = 2.5;
        if (secChange < 0.5) secChange = 0.5;
        scaleTransform.setX(change * scaleFactor);
        scaleTransform.setY(secChange * scaleFactor);
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
        fear.setPadding(new Insets(-95, -125, 95, 125));
        food.setPadding(new Insets(-95, -125, 95, 125));
        tax.setPadding(new Insets(-95, -125, 95, 125));

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

        if (currentSet.equals(BuildingType.FOOD_PROCESSING)) {
            houses[0] = createSourceButton("/Images/buildings/hovel.png", 550, 625, BuildingEnum.HOVEL);
            houses[1] = createSourceButton("/Images/buildings/church.png", 670, 625, BuildingEnum.CHURCH);
            houses[2] = createSourceButton("/Images/buildings/cathedral.png", 790, 625, BuildingEnum.CATHEDRAL);
            houses[3] = createSourceButton("/Images/buildings/small_stone_gatehouse.png", 910, 625, BuildingEnum.SMALL_STONE_GATEHOUSE);
        }

        mainBar.setLayoutX(635);
        mainBar.setLayoutY(640);

        if (first) {
            mainBar.getChildren().addAll(houses[0], houses[1], houses[2], houses[3]);
            root.getChildren().add(mainBar);
        } else {
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
            createPicture(event, i, j, address, buildingEnum);
            ((Pane) primaryStage.getScene().getRoot()).getChildren().add(buildingImageView);
        });

        btn.setLayoutX(i);
        btn.setLayoutY(j);
        return btn;
    }

    int indexOfHoveringTile = -1;

    private Tile findTileByPane(Pane section) {
        Tile tile = null;
        for (java.util.Map.Entry<Pane, Tile> entry : linkedHouses.entrySet()) {
            if (section == null) continue;
            if (entry.getKey().getLayoutX() == section.getLayoutX() &&
                    entry.getKey().getLayoutY() == section.getLayoutY()) {
                tile = entry.getValue();
                break;
            }
        }
        return tile;
    }

    private void addTile(Pane root, int index) {
        for (java.util.Map.Entry<ImageView, Building> entry : buildings.entrySet()) {
            for (Node child : root.getChildren()) {
                if (child.equals(entry.getKey())) {
                    root.getChildren().remove(child);
                    break;
                }
            }
        }

        buildings = new HashMap<>();
        Pane section = new Pane();
        section.setStyle("-fx-border-color: black;");
        section.setPrefHeight(TILE_SIZE);
        section.setPrefWidth(TILE_SIZE);
        double[] ranges = new double[2];
        ranges[0] = ((index % 10) * TILE_SIZE);
        ranges[1] = (index / 10) * TILE_SIZE;
        panes[index % 10][index / 10] = section;
        int[] coordinates = assignTileToScreen(index);
        linkedHouses.put(section, map.getTile(coordinates[1], coordinates[0]));

        addTileTexture(section, coordinates);
        section.getChildren().get(0).setOnMouseEntered(mouseEvent -> {
            if (tilesOccupied) return;
            Tile tile = findTileByPane(section);
            String infoStr = "Texture: " + tile.getTexture().getName() + "\nbuilding num: " + tile.getBuildings().size();
            Label info = simpleLabelStyler(infoStr);
            info.setStyle("-fx-alignment: center; -fx-font-family: Garamond; -fx-text-fill: #EEE2BBFF; -fx-background-color: rgba(40,37,37,0.25);" +
                    "; -fx-font-size: 25; -fx-font-weight: bold");
            root.getChildren().add(info);
            indexOfHoveringTile = root.getChildren().indexOf(info);
        });


        section.getChildren().get(0).setOnMouseExited(mouseEvent -> {
            if (tilesOccupied) return;
            if (indexOfHoveringTile > 0) root.getChildren().remove(indexOfHoveringTile);
            indexOfHoveringTile = -1;
        });

        int fixedSize = root.getChildren().size();
        if (fixedSize <= index)
            root.getChildren().add(section);
        else root.getChildren().set(index, section);
        root.getChildren().get(index).setLayoutX(ranges[0]);
        root.getChildren().get(index).setLayoutY(ranges[1]);
    }

    private void addTileTexture(Pane section, int[] coordinates) {
        ImageView imageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/textures/" + map.getTile(
                coordinates[1], coordinates[0]).getTexture().getName() + ".jpg").toExternalForm(), TILE_SIZE, TILE_SIZE, false
                , false));
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);

        Blend blush = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        imageView.getImage().getWidth(),
                        imageView.getImage().getHeight(),
                        Color.RED
                )
        );
        blush.setOpacity(0.25);

        imageView.effectProperty().bind(
                Bindings
                        .when(imageView.hoverProperty())
                        .then((Effect) blush)
                        .otherwise((Effect) null)
        );

        imageView.setCache(true);
        imageView.setCacheHint(CacheHint.SPEED);
        section.getChildren().add(imageView);
    }


    private void setUpTile(Pane section, Tile tile) {
        //todo add units and trees
        if (tile.getBuildings() != null)
            for (Building building : tile.getBuildings()) {
                //System.out.println("this is going to be a building " + building.getType().getName());
                buildingImageView = new ImageView(new Image(GameMenuControl.class.getResource("/Images/buildings/" +
                        building.getType().getName() + ".png").toExternalForm()));
                buildingImageView.setFitWidth(TILE_SIZE - 10);
                buildingImageView.setPreserveRatio(true);
                buildingImageView.setSmooth(true);
                buildingImageView.setCache(true);
                section.getChildren().add(buildingImageView);
            }
    }

    private int[] assignTileToScreen(int index) {
        int[] ans = new int[2];
        ans[0] = xCenter + (index % 10 - 3);
        ans[1] = yCenter + (index / 10 - 2);
        return ans;
    }

    private void addToTile(int[] index, int say) {
        double[] ranges = new double[2];
        ranges[0] = -550 + 150 * index[0] - say * 120;
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
        buildingImageView.setOnMouseReleased(mouseEvent -> onMouseReleased(mouseEvent, buildingEnum));
        buildingImageView.setUserData(new double[]{event.getY(), event.getY(), ((Button) event.getSource()).getLayoutX()
                , ((Button) event.getSource()).getLayoutY()});

        buildingImageView.setX(i);
        buildingImageView.setY(j);
    }

    private void onMouseDragged(MouseEvent event) {
        if (buildingImageView == null)
            return;
        // Update the position of the building while dragging
        double[] initialPosition = (double[]) buildingImageView.getUserData();
        buildingImageView.relocate(event.getScreenX() - initialPosition[0] - TILE_SIZE / 2, event.getScreenY() - initialPosition[1] - TILE_SIZE / 2);
    }

    private Label simpleLabelStyler(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: Garamond; -fx-text-fill: #EEE2BBFF; -fx-padding: 25 0 0 70; -fx-font-size: 25");
        label.setText(text);
        return label;
    }

    private int indexOfHoveringBuilding = -1;

    private void onMouseReleased(MouseEvent event, BuildingEnum buildingEnum) {
        if (buildingImageView == null) return;
        // System.out.println("this is the mouse coor: " + event.getScreenX() + " , " + event.getScreenY());
        buildingImageView.setOnMouseDragged(null);
        buildingImageView.setOnMouseReleased(null);
        buildings.put(buildingImageView, new Building(buildingEnum, currentPlayer, 0, true));

        int[] index = findTheNearestTile(say(event));
        Tile tile = linkedHouses.get(panes[index[0]][index[1]]);
        linkedHouses.get(panes[index[0]][index[1]]).getBuildings().add(buildings.get(buildingImageView));
        //todo: do the drop building thing here
        System.out.println("index: " + index[0] + " , " + index[1]);
        addToTile(index, say(event));

        AtomicReference<Building> building = new AtomicReference<>(buildings.get(buildingImageView));
        buildingImageView.setOnMouseEntered(mouseEvent -> {
            for (java.util.Map.Entry<ImageView, Building> entry : buildings.entrySet()) {
                if (buildingImageView == null) continue;
                if (entry.getKey().getLayoutX() == buildingImageView.getLayoutX() &&
                        entry.getKey().getLayoutY() == buildingImageView.getLayoutY()) {
                    building.set(entry.getValue());
                    break;
                }
            }
            String infoStr = "Name: " + building.get().getType().getName() + "\nHp: " + building.get().getHp() + "\nOwner: " +
                    building.get().getOwner().getUsername() + "\nActive: " + building.get().isActive();
            Label info = simpleLabelStyler(infoStr);
            info.setStyle("-fx-alignment: center; -fx-font-family: Garamond; -fx-text-fill: #EEE2BBFF; -fx-background-color: rgba(40,37,37,0.25);" +
                    "; -fx-font-size: 25; -fx-font-weight: bold");
            root.getChildren().add(info);
            indexOfHoveringBuilding = root.getChildren().indexOf(info);
        });
        buildingImageView.setOnMouseExited(mouseEvent -> {
            if (indexOfHoveringBuilding > 0) root.getChildren().remove(indexOfHoveringBuilding);
            indexOfHoveringBuilding = -1;
        });

        buildingImageView = null;
    }

    private int say(MouseEvent event) {
        if (event == null) return -2;
        for (int i = 0; i < 4; i++) {
            if (houses[i].getLayoutX() == ((double[]) ((ImageView) event.getSource()).getUserData())[2])
                return i;
        }
        return -1;
    }

    private int[] findTheNearestTile(int number) {
        int[] coor = new int[2];
        double offset = number * 120;
        double currentX = buildingImageView.getLayoutX() + offset;
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
        System.out.println("this is my result: " + coor[0] + " , " + coor[1]);
        return coor;
    }
}

