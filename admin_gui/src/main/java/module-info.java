module com.sisbd.admin_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.sisbd.admin_gui to javafx.fxml;
    opens com.sisbd.admin_gui.connection to javafx.fxml;
    opens com.sisbd.admin_gui.models to javafx.fxml;
    opens com.sisbd.admin_gui.controllers to javafx.fxml;
    exports com.sisbd.admin_gui;
    exports com.sisbd.admin_gui.connection;
    exports com.sisbd.admin_gui.models;
    exports com.sisbd.admin_gui.controllers;

}