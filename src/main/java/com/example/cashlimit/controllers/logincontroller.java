package com.example.cashlimit.controllers;

import com.example.cashlimit.HelloApplication;
import com.example.cashlimit.database.LoggeoDAO;
import com.example.cashlimit.database.UserDAO;
import com.example.cashlimit.model.Loggeo;
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

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.cashlimit.validations.validation.emptyText;
import static com.example.cashlimit.validations.validation.emptyText_Trim;

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
    void bt_login(ActionEvent event) throws IOException, SQLException {

        if(!emptyText_Trim(txt_user)){
            System.out.println("usuario vacio");
        }else if(!emptyText_Trim(txt_password)){
            System.out.println("pass vacio");
        }else{

            //data for loggeo table
            String user = txt_user.getText();
            String password = txt_password.getText();

            Loggeo loggeo = new Loggeo(user, password);
            LoggeoDAO queryLoggeo = new LoggeoDAO();

            if(queryLoggeo.isExitUser(loggeo)){

                UserDAO queryuser = new UserDAO();
                int userId = queryuser.getUserIdByCredentials(user, password);

                userController controlleruser = new userController();
                controlleruser.setUserId(userId);

                FormBillController bill = new FormBillController();
                bill.setUserId(userId);

                System.out.println("el id enviado es " + userId);
                CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());
            }else{
                JOptionPane.showMessageDialog(null, "User or Password incorrect!");
            }



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