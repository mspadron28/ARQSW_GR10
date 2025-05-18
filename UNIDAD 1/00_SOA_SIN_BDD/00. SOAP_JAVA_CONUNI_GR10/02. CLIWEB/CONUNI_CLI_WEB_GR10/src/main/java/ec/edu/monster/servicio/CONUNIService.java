package ec.edu.monster.servicio;

import ec.edu.monster.ws.CONUNI;
import ec.edu.monster.ws.CONUNI_Service;

public class CONUNIService {

    private final CONUNI port;

    public CONUNIService() {
        CONUNI_Service service = new CONUNI_Service();
        this.port = service.getCONUNIPort();
    }

    public double pulgadasACentimetros(double pulgadas) {
        return port.pulgadasACentimetros(pulgadas);
    }

    public double centimetrosAPulgadas(double centimetros) {
        return port.centimetrosAPulgadas(centimetros);
    }

    public double metrosAPies(double metros) {
        return port.metrosAPies(metros);
    }

    public double piesAMetros(double pies) {
        return port.piesAMetros(pies);
    }

    public double metrosAYardas(double metros) {
        return port.metrosAYardas(metros);
    }

    public double yardasAMetros(double yardas) {
        return port.yardasAMetros(yardas);
    }

    public boolean login(String usuario, String contraseña) {
        return port.login(usuario, contraseña);
    }
}
