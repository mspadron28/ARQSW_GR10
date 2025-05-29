package ec.edu.monster.pruebas;

import ec.edu.monster.servicio.ViajecitosService;

/**
 * Clase para probar el método registrarCompra de ViajecitosService.
 * @author MATIAS
 */
public class pruebaRegistrarCompra {
    
    public static void main(String[] args) {
        ViajecitosService service = new ViajecitosService();
        
        // Caso de éxito: Registrar compra para vuelo 1 y cliente 1
        try {
            int idVuelo = 1; // Vuelo Bogotá-Medellín
            int idCliente = 1; // Juan Pérez
            service.registrarCompra(idVuelo, idCliente);
            System.out.println("Caso 1: Compra registrada exitosamente para vuelo ID " + idVuelo + " y cliente ID " + idCliente);
        } catch (Exception e) {
            System.err.println("Caso 1: Error al registrar compra: " + e.getMessage());
        }
        
        // Caso de error: Registrar compra con vuelo inexistente
        try {
            int idVuelo = 999; // Vuelo inexistente
            int idCliente = 1;
            service.registrarCompra(idVuelo, idCliente);
            System.out.println("Caso 2: Compra registrada exitosamente (no debería llegar aquí)");
        } catch (Exception e) {
            System.err.println("Caso 2: Error al registrar compra: " + e.getMessage());
        }
        
        // Caso de error: Registrar compra con cliente inexistente
        try {
            int idVuelo = 1;
            int idCliente = 999; // Cliente inexistente
            service.registrarCompra(idVuelo, idCliente);
            System.out.println("Caso 3: Compra registrada exitosamente (no debería llegar aquí)");
        } catch (Exception e) {
            System.err.println("Caso 3: Error al registrar compra: " + e.getMessage());
        }
    }
}