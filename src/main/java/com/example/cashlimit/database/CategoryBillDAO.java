package com.example.cashlimit.database;

import com.example.cashlimit.model.CategoryBill;
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

public class CategoryBillDAO {

    public static void addCategoryBill(CategoryBill category) throws SQLException {

        //establecer la conexion a la base de datos

        Connection con = getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "insert into CategoryBills (name_CategoryBill) values (?);;";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, category.getNameCategory());

                //ejecutar la sentencia
                pstmt.execute();
                System.out.println("CATEGORY BILL INSERTED!");
                //con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("MISTAKE TO INSERT CATEGORY BILL" + e.getMessage());
                throw e;

            }
        }


    }

    //function for get bill categories
    public ObservableList<Map> getCategoryBill() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "select name_CategoryBill from CategoryBills";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> category = new HashMap<>();
                category.put("name_CategoryBill", rs.getString("name_CategoryBill"));
                lista.add(category);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"MISTAKE TO SELECT CATEGORY BILL" + e.getMessage());
            throw e;
        }

        return lista;
    }

    public List<String> getCategorys() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT name_CategoryBill\n" +
                "FROM CategoryBills";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("name_CategoryBill"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }

}
