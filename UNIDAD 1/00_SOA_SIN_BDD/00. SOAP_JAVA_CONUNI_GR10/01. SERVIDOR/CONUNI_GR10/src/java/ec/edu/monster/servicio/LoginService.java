
package ec.edu.monster.servicio;

/**
 *
 * @author MATIAS
 */
public class LoginService {
     public boolean autenticar(String user, String password) {
        return user.equals("MONSTER") && password.equals("MONSTER9");
    }
}
