package ec.edu.monster.pruebas;

import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.servicio.ViajecitosService;

/**
 * Clase para probar el inicio de sesión en la base de datos vuelos_db.
 * @author MATIAS
 */
public class pruebaIniciarSesion {
    
    public static void main(String[] args) {
        try {
            // Datos de prueba
            String nombreUsuario = "juanperez";
            String claveUsuario = "MONSTER9";
            
            // Proceso
            ViajecitosService service = new ViajecitosService();
            Usuario usuario = service.iniciarSesion(nombreUsuario, claveUsuario);
            
            // Reporte
            System.out.println("¡Inicio de sesión exitoso!");
            System.out.println("ID Usuario: " + usuario.getIdUsuario());
            System.out.println("ID Cliente: " + usuario.getIdCliente());
            System.out.println("Nombre de Usuario: " + usuario.getNombreUsuario());
            System.out.println("Estado: " + usuario.getEstadoUsuario());
            
        } catch (Exception e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
        }
    }
}