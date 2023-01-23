package com.currencyconverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("view.fxml")); //Load the fxml file
        Scene scene = new Scene(fxmlLoader.load(), 550, 450); //Size of the window

        stage.setTitle("Currency Converter"); //Title on the top
        stage.setScene(scene);
        stage.show();





    }

    public static void main(String[] args) {
        launch();
    }
}