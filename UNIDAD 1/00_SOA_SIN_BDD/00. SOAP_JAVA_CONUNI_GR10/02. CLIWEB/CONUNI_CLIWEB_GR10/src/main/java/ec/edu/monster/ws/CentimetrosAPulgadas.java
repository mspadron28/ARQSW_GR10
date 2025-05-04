
package ec.edu.monster.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para centimetrosAPulgadas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="centimetrosAPulgadas"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="centimetros" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "centimetrosAPulgadas", propOrder = {
    "centimetros"
})
public class CentimetrosAPulgadas {

    protected double centimetros;

    /**
     * Obtiene el valor de la propiedad centimetros.
     * 
     */
    public double getCentimetros() {
        return centimetros;
    }

    /**
     * Define el valor de la propiedad centimetros.
     * 
     */
    public void setCentimetros(double value) {
        this.centimetros = value;
    }

}
