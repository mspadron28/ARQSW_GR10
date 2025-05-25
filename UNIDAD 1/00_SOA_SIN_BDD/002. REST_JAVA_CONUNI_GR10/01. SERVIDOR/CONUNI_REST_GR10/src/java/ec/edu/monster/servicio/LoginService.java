package ec.edu.monster.servicio;

/**
 * Service class for user authentication.
 * @author MATIAS
 */
public class LoginService {
    public boolean autenticar(String user, String password) {
        return user.equals("MONSTER") && password.equals("MONSTER9");
    }
}