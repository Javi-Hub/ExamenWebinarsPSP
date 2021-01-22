package com.sanvalero.examenWebinarPSP;

import com.sanvalero.examenWebinarPSP.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Creado por @author: Javier
 * el 22/01/2021
 */
public class App extends Application {

    @Override
    public void init () throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Cargar la ventada de multiDownload
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("examen_webinar.fxml"));
        loader.setController(new AppController());
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }

}
