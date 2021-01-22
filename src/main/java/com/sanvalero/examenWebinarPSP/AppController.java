package com.sanvalero.examenWebinarPSP;

import com.sanvalero.examenWebinarPSP.domain.Tarea;
import com.sanvalero.examenWebinarPSP.util.AlertUtils;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Creado por @author: Javier
 * el 22/01/2021
 */
public class AppController {

    public Label lbContador, lbEstado;
    public TextField tfValorInicial, tfValorFinal;
    public ProgressBar pbProgreso;
    public Button btPausar, btIniciar, btDetener;

    private Tarea tarea;


    @FXML
    public void iniciar(){
        int valorInicial = Integer.parseInt(tfValorInicial.getText());
        int valorFinal = Integer.parseInt(tfValorFinal.getText());
        if (valorFinal < valorInicial){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("El Valor Inicial debe ser inferior al Valor Final");
            alert.show();
            return;
        }

        lbEstado.setText("Contador en MARCHA");
        tarea = new Tarea(valorInicial, valorFinal);

        pbProgreso.progressProperty().unbind();
        pbProgreso.progressProperty().bind(tarea.progressProperty());

        new Thread(tarea).start();

        tarea.messageProperty().addListener((observableValue, oldValue, newValue) -> lbContador.setText(newValue));

        tarea.stateProperty().addListener((observableValue, state, newState) -> {
            if(newState == Worker.State.SUCCEEDED) {
                AlertUtils.mostrarInfo("Finalizado contador");
            }
        });
    }

    @FXML
    public void pausar() {
        tarea.pausar();
        if(btPausar.getText().equals("Pausar")) {
            btPausar.setText("Continuar");
            lbEstado.setText("Contador en PAUSA");
        }
        else if(btPausar.getText().equals("Continuar")) {
            btPausar.setText("Pausar");
            lbEstado.setText("Continua CONTADOR");
        }

    }

    @FXML
    public void detener(){
        tarea.detener();
        lbEstado.setText("Contador DETENIDO");
    }

}
