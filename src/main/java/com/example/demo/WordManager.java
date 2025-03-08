package com.example.demo;
import java.util.Random;
import java.util.Scanner;

/**
 * La clase WordManager se encarga de gestionar palabras. Esto incluye generar
 * palabras al azar y verificar coincidencias con otras palabras.
 */
public class WordManager {
    private String randomWord; // Palabra generada aleatoriamente

    /**
     * Constructor de la clase WordManager.
     * Inicializa una nueva palabra aleatoria automáticamente.
     */
    public WordManager() { this.randomWord = generateWords(); }

    /**
     * Genera una palabra aleatoria seleccionada de un conjunto predefinido de palabras.
     * @return Una palabra generada aleatoriamente.
     */
    private String generateWords() {
        String[] wordsArray = {"Reloj", "Brisa", "Montaña", "Silencio", "Espejo", "Relámpago",
                "Bosque", "Nube", "Escalera", "Viento", "Sombrilla", "Cristal",
                "Refugio", "Horizonte", "Cueva", "Mariposa", "Noche", "Fantasma",
                "Relato", "Estrella", "Camino", "Tinta", "Susurro", "Papel"};
        return wordsArray[new Random().nextInt(wordsArray.length)];
    }

    /**
     * Obtiene la palabra generada aleatoriamente.
     *
     * @return La palabra generada.
     */
    public String getRandomWord() { return randomWord; }

    /**
     * Permite capturar la palabra ingresada por el usuario
     *
     * @return La entrada que ingresó el usuario
     */
    public String userInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una palabra:");
        String userWordInput = scanner.nextLine();
        return userWordInput;
    }

    /**
     * Verifica si una palabra ingresada coincide con la palabra generada aleatoriamente,
     * ignorando mayúsculas y minúsculas.
     *
     * @param userInput La palabra ingresada por el usuario.
     * @return {@code true} si las palabras coinciden, {@code false} de lo contrario.
     */
    public boolean checkWord(String userInput) { return userInput.equalsIgnoreCase(randomWord); }

    /**
     * Genera una nueva palabra aleatoria, reemplazando la generada previamente.
     */
    public void regenerateWord() { this.randomWord = generateWords(); }
}