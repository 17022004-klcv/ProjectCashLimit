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
    @FXML
    void bt_AddBill(ActionEvent event) throws SQLException {
        if (!emptyText(txt_description)) {
            System.out.println("descripcion vacio");
        } else if (!emptyText(txt_amount)) {
            System.out.println("monto vacio");
        } else {
            System.out.println("ningun campo vacio");
            if (validateAmount(txt_amount)) {

                //data for
                double amount = Double.parseDouble(txt_amount.getText());
                String descrip = txt_description.getText();
                LocalDate fechaActual = LocalDate.now();
                String date = String.valueOf(fechaActual);
                //int category = Integer.parseInt(cbb_category.getId());



                BillsDAO queryBill = new BillsDAO();
                Bills bill = new Bills(amount, date, descrip, 1, 1);
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
