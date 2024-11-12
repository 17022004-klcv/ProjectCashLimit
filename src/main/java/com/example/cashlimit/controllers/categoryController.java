package com.example.cashlimit.controllers;

import com.example.cashlimit.database.BillsDAO;
import com.example.cashlimit.database.CategoryBillDAO;
import com.example.cashlimit.model.Bills;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class categoryController {

    @FXML
    private Button btAddcategory;

    @FXML
    private ImageView imgBills;

    @FXML
    private ImageView imgCategory;

    @FXML
    private ImageView imgIncome;

    @FXML
    private ImageView imgUser;


    @FXML
    private TableView<Map> TableCategoy;

    @FXML
    private TableColumn<Map, Object> categoryBill;

    @FXML
    public void initialize() throws SQLException {
        tableCategory();
    }

    @FXML
    void btAddCategory(ActionEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/FormCategory.fxml", (Node) event.getSource());

    }

    @FXML
    void imgBill(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());
    }

    @FXML
    void imgCategory(MouseEvent event) throws IOException{
        CambiarVista("/com/example/cashlimit/views/category.fxml", (Node) event.getSource());
    }

    @FXML
    void imgIncome(MouseEvent event)  throws IOException {
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

    CategoryBillDAO queryCategory = new CategoryBillDAO();

    public void tableCategory() throws SQLException, SQLException {

        ObservableList<Map> lista = queryCategory.getCategoryBill();
        TableCategoy.setItems(lista);

        // Configuración de las columnas de la tabla
        categoryBill.setCellValueFactory(new MapValueFactory<>("name_CategoryBill"));

    }
}
