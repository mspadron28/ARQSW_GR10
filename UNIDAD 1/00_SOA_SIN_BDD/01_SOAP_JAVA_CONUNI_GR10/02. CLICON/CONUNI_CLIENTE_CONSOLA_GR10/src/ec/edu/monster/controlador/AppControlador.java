package ec.edu.monster.controlador;

import ec.edu.monster.servicio.CONUNIService;

/**
 * Controller class to handle operations for the CONUNI client.
 * @author MATIAS
 */
public class AppControlador {

    public double pulgadasACentimetros(double pulgadas) {
        CONUNIService service = new CONUNIService();
        return service.pulgadasACentimetros(pulgadas);
    }

    public double centimetrosAPulgadas(double centimetros) {
        CONUNIService service = new CONUNIService();
        return service.centimetrosAPulgadas(centimetros);
    }

    public boolean login(String user, String password) {
        CONUNIService service = new CONUNIService();
        return service.login(user, password);
    }
}