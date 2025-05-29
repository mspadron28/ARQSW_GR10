
package ec.edu.monster.ws;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para vuelo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="vuelo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ciudadDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ciudadOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="horaSalida" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="idVuelo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vuelo", propOrder = {
    "ciudadDestino",
    "ciudadOrigen",
    "horaSalida",
    "idVuelo",
    "valor"
})
public class Vuelo {

    protected String ciudadDestino;
    protected String ciudadOrigen;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar horaSalida;
    protected int idVuelo;
    protected double valor;

    /**
     * Obtiene el valor de la propiedad ciudadDestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * Define el valor de la propiedad ciudadDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudadDestino(String value) {
        this.ciudadDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudadOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * Define el valor de la propiedad ciudadOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudadOrigen(String value) {
        this.ciudadOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad horaSalida.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHoraSalida() {
        return horaSalida;
    }

    /**
     * Define el valor de la propiedad horaSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHoraSalida(XMLGregorianCalendar value) {
        this.horaSalida = value;
    }

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
     * Obtiene el valor de la propiedad valor.
     * 
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define el valor de la propiedad valor.
     * 
     */
    public void setValor(double value) {
        this.valor = value;
    }

}
