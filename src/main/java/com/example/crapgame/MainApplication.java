package com.example.crapgame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Clase principal de la aplicación
 *
 * @author Juan Pablo Escamilla (2420580-2724)
 */
public class MainApplication extends Application {
   /**
    * Atributos de la interfaz gráfica. En este caso, se declaran los elementos:
    * - Un botón para la validación de las palabras (en caso de que se prefiera no usar enter, por ejemplo).
    * - Una etiqueta para mostrar la palabra a escribir.
    * - Un campo de texto para ingresar la palabra.
    * - Una imagen para mostrar las vidas restantes.
    */
    private Button validateButton;
    private Label wordLabel;
    private TextField inputField;
    private ImageView lifesImageView;

    /**
     * Método principal de la aplicación. Se encarga de cargar el archivo FXML y mostrar la ventana principal.
     * @param primaryStage Ventana principal de la aplicación (escenario).
     * @throws Exception Excepción en caso de error al cargar el archivo FXML.
     */
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
            e.printStackTrace(); // Mostrar error de carga si no se puede cargar el archivo FXML.
        }
    }

    /**
     * Método principal de la aplicación. Se encarga de lanzar la aplicación.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}