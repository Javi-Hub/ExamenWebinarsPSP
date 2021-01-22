package com.sanvalero.examenWebinarPSP.domain;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;

/**
 * Creado por @author: Javier
 * el 22/01/2021
 */
public class Tarea extends Task {

    private boolean detener, pausar;
    public double tiempoInicio;
    public double tiempoFinal;

    public Tarea(int tiempoInicio, int tiempoFinal) {
        detener = false;
        pausar = false;
        this.tiempoInicio = tiempoInicio;
        this.tiempoFinal = tiempoFinal;

    }

    public void detener(){
        detener = true;
    }

    public void pausar() {
        if(pausar) {
            pausar = false;
        } else {
            pausar = true;
        }
    }


    @Override
    protected Object call() throws Exception {
        double i;
        for (i = tiempoInicio; i <= tiempoFinal ; i++) {
            Thread.sleep(1000);
            updateProgress(i, tiempoFinal);
            updateMessage((int) i + " de " + (int) tiempoFinal + " -- " + (int)((i / tiempoFinal) * 100) + "%");
            if (detener) {
                break;
            }
            while (pausar){
                Thread.sleep(1000);
            }
        }
        return i;
    }
}
