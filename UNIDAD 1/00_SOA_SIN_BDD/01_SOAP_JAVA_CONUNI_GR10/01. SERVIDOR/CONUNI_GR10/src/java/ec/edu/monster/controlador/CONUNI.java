/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ec.edu.monster.controlador;

import ec.edu.monster.servicio.ConversionService;
import ec.edu.monster.servicio.LoginService;
import ec.edu.monster.servicio.SumaService;
import jakarta.jws.WebService;  // Cambia javax.jws por jakarta.jws
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

/**
 *
 * @author MATIAS
 */
@WebService(serviceName = "CONUNI")
public class CONUNI {

    @WebMethod(operationName = "suma")
    public int suma(@WebParam(name = "a") int a, @WebParam(name = "b") int b) {
        SumaService service = new SumaService();
        int suma = service.sumar(a, b);
        return suma;
    }

    @WebMethod(operationName = "pulgadasACentimetros")
    public double pulgadasACentimetros(@WebParam(name = "pulgadas") double pulgadas) {
        ConversionService service = new ConversionService();
        return service.pulgadasACentimetros(pulgadas);
    }

    @WebMethod(operationName = "centimetrosAPulgadas")
    public double centimetrosAPulgadas(@WebParam(name = "centimetros") double centimetros) {
        ConversionService service = new ConversionService();
        return service.centimetrosAPulgadas(centimetros);
    }
    
    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "usuario") String usuario, @WebParam(name = "contraseña") String contraseña) {
        LoginService service = new LoginService();
        return service.autenticar(usuario,contraseña);
    }
}
