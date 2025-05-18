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

    public double metrosAPies(double metros) throws Exception {
        return service.metrosAPies(metros);
    }

    public double piesAMetros(double pies) throws Exception {
        return service.piesAMetros(pies);
    }

    public double metrosAYardas(double metros) throws Exception {
        return service.metrosAYardas(metros);
    }

    public double yardasAMetros(double yardas) throws Exception {
        return service.yardasAMetros(yardas);
    }
}