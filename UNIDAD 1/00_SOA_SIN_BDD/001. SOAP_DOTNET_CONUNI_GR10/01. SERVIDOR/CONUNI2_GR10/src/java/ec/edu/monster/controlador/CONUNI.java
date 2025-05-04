/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ec.edu.monster.controlador;

import jakarta.jws.WebService;  // Cambia javax.jws por jakarta.jws
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

/**
 *
 * @author MATIAS
 */
@WebService(serviceName = "CONUNI")
public class CONUNI {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
