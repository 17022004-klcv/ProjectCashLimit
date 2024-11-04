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

import java.io.IOException;

public class AddAccountController {

    @FXML
    private Button bt_addAccount;

    @FXML
    private ComboBox<?> cbb_accounts;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_accountNumber;

    @FXML
    private TextField txt_amount;

    @FXML
    private TextField txt_bank;

    @FXML
    void bt_addAccount(ActionEvent event) {

    }

    @FXML
    void cbb_accounts(ActionEvent event) {

    }

    @FXML
    void imgBack(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/user.fxml", (Node) event.getSource());

    }

    @FXML
    void txt_accountNumber(ActionEvent event) {

    }

    @FXML
    void txt_amount(ActionEvent event) {

    }

    @FXML
    void txt_bank(ActionEvent event) {

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
