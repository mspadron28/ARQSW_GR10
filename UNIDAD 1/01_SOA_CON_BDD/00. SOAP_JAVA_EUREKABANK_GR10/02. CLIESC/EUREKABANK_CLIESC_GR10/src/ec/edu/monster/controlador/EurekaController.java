package ec.edu.monster.controlador;

import ec.edu.monster.servicio.EurekaService;
import ec.edu.monster.ws.Movimiento;
import ec.edu.monster.modelo.Usuario;

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

    /**
     * Inicia sesión para un usuario.
     *
     * @param usuario Nombre de usuario (vch_emplusuario).
     * @param clave Clave del usuario (vch_emplclave).
     * @return true si la autenticación es exitosa, false si falla.
     * @throws Exception si ocurre un error en el proceso.
     */
    public boolean iniciarSesion(String usuario, String clave) throws Exception {
        Usuario user = service.iniciarSesion(usuario, clave);
        if (user == null) {
            return false;
        }
        return true;
    }

}
