module se.blacklemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens se.blacklemon to javafx.fxml;
    exports se.blacklemon;
    exports se.blacklemon.controller;
    opens se.blacklemon.controller to javafx.fxml;
}
