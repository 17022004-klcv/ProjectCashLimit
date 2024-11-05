package com.example.cashlimit.controllers;

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

import static com.example.cashlimit.validations.validation.emptyText;

public class FormCategoryIncomeController {

    @FXML
    private Button btn_addCategory;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_category;

    @FXML
    void btn_addCategory(ActionEvent event) {
        if(!emptyText(txt_category)){
            System.out.println("categoria vacio");
        }else{
            System.out.println("ningun campo vacio");
            JOptionPane.showMessageDialog(null, "Category Added!");

        }
    }

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
}
