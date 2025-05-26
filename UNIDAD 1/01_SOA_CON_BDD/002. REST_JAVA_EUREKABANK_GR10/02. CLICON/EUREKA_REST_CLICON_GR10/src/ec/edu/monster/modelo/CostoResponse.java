package ec.edu.monster.modelo;

/**
 * Clase para manejar respuestas de costo.
 * @author MATIAS
 */
public class CostoResponse {
    private double costo;

    public CostoResponse() {
    }

    public CostoResponse(double costo) {
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}