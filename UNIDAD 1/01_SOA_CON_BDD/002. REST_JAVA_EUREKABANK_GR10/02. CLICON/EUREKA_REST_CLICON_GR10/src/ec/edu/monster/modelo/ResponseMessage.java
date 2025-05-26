package ec.edu.monster.modelo;

/**
 * Clase para manejar mensajes de respuesta genéricos.
 * @author MATIAS
 */
public class ResponseMessage {
    private String message;

    public ResponseMessage() {
    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}