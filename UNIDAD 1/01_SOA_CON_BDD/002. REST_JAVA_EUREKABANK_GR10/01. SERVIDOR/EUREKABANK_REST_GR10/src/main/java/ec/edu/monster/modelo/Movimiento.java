package ec.edu.monster.modelo;

   import jakarta.json.bind.annotation.JsonbProperty;
   import java.util.Date;

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

       @JsonbProperty("cuenta")
       public String getCuenta() {
           return cuenta;
       }

       public void setCuenta(String cuenta) {
           this.cuenta = cuenta;
       }

       @JsonbProperty("nroMov")
       public int getNroMov() {
           return nromov;
       }

       public void setNroMov(int nroMov) {
           this.nromov = nroMov;
       }

       @JsonbProperty("fecha")
       public Date getFecha() {
           return fecha;
       }

       public void setFecha(Date fecha) {
           this.fecha = fecha;
       }

       @JsonbProperty("tipo")
       public String getTipo() {
           return tipo;
       }

       public void setTipo(String tipo) {
           this.tipo = tipo;
       }

       @JsonbProperty("accion")
       public String getAccion() {
           return accion;
       }

       public void setAccion(String accion) {
           this.accion = accion;
       }

       @JsonbProperty("importe")
       public double getImporte() {
           return importe;
       }

       public void setImporte(double importe) {
           this.importe = importe;
       }
   }