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
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.centimetrosAPulgadas(centimetros);
    }

    public double pulgadasACentimetros(double pulgadas) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.pulgadasACentimetros(pulgadas);
    }

   
    
}
