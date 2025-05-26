package ec.edu.monster.modelo;

/**
 * Clase para manejar solicitudes de login.
 * @author MATIAS
 */
public class LoginRequest {
    private String username;
    private String clave;

    public LoginRequest() {
    }

    public LoginRequest(String username, String clave) {
        this.username = username;
        this.clave = clave;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}