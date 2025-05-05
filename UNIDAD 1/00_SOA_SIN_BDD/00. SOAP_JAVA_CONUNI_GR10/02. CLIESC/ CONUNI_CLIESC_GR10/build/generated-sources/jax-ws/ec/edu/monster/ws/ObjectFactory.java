
package ec.edu.monster.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.edu.monster.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CentimetrosAPulgadas_QNAME = new QName("http://controlador.monster.edu.ec/", "centimetrosAPulgadas");
    private final static QName _CentimetrosAPulgadasResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "centimetrosAPulgadasResponse");
    private final static QName _Login_QNAME = new QName("http://controlador.monster.edu.ec/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "loginResponse");
    private final static QName _MetrosAPies_QNAME = new QName("http://controlador.monster.edu.ec/", "metrosAPies");
    private final static QName _MetrosAPiesResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "metrosAPiesResponse");
    private final static QName _MetrosAYardas_QNAME = new QName("http://controlador.monster.edu.ec/", "metrosAYardas");
    private final static QName _MetrosAYardasResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "metrosAYardasResponse");
    private final static QName _PiesAMetros_QNAME = new QName("http://controlador.monster.edu.ec/", "piesAMetros");
    private final static QName _PiesAMetrosResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "piesAMetrosResponse");
    private final static QName _PulgadasACentimetros_QNAME = new QName("http://controlador.monster.edu.ec/", "pulgadasACentimetros");
    private final static QName _PulgadasACentimetrosResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "pulgadasACentimetrosResponse");
    private final static QName _YardasAMetros_QNAME = new QName("http://controlador.monster.edu.ec/", "yardasAMetros");
    private final static QName _YardasAMetrosResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "yardasAMetrosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.edu.monster.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CentimetrosAPulgadas }
     * 
     */
    public CentimetrosAPulgadas createCentimetrosAPulgadas() {
        return new CentimetrosAPulgadas();
    }

    /**
     * Create an instance of {@link CentimetrosAPulgadasResponse }
     * 
     */
    public CentimetrosAPulgadasResponse createCentimetrosAPulgadasResponse() {
        return new CentimetrosAPulgadasResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link MetrosAPies }
     * 
     */
    public MetrosAPies createMetrosAPies() {
        return new MetrosAPies();
    }

    /**
     * Create an instance of {@link MetrosAPiesResponse }
     * 
     */
    public MetrosAPiesResponse createMetrosAPiesResponse() {
        return new MetrosAPiesResponse();
    }

    /**
     * Create an instance of {@link MetrosAYardas }
     * 
     */
    public MetrosAYardas createMetrosAYardas() {
        return new MetrosAYardas();
    }

    /**
     * Create an instance of {@link MetrosAYardasResponse }
     * 
     */
    public MetrosAYardasResponse createMetrosAYardasResponse() {
        return new MetrosAYardasResponse();
    }

    /**
     * Create an instance of {@link PiesAMetros }
     * 
     */
    public PiesAMetros createPiesAMetros() {
        return new PiesAMetros();
    }

    /**
     * Create an instance of {@link PiesAMetrosResponse }
     * 
     */
    public PiesAMetrosResponse createPiesAMetrosResponse() {
        return new PiesAMetrosResponse();
    }

    /**
     * Create an instance of {@link PulgadasACentimetros }
     * 
     */
    public PulgadasACentimetros createPulgadasACentimetros() {
        return new PulgadasACentimetros();
    }

    /**
     * Create an instance of {@link PulgadasACentimetrosResponse }
     * 
     */
    public PulgadasACentimetrosResponse createPulgadasACentimetrosResponse() {
        return new PulgadasACentimetrosResponse();
    }

    /**
     * Create an instance of {@link YardasAMetros }
     * 
     */
    public YardasAMetros createYardasAMetros() {
        return new YardasAMetros();
    }

    /**
     * Create an instance of {@link YardasAMetrosResponse }
     * 
     */
    public YardasAMetrosResponse createYardasAMetrosResponse() {
        return new YardasAMetrosResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CentimetrosAPulgadas }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CentimetrosAPulgadas }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "centimetrosAPulgadas")
    public JAXBElement<CentimetrosAPulgadas> createCentimetrosAPulgadas(CentimetrosAPulgadas value) {
        return new JAXBElement<CentimetrosAPulgadas>(_CentimetrosAPulgadas_QNAME, CentimetrosAPulgadas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CentimetrosAPulgadasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CentimetrosAPulgadasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "centimetrosAPulgadasResponse")
    public JAXBElement<CentimetrosAPulgadasResponse> createCentimetrosAPulgadasResponse(CentimetrosAPulgadasResponse value) {
        return new JAXBElement<CentimetrosAPulgadasResponse>(_CentimetrosAPulgadasResponse_QNAME, CentimetrosAPulgadasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Login }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetrosAPies }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MetrosAPies }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "metrosAPies")
    public JAXBElement<MetrosAPies> createMetrosAPies(MetrosAPies value) {
        return new JAXBElement<MetrosAPies>(_MetrosAPies_QNAME, MetrosAPies.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetrosAPiesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MetrosAPiesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "metrosAPiesResponse")
    public JAXBElement<MetrosAPiesResponse> createMetrosAPiesResponse(MetrosAPiesResponse value) {
        return new JAXBElement<MetrosAPiesResponse>(_MetrosAPiesResponse_QNAME, MetrosAPiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetrosAYardas }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MetrosAYardas }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "metrosAYardas")
    public JAXBElement<MetrosAYardas> createMetrosAYardas(MetrosAYardas value) {
        return new JAXBElement<MetrosAYardas>(_MetrosAYardas_QNAME, MetrosAYardas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetrosAYardasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MetrosAYardasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "metrosAYardasResponse")
    public JAXBElement<MetrosAYardasResponse> createMetrosAYardasResponse(MetrosAYardasResponse value) {
        return new JAXBElement<MetrosAYardasResponse>(_MetrosAYardasResponse_QNAME, MetrosAYardasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PiesAMetros }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PiesAMetros }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "piesAMetros")
    public JAXBElement<PiesAMetros> createPiesAMetros(PiesAMetros value) {
        return new JAXBElement<PiesAMetros>(_PiesAMetros_QNAME, PiesAMetros.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PiesAMetrosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PiesAMetrosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "piesAMetrosResponse")
    public JAXBElement<PiesAMetrosResponse> createPiesAMetrosResponse(PiesAMetrosResponse value) {
        return new JAXBElement<PiesAMetrosResponse>(_PiesAMetrosResponse_QNAME, PiesAMetrosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PulgadasACentimetros }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PulgadasACentimetros }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "pulgadasACentimetros")
    public JAXBElement<PulgadasACentimetros> createPulgadasACentimetros(PulgadasACentimetros value) {
        return new JAXBElement<PulgadasACentimetros>(_PulgadasACentimetros_QNAME, PulgadasACentimetros.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PulgadasACentimetrosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PulgadasACentimetrosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "pulgadasACentimetrosResponse")
    public JAXBElement<PulgadasACentimetrosResponse> createPulgadasACentimetrosResponse(PulgadasACentimetrosResponse value) {
        return new JAXBElement<PulgadasACentimetrosResponse>(_PulgadasACentimetrosResponse_QNAME, PulgadasACentimetrosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YardasAMetros }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link YardasAMetros }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "yardasAMetros")
    public JAXBElement<YardasAMetros> createYardasAMetros(YardasAMetros value) {
        return new JAXBElement<YardasAMetros>(_YardasAMetros_QNAME, YardasAMetros.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YardasAMetrosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link YardasAMetrosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "yardasAMetrosResponse")
    public JAXBElement<YardasAMetrosResponse> createYardasAMetrosResponse(YardasAMetrosResponse value) {
        return new JAXBElement<YardasAMetrosResponse>(_YardasAMetrosResponse_QNAME, YardasAMetrosResponse.class, null, value);
    }

}
