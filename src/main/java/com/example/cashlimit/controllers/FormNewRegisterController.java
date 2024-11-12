package com.example.cashlimit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.cashlimit.validations.validation.emptyText_Trim;

public class FormNewRegisterController {

    @FXML
    private Button bt_register;

    @FXML
    private PasswordField txt_password;

    @FXML
    private PasswordField txt_passwordConfim;

    @FXML
    private TextField txt_user;

    @FXML
    void bt_register(ActionEvent event)  throws IOException  {
        if(!emptyText_Trim(txt_user)){
            System.out.println("usuario vacio");
        }else if(!emptyText_Trim(txt_password)){
            System.out.println("pass vacio");
        }else if (!emptyText_Trim(txt_passwordConfim)){
            System.out.println("pass confirm vacio");
        }else{
            CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());
        }
    }

    @FXML
    void txt_password(ActionEvent event) {

    }

    @FXML
    void txt_passwordConfirm(ActionEvent event) {

    }

    @FXML
    void txt_user(ActionEvent event) {

    }

    public static void CambiarVista(String ruta, Node bt) throws IOException {
        FXMLLoader loader = new FXMLLoader(dashboardController.class.getResource(ruta));
        Parent root = loader.load();

        Stage stage = (Stage) bt.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        // stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.show();
    }
}
