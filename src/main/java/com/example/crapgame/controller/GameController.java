package com.example.crapgame.controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Controlador de la vista principal del juego
 *
 * @author Juan Pablo Escamilla
 */
public class GameController {
    /**
     * Elementos de la interfaz gráfica. Se asocian con los elementos del archivo FXML. Estos incluyen:
     * - Etiqueta para mostrar la palabra actual.
     * - Campo de texto para ingresar la palabra.
     * - Botón para validar la palabra.
     * - Etiqueta para mostrar el tiempo restante.
     * - Etiqueta para mostrar el nivel actual.
     * - Etiqueta para mostrar mensajes al usuario.
     * - Imagen para mostrar las vidas restantes.
     */
    @FXML
    private Label wordLabel;
    @FXML
    private TextField inputField;
    @FXML
    private Button validateButton;
    @FXML
    private Label timerLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView lifesImageView;

    //Lista de palabras que se utilizarán en el juego.
    private List<String> words = Arrays.asList(
            "casa", "perro", "rápido", "computadora", "java",
            "programación", "teclado", "pantalla", "ratón", "ventana",
            "algoritmo", "estructura", "objeto", "evento", "clase"
    );

    //Atributos del controlador. Estos incluyen el nivel, el tiempo restante, las oportunidades y una instancia de Random.
    private int level = 1;
    private int timeRemaining = 20;
    private int opportunities = 4; // Cada error eclipsa 25%
    private Timeline timer;
    private String currentWord;
    private final Random random = new Random();

    /**
     * Metodo que se ejecuta al inicializar el controlador. Se encarga de establecer los eventos de los elementos de la
     * interfaz gráfica y de iniciar el juego.
     */
    @FXML
    public void initialize() {
        setupEvents();
        startGame();
    }

    /**
     * Constructor del controlador. Se encarga de inicializar los atributos del controlador.
     */
    public GameController() {}

    /**
     * Metodo que se encarga de establecer los eventos de los elementos de la interfaz gráfica.
     */
    private void setupEvents() {
        validateButton.setOnAction(e -> validateWord()); // Evento para el botón de validar.
        inputField.setOnKeyPressed(e -> { // Evento para la tecla Enter en el campo de texto.
            if (e.getCode() == KeyCode.ENTER) { // Si la tecla presionada es Enter, se valida la palabra.
                validateWord();
            }
        });
    }

    /**
     * Metodo que inicia el juego, estableciendo los valores iniciales de nivel, tiempo y oportunidades, y seleccionando
     * una nueva palabra aleatoria.
     */
    private void startGame() {
        level = 1;
        timeRemaining = 20;
        opportunities = 4;
        messageLabel.setText("");
        updateLevelLabel();
        showNewWord();
        startTimer();
        updateLifesImage();
    }

    /**
     * Metodo que selecciona una nueva palabra aleatoria de la lista de palabras disponibles y la establece como la
     * palabra actual del juego.
     */
    private void showNewWord() {
        currentWord = words.get(random.nextInt(words.size()));
        wordLabel.setText(currentWord);
        inputField.clear();
    }

    /**
     * Metodo que se encarga de iniciar el temporizador del juego, que se encarga de llevar el control del tiempo restante.
     * Si el tiempo restante llega a 0, se muestra un mensaje de error.
     * Este metodo se implementa gracias a la clase Timeline de JavaFX, que permite ejecutar un evento cada cierto tiempo.
     */
    private void startTimer() {
        if (timer != null) timer.stop(); // Detener el temporizador si ya está en ejecución
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> { // Evento que se ejecuta cada segundo
            timeRemaining--; // Reducir el tiempo restante
            updateTimerLabel(); // Actualizar la etiqueta del temporizador
            if (timeRemaining <= 0) { // Si el tiempo restante es 0, mostrar mensaje de error
                handleError();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE); // Ejecutar el temporizador indefinidamente
        timer.play();                             // Iniciar el temporizador
    }

    /**
     * Metodo que valida si la palabra ingresada por el usuario es igual a la palabra actual del juego. Para esto, se
     * elimina cualquier espacio en blanco al inicio y al final de la palabra ingresada y se compara con la palabra
     * actual del juego.
     */
    private void validateWord() {
        if (inputField.getText().trim().equals(currentWord)) {
            timer.stop();
            messageLabel.setText("¡Correcto!");
            increaseLevel();
        } else {
            opportunities--;
            updateLifesImage();
            messageLabel.setText("Palabra incorrecta. Oportunidades restantes: " + opportunities);
            showNewWord();
            wordLabel.setText(currentWord);
            if (opportunities <= 0) {
                endGame();
            }
        }
    }

    /**
     * Metodo que se encarga de mostrar un mensaje de error al usuario cuando el tiempo se agota. En este caso, se reduce
     * el número de oportunidades y se muestra un mensaje indicando el número de oportunidades restantes.
     */
    private void handleError() {
        timer.stop();
        if (inputField.getText().trim().equals(currentWord)) {
            messageLabel.setText("¡Correcto!");
            increaseLevel();
            return;
        }
        opportunities--;
        updateLifesImage();
        messageLabel.setText("Tiempo agotado. Oportunidades restantes: " + opportunities);
        if (opportunities > 0) {
            endGame();
        } else {
            endGame();
        }
    }

    /**
     * Metodo que incrementa el nivel del juego, ajustando el tiempo inicial por nivel y seleccionando una nueva palabra.
     */
    private void increaseLevel() {
        level++;
        updateLevelLabel();
        adjustTimePerLevel();
        showNewWord();

        timeRemaining = calculateTimeByLevel();
        updateTimerLabel();
        startTimer();
    }

    /**
     * Metodo que ajusta el tiempo por nivel, reduciendo el tiempo por nivel en 2 segundos cada 5 niveles completados.
     */
    private void adjustTimePerLevel() {
        if (level % 5 == 0 && timeRemaining > 2) {
            timeRemaining -= 2;
        } else {
            timeRemaining = Math.max(timeRemaining, 2);
        }
    }

    /**
     * Metodo que se encarga de reiniciar el nivel actual del juego, seleccionando una nueva palabra.
     */
    private void resetLevel() {
        timeRemaining = calculateTimeByLevel();
        updateTimerLabel();
        showNewWord();
        startTimer();
    }

    /**
     * Metodo que calcula el tiempo por nivel del juego. Este método se basa en la regla establecida, que
     * consiste en reducir el tiempo por nivel en 2 segundos cada 5 niveles completados.
     *
     * @return el tiempo por nivel calculado.
     */
    private int calculateTimeByLevel() {
        // Start with 20 seconds, and reduce by 2 seconds for each 5 levels completed
        int baseTime = 20;
        int reduction = (level - 1) / 5 * 2; // Subtract 2 seconds every 5 levels
        return Math.max(baseTime - reduction, 2); // Ensure minimum time is 2 seconds
    }

    // Métodos auxiliares para actualizar la interfaz gráfica.
    private void updateTimerLabel() { timerLabel.setText(timeRemaining + "s"); }
    private void updateLevelLabel() { levelLabel.setText("Nivel: " + level); }

    /**
     * Metodo que finaliza el juego, mostrando un mensaje de juego terminado y el nivel alcanzado por el usuario.
     */
    private void endGame() {
        wordLabel.setText("Juego Terminado");
        messageLabel.setText("Lograste llegar al nivel: " + level);
        inputField.setDisable(true);
        validateButton.setDisable(true);
        timer.stop();
    }

    /**
     * Metodo que actualiza la imagen de las vidas restantes en la interfaz gráfica. La imagen de las vidas se actualiza
     * de acuerdo al número de oportunidades restantes.
     */
    private void updateLifesImage() {
        String imagePath = "/images/4_lifes.png"; // Imagen por defecto

        switch (opportunities) {
            case 3:
                imagePath = "/images/3_lifes.png";
                break;
            case 2:
                imagePath = "/images/2_lifes.png";
                break;
            case 1:
                imagePath = "/images/1_lifes.png";
                break;
            case 0:
                imagePath = "/images/0_lifes.png";
                break;
        }

        Image lifesImage = new Image(getClass().getResourceAsStream(imagePath));
        lifesImageView.setImage(lifesImage);
    }
}
