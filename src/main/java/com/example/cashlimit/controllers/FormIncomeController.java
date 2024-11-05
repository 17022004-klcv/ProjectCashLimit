package com.example.cashlimit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

import static com.example.cashlimit.validations.validation.emptyText;
import static com.example.cashlimit.validations.validation.validateAmount;

public class FormIncomeController {

    @FXML
    private ComboBox<?> cbb_accounts;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_amount;

    @FXML
    private TextField txt_description;

    @FXML
    private Button btn_addIncome;

    @FXML
    void btn_addIncome(ActionEvent event) {
        if(!emptyText(txt_description)){
            System.out.println("descripcion vacio");
        }else if(!emptyText(txt_amount)){
            System.out.println("monto vacio");
        }else{
            if(validateAmount(txt_amount)) {
                JOptionPane.showMessageDialog(null, "Income Added!");
                txt_description.setText("");
                txt_amount.setText("");
            }
        }
    }
    @FXML
    void imgBack(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/income.fxml", (Node) event.getSource());
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

