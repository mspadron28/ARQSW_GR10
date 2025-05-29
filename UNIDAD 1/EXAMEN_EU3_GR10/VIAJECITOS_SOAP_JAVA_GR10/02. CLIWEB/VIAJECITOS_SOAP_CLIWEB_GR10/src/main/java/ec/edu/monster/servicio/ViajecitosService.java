package ec.edu.monster.servicio;

import ec.edu.monster.ws.WSAerolineasCondor;
import ec.edu.monster.ws.WSAerolineasCondor_Service;
import ec.edu.monster.ws.Cliente;
import ec.edu.monster.ws.Compra;
import ec.edu.monster.ws.Usuario;
import ec.edu.monster.ws.Vuelo;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

/**
 * Clase de servicio para interactuar con el servicio web AerolineasCondor.
 * @author MATIAS
 */
public class ViajecitosService {

    private final WSAerolineasCondor port;

    public ViajecitosService() {
        WSAerolineasCondor_Service service = new WSAerolineasCondor_Service();
        this.port = service.getWSAerolineasCondorPort();
    }

    public Vuelo obtenerVueloMasCaro(String ciudadOrigen, String ciudadDestino, XMLGregorianCalendar fecha) {
        return port.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
    }

    public List<Vuelo> buscarVuelos(String ciudadOrigen, String ciudadDestino, XMLGregorianCalendar fecha) {
        return port.buscarVuelos(ciudadOrigen, ciudadDestino, fecha);
    }

    public Usuario iniciarSesion(String nombreUsuario, String claveUsuario) {
        return port.iniciarSesion(nombreUsuario, claveUsuario);
    }

    public List<Compra> obtenerComprasCliente(int idCliente) {
        return port.obtenerComprasCliente(idCliente);
    }

    public Cliente registrarCliente(String nombre, String email, String documentoIdentidad) {
        return port.registrarCliente(nombre, email, documentoIdentidad);
    }

    public Usuario registrarUsuario(int idCliente, String nombreUsuario, String claveUsuario) {
        return port.registrarUsuario(idCliente, nombreUsuario, claveUsuario);
    }

    public int registrarCompra(int idVuelo, int idCliente) {
        return port.registrarCompra(idVuelo, idCliente);
    }
}