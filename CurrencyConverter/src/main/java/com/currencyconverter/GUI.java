package com.currencyconverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 450);

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();

        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());




    }

    public static void main(String[] args) {
        launch();
    }
}