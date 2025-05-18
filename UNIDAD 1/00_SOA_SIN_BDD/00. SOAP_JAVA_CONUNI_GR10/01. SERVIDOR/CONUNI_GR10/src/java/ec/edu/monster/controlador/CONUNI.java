/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ec.edu.monster.controlador;


import jakarta.jws.WebService;  // Cambia javax.jws por jakarta.jws
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import ec.edu.monster.servicio.ConversionService;
import ec.edu.monster.servicio.LoginService;
/**
 *
 * @author MATIAS
 */
@WebService(serviceName = "CONUNI")
public class CONUNI {

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
     @WebMethod(operationName = "metrosAPies")
    public double metrosAPies(@WebParam(name = "metros") double metros) {
        ConversionService service = new ConversionService();
        return service.metrosAPies(metros);
    }

    @WebMethod(operationName = "piesAMetros")
    public double piesAMetros(@WebParam(name = "pies") double pies) {
        ConversionService service = new ConversionService();
        return service.piesAMetros(pies);
    }

    @WebMethod(operationName = "metrosAYardas")
    public double metrosAYardas(@WebParam(name = "metros") double metros) {
        ConversionService service = new ConversionService();
        return service.metrosAYardas(metros);
    }

    @WebMethod(operationName = "yardasAMetros")
    public double yardasAMetros(@WebParam(name = "yardas") double yardas) {
        ConversionService service = new ConversionService();
        return service.yardasAMetros(yardas);
    }
    
    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "usuario") String usuario, @WebParam(name = "contraseña") String contraseña) {
        LoginService service = new LoginService();
        return service.autenticar(usuario,contraseña);
    }
}
