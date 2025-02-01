package com.app.userstable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("table-view.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Users Table");
        primaryStage.setScene(new Scene(root, 720, 640));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}