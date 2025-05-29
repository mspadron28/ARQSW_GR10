package ec.edu.monster.pruebas;

import ec.edu.monster.modelo.Cliente;
import ec.edu.monster.servicio.ViajecitosService;

/**
 * Clase para probar el método registrarCliente de ViajecitosService.
 * @author MATIAS
 */
public class pruebaRegistrarCliente {
    
    public static void main(String[] args) {
        ViajecitosService service = new ViajecitosService();
        
        // Caso de éxito: Registrar nuevo cliente
        try {
            String nombre = "Ana Martínez";
            String email = "ana.martinez@email.com";
            String documentoIdentidad = "789123456";
            Cliente cliente = service.registrarCliente(nombre, email, documentoIdentidad);
            System.out.println("Caso 1: Cliente registrado exitosamente");
            System.out.printf("ID: %d, Nombre: %s, Email: %s, Documento: %s%n",
                    cliente.getIdCliente(), cliente.getNombre(), cliente.getEmail(),
                    cliente.getDocumentoIdentidad());
        } catch (Exception e) {
            System.err.println("Caso 1: Error al registrar cliente: " + e.getMessage());
        }
        
        // Caso de error: Registrar cliente con email duplicado
        try {
            String nombre = "Otro Cliente";
            String email = "juan.perez@email.com"; // Email ya registrado
            String documentoIdentidad = "111222333";
            Cliente cliente = service.registrarCliente(nombre, email, documentoIdentidad);
            System.out.println("Caso 2: Cliente registrado exitosamente (no debería llegar aquí)");
        } catch (Exception e) {
            System.err.println("Caso 2: Error al registrar cliente: " + e.getMessage());
        }
    }
}