package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import ec.edu.monster.ws.Movimiento;

import java.util.List;

/**
 * Controlador para coordinar las operaciones entre las vistas y el servicio.
 *
 * @author MATIAS
 */
public class EurekaController {

    private final EurekaService service;

    public EurekaController() {
        this.service = new EurekaService();
    }

    public List<Movimiento> consultarMovimientos(String cuenta) {
        return service.traerMovimientos(cuenta);
    }

    public boolean registrarDeposito(String cuenta, double importe, String codEmp) {
        return service.registrarDeposito(cuenta, importe, codEmp) == 1;
    }

    public boolean registrarRetiro(String cuenta, double importe, String codEmp) {
        return service.registrarRetiro(cuenta, importe, codEmp) == 1;
    }

    public boolean realizarTransferencia(String cuentaOrigen, String cuentaDestino, double importe, String codEmp) {
        return service.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp) == 1;
    }

    public double verificarSaldo(String cuenta) throws Exception {
        double saldo = service.verificarSaldo(cuenta);
        if (saldo == -1.0) {
            throw new Exception("Error al verificar el saldo");
        }
        return saldo;
    }

    public double obtenerCostoMovimiento(String cuenta) throws Exception {
        double costo = service.obtenerCostoMovimiento(cuenta);
        if (costo == -1.0) {
            throw new Exception("Error al obtener el costo del movimiento");
        }
        return costo;
    }

}
