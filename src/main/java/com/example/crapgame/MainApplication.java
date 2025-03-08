package com.example.crapgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("crap_frontend.fxml"));
        Parent root = loader.load();

        // Configurar y mostrar la escena
        Scene scene = new Scene(root);
        primaryStage.setTitle("Crap Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Lanzar la aplicación JavaFX
        launch(args);
    }
}