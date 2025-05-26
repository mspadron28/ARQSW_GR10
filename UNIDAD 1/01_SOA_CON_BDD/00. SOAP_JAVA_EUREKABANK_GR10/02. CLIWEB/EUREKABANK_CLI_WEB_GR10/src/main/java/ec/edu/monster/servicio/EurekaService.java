package ec.edu.monster.servicio;

import ec.edu.monster.ws.Movimiento;
import ec.edu.monster.ws.WSEureka;
import ec.edu.monster.ws.WSEureka_Service;
import java.util.List;

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