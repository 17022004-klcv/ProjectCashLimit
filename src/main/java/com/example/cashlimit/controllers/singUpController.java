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

import java.io.IOException;

import static com.example.cashlimit.validations.validation.emptyText;

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
        if(emptyText(txt_amount) && emptyText(txt_bank) && emptyText(txt_email) && emptyText(txt_lastname) && emptyText(txt_phone)
        && emptyText(txt_firstname) && emptyText(txt_numAccount)){
            CambiarVista("/com/example/cashlimit/views/login.fxml", (Node) event.getSource());
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
