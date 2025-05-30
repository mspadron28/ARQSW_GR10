package ec.edu.monster.util;

public class SoapConstants {
    public static final String NAMESPACE = "http://controlador.monster.edu.ec/";
    public static final String URL = "http://192.168.1.10:8080/VIAJECITOS_SOAP_GR10/WSAerolineasCondor?wsdl";
    public static final String SOAP_ACTION_PREFIX = "http://controlador.monster.edu.ec/";

    // Method names from WSDL
    public static final String LOGIN_METHOD = "iniciarSesion";
    public static final String BUSCAR_VUELOS_METHOD = "buscarVuelos";
    public static final String OBTENER_VUELO_MAS_CARO_METHOD = "obtenerVueloMasCaro";
    public static final String REGISTRAR_CLIENTE_METHOD = "registrarCliente";
    public static final String REGISTRAR_USUARIO_METHOD = "registrarUsuario";
    public static final String OBTENER_COMPRAS_CLIENTE_METHOD = "obtenerComprasCliente";
    public static final String REGISTRAR_COMPRA_METHOD = "registrarCompra";
}