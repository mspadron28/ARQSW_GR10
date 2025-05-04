package ec.edu.monster.controlador;

import ec.edu.monster.servicio.CONUNIService;

public class AppControlador {

    private final CONUNIService service;

    public AppControlador() {
        this.service = new CONUNIService();
    }

    public double pulgadasACentimetros(double pulgadas) {
        return service.pulgadasACentimetros(pulgadas);
    }

    public double centimetrosAPulgadas(double centimetros) {
        return service.centimetrosAPulgadas(centimetros);
    }

    public boolean login(String user, String password) {
        return service.login(user, password);
    }
}