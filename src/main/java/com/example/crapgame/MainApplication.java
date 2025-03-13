package com.example.crapgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainApplication extends Application {
    private Button validateButton;
    private Label wordLabel;
    private TextField inputField;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/crap-frontend.fxml"));
            Parent root = loader.load();

            // Configuración de la escena
            Scene scene = new Scene(root);

            // Configuración de la ventana principal
            primaryStage.setTitle("Escritura Rápida - Juego");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false); // Para evitar redimensionamiento
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace(); // Mostrar error de carga
        }
    }

    public static void main(String[] args) {
        launch(args); // Lanzar la aplicación JavaFX
    }
}