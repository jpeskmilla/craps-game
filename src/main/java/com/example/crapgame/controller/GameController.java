package com.example.crapgame.controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameController {

    @FXML private Label wordLabel;
    @FXML private TextField inputField;
    @FXML private Button validateButton;
    @FXML private ProgressBar eclipseProgress;
    @FXML private Label timerLabel;
    @FXML private Label levelLabel;
    @FXML private Label messageLabel;

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
        eclipseProgress.setProgress(1.0);
        messageLabel.setText("");
        updateLevelLabel();
        showNewWord();
        startTimer();
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
            messageLabel.setText("¡Incorrecto, intenta de nuevo!");
        }
    }

    private void handleError() {
        timer.stop();
        opportunities--;
        updateEclipseProgress();
        messageLabel.setText("Tiempo agotado. Oportunidades restantes: " + opportunities);
        if (opportunities > 0) {
            resetLevel();
        } else {
            endGame();
        }
    }

    private void increaseLevel() {
        level++;
        updateLevelLabel();
        adjustTimePerLevel();
        showNewWord();
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
        int reducedTime = 20 - ((level / 5) * 2);
        return Math.max(reducedTime, 2);
    }

    private void updateTimerLabel() {
        timerLabel.setText("Tiempo: " + timeRemaining + "s");
    }

    private void updateLevelLabel() {
        levelLabel.setText("Nivel: " + level);
    }

    private void updateEclipseProgress() {
        double progress = opportunities / 4.0;
        eclipseProgress.setProgress(progress);
    }

    private void endGame() {
        wordLabel.setText("Juego Terminado");
        messageLabel.setText("Lograste llegar al nivel: " + level);
        inputField.setDisable(true);
        validateButton.setDisable(true);
    }
}
