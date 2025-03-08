package com.example.demo;

/*
* Esta clase va a ser la encargada de manejar todo lo que este relacionado con las vidas
* dentro del juego. En ella se encuentran los métodos para establecer las vidas, así como
* para decrementarlas y detectar cuando al usuario no le queden vidas.
* */
public class LifesManager {
    private int lifes; // Atributo de la clase que representa las vidas del jugador.
    WordManager wordManager = new WordManager(); // Instancia de la clase WordManager".

    public LifesManager() { this.lifes = 4; }  // Se establecen las vidas del jugador.
    public int getLifes() { return lifes; } // Metodo para acceder a las vidas
    /*
    * Este metodo se encarga de reducir las vidas del usuario. Cuando la entrada ingresada por
    * el usuario no coincide con la palabra generada, esta será detectada como un error, haciendo
    * que el usuario pierda una vida. */
    public void decreaseLifes() {
        if (wordManager.checkWord(wordManager.userInput()) == false) { lifes--; }
    }
    /*
    * Este metodo se va a encargar de revisar cuando las vidas sean cero, para asi enviar un
    * mensaje, avisando que el usuario se ha quedado sin vidas.
    * */
    public void lifesOver() { if (lifes == 0) { System.out.println("Has perdido"); } }
}
