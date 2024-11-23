package com.example.cashlimit.database;

import com.example.cashlimit.model.CategoryBill;
import com.example.cashlimit.model.CategoryIncome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.cashlimit.database.Conneection.getConnection;

public class CategoryIncomeDAO {


    public static void addCategoryIncome(CategoryIncome category) throws SQLException {

        //establecer la conexion a la base de datos

        Connection con = getConnection();
        //Verificamos la conexion a la base de datos
        if (con != null) {
            //ejecutar la query para insertar empleado
            try {
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "insert into CategoryIncomes (name_CategoryIncome) values (?);;";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, category.getNameCategory());

                //ejecutar la sentencia
                pstmt.execute();
                System.out.println("CATEGORY INCOME INSERTED!");
                //con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("MISTAKE TO INSERT CATEGORY INCOME" + e.getMessage());
                throw e;

            }
        }


    }

    //function for get income categories
    public List<String> getCategorys() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT name_CategoryIncome\n" +
                "FROM CategoryIncomes";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("name_CategoryIncome"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }

    //function for get income categories
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
