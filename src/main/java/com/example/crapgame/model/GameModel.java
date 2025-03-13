package com.example.crapgame.model;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Modelo de datos del juego
 *
 * @author Juan Pablo Escamilla (2420580-2724)
 */
public class GameModel {

    //Lista de palabras disponibles para el juego para que estas sean seleccionadas aleatoriamente.
    private final List<String> words = Arrays.asList(
            "casa", "perro", "rápido", "computadora", "java",
            "programación", "teclado", "pantalla", "ratón", "ventana",
            "algoritmo", "estructura", "objeto", "evento", "clase"
    );

    /**
     * Atributos del modelo. Estos incluyen el nivel, el tiempo, las vidas y la palabra actual, además de una
     * instancia de Random para seleccionar palabras aleatorias.
     */
    private int level;
    private int timePerLevel;
    private int opportunities;
    private String currentWord;
    private final Random random;

    /**
     * Constructor del modelo de juego, que se encarga de establecer los atributos iniciales del juego. Estos son:
     * - Nivel inicial: 1
     * - Tiempo por nivel: 20 segundos
     * - Oportunidades: 4
     * - Instancia de Random para selección aleatoria de palabras
     */
    public GameModel() {
        this.level = 1;
        this.timePerLevel = 20;
        this.opportunities = 4;
        this.random = new Random();
    }

    /**
     * Método que inicia el juego, estableciendo los valores iniciales de nivel, tiempo y oportunidades, y seleccionando
     */
    public void startGame() {
        level = 1;
        timePerLevel = 20;
        opportunities = 4;
        selectNewWord();
    }

    /**
     * Método que selecciona una nueva palabra aleatoria de la lista de palabras disponibles y la establece como la
     * palabra actual del juego.
     * @return La palabra seleccionada aleatoriamente.
     */
    public String selectNewWord() {
        currentWord = words.get(random.nextInt(words.size()));
        return currentWord;
    }

    /**
     * Método que valida si la palabra ingresada por el usuario es igual a la palabra actual del juego. Para esto, se
     * elimina cualquier espacio en blanco al inicio y al final de la palabra ingresada y se compara con la palabra
     * actual del juego.
     *
     * @param input - La palabra ingresada por el usuario
     * @return true si la palabra ingresada es igual a la palabra actual, false en caso contrario.
     */
    public boolean validateWord(String input) { return input.trim().equals(currentWord); }

    /**
     * Método que incrementa el nivel del juego, ajustando el tiempo inicial por nivel y seleccionando una nueva palabra.
     */
    public void increaseLevel() {
        level++;
        adjustTimePerLevel();
        selectNewWord();
    }

    /**
     * Método que ajusta el tiempo por nivel del juego. Este método se llama cada vez que el nivel del juego aumenta y
     * ajusta el tiempo por nivel de acuerdo a la regla de negocio establecida. En este caso, se reduce el tiempo por
     * nivel en 2 segundos cada 5 niveles, hasta llegar a un mínimo de 2 segundos.
     */
    private void adjustTimePerLevel() {
        if (level % 5 == 0 && timePerLevel > 2) {
            timePerLevel = Math.max(2, timePerLevel - 2);
        }
    }

    /**
     * Método que decrementa las vidas del juego. Este método se llama cada vez que el usuario comete un error
     */
    public void registerError() { opportunities--; }

    /**
     * Método que reinicia el nivel actual del juego, seleccionando una nueva palabra.
     */
    public void resetCurrentLevel() { selectNewWord(); }

    /**
     * Métodos getter para los atributos del modelo. Estos métodos permiten obtener los valores de los atributos del
     * modelo desde otras clases.
     */
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

    /**
     * Método que indica si el juego ha terminado. El juego termina cuando las oportunidades llegan a 0.
     * @return true si el juego ha terminado, false en caso contrario.
     */
    public boolean isGameOver() { return opportunities <= 0; }
}