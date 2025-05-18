package ec.edu.monster.prueba;

import ec.edu.monster.servicio.EurekaService;

/**
 * Clase para probar la realización de una transferencia en la base de datos.
 * @author MATIAS
 */
public class pruebaRealizarTransferencia {
    
    public static void main(String[] args) {
        try {
            // DATOS
            String cuentaOrigen = "00100001";
            String cuentaDestino = "00200001";
            double importe = 150.0;
            String codEmp = "0001";
            
            // PROCESO
            EurekaService service = new EurekaService();
            service.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
            
            // REPORTE
            System.out.println("Transferencia realizada exitosamente.");
            System.out.println("Cuenta Origen: " + cuentaOrigen);
            System.out.println("Cuenta Destino: " + cuentaDestino);
            System.out.println("Importe: " + importe);
            System.out.println("Código Empleado: " + codEmp);
            
        } catch (Exception e) {
            System.err.println("Error al realizar la transferencia: " + e.getMessage());
        }
    }
}