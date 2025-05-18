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

    public double metrosAPies(double metros) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.metrosAPies(metros);
    }

    public double piesAMetros(double pies) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.piesAMetros(pies);
    }

    public double metrosAYardas(double metros) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.metrosAYardas(metros);
    }

    public double yardasAMetros(double yardas) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.yardasAMetros(yardas);
    }

    private static boolean login_1(java.lang.String usuario, java.lang.String contraseña) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.login(usuario, contraseña);
    }
}
