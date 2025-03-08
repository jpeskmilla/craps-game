package com.example.demo;

/*
* Clase que se encarga de manejar todo lo relacionado con los niveles. En ella podemos encontrar
* funciones encargadas del aumento de niveles, además del metodo para disminuir el tiempo total de
* juego cada 5 niveles.
* */
public class LevelManager {
    private int level = 1; // Atributo que va a representar los niveles.
    WordManager wordManager = new WordManager(); // Se instancia la clase WordManager.
    TimeManager timeManager = new TimeManager(); // Se instancia la clase TimeManager.

    public LevelManager() { } // Metodo constructor vacio.

    /**
     * Método para obtener el nivel del juego.
     *
     * @return El nivel en el que se encuentra el jugador
     */
    public int getLevel() { return level; }

    /**
     * Metodo encargado para subir el nivel del juego. Esta posee un condicional que va a revisar
     * si la entrada del usuario es correcta. En caso de ser correcta, el nivel va a ser
     * aumentado en 1.
     *
     * @return El nivel del juego.
     */
    public int levelUp() {
        if (wordManager.checkWord(wordManager.userInput()) == true) { level++; }
        else { level = 0; }
        return level;
    }

    /**
     * Metodo que se encarga de reducir el tiempo en 2 segundos cada 5 niveles.
     * @param level Se pasa el nivel como parametro para revisar si este es un múltiplo de 5.
     */
    public void timeDecreaseByLevel(int level) {
        while (level % 5 == 0) { timeManager.setTime(timeManager.getTime() - 2); }
    }

}


