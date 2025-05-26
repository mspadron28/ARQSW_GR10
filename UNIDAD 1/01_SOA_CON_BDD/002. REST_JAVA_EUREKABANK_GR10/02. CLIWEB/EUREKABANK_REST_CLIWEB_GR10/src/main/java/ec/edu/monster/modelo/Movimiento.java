package ec.edu.monster.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * Modelo para la entidad Movimiento.
 * @author MATIAS
 */
public class Movimiento {
    @JsonProperty("cuenta")
    private String cuenta;
    
    @JsonProperty("nroMov")
    private int nroMov;
    
    @JsonProperty("fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'Z'", timezone = "UTC")
    private Date fecha;
    
    @JsonProperty("tipo")
    private String tipo;
    
    @JsonProperty("accion")
    private String accion;
    
    @JsonProperty("importe")
    private double importe;

    public Movimiento() {
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public int getNroMov() {
        return nroMov;
    }

    public void setNroMov(int nroMov) {
        this.nroMov = nroMov;
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