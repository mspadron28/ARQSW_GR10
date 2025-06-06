
package ec.edu.monster.ws;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 4.0.3
 * 2025-05-25T20:24:56.689-05:00
 * Generated source version: 4.0.3
 *
 */
public final class WSEureka_WSEurekaPort_Client {

    private static final QName SERVICE_NAME = new QName("http://controlador.monster.edu.ec/", "WSEureka");

    private WSEureka_WSEurekaPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = WSEureka_Service.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        WSEureka_Service ss = new WSEureka_Service(wsdlURL, SERVICE_NAME);
        WSEureka port = ss.getWSEurekaPort();

        {
        System.out.println("Invoking iniciarSesion...");
        java.lang.String _iniciarSesion_username = "";
        java.lang.String _iniciarSesion_clave = "";
        ec.edu.monster.ws.Usuario _iniciarSesion__return = port.iniciarSesion(_iniciarSesion_username, _iniciarSesion_clave);
        System.out.println("iniciarSesion.result=" + _iniciarSesion__return);


        }
        {
        System.out.println("Invoking realizarTransferencia...");
        java.lang.String _realizarTransferencia_cuentaOrigen = "";
        java.lang.String _realizarTransferencia_cuentaDestino = "";
        double _realizarTransferencia_importe = 0.0;
        java.lang.String _realizarTransferencia_codEmp = "";
        int _realizarTransferencia__return = port.realizarTransferencia(_realizarTransferencia_cuentaOrigen, _realizarTransferencia_cuentaDestino, _realizarTransferencia_importe, _realizarTransferencia_codEmp);
        System.out.println("realizarTransferencia.result=" + _realizarTransferencia__return);


        }
        {
        System.out.println("Invoking registrarDeposito...");
        java.lang.String _registrarDeposito_cuenta = "";
        double _registrarDeposito_importe = 0.0;
        java.lang.String _registrarDeposito_codEmp = "";
        int _registrarDeposito__return = port.registrarDeposito(_registrarDeposito_cuenta, _registrarDeposito_importe, _registrarDeposito_codEmp);
        System.out.println("registrarDeposito.result=" + _registrarDeposito__return);


        }
        {
        System.out.println("Invoking obtenerCostoMovimiento...");
        java.lang.String _obtenerCostoMovimiento_cuenta = "";
        double _obtenerCostoMovimiento__return = port.obtenerCostoMovimiento(_obtenerCostoMovimiento_cuenta);
        System.out.println("obtenerCostoMovimiento.result=" + _obtenerCostoMovimiento__return);


        }
        {
        System.out.println("Invoking verificarSaldo...");
        java.lang.String _verificarSaldo_cuenta = "";
        double _verificarSaldo__return = port.verificarSaldo(_verificarSaldo_cuenta);
        System.out.println("verificarSaldo.result=" + _verificarSaldo__return);


        }
        {
        System.out.println("Invoking registrarRetiro...");
        java.lang.String _registrarRetiro_cuenta = "";
        double _registrarRetiro_importe = 0.0;
        java.lang.String _registrarRetiro_codEmp = "";
        int _registrarRetiro__return = port.registrarRetiro(_registrarRetiro_cuenta, _registrarRetiro_importe, _registrarRetiro_codEmp);
        System.out.println("registrarRetiro.result=" + _registrarRetiro__return);


        }
        {
        System.out.println("Invoking leerMovimientos...");
        java.lang.String _leerMovimientos_cuenta = "";
        java.util.List<ec.edu.monster.ws.Movimiento> _leerMovimientos__return = port.leerMovimientos(_leerMovimientos_cuenta);
        System.out.println("leerMovimientos.result=" + _leerMovimientos__return);


        }

        System.exit(0);
    }

}
