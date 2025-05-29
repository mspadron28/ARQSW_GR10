
package ec.edu.monster.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para registrarCompra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registrarCompra"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idVuelo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="idCliente" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarCompra", propOrder = {
    "idVuelo",
    "idCliente"
})
public class RegistrarCompra {

    protected int idVuelo;
    protected int idCliente;

    /**
     * Obtiene el valor de la propiedad idVuelo.
     * 
     */
    public int getIdVuelo() {
        return idVuelo;
    }

    /**
     * Define el valor de la propiedad idVuelo.
     * 
     */
    public void setIdVuelo(int value) {
        this.idVuelo = value;
    }

    /**
     * Obtiene el valor de la propiedad idCliente.
     * 
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define el valor de la propiedad idCliente.
     * 
     */
    public void setIdCliente(int value) {
        this.idCliente = value;
    }

}
