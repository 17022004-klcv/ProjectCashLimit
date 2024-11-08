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

import static com.example.cashlimit.validations.validation.*;

public class userController {

    @FXML
    private Button btUpdateInfo;

    @FXML
    private ComboBox<?> cbb_accounts;

    @FXML
    private ImageView imgBill;

    @FXML
    private ImageView imgCategory;

    @FXML
    private ImageView imgIncome;

    @FXML
    private ImageView imgUser;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_phone;

    @FXML
    private TextField txt_user;
    @FXML
    private Button bt_addAccount;

    @FXML
    private ImageView imglog_out;

    @FXML
    void imgLog_out(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/login.fxml", (Node) event.getSource());

    }
    @FXML
    void bt_addAccount(ActionEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/AddAccount.fxml", (Node) event.getSource());

    }
    @FXML
    void btUpdateInfo(ActionEvent event) {
        if(!emptyText(txt_user)){
            System.out.println("usuario vacio");
        }else if(!emptyText(txt_password)){
            System.out.println("contra vacio");
        }else if(!emptyText(txt_email)){
            System.out.println("email vacio");
        }else if(!emptyText(txt_phone)){
            System.out.println("telefono vacio");
        }else{

            System.out.println("ningun campo vacio");
            if(validateNumberFormat(txt_phone)){
                if(validateEmail(txt_email)){
                    JOptionPane.showMessageDialog(null, "Updated information!");
                }
            }

        }
    }

    @FXML
    void imgBill(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());
    }

    @FXML
    void imgCategory(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/category.fxml", (Node) event.getSource());
    }

    @FXML
    void imgIncome(MouseEvent event) throws IOException{
        CambiarVista("/com/example/cashlimit/views/income.fxml", (Node) event.getSource());
    }

    @FXML
    void imgUser(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/user.fxml", (Node) event.getSource());
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

