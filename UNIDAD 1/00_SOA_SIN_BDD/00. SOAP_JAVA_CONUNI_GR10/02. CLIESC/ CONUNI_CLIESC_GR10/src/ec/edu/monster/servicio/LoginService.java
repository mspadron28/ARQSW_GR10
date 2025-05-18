/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.servicio;

/**
 *
 * @author MATIAS
 */
public class LoginService {

    public boolean login(java.lang.String usuario, java.lang.String contraseña) {
        ec.edu.monster.CONUNI_Service service = new ec.edu.monster.CONUNI_Service();
        ec.edu.monster.CONUNI port = service.getCONUNIPort();
        return port.login(usuario, contraseña);
    }
    
}
