package com.example.cashlimit.controllers;

import com.example.cashlimit.database.CategoryBillDAO;
import com.example.cashlimit.model.CategoryBill;
import com.example.cashlimit.model.Loggeo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.cashlimit.validations.validation.emptyText;

public class FormCategoryController {

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_category;
    @FXML
    private Button btn_addCategory;

    @FXML
    void btn_addCategory(ActionEvent event) throws SQLException {
        if(!emptyText(txt_category)){
            System.out.println("descripcion vacio");
        }else{

            //data for categoryBill
            String category = txt_category.getText();

            CategoryBill categoryB = new CategoryBill(category);
            CategoryBillDAO queryCategory = new CategoryBillDAO();

            queryCategory.addCategoryBill(categoryB);

            JOptionPane.showMessageDialog(null, "Category Added!");
            txt_category.setText("");

        }
    }

    @FXML
    void imgBack(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/category.fxml", (Node) event.getSource());

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