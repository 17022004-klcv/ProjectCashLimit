package com.example.cashlimit.controllers;

import com.example.cashlimit.database.LoggeoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    void bt_register(ActionEvent event) throws IOException {
        if (!emptyText_Trim(txt_user)) {
            System.out.println("Usuario vacío");
        } else if (!emptyText_Trim(txt_password)) {
            System.out.println("Contraseña vacía");
        } else if (!emptyText_Trim(txt_passwordConfim)) {
            System.out.println("Confirmación de contraseña vacía");
        } else {
            String user = txt_user.getText();
            String password = txt_password.getText();
            String confirmPassword = txt_passwordConfim.getText();

            // Validar que las contraseñas coincidan
            if (!password.equals(confirmPassword)) {
                // Mostrar alerta si no coinciden
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Registro");
                alert.setHeaderText("Contraseñas no coinciden");
                alert.setContentText("Por favor, verifica que las contraseñas ingresadas sean iguales.");
                alert.showAndWait();
            } else {
                // Si coinciden, registrar el loggeo
                LoggeoDAO queryInsertUser = new LoggeoDAO();
                queryInsertUser.registrarLoggeo(userId, user, password);

                // Cambiar a la vista de dashboard
                CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());
            }
        }
    }



    private int userId; // Variable para almacenar el id_user

    // Método para recibir el id_user desde el primer controlador
    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("ID del usuario recibido: " + userId); // Verificar que se recibió correctamente
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
