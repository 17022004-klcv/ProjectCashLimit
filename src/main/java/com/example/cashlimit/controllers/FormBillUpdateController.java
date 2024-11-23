package com.example.cashlimit.controllers;

import com.example.cashlimit.database.BillsDAO;
import com.example.cashlimit.database.CategoryBillDAO;
import com.example.cashlimit.model.Bills;
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
import java.text.SimpleDateFormat;

import static com.example.cashlimit.validations.validation.emptyText;
import static com.example.cashlimit.validations.validation.validateAmount;

public class FormBillUpdateController {

    @FXML
    private Button btn_updateBill;

    @FXML
    private ComboBox<String> cbb_accounts;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_amount;

    @FXML
    private TextField txt_description;
    CategoryBillDAO queryCategorys = new CategoryBillDAO();

    @FXML
    public void initialize() throws SQLException {
        cargarCategorias();

    }

    @FXML
    void btn_updateBill(ActionEvent event) throws IOException {
        try {
            // Validar que los campos no estén vacíos
            if (txt_description.getText().isEmpty() || txt_amount.getText().isEmpty()) {
                System.out.println("Empty filds!");
                return;
            }

            // Validar que el monto sea un número válido
            double amount;
            try {
                amount = Double.parseDouble(txt_amount.getText());
            } catch (NumberFormatException e) {
                System.out.println("El monto debe ser un número válido.");
                return;
            }
            CategoryBillDAO queryCategory = new CategoryBillDAO();

            // Obtener la categoría seleccionada del ComboBox
            String seleccion = manejarSeleccion();
            int categoryId = queryCategory.searchCategory(seleccion);

            // Crear objeto Bill con los valores actualizados
            Bills bill = new Bills();
            bill.setDescrip_bill(txt_description.getText());
            bill.setTotal_bill(amount);
            bill.setId_CategoryBill(categoryId);
            bill.setDate_bill(date); // Fecha proporcionada al cargar los datos

            // Llamar al método de actualización por fecha y monto
            BillsDAO queryBill = new BillsDAO();
            queryBill.updateBillByDateAndAmount(bill);

            // Cambiar a la vista del dashboard
            CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());
        } catch (Exception e) {
            System.out.println("Error al actualizar la factura: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public String manejarSeleccion() {
        // Obtener el valor seleccionado al hacer clic o usar el ComboBox
        String seleccion = cbb_accounts.getValue();
        System.out.println("Seleccionaste desde manejarSeleccion: " + seleccion);
        return seleccion;
    }

    @FXML
    void imgBack(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());

    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    String date;
    public void setBillData(Object totalBill, Object category, Object description, Object dateParam) {
        // Asignar los valores recibidos a los campos correspondientes
        cbb_accounts.setValue(category != null ? category.toString() : "");
        txt_description.setText(description != null ? description.toString() : "");
        txt_amount.setText(totalBill != null ? totalBill.toString() : "");

        // Convertir y formatear la fecha
        if (dateParam != null) {
            this.date = dateParam.toString(); // Almacenar la fecha como cadena
            System.out.println("La fecha recibida es: " + this.date);
        } else {
            this.date = null;
        }
    }



    public void cargarCategorias() {
        cbb_accounts.getItems().clear();
        cbb_accounts.getItems().addAll(queryCategorys.getCategorys());
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
