package com.example.cashlimit.database;

import com.example.cashlimit.model.User;

import java.sql.Connection;
import java.sql.SQLException;

import static com.example.cashlimit.database.Conneection.getConnection;

public class UserDAO {

    public static void RegisterUser(User user) throws SQLException {
            //establecer la conexion a la base de datos

            Connection con = getConnection();
            //Verificamos la conexion a la base de datos
            if(con!=null){
                //ejecutar la query para insertar empleado
                try{
                    //query de insertar empleado
                    //se hace de esta forma para evitar ataques de inyeccion SQL
                    String query = "insert into users (name_user, lastname_user, mail_user, tel_user) values (?, ?, ?, ?);";

                    //preparar la sentencia
                    java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, user.getName_user());
                    pstmt.setString(2, user.getLastname_user());
                    pstmt.setString(3, user.getMail_user());
                    pstmt.setString(4, user.getTel_user());


                    //ejecutar la sentencia
                    pstmt.execute();
                    System.out.println("Empleado insertado correctamente");
                    //con.close(); //cerrar la conexion
                } catch (SQLException e) {
                    System.out.println("Error al insertar datos de empleado" + e.getMessage());
                    throw e;
                }
            }
    }
}
