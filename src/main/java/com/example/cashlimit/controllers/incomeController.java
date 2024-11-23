package com.example.cashlimit.controllers;

import com.example.cashlimit.database.IncomeDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

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
    private TableView<Map> table;

    @FXML
    private TableColumn<Map, Object>tb_category;

    @FXML
    private TableColumn<Map, Object> tb_date;

    @FXML
    private TableColumn<Map, Object>tb_description;

    @FXML
    private TableColumn<Map, Object>tb_mount;

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

        // Obtener el registro seleccionado de la tabla
        Map selectedBill = table.getSelectionModel().getSelectedItem();

        if (selectedBill != null) {
            // Aquí puedes extraer los datos del registro seleccionado
            Object totalIncome = selectedBill.get("b.total_income");
            Object category = selectedBill.get("c.name_CategoryIncome");
            Object description = selectedBill.get("b.descrip_income");
            Object date = selectedBill.get("b.date_income");

            // Cargar la nueva vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cashlimit/views/FormIncomeUpdate.fxml"));
            Parent root = loader.load();

            // Pasar los datos seleccionados al controlador de la nueva vista
            FormIncomeUpdate controller = loader.getController();
            controller.setIncomeData(totalIncome, category, description, date);

            // Cambiar la vista
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            System.out.println("Registro seleccionado: " + selectedBill);
        } else {
            // Mostrar mensaje si no se seleccionó nada
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un registro.", ButtonType.OK);
            alert.showAndWait();
        }


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
    @FXML
    public void initialize() throws SQLException {
        tableIncomes();
    }
    public void tableIncomes() throws SQLException, SQLException {

        IncomeDAO queryIncome = new IncomeDAO();
        ObservableList<Map> lista = queryIncome.getIncomes();
        table.setItems(lista);

        // Configuración de las columnas de la tabla
        tb_mount.setCellValueFactory(new MapValueFactory<>("b.total_income"));
        tb_category.setCellValueFactory(new MapValueFactory<>("c.name_CategoryIncome"));
        tb_description.setCellValueFactory(new MapValueFactory<>("b.descrip_income,"));
        tb_date.setCellValueFactory(new MapValueFactory<>("b.date_income"));

    }
}
