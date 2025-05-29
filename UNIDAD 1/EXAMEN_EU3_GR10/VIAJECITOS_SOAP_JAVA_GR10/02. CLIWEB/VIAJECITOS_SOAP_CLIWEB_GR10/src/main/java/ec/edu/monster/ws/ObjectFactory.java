
package ec.edu.monster.ws;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


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

    private final static QName _BuscarVuelos_QNAME = new QName("http://controlador.monster.edu.ec/", "buscarVuelos");
    private final static QName _BuscarVuelosResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "buscarVuelosResponse");
    private final static QName _Cliente_QNAME = new QName("http://controlador.monster.edu.ec/", "cliente");
    private final static QName _Compra_QNAME = new QName("http://controlador.monster.edu.ec/", "compra");
    private final static QName _IniciarSesion_QNAME = new QName("http://controlador.monster.edu.ec/", "iniciarSesion");
    private final static QName _IniciarSesionResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "iniciarSesionResponse");
    private final static QName _ObtenerComprasCliente_QNAME = new QName("http://controlador.monster.edu.ec/", "obtenerComprasCliente");
    private final static QName _ObtenerComprasClienteResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "obtenerComprasClienteResponse");
    private final static QName _ObtenerVueloMasCaro_QNAME = new QName("http://controlador.monster.edu.ec/", "obtenerVueloMasCaro");
    private final static QName _ObtenerVueloMasCaroResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "obtenerVueloMasCaroResponse");
    private final static QName _RegistrarCliente_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarCliente");
    private final static QName _RegistrarClienteResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarClienteResponse");
    private final static QName _RegistrarCompra_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarCompra");
    private final static QName _RegistrarCompraResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarCompraResponse");
    private final static QName _RegistrarUsuario_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarUsuario");
    private final static QName _RegistrarUsuarioResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarUsuarioResponse");
    private final static QName _Usuario_QNAME = new QName("http://controlador.monster.edu.ec/", "usuario");
    private final static QName _Vuelo_QNAME = new QName("http://controlador.monster.edu.ec/", "vuelo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.edu.monster.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BuscarVuelos }
     * 
     */
    public BuscarVuelos createBuscarVuelos() {
        return new BuscarVuelos();
    }

    /**
     * Create an instance of {@link BuscarVuelosResponse }
     * 
     */
    public BuscarVuelosResponse createBuscarVuelosResponse() {
        return new BuscarVuelosResponse();
    }

    /**
     * Create an instance of {@link Cliente }
     * 
     */
    public Cliente createCliente() {
        return new Cliente();
    }

    /**
     * Create an instance of {@link Compra }
     * 
     */
    public Compra createCompra() {
        return new Compra();
    }

    /**
     * Create an instance of {@link IniciarSesion }
     * 
     */
    public IniciarSesion createIniciarSesion() {
        return new IniciarSesion();
    }

    /**
     * Create an instance of {@link IniciarSesionResponse }
     * 
     */
    public IniciarSesionResponse createIniciarSesionResponse() {
        return new IniciarSesionResponse();
    }

    /**
     * Create an instance of {@link ObtenerComprasCliente }
     * 
     */
    public ObtenerComprasCliente createObtenerComprasCliente() {
        return new ObtenerComprasCliente();
    }

    /**
     * Create an instance of {@link ObtenerComprasClienteResponse }
     * 
     */
    public ObtenerComprasClienteResponse createObtenerComprasClienteResponse() {
        return new ObtenerComprasClienteResponse();
    }

    /**
     * Create an instance of {@link ObtenerVueloMasCaro }
     * 
     */
    public ObtenerVueloMasCaro createObtenerVueloMasCaro() {
        return new ObtenerVueloMasCaro();
    }

    /**
     * Create an instance of {@link ObtenerVueloMasCaroResponse }
     * 
     */
    public ObtenerVueloMasCaroResponse createObtenerVueloMasCaroResponse() {
        return new ObtenerVueloMasCaroResponse();
    }

    /**
     * Create an instance of {@link RegistrarCliente }
     * 
     */
    public RegistrarCliente createRegistrarCliente() {
        return new RegistrarCliente();
    }

    /**
     * Create an instance of {@link RegistrarClienteResponse }
     * 
     */
    public RegistrarClienteResponse createRegistrarClienteResponse() {
        return new RegistrarClienteResponse();
    }

    /**
     * Create an instance of {@link RegistrarCompra }
     * 
     */
    public RegistrarCompra createRegistrarCompra() {
        return new RegistrarCompra();
    }

    /**
     * Create an instance of {@link RegistrarCompraResponse }
     * 
     */
    public RegistrarCompraResponse createRegistrarCompraResponse() {
        return new RegistrarCompraResponse();
    }

    /**
     * Create an instance of {@link RegistrarUsuario }
     * 
     */
    public RegistrarUsuario createRegistrarUsuario() {
        return new RegistrarUsuario();
    }

    /**
     * Create an instance of {@link RegistrarUsuarioResponse }
     * 
     */
    public RegistrarUsuarioResponse createRegistrarUsuarioResponse() {
        return new RegistrarUsuarioResponse();
    }

    /**
     * Create an instance of {@link Usuario }
     * 
     */
    public Usuario createUsuario() {
        return new Usuario();
    }

    /**
     * Create an instance of {@link Vuelo }
     * 
     */
    public Vuelo createVuelo() {
        return new Vuelo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarVuelos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuscarVuelos }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "buscarVuelos")
    public JAXBElement<BuscarVuelos> createBuscarVuelos(BuscarVuelos value) {
        return new JAXBElement<BuscarVuelos>(_BuscarVuelos_QNAME, BuscarVuelos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarVuelosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuscarVuelosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "buscarVuelosResponse")
    public JAXBElement<BuscarVuelosResponse> createBuscarVuelosResponse(BuscarVuelosResponse value) {
        return new JAXBElement<BuscarVuelosResponse>(_BuscarVuelosResponse_QNAME, BuscarVuelosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cliente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Cliente }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "cliente")
    public JAXBElement<Cliente> createCliente(Cliente value) {
        return new JAXBElement<Cliente>(_Cliente_QNAME, Cliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Compra }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Compra }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "compra")
    public JAXBElement<Compra> createCompra(Compra value) {
        return new JAXBElement<Compra>(_Compra_QNAME, Compra.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarSesion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IniciarSesion }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "iniciarSesion")
    public JAXBElement<IniciarSesion> createIniciarSesion(IniciarSesion value) {
        return new JAXBElement<IniciarSesion>(_IniciarSesion_QNAME, IniciarSesion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarSesionResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IniciarSesionResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "iniciarSesionResponse")
    public JAXBElement<IniciarSesionResponse> createIniciarSesionResponse(IniciarSesionResponse value) {
        return new JAXBElement<IniciarSesionResponse>(_IniciarSesionResponse_QNAME, IniciarSesionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerComprasCliente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerComprasCliente }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "obtenerComprasCliente")
    public JAXBElement<ObtenerComprasCliente> createObtenerComprasCliente(ObtenerComprasCliente value) {
        return new JAXBElement<ObtenerComprasCliente>(_ObtenerComprasCliente_QNAME, ObtenerComprasCliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerComprasClienteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerComprasClienteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "obtenerComprasClienteResponse")
    public JAXBElement<ObtenerComprasClienteResponse> createObtenerComprasClienteResponse(ObtenerComprasClienteResponse value) {
        return new JAXBElement<ObtenerComprasClienteResponse>(_ObtenerComprasClienteResponse_QNAME, ObtenerComprasClienteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerVueloMasCaro }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerVueloMasCaro }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "obtenerVueloMasCaro")
    public JAXBElement<ObtenerVueloMasCaro> createObtenerVueloMasCaro(ObtenerVueloMasCaro value) {
        return new JAXBElement<ObtenerVueloMasCaro>(_ObtenerVueloMasCaro_QNAME, ObtenerVueloMasCaro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerVueloMasCaroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerVueloMasCaroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "obtenerVueloMasCaroResponse")
    public JAXBElement<ObtenerVueloMasCaroResponse> createObtenerVueloMasCaroResponse(ObtenerVueloMasCaroResponse value) {
        return new JAXBElement<ObtenerVueloMasCaroResponse>(_ObtenerVueloMasCaroResponse_QNAME, ObtenerVueloMasCaroResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarCliente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarCliente }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarCliente")
    public JAXBElement<RegistrarCliente> createRegistrarCliente(RegistrarCliente value) {
        return new JAXBElement<RegistrarCliente>(_RegistrarCliente_QNAME, RegistrarCliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarClienteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarClienteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarClienteResponse")
    public JAXBElement<RegistrarClienteResponse> createRegistrarClienteResponse(RegistrarClienteResponse value) {
        return new JAXBElement<RegistrarClienteResponse>(_RegistrarClienteResponse_QNAME, RegistrarClienteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarCompra }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarCompra }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarCompra")
    public JAXBElement<RegistrarCompra> createRegistrarCompra(RegistrarCompra value) {
        return new JAXBElement<RegistrarCompra>(_RegistrarCompra_QNAME, RegistrarCompra.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarCompraResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarCompraResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarCompraResponse")
    public JAXBElement<RegistrarCompraResponse> createRegistrarCompraResponse(RegistrarCompraResponse value) {
        return new JAXBElement<RegistrarCompraResponse>(_RegistrarCompraResponse_QNAME, RegistrarCompraResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarUsuario")
    public JAXBElement<RegistrarUsuario> createRegistrarUsuario(RegistrarUsuario value) {
        return new JAXBElement<RegistrarUsuario>(_RegistrarUsuario_QNAME, RegistrarUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarUsuarioResponse")
    public JAXBElement<RegistrarUsuarioResponse> createRegistrarUsuarioResponse(RegistrarUsuarioResponse value) {
        return new JAXBElement<RegistrarUsuarioResponse>(_RegistrarUsuarioResponse_QNAME, RegistrarUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Usuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Usuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "usuario")
    public JAXBElement<Usuario> createUsuario(Usuario value) {
        return new JAXBElement<Usuario>(_Usuario_QNAME, Usuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vuelo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Vuelo }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "vuelo")
    public JAXBElement<Vuelo> createVuelo(Vuelo value) {
        return new JAXBElement<Vuelo>(_Vuelo_QNAME, Vuelo.class, null, value);
    }

}
