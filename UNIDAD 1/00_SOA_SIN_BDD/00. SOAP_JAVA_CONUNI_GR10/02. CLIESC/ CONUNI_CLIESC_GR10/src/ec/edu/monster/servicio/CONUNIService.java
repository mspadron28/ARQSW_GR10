/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.servicio;

/**
 *
 * @author MATIAS
 */
public class CONUNIService {

    public double centimetrosAPulgadas(double centimetros) {
        ec.edu.monster.ws.CONUNI_Service service = new ec.edu.monster.ws.CONUNI_Service();
        ec.edu.monster.ws.CONUNI port = service.getCONUNIPort();
        return port.centimetrosAPulgadas(centimetros);
    }

    public double pulgadasACentimetros(double pulgadas) {
        ec.edu.monster.ws.CONUNI_Service service = new ec.edu.monster.ws.CONUNI_Service();
        ec.edu.monster.ws.CONUNI port = service.getCONUNIPort();
        return port.pulgadasACentimetros(pulgadas);
    }

    public double metrosAPies(double metros) {
        ec.edu.monster.ws.CONUNI_Service service = new ec.edu.monster.ws.CONUNI_Service();
        ec.edu.monster.ws.CONUNI port = service.getCONUNIPort();
        return port.metrosAPies(metros);
    }

    public double piesAMetros(double pies) {
        ec.edu.monster.ws.CONUNI_Service service = new ec.edu.monster.ws.CONUNI_Service();
        ec.edu.monster.ws.CONUNI port = service.getCONUNIPort();
        return port.piesAMetros(pies);
    }

    public double metrosAYardas(double metros) {
        ec.edu.monster.ws.CONUNI_Service service = new ec.edu.monster.ws.CONUNI_Service();
        ec.edu.monster.ws.CONUNI port = service.getCONUNIPort();
        return port.metrosAYardas(metros);
    }

    public double yardasAMetros(double yardas) {
        ec.edu.monster.ws.CONUNI_Service service = new ec.edu.monster.ws.CONUNI_Service();
        ec.edu.monster.ws.CONUNI port = service.getCONUNIPort();
        return port.yardasAMetros(yardas);
    }
    
    
}
