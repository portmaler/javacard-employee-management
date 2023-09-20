module com.sisbd.confirmationdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires java.smartcardio;


    opens com.sisbd.confirmationdb to javafx.fxml;
    opens com.sisbd.confirmationdb.models to javafx.fxml;
    opens com.sisbd.confirmationdb.controllers to javafx.fxml;
    exports com.sisbd.confirmationdb;
    exports com.sisbd.confirmationdb.javacard;
    exports com.sisbd.confirmationdb.models;
    exports com.sisbd.confirmationdb.controllers;
    exports com.sisbd.confirmationdb.utils;

}