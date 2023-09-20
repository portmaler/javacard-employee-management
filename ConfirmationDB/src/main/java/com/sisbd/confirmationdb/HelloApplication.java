package com.sisbd.confirmationdb;

import com.sisbd.confirmationdb.utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Constants.FXML_HELLO));
        Scene scene = new Scene(fxmlLoader.load(), 1049, 595);
        stage.setTitle("Job Confirmation");
        stage.setScene(scene);
        stage.show();
    }

    public static void  changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
        primaryStage.getScene().setRoot(pane);
    }
    public static void main(String[] args) {

        launch();
    }
}