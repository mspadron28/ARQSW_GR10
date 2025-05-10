package ec.edu.monster.controlador;

import ec.edu.monster.servicio.CONUNIService;
import ec.edu.monster.servicio.LoginService;

/**
 * Controller class to handle operations for the CONUNI desktop client.
 * @author MATIAS
 */
public class AppControlador {

    public double pulgadasACentimetros(double pulgadas) throws Exception {
        CONUNIService service = new CONUNIService();
        return service.pulgadasACentimetros(pulgadas);
    }

    public double centimetrosAPulgadas(double centimetros) throws Exception {
        CONUNIService service = new CONUNIService();
        return service.centimetrosAPulgadas(centimetros);
    }

    public double metrosAPies(double metros) throws Exception {
        CONUNIService service = new CONUNIService();
        return service.metrosAPies(metros);
    }

    public double piesAMetros(double pies) throws Exception {
        CONUNIService service = new CONUNIService();
        return service.piesAMetros(pies);
    }

    public double metrosAYardas(double metros) throws Exception {
        CONUNIService service = new CONUNIService();
        return service.metrosAYardas(metros);
    }

    public double yardasAMetros(double yardas) throws Exception {
        CONUNIService service = new CONUNIService();
        return service.yardasAMetros(yardas);
    }

    public boolean login(String user, String password) throws Exception {
        LoginService service = new LoginService();
        return service.login(user, password);
    }
}