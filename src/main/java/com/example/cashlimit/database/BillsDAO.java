package com.example.cashlimit.database;

import com.example.cashlimit.model.Bills;
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

public class BillsDAO {

    public ObservableList<Map> getBills() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "select b.total_bill, c.name_CategoryBill, b.descrip_bill, b.date_bill from bills b\n" +
                "join CategoryBills c on c.id_CategoryBill = b.id_CategoryBill;";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> category = new HashMap<>();
                category.put("b.total_bill", rs.getString("b.total_bill"));
                category.put("c.name_CategoryBill", rs.getString("c.name_CategoryBill"));
                category.put("b.descrip_bill,", rs.getString("b.descrip_bill"));
                category.put("b.date_bill", rs.getString("b.date_bill"));
                lista.add(category);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"MISTAKE TO SELECT BILLS" + e.getMessage());
            throw e;
        }

        return lista;
    }

    public static void insertBill(Bills bill) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "insert into bills (total_bill, date_bill, descrip_bill, id_CategoryBill, id_user) values\n" +
                        "(?, ?, ?, ?, ?);";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setDouble(1, bill.getTotal_bill());
                pstmt.setString(2, bill.getDate_bill());
                pstmt.setString(3, bill.getDescrip_bill());
                pstmt.setInt(4, bill.getId_CategoryBill());
                pstmt.setInt(5, bill.getId_user());

                //ejecutar la sentencia
                pstmt.execute();
                System.out.println(" BILL INSERTED!");
                //con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("MISTAKE TO INSERT BILL" + e.getMessage());
                throw e;

            }
        }

    }
}
