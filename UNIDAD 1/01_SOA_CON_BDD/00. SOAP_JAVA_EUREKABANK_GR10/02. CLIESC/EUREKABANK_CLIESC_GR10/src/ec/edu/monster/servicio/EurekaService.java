package ec.edu.monster.servicio;

import ec.edu.monster.ws.Movimiento;
import ec.edu.monster.ws.WSEureka;
import ec.edu.monster.ws.WSEureka_Service;
import java.util.List;

/**
 * Servicio para consumir las operaciones del web service WSEureka.
 *
 * @author MATIAS
 */
public class EurekaService {

    private final WSEureka port;

    public EurekaService() {
        WSEureka_Service service = new WSEureka_Service();
        this.port = service.getWSEurekaPort();
    }

    /**
     * Obtiene la lista de movimientos para una cuenta específica.
     *
     * @param cuenta Código de la cuenta.
     * @return Lista de movimientos.
     */
    public List<Movimiento> traerMovimientos(String cuenta) {
        return port.leerMovimientos(cuenta);
    }

    /**
     * Registra un depósito en una cuenta.
     *
     * @param cuenta Código de la cuenta.
     * @param importe Monto del depósito.
     * @param codEmp Código del empleado.
     * @return 1 si el depósito es exitoso, -1 si falla.
     */
    public int registrarDeposito(String cuenta, double importe, String codEmp) {
        return port.registrarDeposito(cuenta, importe, codEmp);
    }

    /**
     * Registra un retiro en una cuenta.
     *
     * @param cuenta Código de la cuenta.
     * @param importe Monto del retiro.
     * @param codEmp Código del empleado.
     * @return 1 si el retiro es exitoso, -1 si falla.
     */
    public int registrarRetiro(String cuenta, double importe, String codEmp) {
        return port.registrarRetiro(cuenta, importe, codEmp);
    }

    /**
     * Realiza una transferencia entre dos cuentas.
     *
     * @param cuentaOrigen Código de la cuenta origen.
     * @param cuentaDestino Código de la cuenta destino.
     * @param importe Monto de la transferencia.
     * @param codEmp Código del empleado.
     * @return 1 si la transferencia es exitosa, -1 si falla.
     */
    public int realizarTransferencia(String cuentaOrigen, String cuentaDestino, double importe, String codEmp) {
        return port.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
    }

    /**
     * Verifica el saldo de una cuenta.
     *
     * @param cuenta Código de la cuenta.
     * @return Saldo de la cuenta, o -1 si hay error.
     */
    public double verificarSaldo(String cuenta) {
        return port.verificarSaldo(cuenta);
    }

    /**
     * Obtiene el costo de movimiento para una cuenta.
     *
     * @param cuenta Código de la cuenta.
     * @return Costo del movimiento, o -1 si hay error.
     */
    public double obtenerCostoMovimiento(String cuenta) {
        return port.obtenerCostoMovimiento(cuenta);
    }

    /**
     * Inicia sesión para un usuario.
     *
     * @param usuario Nombre de usuario (vch_emplusuario).
     * @param clave Clave del usuario (vch_emplclave).
     * @return Objeto Usuario si la autenticación es exitosa, null si falla.
     */
    public ec.edu.monster.modelo.Usuario iniciarSesion(String usuario, String clave) {
        ec.edu.monster.ws.Usuario wsUser = port.iniciarSesion(usuario, clave);
        if (wsUser != null) {
            return new ec.edu.monster.modelo.Usuario(
                    wsUser.getCodigo(),
                    wsUser.getUsuario(),
                    wsUser.getClave(),
                    wsUser.getEstado()
            );
        }
        return null;
    }

}
