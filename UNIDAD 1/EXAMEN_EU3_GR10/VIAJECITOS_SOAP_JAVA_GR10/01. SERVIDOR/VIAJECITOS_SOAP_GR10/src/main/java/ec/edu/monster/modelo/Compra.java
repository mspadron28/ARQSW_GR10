package ec.edu.monster.modelo;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Model for the Compra table.
 *
 * @author MATIAS
 */
@XmlRootElement(name = "compra")
public class Compra {

    private int idCompra;
    private int idVuelo;
    private int idCliente;
    private Date fechaCompra;
    private Vuelo vuelo;

    public Compra() {
    }

    public Compra(int idCompra, int idVuelo, int idCliente, Date fechaCompra) {
        this.idCompra = idCompra;
        this.idVuelo = idVuelo;
        this.idCliente = idCliente;
        this.fechaCompra = fechaCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

}
