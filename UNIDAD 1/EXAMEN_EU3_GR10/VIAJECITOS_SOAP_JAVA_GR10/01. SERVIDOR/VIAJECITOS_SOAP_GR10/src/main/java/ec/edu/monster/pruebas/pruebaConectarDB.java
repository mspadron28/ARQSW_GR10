package ec.edu.monster.pruebas;

import ec.edu.monster.db.AccesoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to test the database connection to vuelos_db.
 * @author MATIAS
 */
public class pruebaConectarDB {
    
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Obtain database connection
            conn = AccesoDB.getConnection();
            System.out.println("¡Conexión exitosa a la base de datos vuelos_db!");
            
            // Prepare a simple query to test
            String sql = "SELECT id_vuelo, ciudad_origen, ciudad_destino, valor, hora_salida FROM Vuelo";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            // Display results
            System.out.println("\nLista de Vuelos:");
            System.out.println("==================");
            while (rs.next()) {
                int idVuelo = rs.getInt("id_vuelo");
                String origen = rs.getString("ciudad_origen");
                String destino = rs.getString("ciudad_destino");
                double valor = rs.getDouble("valor");
                String horaSalida = rs.getString("hora_salida");
                System.out.printf("ID: %d, Origen: %s, Destino: %s, Valor: %.2f, Salida: %s%n",
                        idVuelo, origen, destino, valor, horaSalida);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al conectar o consultar la base de datos: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}