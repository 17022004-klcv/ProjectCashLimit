package com.example.cashlimit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

import static com.example.cashlimit.validations.validation.*;

public class singUpController {

    @FXML
    private Button bt_singUp;

    @FXML
    private ComboBox<?> cbb_accounts;

    @FXML
    private TextField txt_amount;

    @FXML
    private TextField txt_bank;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_firstname;

    @FXML
    private TextField txt_lastname;

    @FXML
    private TextField txt_numAccount;

    @FXML
    private TextField txt_phone;

    @FXML
    void bt_singUp(ActionEvent event) throws IOException {
        if (!emptyText(txt_amount)) {
            System.out.println("Monto vacío");
        } else if (!emptyText(txt_bank)) {
            System.out.println("Banco vacío");
        } else if (!emptyText(txt_email)) {
            System.out.println("Email vacío");
        } else if (!emptyText(txt_lastname)) {
            System.out.println("Apellido vacío");
        } else if (!emptyText(txt_phone)) {
            System.out.println("Teléfono vacío");
        } else if (!emptyText(txt_firstname)) {
            System.out.println("Nombre vacío");
        } else if (!emptyText(txt_numAccount)) {
            System.out.println("Número de cuenta vacío");
        } else {
            System.out.println("Ningún campo vacío");
            if (validateNumberFormat(txt_phone)){
                if (validateEmail(txt_email)){
                    if(validateAccountNumber(txt_numAccount)){
                        JOptionPane.showMessageDialog(null, "New user created with success !");

                        // Limpiar los campos después de agregar la factura
                        txt_amount.setText("");
                        txt_bank.setText("");
                        txt_email.setText("");
                        txt_lastname.setText("");
                        txt_phone.setText("");
                        txt_firstname.setText("");
                        txt_numAccount.setText("");

                        // Cambiar de vista si todos los campos están llenos
                        CambiarVista("/com/example/cashlimit/views/login.fxml", (Node) event.getSource());
                    }

                }

            }
        }


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
