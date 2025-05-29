
package ec.edu.monster.ws;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para buscarVuelosResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="buscarVuelosResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Vuelo" type="{http://controlador.monster.edu.ec/}vuelo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscarVuelosResponse", propOrder = {
    "vuelo"
})
public class BuscarVuelosResponse {

    @XmlElement(name = "Vuelo")
    protected List<Vuelo> vuelo;

    /**
     * Gets the value of the vuelo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the vuelo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVuelo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Vuelo }
     * 
     * 
     */
    public List<Vuelo> getVuelo() {
        if (vuelo == null) {
            vuelo = new ArrayList<Vuelo>();
        }
        return this.vuelo;
    }

}
