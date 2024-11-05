package com.example.cashlimit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import javax.swing.*;
import java.io.IOException;

import static com.example.cashlimit.validations.validation.emptyText;
import static com.example.cashlimit.validations.validation.validateAmount;

public class FormBillController {

    @FXML
    private ComboBox<?> cbb_accounts;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_description;

    @FXML
    private TextField txt_amount;

    @FXML
    private Button bt_AddBill;

    @FXML
    void bt_AddBill(ActionEvent event) {
        if (!emptyText(txt_description)) {
            System.out.println("descripcion vacio");
        } else if (!emptyText(txt_amount)) {
            System.out.println("monto vacio");
        } else {
            System.out.println("ningun campo vacio");
            if (validateAmount(txt_amount)) {
                JOptionPane.showMessageDialog(null, "Bill Added!");
                txt_description.setText("");
                txt_amount.setText("");
            }

        }
    }

    @FXML
    void imgBack(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());

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
