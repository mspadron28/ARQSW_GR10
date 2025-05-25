package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import ec.edu.monster.ws.Movimiento;
import java.util.List;

/**
 * Controller to coordinate between console input and service layer.
 *
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
        List<Movimiento> movimientos = service.traerMovimientos(cuenta);
        if (movimientos.isEmpty()) {
            throw new Exception("No se encontraron movimientos para la cuenta.");
        }
        return movimientos;
    }

    public boolean registrarDeposito(String cuenta, double importe, String codEmp) throws Exception {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new Exception("El número de cuenta es requerido.");
        }
        if (importe <= 0) {
            throw new Exception("El importe debe ser mayor que 0.");
        }
        if (codEmp == null || codEmp.trim().isEmpty()) {
            throw new Exception("El código de empleado es requerido.");
        }
        return service.registrarDeposito(cuenta, importe, codEmp) == 1;
    }

    public boolean registrarRetiro(String cuenta, double importe, String codEmp) throws Exception {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new Exception("El número de cuenta es requerido.");
        }
        if (importe <= 0) {
            throw new Exception("El importe debe ser mayor que 0.");
        }
        if (codEmp == null || codEmp.trim().isEmpty()) {
            throw new Exception("El código de empleado es requerido.");
        }
        double costo = service.obtenerCostoMovimiento(cuenta);
        if (costo == -1.0) {
            throw new Exception("Error al obtener el costo del movimiento.");
        }
        return service.registrarRetiro(cuenta, importe, codEmp) == 1;
    }

    public boolean realizarTransferencia(String cuentaOrigen, String cuentaDestino, double importe, String codEmp) throws Exception {
        if (cuentaOrigen == null || cuentaOrigen.trim().isEmpty() || cuentaDestino == null || cuentaDestino.trim().isEmpty()) {
            throw new Exception("Los números de cuenta son requeridos.");
        }
        if (importe <= 0) {
            throw new Exception("El importe debe ser mayor que 0.");
        }
        if (codEmp == null || codEmp.trim().isEmpty()) {
            throw new Exception("El código de empleado es requerido.");
        }
        double costo = service.obtenerCostoMovimiento(cuentaOrigen);
        if (costo == -1.0) {
            throw new Exception("Error al obtener el costo del movimiento.");
        }
        return service.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp) == 1;
    }

    public double verificarSaldo(String cuenta) throws Exception {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new Exception("El número de cuenta es requerido.");
        }
        double saldo = service.verificarSaldo(cuenta);
        if (saldo == -1.0) {
            throw new Exception("Error al verificar el saldo.");
        }
        return saldo;
    }

    public double obtenerCostoMovimiento(String cuenta) throws Exception {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new Exception("El número de cuenta es requerido.");
        }
        double costo = service.obtenerCostoMovimiento(cuenta);
        if (costo == -1.0) {
            throw new Exception("Error al obtener el costo del movimiento.");
        }
        return costo;
    }

    /**
     * Inicia sesión para un usuario.
     *
     * @param usuario Nombre de usuario (vch_emplusuario).
     * @param clave Clave del usuario (vch_emplclave).
     * @return true si la autenticación es exitosa, false si falla.
     * @throws Exception si ocurre un error en el proceso.
     */
    public boolean iniciarSesion(String usuario, String clave) throws Exception {
        if (usuario == null || usuario.trim().isEmpty() || clave == null || clave.trim().isEmpty()) {
            throw new Exception("Usuario y contraseña son requeridos.");
        }
        ec.edu.monster.modelo.Usuario user = service.iniciarSesion(usuario, clave);
        return user != null;
    }
}
