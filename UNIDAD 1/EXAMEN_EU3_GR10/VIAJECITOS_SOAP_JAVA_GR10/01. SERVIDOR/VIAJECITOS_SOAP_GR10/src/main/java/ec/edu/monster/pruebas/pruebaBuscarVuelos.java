package ec.edu.monster.pruebas;

import ec.edu.monster.modelo.Vuelo;
import ec.edu.monster.servicio.ViajecitosService;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Clase para probar el método buscarVuelos de ViajecitosService.
 * @author MATIAS
 */
public class pruebaBuscarVuelos {
    
    public static void main(String[] args) {
        try {
            // Caso de éxito: Buscar vuelos de Bogotá a Medellín el 2025-06-01
            String ciudadOrigen = "Bogotá";
            String ciudadDestino = "Medellín";
            String fechaStr = "2025-06-01";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fecha = sdf.parse(fechaStr);
            
            ViajecitosService service = new ViajecitosService();
            List<Vuelo> vuelos = service.buscarVuelos(ciudadOrigen, ciudadDestino, fecha);
            
            System.out.println("Caso 1: Vuelos de Bogotá a Medellín el 2025-06-01");
            System.out.println("=============================================");
            if (!vuelos.isEmpty()) {
                for (Vuelo v : vuelos) {
                    System.out.printf("ID: %d, Origen: %s, Destino: %s, Valor: %.2f, Salida: %s%n",
                            v.getIdVuelo(), v.getCiudadOrigen(), v.getCiudadDestino(),
                            v.getValor(), v.getHoraSalida());
                }
            } else {
                System.out.println("No se encontraron vuelos.");
            }
            
            // Caso de error: Buscar vuelos de Lima a Santiago (ruta inexistente)
            ciudadOrigen = "Lima";
            ciudadDestino = "Santiago";
            vuelos = service.buscarVuelos(ciudadOrigen, ciudadDestino, fecha);
            
            System.out.println("\nCaso 2: Vuelos de Lima a Santiago el 2025-06-01");
            System.out.println("=============================================");
            if (!vuelos.isEmpty()) {
                for (Vuelo v : vuelos) {
                    System.out.printf("ID: %d, Origen: %s, Destino: %s, Valor: %.2f, Salida: %s%n",
                            v.getIdVuelo(), v.getCiudadOrigen(), v.getCiudadDestino(),
                            v.getValor(), v.getHoraSalida());
                }
            } else {
                System.out.println("No se encontraron vuelos.");
            }
            
        } catch (Exception e) {
            System.err.println("Error al buscar vuelos: " + e.getMessage());
        }
    }
}