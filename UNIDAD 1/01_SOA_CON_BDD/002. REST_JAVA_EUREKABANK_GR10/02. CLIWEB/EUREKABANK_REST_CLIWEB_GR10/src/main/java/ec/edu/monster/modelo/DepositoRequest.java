package ec.edu.monster.modelo;

/**
 * Clase para manejar solicitudes de depósito.
 * @author MATIAS
 */
public class DepositoRequest {
    private String cuenta;
    private double importe;
    private String codEmp;

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(String codEmp) {
        this.codEmp = codEmp;
    }
}