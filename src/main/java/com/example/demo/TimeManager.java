package com.example.demo;

import java.util.concurrent.TimeUnit;

/**
 * Clase `Persona` que modela a una persona con atributos y comportamientos básicos.
 */
public class TimeManager {
    private int time; // Tiempo. En este caso, se va a manejar en segundos.

    public void TimeManager() {
        this.time = 20;
    }   // Permite establecer el tiempo de juego.

    /**
     * Obtiene el tiempo actual.
     *
     * @return el valor del tiempo en segundos.     *
     */
    public int getTime() { return time; }

    /**
     * Establece el tiempo en segundos.
     *
     * @param time El valor del tiempo a establecer.
     */
    public void setTime(int time) { this.time = time; }

    /*
    * Este metodo se encarga de simular el cronometro que se va a usar para llevar el registro
    * del tiempo de juego.
    * */

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