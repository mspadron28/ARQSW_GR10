package ec.edu.monster.prueba;

import ec.edu.monster.servicio.EurekaService;

/**
 * Clase para probar la verificación del saldo de una cuenta en la base de datos.
 * @author MATIAS
 */
public class pruebaVerificarSaldo {
    
    public static void main(String[] args) {
        try {
            // DATOS
            String cuenta = "00100001";
            
            // PROCESO
            EurekaService service = new EurekaService();
            double saldo = service.verificarSaldo(cuenta);
            
            // REPORTE
            System.out.println("Saldo verificado exitosamente.");
            System.out.println("Cuenta: " + cuenta);
            System.out.println("Saldo: " + saldo);
            
        } catch (Exception e) {
            System.err.println("Error al verificar el saldo: " + e.getMessage());
        }
    }
}