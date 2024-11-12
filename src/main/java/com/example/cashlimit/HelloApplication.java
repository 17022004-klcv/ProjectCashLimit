package com.example.cashlimit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.cashlimit.database.Conneection;
import java.io.IOException;

import static javafx.application.Application.launch;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CashLimit");
        stage.setScene(scene);
        stage.show();
        if(Conneection.getConnection() != null) {
            System.out.println("Conexión exitosa");

        } else {
            System.out.println("Conexión fallida");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}