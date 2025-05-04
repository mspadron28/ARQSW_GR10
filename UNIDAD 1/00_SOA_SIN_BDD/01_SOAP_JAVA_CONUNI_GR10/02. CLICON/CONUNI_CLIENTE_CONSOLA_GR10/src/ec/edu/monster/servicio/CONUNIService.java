package ec.edu.monster.servicio;

/**
 * Service class to interact with the CONUNI web service.
 * @author MATIAS
 */
public class CONUNIService {


    public double pulgadasACentimetros(double pulgadas) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.pulgadasACentimetros(pulgadas);
    }

    public double centimetrosAPulgadas(double centimetros) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.centimetrosAPulgadas(centimetros);
    }

    public boolean login(String usuario, String contraseña) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.login(usuario, contraseña);
    }
}