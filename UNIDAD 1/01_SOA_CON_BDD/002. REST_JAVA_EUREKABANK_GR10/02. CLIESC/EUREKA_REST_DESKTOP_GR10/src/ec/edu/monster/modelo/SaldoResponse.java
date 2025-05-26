package ec.edu.monster.modelo;

/**
 * Clase para manejar respuestas de saldo.
 * @author MATIAS
 */
public class SaldoResponse {
    private double saldo;

    public SaldoResponse() {
    }

    public SaldoResponse(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}