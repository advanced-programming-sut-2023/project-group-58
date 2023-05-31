module APP {
    requires com.google.gson;
    requires json.simple;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    exports view;
    exports model;
    exports controller;
    opens view to javafx.fxml;
    opens model to javafx.fxml;
    opens controller to javafx.fxml;
}