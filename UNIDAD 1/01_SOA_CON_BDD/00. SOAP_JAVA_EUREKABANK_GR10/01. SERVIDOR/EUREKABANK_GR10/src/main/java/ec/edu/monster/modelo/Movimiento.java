package ec.edu.monster.modelo;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;


/**
 *
 * @author MATIAS
 */
@XmlRootElement(name = "movimiento")
public class Movimiento {
    
    private String cuenta;
    private int nromov;
    private Date fecha;
    private String tipo;
    private String accion;
    private double importe;

    public Movimiento() {
    }

    public Movimiento(String cuenta, int nromov, Date fecha, String tipo, String accion, double importe) {
        this.cuenta = cuenta;
        this.nromov = nromov;
        this.fecha = fecha;
        this.tipo = tipo;
        this.accion = accion;
        this.importe = importe;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public int getNroMov() {
        return nromov;
    }

    public void setNroMov(int nroMov) {
        this.nromov = nroMov;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    
    
}
