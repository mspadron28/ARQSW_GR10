package ec.edu.monster.controlador;

import ec.edu.monster.modelo.Movimiento;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.servicio.EurekaService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio web para operaciones bancarias de EurekaBank.
 *
 * @author MATIAS
 */
@WebService(serviceName = "WSEureka")
public class WSEureka {

    private final EurekaService eurekaService;

    /**
     * Constructor que inicializa el servicio EurekaService.
     */
    public WSEureka() {
        this.eurekaService = new EurekaService();
    }

    /**
     * Obtiene la lista de movimientos para una cuenta específica.
     *
     * @param cuenta Código de la cuenta (chr_cuencodigo).
     * @return Lista de movimientos asociados a la cuenta.
     */
    @WebMethod(operationName = "leerMovimientos")
    @WebResult(name = "Movimiento")
    public List<Movimiento> leerMovimientos(@WebParam(name = "cuenta") String cuenta) {
        List<Movimiento> lista;
        try {
            lista = eurekaService.leerMovimientos(cuenta);
        } catch (Exception e) {
            lista = new ArrayList<>();
        }
        return lista;
    }

    /**
     * Registra un depósito en una cuenta específica.
     *
     * @param cuenta Código de la cuenta (chr_cuencodigo).
     * @param importe Monto del depósito.
     * @param codEmp Código del empleado que realiza la operación.
     * @return estado, 1 ó -1
     */
    @WebMethod(operationName = "registrarDeposito")
    @WebResult(name = "estado")
    public int registrarDeposito(
            @WebParam(name = "cuenta") String cuenta,
            @WebParam(name = "importe") double importe,
            @WebParam(name = "codEmp") String codEmp) {
        int estado;
        try {
            eurekaService.registrarDeposito(cuenta, importe, codEmp);
            estado = 1;
        } catch (Exception e) {
            estado = -1;
        }
        return estado;
    }

    /**
     * Registra un retiro de una cuenta específica.
     *
     * @param cuenta Código de la cuenta (chr_cuencodigo).
     * @param importe Monto del retiro.
     * @param codEmp Código del empleado que realiza la operación.
     * @return estado, 1 ó -1
     */
    @WebMethod(operationName = "registrarRetiro")
    @WebResult(name = "estado")
    public int registrarRetiro(
            @WebParam(name = "cuenta") String cuenta,
            @WebParam(name = "importe") double importe,
            @WebParam(name = "codEmp") String codEmp) {
        int estado;
        try {
            eurekaService.registrarRetiro(cuenta, importe, codEmp);
            estado = 1;
        } catch (Exception e) {
            estado = -1;
        }
        return estado;
    }

    /**
     * Realiza una transferencia entre dos cuentas.
     *
     * @param cuentaOrigen Código de la cuenta origen (chr_cuencodigo).
     * @param cuentaDestino Código de la cuenta destino (chr_cuencodigo).
     * @param importe Monto de la transferencia.
     * @param codEmp Código del empleado que realiza la operación.
     * @return estado, 1 ó -1
     */
    @WebMethod(operationName = "realizarTransferencia")
    @WebResult(name = "estado")
    public int realizarTransferencia(
            @WebParam(name = "cuentaOrigen") String cuentaOrigen,
            @WebParam(name = "cuentaDestino") String cuentaDestino,
            @WebParam(name = "importe") double importe,
            @WebParam(name = "codEmp") String codEmp) {
        int estado;
        try {
            eurekaService.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
            estado = 1;
        } catch (Exception e) {
            estado = -1;
        }
        return estado;
    }

    /**
     * Verifica el saldo de una cuenta específica.
     *
     * @param cuenta Código de la cuenta (chr_cuencodigo).
     * @return Saldo actual de la cuenta, o -1 si hay error.
     */
    @WebMethod(operationName = "verificarSaldo")
    @WebResult(name = "saldo")
    public double verificarSaldo(@WebParam(name = "cuenta") String cuenta) {
        try {
            return eurekaService.verificarSaldo(cuenta);
        } catch (Exception e) {
            return -1.0;
        }
    }

    /**
     * Obtiene el costo de movimiento para una cuenta específica.
     *
     * @param cuenta Código de la cuenta (chr_cuencodigo).
     * @return Costo del movimiento, o -1 si hay error.
     */
    @WebMethod(operationName = "obtenerCostoMovimiento")
    @WebResult(name = "costo")
    public double obtenerCostoMovimiento(@WebParam(name = "cuenta") String cuenta) {
        try {
            return eurekaService.obtenerCostoMovimiento(cuenta);
        } catch (Exception e) {
            return -1.0;
        }
    }

    /**
     * Inicia sesión para un usuario y registra la entrada en LOGSESSION.
     *
     * @param username Nombre de usuario (vch_emplusuario).
     * @param clave Clave del usuario (vch_emplclave).
     * @return Objeto Usuario con los datos del usuario autenticado, o null si
     * hay error.
     */
    @WebMethod(operationName = "iniciarSesion")
    @WebResult(name = "usuario")
    public Usuario iniciarSesion(
            @WebParam(name = "username") String username, // Cambiar 'usuario' a 'username'
            @WebParam(name = "clave") String clave) {
        try {
            return eurekaService.iniciarSesion(username, clave); // Actualizar el nombre del parámetro
        } catch (Exception e) {
            return null;
        }
    }
}
