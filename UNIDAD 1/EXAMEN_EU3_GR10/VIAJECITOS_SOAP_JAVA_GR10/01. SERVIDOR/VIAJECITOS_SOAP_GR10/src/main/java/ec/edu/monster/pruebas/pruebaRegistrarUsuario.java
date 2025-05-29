package ec.edu.monster.pruebas;

import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.servicio.ViajecitosService;

/**
 * Clase para probar el método registrarUsuario de ViajecitosService.
 * @author MATIAS
 */
public class pruebaRegistrarUsuario {
    
    public static void main(String[] args) {
        ViajecitosService service = new ViajecitosService();
        
        // Caso de éxito: Registrar nuevo usuario para cliente existente
        try {
            int idCliente = 4; // Juan Pérez
            String nombreUsuario = "anaperez";
            String claveUsuario = "MONSTER9";
            Usuario usuario = service.registrarUsuario(idCliente, nombreUsuario, claveUsuario);
            System.out.println("Caso 1: Usuario registrado exitosamente");
            System.out.printf("ID: %d, Cliente ID: %d, Nombre: %s, Estado: %s%n",
                    usuario.getIdUsuario(), usuario.getIdCliente(), usuario.getNombreUsuario(),
                    usuario.getEstadoUsuario());
        } catch (Exception e) {
            System.err.println("Caso 1: Error al registrar usuario: " + e.getMessage());
        }
        
        // Caso de error: Registrar usuario con cliente inexistente
        try {
            int idCliente = 999; // Cliente inexistente
            String nombreUsuario = "otrousuario";
            String claveUsuario = "MONSTER9";
            Usuario usuario = service.registrarUsuario(idCliente, nombreUsuario, claveUsuario);
            System.out.println("Caso 2: Usuario registrado exitosamente (no debería llegar aquí)");
        } catch (Exception e) {
            System.err.println("Caso 2: Error al registrar usuario: " + e.getMessage());
        }
        
        // Caso de error: Registrar usuario con nombre de usuario duplicado
        try {
            int idCliente = 1;
            String nombreUsuario = "juanperez"; // Nombre ya registrado
            String claveUsuario = "MONSTER9";
            Usuario usuario = service.registrarUsuario(idCliente, nombreUsuario, claveUsuario);
            System.out.println("Caso 3: Usuario registrado exitosamente (no debería llegar aquí)");
        } catch (Exception e) {
            System.err.println("Caso 3: Error al registrar usuario: " + e.getMessage());
        }
    }
}