package ec.edu.monster.controlador;

import ec.edu.monster.modelo.Movimiento;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.servicio.EurekaService;

import java.util.List;

public class AppControlador {

    private final EurekaService service;

    public AppControlador() {
        this.service = new EurekaService();
    }

    public Usuario login(String usuario, String contraseña) throws Exception {
        return service.login(usuario, contraseña);
    }

    public List<Movimiento> traerMovimientos(String cuenta) throws Exception {
        return service.leerMovimientos(cuenta);
    }

    public void registrarDeposito(String cuenta, double importe, String codEmp) throws Exception {
        service.registrarDeposito(cuenta, importe, codEmp);
    }

    public void registrarRetiro(String cuenta, double importe, String codEmp) throws Exception {
        service.registrarRetiro(cuenta, importe, codEmp);
    }

    public void realizarTransferencia(String cuentaOrigen, String cuentaDestino, double importe, String codEmp) throws Exception {
        service.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
    }

    public double verificarSaldo(String cuenta) throws Exception {
        return service.verificarSaldo(cuenta);
    }

    public double obtenerCostoMovimiento(String cuenta) throws Exception {
        return service.obtenerCostoMovimiento(cuenta);
    }
}