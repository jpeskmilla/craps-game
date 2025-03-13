package com.example.crapgame.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameModel {

    private final List<String> words = Arrays.asList(
            "casa", "perro", "rápido", "computadora", "java",
            "programación", "teclado", "pantalla", "ratón", "ventana",
            "algoritmo", "estructura", "objeto", "evento", "clase"
    );
    private int level;
    private int timePerLevel;
    private int opportunities;
    private String currentWord;
    private final Random random;

    public GameModel() {
        this.level = 1;
        this.timePerLevel = 20;
        this.opportunities = 4;
        this.random = new Random();
    }

    // Inicializar juego
    public void startGame() {
        level = 1;
        timePerLevel = 20;
        opportunities = 4;
        selectNewWord();
    }

    // Obtener nueva palabra
    public String selectNewWord() {
        currentWord = words.get(random.nextInt(words.size()));
        return currentWord;
    }

    // Validar palabra escrita
    public boolean validateWord(String input) {
        return input.trim().equals(currentWord);
    }

    // Subir de nivel
    public void increaseLevel() {
        level++;
        adjustTimePerLevel();
        selectNewWord();
    }

    // Ajustar tiempo según nivel
    private void adjustTimePerLevel() {
        if (level % 5 == 0 && timePerLevel > 2) {
            timePerLevel = Math.max(2, timePerLevel - 2);
        }
    }

    // Registrar error y reducir oportunidades
    public void registerError() {
        opportunities--;
    }

    // Reiniciar mismo nivel tras error
    public void resetCurrentLevel() {
        selectNewWord();
    }

    // Getters
    public int getLevel() {
        return level;
    }

    public int getTimePerLevel() {
        return timePerLevel;
    }

    public int getOpportunities() {
        return opportunities;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    // Verificar si juego sigue activo
    public boolean isGameOver() {
        return opportunities <= 0;
    }
}