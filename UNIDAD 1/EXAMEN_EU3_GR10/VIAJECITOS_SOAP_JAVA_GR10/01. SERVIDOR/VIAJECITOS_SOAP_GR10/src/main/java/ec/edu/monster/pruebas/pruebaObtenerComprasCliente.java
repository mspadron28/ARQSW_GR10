package ec.edu.monster.pruebas;

import ec.edu.monster.modelo.Compra;
import ec.edu.monster.servicio.ViajecitosService;
import java.util.List;

/**
 * Clase para probar el método obtenerComprasCliente de ViajecitosService.
 * @author MATIAS
 */
public class pruebaObtenerComprasCliente {
    
    public static void main(String[] args) {
        ViajecitosService service = new ViajecitosService();
        
        // Caso de éxito: Obtener compras del cliente con ID 1
        try {
            int idCliente = 1; // Juan Pérez
            List<Compra> compras = service.obtenerComprasCliente(idCliente);
            System.out.println("Caso 1: Compras del cliente ID " + idCliente);
            System.out.println("=================================");
            if (!compras.isEmpty()) {
                for (Compra c : compras) {
                    System.out.printf("ID Compra: %d, Vuelo: %s a %s, Valor: %.2f, Fecha Compra: %s%n",
                            c.getIdCompra(), c.getVuelo().getCiudadOrigen(),
                            c.getVuelo().getCiudadDestino(), c.getVuelo().getValor(),
                            c.getFechaCompra());
                }
            } else {
                System.out.println("No se encontraron compras.");
            }
        } catch (Exception e) {
            System.err.println("Caso 1: Error al obtener compras: " + e.getMessage());
        }
        
        // Caso de error: Obtener compras de cliente inexistente
        try {
            int idCliente = 999; // Cliente inexistente
            List<Compra> compras = service.obtenerComprasCliente(idCliente);
            System.out.println("\nCaso 2: Compras del cliente ID " + idCliente);
            System.out.println("=================================");
            if (!compras.isEmpty()) {
                for (Compra c : compras) {
                    System.out.printf("ID Compra: %d, Vuelo: %s a %s, Valor: %.2f, Fecha Compra: %s%n",
                            c.getIdCompra(), c.getVuelo().getCiudadOrigen(),
                            c.getVuelo().getCiudadDestino(), c.getVuelo().getValor(),
                            c.getFechaCompra());
                }
            } else {
                System.out.println("No se encontraron compras.");
            }
        } catch (Exception e) {
            System.err.println("Caso 2: Error al obtener compras: " + e.getMessage());
        }
    }
}