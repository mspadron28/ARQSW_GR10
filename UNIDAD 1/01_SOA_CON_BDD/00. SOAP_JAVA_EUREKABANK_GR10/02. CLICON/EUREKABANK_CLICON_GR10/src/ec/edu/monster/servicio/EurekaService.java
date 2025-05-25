package ec.edu.monster.servicio;

import ec.edu.monster.ws.Movimiento;
import ec.edu.monster.ws.WSEureka;
import ec.edu.monster.ws.WSEureka_Service;
import java.util.List;

/**
 * Service class to interact with the WSEureka web service.
 * @author MATIAS
 */
public class EurekaService {

    private final WSEureka port;

    public EurekaService() {
        WSEureka_Service service = new WSEureka_Service();
        this.port = service.getWSEurekaPort();
    }

    public List<Movimiento> traerMovimientos(String cuenta) {
        return port.leerMovimientos(cuenta);
    }

    public int registrarDeposito(String cuenta, double importe, String codEmp) {
        return port.registrarDeposito(cuenta, importe, codEmp);
    }

    public int registrarRetiro(String cuenta, double importe, String codEmp) {
        return port.registrarRetiro(cuenta, importe, codEmp);
    }

    public int realizarTransferencia(String cuentaOrigen, String cuentaDestino, double importe, String codEmp) {
        return port.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
    }

    public double verificarSaldo(String cuenta) {
        return port.verificarSaldo(cuenta);
    }

    public double obtenerCostoMovimiento(String cuenta) {
        return port.obtenerCostoMovimiento(cuenta);
    }
}