package com.example.cashlimit.database;

import com.example.cashlimit.model.Loggeo;

import java.sql.Connection;
import java.sql.SQLException;

import static com.example.cashlimit.database.Conneection.getConnection;

public class LoggeoDAO {

    public static boolean isExitUser(Loggeo loggeo) throws SQLException {

        //establecer la conexion a la base de datos

        Connection con = getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
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
}
