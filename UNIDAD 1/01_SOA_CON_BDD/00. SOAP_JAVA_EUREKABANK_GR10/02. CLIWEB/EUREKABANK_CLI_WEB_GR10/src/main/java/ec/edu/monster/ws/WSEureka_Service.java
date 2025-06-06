package ec.edu.monster.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;
import jakarta.xml.ws.Service;

/**
 * This class was generated by Apache CXF 4.0.3
 * 2025-05-25T20:24:56.932-05:00
 * Generated source version: 4.0.3
 *
 */
@WebServiceClient(name = "WSEureka",
                  wsdlLocation = "http://localhost:8080/EUREKABANK_GR10/WSEureka?wsdl",
                  targetNamespace = "http://controlador.monster.edu.ec/")
public class WSEureka_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://controlador.monster.edu.ec/", "WSEureka");
    public final static QName WSEurekaPort = new QName("http://controlador.monster.edu.ec/", "WSEurekaPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/EUREKABANK_GR10/WSEureka?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WSEureka_Service.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/EUREKABANK_GR10/WSEureka?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WSEureka_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSEureka_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSEureka_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    public WSEureka_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public WSEureka_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public WSEureka_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns WSEureka
     */
    @WebEndpoint(name = "WSEurekaPort")
    public WSEureka getWSEurekaPort() {
        return super.getPort(WSEurekaPort, WSEureka.class);
    }

    /**
     *
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSEureka
     */
    @WebEndpoint(name = "WSEurekaPort")
    public WSEureka getWSEurekaPort(WebServiceFeature... features) {
        return super.getPort(WSEurekaPort, WSEureka.class, features);
    }

}
