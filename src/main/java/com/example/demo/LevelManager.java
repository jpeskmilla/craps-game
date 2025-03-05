package com.example.demo;

public class LevelManager {
    private int level = 1; // Inicializamos directamente
    WordManager wordManager = new WordManager(); // Inicializa el manager aquí
    TimeManager timeManager = new TimeManager(); // Inicializa el manager aquí

    public LevelManager() { } // El constructor puede quedar vacío


    public int getLevel() {
        return level;
    }

    public int levelUp() {
        if (wordManager.checkWord(wordManager.userInput()) == true) { level++; }
        else { level = 0; }
        return level;
    }

    public void timeDecreaseByLevel(int level) {
        while (level % 5 == 0) { timeManager.setTime(timeManager.getTime() - 2); }
    }

}


