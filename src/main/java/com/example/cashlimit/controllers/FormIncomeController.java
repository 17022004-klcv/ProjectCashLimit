package com.example.cashlimit.controllers;

import com.example.cashlimit.database.CategoryIncomeDAO;
import com.example.cashlimit.database.IncomeDAO;
import com.example.cashlimit.model.Income;
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
import java.time.LocalDate;
import java.util.concurrent.Callable;

import static com.example.cashlimit.validations.validation.emptyText;
import static com.example.cashlimit.validations.validation.validateAmount;

public class FormIncomeController {

    @FXML
    private ComboBox<String> cbb_accounts;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_amount;

    @FXML
    private TextField txt_description;

    @FXML
    private Button btn_addIncome;
    @FXML
    public void initialize() throws SQLException {
        cargarCategorias();
    }
    @FXML
    void btn_addIncome(ActionEvent event) throws SQLException {
        if(!emptyText(txt_description)){
            System.out.println("descripcion vacio");
        }else if(!emptyText(txt_amount)){
            System.out.println("monto vacio");
        }else{
            if(validateAmount(txt_amount)) {

                CategoryIncomeDAO queryCategory = new CategoryIncomeDAO();
                //data for
                double amount = Double.parseDouble(txt_amount.getText());
                String descrip = txt_description.getText();
                LocalDate fechaActual = LocalDate.now();
                String date = String.valueOf(fechaActual);

                // Obtener la categoría seleccionada del ComboBox
                String seleccion = manejarSeleccion();
                int categoryId = queryCategory.searchCategory(seleccion);

                IncomeDAO quwryIncome = new IncomeDAO();
                Income income = new Income(amount, date, descrip, categoryId, 1);
                quwryIncome.insertIncome(income);
                JOptionPane.showMessageDialog(null, "Income Added!");
                txt_description.setText("");
                txt_amount.setText("");

            }
        }
    }
    @FXML
    void imgBack(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/income.fxml", (Node) event.getSource());
    }
    CategoryIncomeDAO queryCategorys = new CategoryIncomeDAO();
    public void cargarCategorias() {
        cbb_accounts.getItems().clear();
        cbb_accounts.getItems().addAll(queryCategorys.getCategorys());
    }

    public String manejarSeleccion() {
        // Obtener el valor seleccionado al hacer clic o usar el ComboBox
        String seleccion = cbb_accounts.getValue();
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

