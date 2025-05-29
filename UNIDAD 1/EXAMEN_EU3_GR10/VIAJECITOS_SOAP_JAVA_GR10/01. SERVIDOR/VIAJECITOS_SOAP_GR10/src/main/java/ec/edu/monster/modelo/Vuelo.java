package ec.edu.monster.modelo;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Model for the Vuelo table.
 * @author MATIAS
 */
@XmlRootElement(name = "vuelo")
public class Vuelo {
    
    private int idVuelo;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double valor;
    private Date horaSalida;

    public Vuelo() {
    }

    public Vuelo(int idVuelo, String ciudadOrigen, String ciudadDestino, double valor, Date horaSalida) {
        this.idVuelo = idVuelo;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.valor = valor;
        this.horaSalida = horaSalida;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }
}