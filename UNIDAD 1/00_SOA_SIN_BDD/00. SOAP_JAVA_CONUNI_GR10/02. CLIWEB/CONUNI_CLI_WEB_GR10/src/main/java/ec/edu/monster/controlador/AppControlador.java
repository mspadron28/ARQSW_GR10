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

    public double metrosAPies(double metros) {
        return service.metrosAPies(metros);
    }

    public double piesAMetros(double pies) {
        return service.piesAMetros(pies);
    }

    public double metrosAYardas(double metros) {
        return service.metrosAYardas(metros);
    }

    public double yardasAMetros(double yardas) {
        return service.yardasAMetros(yardas);
    }
}
