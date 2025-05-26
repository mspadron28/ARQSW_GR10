package ec.edu.monster.modelo;

   import jakarta.json.bind.annotation.JsonbProperty;

   public class Usuario {

       private String codigo;
       private String usuario;
       private String clave;
       private String estado;

       public Usuario() {
       }

       public Usuario(String codigo, String usuario, String clave, String estado) {
           this.codigo = codigo;
           this.usuario = usuario;
           this.clave = clave;
           this.estado = estado;
       }

       @JsonbProperty("codigo")
       public String getCodigo() {
           return codigo;
       }

       public void setCodigo(String codigo) {
           this.codigo = codigo;
       }

       @JsonbProperty("usuario")
       public String getUsuario() {
           return usuario;
       }

       public void setUsuario(String usuario) {
           this.usuario = usuario;
       }

       @JsonbProperty("clave")
       public String getClave() {
           return clave;
       }

       public void setClave(String clave) {
           this.clave = clave;
       }

       @JsonbProperty("estado")
       public String getEstado() {
           return estado;
       }

       public void setEstado(String estado) {
           this.estado = estado;
       }
   }