package ec.edu.monster.modelo;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Model for the Cliente table.
 * @author MATIAS
 */
@XmlRootElement(name = "cliente")
public class Cliente {
    
    private int idCliente;
    private String nombre;
    private String email;
    private String documentoIdentidad;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String email, String documentoIdentidad) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.documentoIdentidad = documentoIdentidad;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }
}