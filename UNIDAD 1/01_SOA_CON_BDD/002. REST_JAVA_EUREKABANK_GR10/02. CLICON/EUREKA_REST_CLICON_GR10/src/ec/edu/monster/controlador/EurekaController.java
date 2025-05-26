package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import ec.edu.monster.modelo.Movimiento;
import java.util.List;

/**
 * Controller to coordinate between console input and service layer.
 * @author MATIAS
 */
public class EurekaController {

    private final EurekaService service;

    public EurekaController() {
        this.service = new EurekaService();
    }

    public List<Movimiento> consultarMovimientos(String cuenta) throws Exception {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new Exception("El número de cuenta es requerido.");
        }
        List<Movimiento> movimientos = service.leerMovimientos(cuenta);
        if (movimientos.isEmpty()) {
            throw new Exception("No se encontraron movimientos para la cuenta.");
        }
        return movimientos;
    }

    public void registrarDeposito(String cuenta, double importe, String codEmp) throws Exception {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new Exception("El número de cuenta es requerido.");
        }
        if (importe <= 0) {
            throw new Exception("El importe debe ser mayor que 0.");
        }
        if (codEmp == null || codEmp.trim().isEmpty()) {
            throw new Exception("El código de empleado es requerido.");
        }
        service.registrarDeposito(cuenta, importe, codEmp);
    }

    public void registrarRetiro(String cuenta, double importe, String codEmp) throws Exception {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new Exception("El número de cuenta es requerido.");
        }
        if (importe <= 0) {
            throw new Exception("El importe debe ser mayor que 0.");
        }
        if (codEmp == null || codEmp.trim().isEmpty()) {
            throw new Exception("El código de empleado es requerido.");
        }
        service.registrarRetiro(cuenta, importe, codEmp);
    }

    public void realizarTransferencia(String cuentaOrigen, String cuentaDestino, double importe, String codEmp) throws Exception {
        if (cuentaOrigen == null || cuentaOrigen.trim().isEmpty() || cuentaDestino == null || cuentaDestino.trim().isEmpty()) {
            throw new Exception("Los números de cuenta son requeridos.");
        }
        if (cuentaOrigen.equals(cuentaDestino)) {
            throw new Exception("Las cuentas de origen y destino deben ser diferentes.");
        }
        if (importe <= 0) {
            throw new Exception("El importe debe ser mayor que 0.");
        }
        if (codEmp == null || codEmp.trim().isEmpty()) {
            throw new Exception("El código de empleado es requerido.");
        }
        service.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
    }

    public double verificarSaldo(String cuenta) throws Exception {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new Exception("El número de cuenta es requerido.");
        }
        return service.verificarSaldo(cuenta);
    }

    public double obtenerCostoMovimiento(String cuenta) throws Exception {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new Exception("El número de cuenta es requerido.");
        }
        return service.obtenerCostoMovimiento(cuenta);
    }

    public boolean iniciarSesion(String usuario, String clave) throws Exception {
        if (usuario == null || usuario.trim().isEmpty() || clave == null || clave.trim().isEmpty()) {
            throw new Exception("Usuario y contraseña son requeridos.");
        }
        return service.iniciarSesion(usuario, clave) != null;
    }
}