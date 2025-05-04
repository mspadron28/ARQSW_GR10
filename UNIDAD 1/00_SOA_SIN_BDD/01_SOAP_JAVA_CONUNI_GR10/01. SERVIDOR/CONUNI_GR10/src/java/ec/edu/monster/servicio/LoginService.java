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
     public boolean autenticar(String user, String password) {
        return user.equals("monster") && password.equals("monster9");
    }
}
