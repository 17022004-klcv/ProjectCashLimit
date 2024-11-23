package com.example.cashlimit.controllers;

import com.example.cashlimit.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.cashlimit.validations.validation.emptyText;
import static com.example.cashlimit.validations.validation.validateEmail;

public class ForgotPasswordController {

    @FXML
    private Button bt_check;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_user;

    @FXML
    void bt_check(ActionEvent event) throws IOException {

        if(emptyText(txt_user) && emptyText(txt_password)){
            if(validateEmail(txt_user)){

                String email = txt_user.getText();  // Obtenemos el correo desde un TextField
                String newPassword = txt_password.getText();  // Obtenemos la nueva contraseña desde un TextField

                try {
                    UserDAO queryUser = new UserDAO();  // Asegúrate de que esta instancia esté bien inicializada

                    if(queryUser.forgotPassword(email, newPassword)){
                        // Si la contraseña se actualizó correctamente
                        JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente.");
                        CambiarVista("/com/example/cashlimit/views/login.fxml", (Node) event.getSource());
                    }else{
                        // Si hubo un error al buscar el correo o actualizar la contraseña
                        JOptionPane.showMessageDialog(null, "Error al actualizar la contraseña. No se encontró el correo.");
                    }



                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar la contraseña: " + e.getMessage());
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