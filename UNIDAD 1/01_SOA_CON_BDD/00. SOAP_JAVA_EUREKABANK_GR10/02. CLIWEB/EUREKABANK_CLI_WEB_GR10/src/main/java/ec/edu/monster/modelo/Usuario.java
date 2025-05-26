package ec.edu.monster.modelo;

/**
 * Modelo para la entidad Usuario en el cliente.
 * @author MATIAS
 */
public class Usuario {
    
    private String codigo; // chr_emplcodigo
    private String usuario; // vch_emplusuario
    private String clave; // vch_emplclave
    private String estado; // vch_emplestado

    public Usuario() {
    }

    public Usuario(String codigo, String usuario, String clave, String estado) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.clave = clave;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}