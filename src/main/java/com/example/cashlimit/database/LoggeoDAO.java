package com.example.cashlimit.database;

import com.example.cashlimit.model.Bills;
import com.example.cashlimit.model.Loggeo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.cashlimit.database.Conneection.getConnection;

public class LoggeoDAO {

    public static boolean isExitUser(Loggeo loggeo) throws SQLException {

        //establecer la conexion a la base de datos

        Connection con = getConnection();
        //Verificamos la conexion a la base de datos
        if (con != null) {
            //ejecutar la query para insertar empleado
            try {
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "select * from loggeo where user_loggeo = ? and password_loggeo = ?;";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, loggeo.getUser_loggeo());
                pstmt.setString(2, loggeo.getPassword_loggeo());

                //ejecutar la sentencia
                pstmt.execute();
                System.out.println("USER SEARCH!");
                return true;
                //con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("MISTAKE TO SEARCH USER" + e.getMessage());
                throw e;

            }
        }
        return false;
    }



    public void registrarLoggeo(int userId, String user, String password) {
        Connection con = null;
        PreparedStatement insertLoggeoStmt = null;

        try {
            // Establecer la conexión a la base de datos
            con = getConnection();

            // Verificamos la conexión a la base de datos
            if (con != null) {
                // Consulta para insertar datos en `loggeo`
                String insertLoggeoQuery = "INSERT INTO loggeo (id_user, user_loggeo, password_loggeo) VALUES (?, ?, ?)";
                insertLoggeoStmt = con.prepareStatement(insertLoggeoQuery);

                // Establecer valores (asegúrate de que `userId` esté correctamente inicializado)
                insertLoggeoStmt.setInt(1, userId); // Usar el id_user recibido
                insertLoggeoStmt.setString(2, user);
                insertLoggeoStmt.setString(3, password);

                // Ejecutar la consulta
                insertLoggeoStmt.executeUpdate();
                System.out.println("Loggeo registrado correctamente.");
            } else {
                System.err.println("No se pudo establecer la conexión con la base de datos.");
            }

        } catch (SQLException e) {
            System.err.println("Error al intentar registrar el loggeo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Liberar recursos
            try {
                if (insertLoggeoStmt != null) {
                    insertLoggeoStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}
