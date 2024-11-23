package com.example.cashlimit.database;

import com.example.cashlimit.controllers.FormNewRegisterController;
import com.example.cashlimit.model.Accounts;
import com.example.cashlimit.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.example.cashlimit.database.Conneection.getConnection;

public class UserDAO {
    int idUser;
    public static void RegisterUser(Accounts accounts, String name_user, String lastname_user, String mail_user, String tel_user) throws SQLException {
        Connection con = getConnection();
        int  idUser;
        if (con != null) {
            try {
                // Primero, insertar en la tabla 'users' y obtener el último ID insertado
                String insertUserQuery = "INSERT INTO users (name_user, lastname_user, mail_user, tel_user) VALUES (?, ?, ?, ?);";
                java.sql.PreparedStatement userStmt = con.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
                userStmt.setString(1, name_user);  // Cambiar según el usuario real
                userStmt.setString(2, lastname_user);
                userStmt.setString(3, mail_user);
                userStmt.setString(4, tel_user);

                userStmt.executeUpdate();

                // Obtener el ID del usuario insertado
                ResultSet generatedKeys = userStmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    idUser = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID del nuevo usuario.");
                }

                // Ahora insertar en la tabla 'accounts' usando el ID del usuario
                String query = "INSERT INTO accounts (num_account, amount, bank, id_user, id_type_account, status_account) " +
                        "VALUES (?, ?, ?, ?, 2, 'Active');";
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, accounts.getNum_account());
                pstmt.setDouble(2, accounts.getAmount());
                pstmt.setString(3, accounts.getBank());
                pstmt.setInt(4, idUser);

                pstmt.execute();
                System.out.println("Usuario y cuenta insertados correctamente.");

            } catch (SQLException e) {
                System.out.println("Error al insertar datos: " + e.getMessage());
                throw e;
            }
        }
    }

    // Método para pasar el id_user
    public int obtenerIdUsuario(String mail_user) throws SQLException {
        Connection con = getConnection();

        if (con != null) {
            try {
                String query = "SELECT id_user FROM users WHERE mail_user = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, mail_user);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id_user");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }

        throw new SQLException("No se encontró el usuario con el correo proporcionado.");
    }

    public int getUserIdByCredentials(String username, String password) throws SQLException {
        int userId = -1; // Valor por defecto si no se encuentra el usuario

        // Consulta SQL para buscar el id_user
        String sql = "SELECT id_user FROM loggeo WHERE user_loggeo = ? AND password_loggeo = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Establecer los parámetros en la consulta
            ps.setString(1, username);
            ps.setString(2, password);

            // Ejecutar la consulta
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Si se encuentra el usuario, obtener el id_user
                    userId = rs.getInt("id_user");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al buscar el usuario: " + e.getMessage(), e);
        }

        return userId;
    }



    // Método para obtener los datos de un usuario
    public Map<String, String> getUserData(int userId) throws SQLException {
        Map<String, String> userData = new HashMap<>();

        // Consulta SQL
        String sql = "SELECT l.user_loggeo, l.password_loggeo, u.mail_user, u.tel_user " +
                "FROM loggeo l " +
                "JOIN users u ON u.id_user = l.id_user " +
                "WHERE u.id_user = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Establecer el parámetro de búsqueda
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Si encontramos el usuario, almacenamos los datos en el mapa
                    userData.put("user_loggeo", rs.getString("user_loggeo")); // Usar los nombres sin alias
                    userData.put("password_loggeo", rs.getString("password_loggeo"));
                    userData.put("mail_user", rs.getString("mail_user"));
                    userData.put("tel_user", rs.getString("tel_user"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al consultar los datos del usuario: " + e.getMessage(), e);
        }

        return userData;
    }


    public void updateUserInformation(String username, String newPassword, String newEmail, String newPhone) throws SQLException {
        String sqlUpdateLoggeo = "UPDATE loggeo SET password_loggeo = ? WHERE user_loggeo = ?";
        String sqlUpdateUser = "UPDATE users SET mail_user = ?, tel_user = ? WHERE name_user = ?";

        // Iniciamos la conexión
        try (Connection con = getConnection()) {
            // Comenzamos la transacción para que ambas actualizaciones se hagan de manera atómica
            con.setAutoCommit(false);

            // Primero, actualizamos la contraseña en la tabla loggeo
            try (PreparedStatement psLoggeo = con.prepareStatement(sqlUpdateLoggeo)) {
                psLoggeo.setString(1, newPassword); // Nueva contraseña
                psLoggeo.setString(2, username);    // Usuario (nombre)
                int rowsUpdatedLoggeo = psLoggeo.executeUpdate();
                if (rowsUpdatedLoggeo == 0) {
                    System.out.println("No se actualizó el registro de loggeo");
                }
            }

            // Luego, actualizamos el email y teléfono en la tabla users
            try (PreparedStatement psUser = con.prepareStatement(sqlUpdateUser)) {
                psUser.setString(1, newEmail);  // Nuevo email
                psUser.setString(2, newPhone);  // Nuevo teléfono
                psUser.setString(3, username);  // Usuario (nombre)
                int rowsUpdatedUser = psUser.executeUpdate();
                if (rowsUpdatedUser == 0) {
                    System.out.println("No se actualizó el registro de users");
                }
            }

            // Si ambos UPDATEs fueron exitosos, hacemos commit de la transacción
            con.commit();
            System.out.println("Información actualizada correctamente.");
        } catch (SQLException e) {
            // Si hubo un error, hacemos rollback de la transacción
            e.printStackTrace();
            try {
                Connection con = getConnection();
                con.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            throw e;  // Re-lanzamos la excepción
        }
    }

    public boolean forgotPassword(String email, String newPassword) throws SQLException {
        String searchMail = "SELECT * FROM users WHERE mail_user = ?";
        String changePassword = "UPDATE loggeo SET password_loggeo = ? WHERE id_user = (SELECT id_user FROM users WHERE mail_user = ?)";

        // Iniciamos la conexión
        try (Connection con = getConnection()) {
            // Desactivamos el autocommit para manejar la transacción manualmente
            con.setAutoCommit(false);

            // Primero, buscamos si el correo electrónico existe en la tabla users
            try (PreparedStatement psSearch = con.prepareStatement(searchMail)) {
                psSearch.setString(1, email);
                try (ResultSet rs = psSearch.executeQuery()) {
                    if (rs.next()) {
                        // El correo existe, ahora actualizamos la contraseña en la tabla loggeo
                        try (PreparedStatement psUpdatePassword = con.prepareStatement(changePassword)) {
                            psUpdatePassword.setString(1, newPassword); // Nueva contraseña
                            psUpdatePassword.setString(2, email);       // Correo electrónico
                            int rowsUpdated = psUpdatePassword.executeUpdate();
                            if (rowsUpdated > 0) {
                                // Si se actualizó la contraseña, hacemos commit de la transacción
                                con.commit();
                                return true;  // Devuelve true si se actualizó correctamente
                            } else {
                                con.rollback();  // Si no se actualiza, revertimos los cambios
                                return false;  // Devuelve false si la actualización falla
                            }
                        }
                    } else {
                        // Si no existe el correo, lanzamos un mensaje indicando que no se encontró el usuario
                        con.rollback();  // Si no se encuentra el correo, revertimos los cambios
                        return false;  // Devuelve false si no se encuentra el correo
                    }
                }
            } catch (SQLException e) {
                // Si hubo un error en la búsqueda o actualización, hacemos rollback
                con.rollback();
                e.printStackTrace();
                throw e;  // Re-lanzamos la excepción
            } finally {
                // Volver a activar el autocommit después de la transacción
                con.setAutoCommit(true);
            }
        }
    }
}