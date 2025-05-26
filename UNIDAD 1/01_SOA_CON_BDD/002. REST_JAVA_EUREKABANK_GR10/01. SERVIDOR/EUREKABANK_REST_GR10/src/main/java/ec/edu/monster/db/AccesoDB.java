package ec.edu.monster.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides access to the eurekabank database.
 * @author MATIAS
 */
public class AccesoDB {

    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/eurekabank?useSSL=false&serverTimezone=UTC";
    private static final String USER = "eureka";
    private static final String PASSWORD = "admin";

    // Private constructor to prevent instantiation
    private AccesoDB() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Establish the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("ERROR, no se tiene acceso al Servidor: " + e.getMessage());
        }
    }
}