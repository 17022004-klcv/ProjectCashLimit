package com.example.cashlimit.controllers;

import com.example.cashlimit.database.UserDAO;
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
import java.sql.SQLException;
import java.util.Map;

import static com.example.cashlimit.validations.validation.*;

public class userController {

    @FXML
    private Button btUpdateInfo;

    @FXML
    private ComboBox<String> cbb_accounts;

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
    void bt_logout(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/login.fxml", (Node) event.getSource());

    }

    @FXML
    void bt_addAccount(ActionEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/AddAccount.fxml", (Node) event.getSource());

    }
    String userName = "asd@jasd.com";





   // @FXML
    public void initialize() {
        try {
            // Llamamos a UserDAO para obtener los datos del usuario al iniciar la vista
            Map<String, String> userData = userDAO.getUserData(userId);
            System.out.println("el id recibido del usuario es " + userId);
            // Si se encuentran los datos del usuario, los mostramos en los TextFields
            if (!userData.isEmpty()) {
                txt_user.setText(userData.get("user_loggeo")); // Usar las nuevas claves
                txt_password.setText(userData.get("password_loggeo"));
                txt_email.setText(userData.get("mail_user"));
                txt_phone.setText(userData.get("tel_user"));
            } else {
                // Si no se encuentra el usuario, mostramos un mensaje
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }
        } catch (SQLException e) {
            // En caso de error en la consulta
            JOptionPane.showMessageDialog(null, "Error al consultar los datos: " + e.getMessage());
            e.printStackTrace();
        }


    }


    private int userId = 0;  // Variable para almacenar el id_user

    // Método para recibir el id_user desde el primer controlador
    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("ID del usuario recibido: " + userId); // Verificar que se recibió correctamente
    }
    private UserDAO userDAO = new UserDAO();
    @FXML
    void btUpdateInfo(ActionEvent event) throws SQLException {
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

                    String user = txt_user.getText();
                    String password = txt_password.getText();
                    String email = txt_email.getText();
                    String phone = txt_phone.getText();

                    UserDAO queryUser = new UserDAO();
                    queryUser.updateUserInformation(userName, password, email, phone);
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

