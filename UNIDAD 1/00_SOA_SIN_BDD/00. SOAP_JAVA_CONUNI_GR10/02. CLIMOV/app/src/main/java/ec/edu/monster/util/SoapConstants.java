package ec.edu.monster.util;

public class SoapConstants {
    public static final String NAMESPACE = "http://controlador.monster.edu.ec/";

    // URL del servicio SOAP (usar 10.0.2.2 para emulador, ajustar según entorno)
    public static final String URL = "http://192.168.1.10:8080/EUREKABANK_GR10/WSEureka";

    public static final String SOAP_ACTION_PREFIX = "http://controlador.monster.edu.ec/";

    // Nombres de los métodos según el WSDL
    public static final String LOGIN_METHOD = "iniciarSesion";
    public static final String LEER_MOVIMIENTOS_METHOD = "leerMovimientos";
    public static final String REGISTRAR_DEPOSITO_METHOD = "registrarDeposito";
    public static final String REGISTRAR_RETIRO_METHOD = "registrarRetiro";
    public static final String REALIZAR_TRANSFERENCIA_METHOD = "realizarTransferencia";
    public static final String VERIFICAR_SALDO_METHOD = "verificarSaldo";
    public static final String OBTENER_COSTO_MOVIMIENTO_METHOD = "obtenerCostoMovimiento";
}