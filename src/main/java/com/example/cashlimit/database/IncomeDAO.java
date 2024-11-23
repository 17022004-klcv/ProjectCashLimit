package com.example.cashlimit.database;

import com.example.cashlimit.model.Bills;
import com.example.cashlimit.model.Income;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.example.cashlimit.database.Conneection.getConnection;

public class IncomeDAO {


    public static void insertIncome(Income income) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "insert into incomes (total_income, date_income, descrip_income, id_CategoryIncome, id_user) values\n" +
                        "(?, ?, ?, ?, ?);";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setDouble(1, income.getTotal_income());
                pstmt.setString(2, income.getDate_income());
                pstmt.setString(3, income.getDescrip_income());
                pstmt.setInt(4, income.getId_CategoryIncome());
                pstmt.setInt(5, income.getId_user());

                //ejecutar la sentencia
                pstmt.execute();
                System.out.println(" INCOME INSERTED!");
                //con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("MISTAKE TO INSERT INCOME" + e.getMessage());
                throw e;

            }
        }

    }

    public ObservableList<Map> getIncomes() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "select b.total_income, c.name_CategoryIncome, b.descrip_income, b.date_income from incomes b\n" +
                "join CategoryIncomes c on c.id_CategoryIncome = b.id_CategoryIncome;";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> category = new HashMap<>();
                category.put("b.total_income", rs.getString("b.total_income"));
                category.put("c.name_CategoryIncome", rs.getString("c.name_CategoryIncome"));
                category.put("b.descrip_income,", rs.getString("b.descrip_income"));
                category.put("b.date_income", rs.getString("b.date_income"));
                lista.add(category);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"MISTAKE TO SELECT INCOMES" + e.getMessage());
            throw e;
        }

        return lista;
    }

    public void updateIncomeByDateAndAmount(Income income) throws SQLException {
        String sql = "UPDATE incomes SET descrip_income = ?, total_income = ?, id_CategoryIncome = ? WHERE date_income = ? AND total_income = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, income.getDescrip_income());
            stmt.setDouble(2, income.getTotal_income());
            stmt.setInt(3, income.getId_CategoryIncome());
            stmt.setString(4, income.getDate_income());
            stmt.setDouble(5, income.getTotal_income()); // Busca por fecha y monto original

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Income updated!");
            } else {
                System.out.println("No se encontró ninguna factura con los criterios especificados.");
            }
        }
    }

    public int searchCategory(String category) throws SQLException {
        // Variable para almacenar el ID
        int id = -1;

        // Consulta SQL con un parámetro
        String sql = "SELECT id_CategoryIncome FROM CategoryIncomes WHERE name_CategoryIncome = ?";

        // Usar try-with-resources para asegurar el cierre de recursos
        try (Connection conn = getConnection();// Cambia a tu método para obtener la conexión
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Asignar el parámetro de la consulta
            pstmt.setString(1, category);

            // Ejecutar la consulta
            try (ResultSet rs = pstmt.executeQuery()) {
                // Verificar si hay un resultado
                if (rs.next()) {
                    id = rs.getInt("id_CategoryIncome");
                }
            }
        }

        return id;
    }

}
