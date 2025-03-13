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

public class GameController {

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

    private List<String> words = Arrays.asList("casa", "perro", "rápido", "computadora", "java", "programación", "teclado", "pantalla", "ratón", "ventana");
    private int level = 1;
    private int timeRemaining = 20;
    private int opportunities = 4; // Cada error eclipsa 25%
    private Timeline timer;
    private String currentWord;
    private final Random random = new Random();

    @FXML
    public void initialize() {
        setupEvents();
        startGame();
    }

    public GameController() {}

    private void setupEvents() {
        validateButton.setOnAction(e -> validateWord());
        inputField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                validateWord();
            }
        });
    }

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

    private void showNewWord() {
        currentWord = words.get(random.nextInt(words.size()));
        wordLabel.setText(currentWord);
        inputField.clear();
    }

    private void startTimer() {
        if (timer != null) timer.stop();
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeRemaining--;
            updateTimerLabel();
            if (timeRemaining <= 0) {
                handleError();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void validateWord() {
        if (inputField.getText().trim().equals(currentWord)) {
            timer.stop();
            messageLabel.setText("¡Correcto!");
            increaseLevel();
        } else {
            opportunities--;
            updateLifesImage();
            messageLabel.setText("Palabra incorrecta. Oportunidades restantes: " + opportunities);
            if (opportunities <= 0) {
                endGame();
            }
        }
    }

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

    private void increaseLevel() {
        level++;
        updateLevelLabel();
        adjustTimePerLevel();
        showNewWord();

        timeRemaining = calculateTimeByLevel();
        updateTimerLabel();
        startTimer();
    }

    private void adjustTimePerLevel() {
        if (level % 5 == 0 && timeRemaining > 2) {
            timeRemaining -= 2;
        } else {
            timeRemaining = Math.max(timeRemaining, 2);
        }
    }

    private void resetLevel() {
        timeRemaining = calculateTimeByLevel();
        updateTimerLabel();
        showNewWord();
        startTimer();
    }

    private int calculateTimeByLevel() {
        // Start with 20 seconds, and reduce by 2 seconds for each 5 levels completed
        int baseTime = 20;
        int reduction = (level - 1) / 5 * 2; // Subtract 2 seconds every 5 levels
        return Math.max(baseTime - reduction, 2); // Ensure minimum time is 2 seconds
    }

    private void updateTimerLabel() {
        timerLabel.setText(timeRemaining + "s");
    }

    private void updateLevelLabel() {
        levelLabel.setText("Nivel: " + level);
    }

    private void endGame() {
        wordLabel.setText("Juego Terminado");
        messageLabel.setText("Lograste llegar al nivel: " + level);
        inputField.setDisable(true);
        validateButton.setDisable(true);
        timer.stop();
    }

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
