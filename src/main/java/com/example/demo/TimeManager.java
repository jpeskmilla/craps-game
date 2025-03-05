package com.example.demo;

import java.util.concurrent.TimeUnit;

/**
 * Clase `Persona` que modela a una persona con atributos y comportamientos básicos.
 */
public class TimeManager {
    private int time; // Tiempo. En este caso, se va a manejar en segundos.

    public void TimeManager() {
        this.time = 20;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) { this.time = time; }
    public void countdown() {
        while (time > 0) {
            System.out.println("Tiempo Restante:" + time);
            time--;

            /*
             * Este bloque de codigo permite pausar la ejecucion del programa durante
             * segundo, para asi poder ir disminuyendo el tiempo de juego.
             */
            try { Thread.sleep(1000); } // Pausa de un segundo.
            catch (InterruptedException e) {System.out.println("Se acabo el tiempo");}

        };
    }

    }