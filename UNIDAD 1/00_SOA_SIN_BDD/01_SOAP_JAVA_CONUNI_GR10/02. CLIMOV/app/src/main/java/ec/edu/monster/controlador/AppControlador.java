package ec.edu.monster.controlador;

import ec.edu.monster.servicio.CONUNIService;

public class AppControlador {
    private final CONUNIService service;

    public AppControlador() {
        this.service = new CONUNIService();
    }

    public boolean login(String usuario, String contraseña) throws Exception {
        return service.login(usuario, contraseña);
    }

    public double pulgadasACentimetros(double pulgadas) throws Exception {
        return service.pulgadasACentimetros(pulgadas);
    }

    public double centimetrosAPulgadas(double centimetros) throws Exception {
        return service.centimetrosAPulgadas(centimetros);
    }
}