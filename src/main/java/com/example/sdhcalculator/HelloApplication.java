package com.example.sdhcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Scene scene = new Scene(root, 650, 400);
        String css = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setTitle("Калькулятор СДГ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
//(с) 2025 Tumanov Nikita Alekseevich All Rights Reserved