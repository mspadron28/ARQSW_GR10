package ec.edu.monster.modelo;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Model for the Usuario table.
 * @author MATIAS
 */
@XmlRootElement(name = "usuario")
public class Usuario {
    
    private int idUsuario;
    private int idCliente;
    private String nombreUsuario;
    private String claveUsuario;
    private String estadoUsuario;

    public Usuario() {
    }

    public Usuario(int idUsuario, int idCliente, String nombreUsuario, String claveUsuario, String estadoUsuario) {
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.nombreUsuario = nombreUsuario;
        this.claveUsuario = claveUsuario;
        this.estadoUsuario = estadoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }
}