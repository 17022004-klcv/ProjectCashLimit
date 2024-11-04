package com.example.cashlimit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class incomeController {

    @FXML
    private Button btAddCategoryIncome;

    @FXML
    private Button btAddIncome;

    @FXML
    private Button btUpdateIncome;

    @FXML
    private ImageView imgBill;

    @FXML
    private ImageView imgCategory;

    @FXML
    private ImageView imgIncome;

    @FXML
    private ImageView imgUser;

    @FXML
    void btAddCategoryIncome(ActionEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/FormCategoryIncome.fxml", (Node) event.getSource());

    }

    @FXML
    void btAddIncome(ActionEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/FormIncome.fxml", (Node) event.getSource());

    }

    @FXML
    void btUpdateIncome(ActionEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/FormIncomeUpdate.fxml", (Node) event.getSource());

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
