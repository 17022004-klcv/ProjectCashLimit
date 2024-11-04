package com.example.cashlimit.controllers;

import com.example.cashlimit.HelloApplication;
import com.example.cashlimit.validations.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.cashlimit.validations.validation.emptyText;

public class logincontroller {

    @FXML
    private Button bt_login;

    @FXML
    private Label lb_create_account;

    @FXML
    private Label lb_forgot_password;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_user;

    @FXML
    void lb_create_account(MouseEvent event) throws IOException{
        CambiarVista("/com/example/cashlimit/views/singUp.fxml", (Node) event.getSource());

    }

    @FXML
    void lb_forgot_password(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/forgotPassword.fxml", (Node) event.getSource());

    }

    @FXML
    void bt_login(ActionEvent event) throws IOException {
        if(emptyText(txt_user) && emptyText(txt_password)){
            CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());
        }
    }
    validation validate = new validation();
    @FXML
    void txt_password(ActionEvent event) {
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