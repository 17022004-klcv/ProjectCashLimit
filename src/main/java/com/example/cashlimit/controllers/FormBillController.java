package com.example.cashlimit.controllers;

import com.example.cashlimit.database.BillsDAO;
import com.example.cashlimit.database.CategoryBillDAO;
import com.example.cashlimit.model.Bills;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import static com.example.cashlimit.validations.validation.emptyText;
import static com.example.cashlimit.validations.validation.validateAmount;
import static javafx.scene.input.KeyCode.Y;

public class FormBillController {

    @FXML
    private ComboBox<String> cbb_category;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_description;

    @FXML
    private TextField txt_amount;

    @FXML
    private Button bt_AddBill;

    @FXML
    void cbb_category(ActionEvent event) {

    }

    @FXML
    public void initialize() throws SQLException {
        cargarCategorias();
    }
    private int userId; // Variable para almacenar el id_user

    // Método para recibir el id_user desde el primer controlador
    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("ID del usuario recibido: " + userId); // Verificar que se recibió correctamente
    }


    @FXML
    void bt_AddBill(ActionEvent event) throws SQLException {
        if (!emptyText(txt_description)) {
            System.out.println("descripcion vacio");
        } else if (!emptyText(txt_amount)) {
            System.out.println("monto vacio");
        } else {
            System.out.println("ningun campo vacio");
            if (validateAmount(txt_amount)) {
                BillsDAO queryBill = new BillsDAO();
                CategoryBillDAO queryCategory = new CategoryBillDAO();
                //data for
                double amount = Double.parseDouble(txt_amount.getText());
                String descrip = txt_description.getText();
                LocalDate fechaActual = LocalDate.now();
                String date = String.valueOf(fechaActual);

                // Obtener la categoría seleccionada del ComboBox
                String seleccion = manejarSeleccion();
                int categoryId = queryCategory.searchCategory(seleccion);

                System.out.println("el id recibido " + userId);
                Bills bill = new Bills(amount, date, descrip, categoryId, 1);
                queryBill.insertBill(bill);
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
    CategoryBillDAO queryCategorys = new CategoryBillDAO();
    public void cargarCategorias() {
        cbb_category.getItems().clear();
        cbb_category.getItems().addAll(queryCategorys.getCategorys());
    }

    public String manejarSeleccion() {
        // Obtener el valor seleccionado al hacer clic o usar el ComboBox
        String seleccion = cbb_category.getValue();
        System.out.println("Seleccionaste desde manejarSeleccion: " + seleccion);
        return seleccion;
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
