package com.example.cashlimit.database;

import com.example.cashlimit.model.Accounts;
import com.example.cashlimit.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.cashlimit.database.Conneection.getConnection;

public class AccountsDAO {

    public static void RegisterAccount(Accounts accounts) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try {
                // Primero, insertar en la tabla 'users' y obtener el último ID insertado.
                String insertUserQuery = "INSERT INTO users (id_user) VALUES (?);";  // Ajusta según los campos de 'users'
                java.sql.PreparedStatement userStmt = con.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
                userStmt.setString(1, "Detalles del usuario");  // Ajusta según los detalles del usuario
                userStmt.executeUpdate();

                // Obtener el último ID insertado en 'users'
                ResultSet generatedKeys = userStmt.getGeneratedKeys();
                int idUser = -1;
                if (generatedKeys.next()) {
                    idUser = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID del nuevo usuario.");
                }

                // Ahora insertar en la tabla 'accounts' utilizando el ID obtenido
                String query = "INSERT INTO users (name_user, lastname_user, mail_user, tel_user) VALUES (?, ?, ?, ?);";

                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, accounts.getNum_account());
                pstmt.setDouble(2, accounts.getAmount());
                pstmt.setString(3, accounts.getBank());
                pstmt.setInt(4, idUser);  // Usar el ID del usuario insertado previamente

                // Ejecutar la sentencia
                pstmt.execute();
                System.out.println("Empleado y cuenta insertados correctamente");

            } catch (SQLException e) {
                System.out.println("Error al insertar datos de empleado: " + e.getMessage());
                throw e;
            }

        }


    }

    public List<String> getCategorys() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT type_account\n" +
                "FROM type_account";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("type_account"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
