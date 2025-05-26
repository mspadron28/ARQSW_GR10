package ec.edu.monster.prueba;

import ec.edu.monster.db.AccesoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase para probar la conexión a la base de datos eurekabank.
 * @author MATIAS
 */
public class pruebaConectarDB {
    
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Obtener la conexión a la base de datos
            conn = AccesoDB.getConnection();
            System.out.println("¡Conexión exitosa a la base de datos eurekabank!");
            
            // Preparar una consulta simple para probar
            String sql = "SELECT chr_cliecodigo, vch_clienombre, vch_cliepaterno, vch_cliematerno FROM Cliente";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            // Mostrar los resultados
            System.out.println("\nLista de Clientes:");
            System.out.println("==================");
            while (rs.next()) {
                String codigo = rs.getString("chr_cliecodigo");
                String nombre = rs.getString("vch_clienombre");
                String paterno = rs.getString("vch_cliepaterno");
                String materno = rs.getString("vch_cliematerno");
                System.out.printf("Código: %s, Nombre: %s %s %s%n", codigo, nombre, paterno, materno);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al conectar o consultar la base de datos: " + e.getMessage());
        } finally {
            // Cerrar recursos
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