package ec.edu.monster.pruebas;

import ec.edu.monster.modelo.Vuelo;
import ec.edu.monster.servicio.ViajecitosService;
import java.text.SimpleDateFormat;

/**
 * Clase para probar el método obtenerVueloMasCaro de ViajecitosService.
 * @author MATIAS
 */
public class pruebaObtenerVueloMasCaro {
    
    public static void main(String[] args) {
        try {
            // Caso de éxito: Vuelo más caro de Quito a Guayaquil el 2025-06-03
            String ciudadOrigen = "Quito";
            String ciudadDestino = "Guayaquil";
            String fechaStr = "2025-06-03";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fecha = sdf.parse(fechaStr);
            
            ViajecitosService service = new ViajecitosService();
            Vuelo vuelo = service.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
            
            System.out.println("Caso 1: Vuelo más caro de Quito a Guayaquil el 2025-06-03");
            System.out.println("=================================================");
            if (vuelo != null) {
                System.out.printf("ID: %d, Origen: %s, Destino: %s, Valor: %.2f, Salida: %s%n",
                        vuelo.getIdVuelo(), vuelo.getCiudadOrigen(), vuelo.getCiudadDestino(),
                        vuelo.getValor(), vuelo.getHoraSalida());
            } else {
                System.out.println("No se encontró vuelo.");
            }
            
            // Caso de error: Vuelo más caro de Lima a Santiago (ruta inexistente)
            ciudadOrigen = "Lima";
            ciudadDestino = "Santiago";
            vuelo = service.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
            
            System.out.println("\nCaso 2: Vuelo más caro de Lima a Santiago el 2025-06-03");
            System.out.println("=================================================");
            if (vuelo != null) {
                System.out.printf("ID: %d, Origen: %s, Destino: %s, Valor: %.2f, Salida: %s%n",
                        vuelo.getIdVuelo(), vuelo.getCiudadOrigen(), vuelo.getCiudadDestino(),
                        vuelo.getValor(), vuelo.getHoraSalida());
            } else {
                System.out.println("No se encontró vuelo.");
            }
            
        } catch (Exception e) {
            System.err.println("Error al obtener vuelo más caro: " + e.getMessage());
        }
    }
}