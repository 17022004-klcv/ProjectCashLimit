package com.example.cashlimit.controllers;

import com.example.cashlimit.database.BillsDAO;
import com.example.cashlimit.database.CategoryBillDAO;
import com.example.cashlimit.database.IncomeDAO;
import com.example.cashlimit.model.Bills;
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

import static com.example.cashlimit.validations.validation.emptyText;
import static com.example.cashlimit.validations.validation.validateAmount;

public class FormIncomeUpdate {

    @FXML
    private ComboBox<String> cbb_accounts;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_amount;

    @FXML
    private TextField txt_description;

    @FXML
    void btn_updateIncome(ActionEvent event) throws IOException, SQLException {
        if(!emptyText(txt_description)){
            System.out.println("descripcion vacio");
        }else if(!emptyText(txt_amount)){
            System.out.println("monto vacio");
        }else{
            if (validateAmount(txt_amount)){

                // Validar que el monto sea un número válido
                double amount;
                try {
                    amount = Double.parseDouble(txt_amount.getText());
                } catch (NumberFormatException e) {
                    System.out.println("El monto debe ser un número válido.");
                    return;
                }
                IncomeDAO queryCategory = new IncomeDAO();

                // Obtener la categoría seleccionada del ComboBox
                String seleccion = manejarSeleccion();
                int categoryId = queryCategory.searchCategory(seleccion);

                // Crear objeto Bill con los valores actualizados
                Income incomes = new Income();
                incomes.setDescrip_income(txt_description.getText());
                incomes.setTotal_income(amount);
                incomes.setId_CategoryIncome(categoryId);
                incomes.setDate_income(date); // Fecha proporcionada al cargar los datos

                // Llamar al método de actualización por fecha y monto
                IncomeDAO queryIncome = new IncomeDAO();
                queryIncome.updateIncomeByDateAndAmount(incomes);

                JOptionPane.showMessageDialog(null, "Income Updated!");
                CambiarVista("/com/example/cashlimit/views/income.fxml", (Node) event.getSource());
            }
        }



    }
    String date;
    public void setIncomeData(Object totalIncome, Object category, Object description, Object dateParam) {
        // Asignar los valores recibidos a los campos correspondientes
        cbb_accounts.setValue(category != null ? category.toString() : "");
        txt_description.setText(description != null ? description.toString() : "");
        txt_amount.setText(totalIncome != null ? totalIncome.toString() : "");

        // Convertir y formatear la fecha
        if (dateParam != null) {
            this.date = dateParam.toString(); // Almacenar la fecha como cadena
            System.out.println("La fecha recibida es: " + this.date);
        } else {
            this.date = null;
        }
    }
    @FXML
    private Button btn_updateIncome;

    @FXML
    void imgBack(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/income.fxml", (Node) event.getSource());

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

    public String manejarSeleccion() {
        // Obtener el valor seleccionado al hacer clic o usar el ComboBox
        String seleccion = cbb_accounts.getValue();
        System.out.println("Seleccionaste desde manejarSeleccion: " + seleccion);
        return seleccion;
    }

}
