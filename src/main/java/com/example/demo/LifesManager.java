package com.example.demo;

public class LifesManager {
    private int lifes;
    WordManager wordManager = new WordManager();

    public LifesManager() { this.lifes = 4; }
    public int getLifes() { return lifes; }
    public void decreaseLifes() {
        if (wordManager.checkWord(wordManager.userInput()) == false) { lifes--; }
    }
    public void lifesOver() { if (lifes == 0) { System.out.println("Has perdido"); } }
}
