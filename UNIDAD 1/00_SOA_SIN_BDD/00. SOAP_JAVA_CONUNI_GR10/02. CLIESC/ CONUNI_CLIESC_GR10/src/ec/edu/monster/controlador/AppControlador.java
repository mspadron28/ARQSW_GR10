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

    public double metrosAPies(double metros) {
        CONUNIService service = new CONUNIService();
        return service.metrosAPies(metros);
    }

    public double piesAMetros(double pies) {
        CONUNIService service = new CONUNIService();
        return service.piesAMetros(pies);
    }

    public double metrosAYardas(double metros) {
        CONUNIService service = new CONUNIService();
        return service.metrosAYardas(metros);
    }

    public double yardasAMetros(double yardas) {
        CONUNIService service = new CONUNIService();
        return service.yardasAMetros(yardas);
    }

    public boolean login(String user, String password) {
        LoginService service = new LoginService();
        return service.login(user, password);
    }
}
