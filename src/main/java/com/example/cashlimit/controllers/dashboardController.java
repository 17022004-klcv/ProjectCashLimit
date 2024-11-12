package com.example.cashlimit.controllers;

import com.example.cashlimit.database.BillsDAO;
import com.example.cashlimit.database.CategoryBillDAO;
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

public class dashboardController {

    @FXML
    private Button btAddBill;

    @FXML
    private Button btUpdateBill;

    @FXML
    private ImageView imgBill;

    @FXML
    private ImageView imgCategory;

    @FXML
    private ImageView imgIncome;

    @FXML
    private ImageView imgUser;

    @FXML
    private TableColumn<Map, Object> ColumnAmount;

    @FXML
    private TableColumn<Map, Object> ColumnCategory;

    @FXML
    private TableColumn<Map, Object>  ColumnDate;

    @FXML
    private TableColumn<Map, Object>  ColumnDescription;

    @FXML
    private TableView<Map> TableBills;

    @FXML
    public void initialize() throws SQLException {
        tableBills();
    }
    @FXML
    void btAddBill(ActionEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/FormBill.fxml", (Node) event.getSource());
    }

    @FXML
    void btUpdateBill(ActionEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/FormBillUpdate.fxml", (Node) event.getSource());

    }

    @FXML
    void imgBill(MouseEvent event) throws IOException {
        CambiarVista("/com/example/cashlimit/views/dashboard.fxml", (Node) event.getSource());

    }
    @FXML
    void ImgUser(MouseEvent event) throws IOException{
        CambiarVista("/com/example/cashlimit/views/user.fxml", (Node) event.getSource());

    }

    @FXML
    void imgCategory(MouseEvent event)throws IOException {
        CambiarVista("/com/example/cashlimit/views/category.fxml", (Node) event.getSource());
    }

    @FXML
    void imgIncome(MouseEvent event) throws IOException {
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

    BillsDAO queryBill = new BillsDAO();

    public void tableBills() throws SQLException, SQLException {

        ObservableList<Map> lista = queryBill.getBills();
        TableBills.setItems(lista);

        // Configuración de las columnas de la tabla
        ColumnAmount.setCellValueFactory(new MapValueFactory<>("b.total_bill"));
        ColumnCategory.setCellValueFactory(new MapValueFactory<>("c.name_CategoryBill"));
        ColumnDescription.setCellValueFactory(new MapValueFactory<>("b.descrip_bill,"));
        ColumnDate.setCellValueFactory(new MapValueFactory<>("b.date_bill"));

    }
}
