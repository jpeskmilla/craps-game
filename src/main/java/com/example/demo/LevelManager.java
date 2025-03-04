package com.example.demo;

public class LevelManager {
    private int level;
    WordManager wordManager;
    TimeManager timeManager;

    public LevelManager() {
    }

    public int getLevel() {
        return level;
    }

    public int levelUp() {
        if (wordManager.checkWord(wordManager.userInput()) == true) {
            level++;
        } else {
            level = 0;
        }
        return level;
    }

    public void timeDecreaseByLevel(int level) {
        while (level % 5 == 0) { timeManager.setTime(timeManager.getTime() - 2); }
    }
}


