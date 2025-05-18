package ec.edu.monster.prueba;

import ec.edu.monster.servicio.EurekaService;

/**
 * Clase para probar el registro de un depósito en la base de datos.
 * @author MATIAS
 */
public class pruebaRegistrarDeposito {
    
    public static void main(String[] args) {
        try {
            // DATOS
            String cuenta = "00100001";
            double importe = 200.0;
            String codEmp = "0001";
            
            // PROCESO
            EurekaService service = new EurekaService();
            service.registrarDeposito(cuenta, importe, codEmp);
            
            // REPORTE
            System.out.println("Depósito registrado exitosamente.");
            System.out.println("Cuenta: " + cuenta);
            System.out.println("Importe: " + importe);
            System.out.println("Código Empleado: " + codEmp);
            
        } catch (Exception e) {
            System.err.println("Error al registrar el depósito: " + e.getMessage());
        }
    }
}