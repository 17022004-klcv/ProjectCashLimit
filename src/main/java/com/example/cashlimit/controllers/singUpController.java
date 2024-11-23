package com.example.cashlimit.controllers;

import com.example.cashlimit.database.AccountsDAO;
import com.example.cashlimit.database.CategoryBillDAO;
import com.example.cashlimit.database.UserDAO;
import com.example.cashlimit.model.Accounts;
import com.example.cashlimit.model.User;
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
import java.sql.SQLException;

import static com.example.cashlimit.validations.validation.*;

public class singUpController {

    @FXML
    private Button bt_singUp;

    @FXML
    private ComboBox<String> cbb_accounts;

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

    UserDAO queryUser = new UserDAO();
    AccountsDAO queryAccount = new AccountsDAO();

    @FXML
    public void initialize() throws SQLException {
        cargarCategorias();
    }

    AccountsDAO queryCategorys = new AccountsDAO();
    public void cargarCategorias() {
        cbb_accounts.getItems().clear();
        cbb_accounts.getItems().addAll(queryCategorys.getCategorys());
    }
    @FXML
    void bt_singUp(ActionEvent event) throws IOException, SQLException {
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

                        //data for Table user
                        String name = txt_firstname.getText();
                        String lastname = txt_lastname.getText();
                        String mail = txt_email.getText();
                        String tel = txt_phone.getText();

                        // data for table accounts
                        String num_account = txt_numAccount.getText();
                        double amount = Double.parseDouble(txt_amount.getText());
                        String bank = txt_bank.getText();

                        // falta agregar el tipo de cuenta con el combobox


                        System.out.println("nombre " + name);
                        System.out.println("mail "+mail);

                        User user = new User(name, lastname, mail, tel);
                        Accounts account = new Accounts(num_account, amount, bank, 0, 1, "");

                        queryUser.RegisterUser(account, name, lastname, mail, tel);
                        // queryAccount.RegisterAccount(account);
                        UserDAO queryUserId = new UserDAO();
                        int userId = queryUserId.obtenerIdUsuario(mail);

                        JOptionPane.showMessageDialog(null, "Your registration is almost there!");

                        cambiarVista(event, userId);
                        // Limpiar los campos después de agregar la factura
//                        txt_amount.setText("");
//                        txt_bank.setText("");
//                        txt_email.setText("");
//                        txt_lastname.setText("");
//                        txt_phone.setText("");
//                        txt_firstname.setText("");
//                        txt_numAccount.setText("");

                        // Cambiar de vista si todos los campos están llenos
                        //CambiarVista("/com/example/cashlimit/views/FormNewRegister.fxml", (Node) event.getSource());
                    }

                }

            }
        }


    }
    private void cambiarVista(ActionEvent event, int userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cashlimit/views/FormNewRegister.fxml"));
            Parent root = loader.load();

            // Pasar el id_user al controlador de la nueva vista
            FormNewRegisterController controlador = loader.getController();

            controlador.setUserId(userId);


            // Crear e inyectar manualmente el controlador adicional (userController)
            // Cambiar la escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
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
