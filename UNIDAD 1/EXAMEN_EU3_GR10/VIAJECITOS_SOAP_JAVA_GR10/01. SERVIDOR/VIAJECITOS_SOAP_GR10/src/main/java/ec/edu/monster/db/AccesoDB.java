package ec.edu.monster.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides access to the vuelos_db database.
 * @author MATIAS
 */
public class AccesoDB {

    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/vuelos_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Default XAMPP MySQL user
    private static final String PASSWORD = ""; // Default XAMPP MySQL password (empty)

    // Private constructor to prevent instantiation
    private AccesoDB() {
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("ERROR, no se tiene acceso al Servidor: " + e.getMessage());
        }
        return conn;
    }
}