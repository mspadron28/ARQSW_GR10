/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controlador;

import ec.edu.monster.servicio.CONUNIService;
import ec.edu.monster.servicio.LoginService;

/**
 *
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
        LoginService service = new LoginService();
        return service.login(user, password);
   }

}
