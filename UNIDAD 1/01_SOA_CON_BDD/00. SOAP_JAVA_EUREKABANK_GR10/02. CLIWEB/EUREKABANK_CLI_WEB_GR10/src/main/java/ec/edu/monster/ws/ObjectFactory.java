
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

    private final static QName _IniciarSesion_QNAME = new QName("http://controlador.monster.edu.ec/", "iniciarSesion");
    private final static QName _IniciarSesionResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "iniciarSesionResponse");
    private final static QName _LeerMovimientos_QNAME = new QName("http://controlador.monster.edu.ec/", "leerMovimientos");
    private final static QName _LeerMovimientosResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "leerMovimientosResponse");
    private final static QName _Movimiento_QNAME = new QName("http://controlador.monster.edu.ec/", "movimiento");
    private final static QName _ObtenerCostoMovimiento_QNAME = new QName("http://controlador.monster.edu.ec/", "obtenerCostoMovimiento");
    private final static QName _ObtenerCostoMovimientoResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "obtenerCostoMovimientoResponse");
    private final static QName _RealizarTransferencia_QNAME = new QName("http://controlador.monster.edu.ec/", "realizarTransferencia");
    private final static QName _RealizarTransferenciaResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "realizarTransferenciaResponse");
    private final static QName _RegistrarDeposito_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarDeposito");
    private final static QName _RegistrarDepositoResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarDepositoResponse");
    private final static QName _RegistrarRetiro_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarRetiro");
    private final static QName _RegistrarRetiroResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "registrarRetiroResponse");
    private final static QName _Usuario_QNAME = new QName("http://controlador.monster.edu.ec/", "usuario");
    private final static QName _VerificarSaldo_QNAME = new QName("http://controlador.monster.edu.ec/", "verificarSaldo");
    private final static QName _VerificarSaldoResponse_QNAME = new QName("http://controlador.monster.edu.ec/", "verificarSaldoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.edu.monster.ws
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link LeerMovimientos }
     * 
     */
    public LeerMovimientos createLeerMovimientos() {
        return new LeerMovimientos();
    }

    /**
     * Create an instance of {@link LeerMovimientosResponse }
     * 
     */
    public LeerMovimientosResponse createLeerMovimientosResponse() {
        return new LeerMovimientosResponse();
    }

    /**
     * Create an instance of {@link Movimiento }
     * 
     */
    public Movimiento createMovimiento() {
        return new Movimiento();
    }

    /**
     * Create an instance of {@link ObtenerCostoMovimiento }
     * 
     */
    public ObtenerCostoMovimiento createObtenerCostoMovimiento() {
        return new ObtenerCostoMovimiento();
    }

    /**
     * Create an instance of {@link ObtenerCostoMovimientoResponse }
     * 
     */
    public ObtenerCostoMovimientoResponse createObtenerCostoMovimientoResponse() {
        return new ObtenerCostoMovimientoResponse();
    }

    /**
     * Create an instance of {@link RealizarTransferencia }
     * 
     */
    public RealizarTransferencia createRealizarTransferencia() {
        return new RealizarTransferencia();
    }

    /**
     * Create an instance of {@link RealizarTransferenciaResponse }
     * 
     */
    public RealizarTransferenciaResponse createRealizarTransferenciaResponse() {
        return new RealizarTransferenciaResponse();
    }

    /**
     * Create an instance of {@link RegistrarDeposito }
     * 
     */
    public RegistrarDeposito createRegistrarDeposito() {
        return new RegistrarDeposito();
    }

    /**
     * Create an instance of {@link RegistrarDepositoResponse }
     * 
     */
    public RegistrarDepositoResponse createRegistrarDepositoResponse() {
        return new RegistrarDepositoResponse();
    }

    /**
     * Create an instance of {@link RegistrarRetiro }
     * 
     */
    public RegistrarRetiro createRegistrarRetiro() {
        return new RegistrarRetiro();
    }

    /**
     * Create an instance of {@link RegistrarRetiroResponse }
     * 
     */
    public RegistrarRetiroResponse createRegistrarRetiroResponse() {
        return new RegistrarRetiroResponse();
    }

    /**
     * Create an instance of {@link Usuario }
     * 
     */
    public Usuario createUsuario() {
        return new Usuario();
    }

    /**
     * Create an instance of {@link VerificarSaldo }
     * 
     */
    public VerificarSaldo createVerificarSaldo() {
        return new VerificarSaldo();
    }

    /**
     * Create an instance of {@link VerificarSaldoResponse }
     * 
     */
    public VerificarSaldoResponse createVerificarSaldoResponse() {
        return new VerificarSaldoResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link LeerMovimientos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LeerMovimientos }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "leerMovimientos")
    public JAXBElement<LeerMovimientos> createLeerMovimientos(LeerMovimientos value) {
        return new JAXBElement<LeerMovimientos>(_LeerMovimientos_QNAME, LeerMovimientos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeerMovimientosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LeerMovimientosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "leerMovimientosResponse")
    public JAXBElement<LeerMovimientosResponse> createLeerMovimientosResponse(LeerMovimientosResponse value) {
        return new JAXBElement<LeerMovimientosResponse>(_LeerMovimientosResponse_QNAME, LeerMovimientosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Movimiento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Movimiento }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "movimiento")
    public JAXBElement<Movimiento> createMovimiento(Movimiento value) {
        return new JAXBElement<Movimiento>(_Movimiento_QNAME, Movimiento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerCostoMovimiento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerCostoMovimiento }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "obtenerCostoMovimiento")
    public JAXBElement<ObtenerCostoMovimiento> createObtenerCostoMovimiento(ObtenerCostoMovimiento value) {
        return new JAXBElement<ObtenerCostoMovimiento>(_ObtenerCostoMovimiento_QNAME, ObtenerCostoMovimiento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerCostoMovimientoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerCostoMovimientoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "obtenerCostoMovimientoResponse")
    public JAXBElement<ObtenerCostoMovimientoResponse> createObtenerCostoMovimientoResponse(ObtenerCostoMovimientoResponse value) {
        return new JAXBElement<ObtenerCostoMovimientoResponse>(_ObtenerCostoMovimientoResponse_QNAME, ObtenerCostoMovimientoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealizarTransferencia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealizarTransferencia }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "realizarTransferencia")
    public JAXBElement<RealizarTransferencia> createRealizarTransferencia(RealizarTransferencia value) {
        return new JAXBElement<RealizarTransferencia>(_RealizarTransferencia_QNAME, RealizarTransferencia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealizarTransferenciaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealizarTransferenciaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "realizarTransferenciaResponse")
    public JAXBElement<RealizarTransferenciaResponse> createRealizarTransferenciaResponse(RealizarTransferenciaResponse value) {
        return new JAXBElement<RealizarTransferenciaResponse>(_RealizarTransferenciaResponse_QNAME, RealizarTransferenciaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarDeposito }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarDeposito }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarDeposito")
    public JAXBElement<RegistrarDeposito> createRegistrarDeposito(RegistrarDeposito value) {
        return new JAXBElement<RegistrarDeposito>(_RegistrarDeposito_QNAME, RegistrarDeposito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarDepositoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarDepositoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarDepositoResponse")
    public JAXBElement<RegistrarDepositoResponse> createRegistrarDepositoResponse(RegistrarDepositoResponse value) {
        return new JAXBElement<RegistrarDepositoResponse>(_RegistrarDepositoResponse_QNAME, RegistrarDepositoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarRetiro }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarRetiro }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarRetiro")
    public JAXBElement<RegistrarRetiro> createRegistrarRetiro(RegistrarRetiro value) {
        return new JAXBElement<RegistrarRetiro>(_RegistrarRetiro_QNAME, RegistrarRetiro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarRetiroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistrarRetiroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "registrarRetiroResponse")
    public JAXBElement<RegistrarRetiroResponse> createRegistrarRetiroResponse(RegistrarRetiroResponse value) {
        return new JAXBElement<RegistrarRetiroResponse>(_RegistrarRetiroResponse_QNAME, RegistrarRetiroResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificarSaldo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerificarSaldo }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "verificarSaldo")
    public JAXBElement<VerificarSaldo> createVerificarSaldo(VerificarSaldo value) {
        return new JAXBElement<VerificarSaldo>(_VerificarSaldo_QNAME, VerificarSaldo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificarSaldoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerificarSaldoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controlador.monster.edu.ec/", name = "verificarSaldoResponse")
    public JAXBElement<VerificarSaldoResponse> createVerificarSaldoResponse(VerificarSaldoResponse value) {
        return new JAXBElement<VerificarSaldoResponse>(_VerificarSaldoResponse_QNAME, VerificarSaldoResponse.class, null, value);
    }

}
