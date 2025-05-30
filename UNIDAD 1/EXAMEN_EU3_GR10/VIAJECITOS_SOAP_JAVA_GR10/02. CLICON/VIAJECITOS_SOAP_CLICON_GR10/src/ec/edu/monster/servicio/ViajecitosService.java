package ec.edu.monster.servicio;

import ec.edu.monster.ws.Cliente;
import ec.edu.monster.ws.Compra;
import ec.edu.monster.ws.Usuario;
import ec.edu.monster.ws.Vuelo;
import ec.edu.monster.ws.WSAerolineasCondor;
import ec.edu.monster.ws.WSAerolineasCondor_Service;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Service class to interact with the WSAerolineasCondor web service for Viajecitos SA.
 *
 * @author MATIAS
 */
public class ViajecitosService {

    private final WSAerolineasCondor port;

    public ViajecitosService() {
        WSAerolineasCondor_Service service = new WSAerolineasCondor_Service();
        this.port = service.getWSAerolineasCondorPort();
    }

    /**
     * Obtiene el vuelo más caro para un origen, destino y fecha específicos.
     *
     * @param ciudadOrigen Ciudad de origen.
     * @param ciudadDestino Ciudad de destino.
     * @param fecha Fecha del viaje.
     * @return Vuelo más caro.
     */
    public Vuelo obtenerVueloMasCaro(String ciudadOrigen, String ciudadDestino, XMLGregorianCalendar fecha) {
        return port.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
    }

    /**
     * Inicia sesión para un usuario.
     *
     * @param nombreUsuario Nombre de usuario.
     * @param claveUsuario Contraseña del usuario.
     * @return Objeto Usuario si la autenticación es exitosa, null si falla.
     */
    public Usuario iniciarSesion(String nombreUsuario, String claveUsuario) {
        return port.iniciarSesion(nombreUsuario, claveUsuario);
    }

    /**
     * Obtiene las compras de un cliente.
     *
     * @param idCliente ID del cliente.
     * @return Lista de compras.
     */
    public List<Compra> obtenerComprasCliente(int idCliente) {
        return port.obtenerComprasCliente(idCliente);
    }

    /**
     * Busca vuelos disponibles.
     *
     * @param ciudadOrigen Ciudad de origen.
     * @param ciudadDestino Ciudad de destino.
     * @param fecha Fecha del viaje.
     * @return Lista de vuelos disponibles.
     */
    public List<Vuelo> buscarVuelos(String ciudadOrigen, String ciudadDestino, XMLGregorianCalendar fecha) {
        return port.buscarVuelos(ciudadOrigen, ciudadDestino, fecha);
    }

    /**
     * Registra un nuevo usuario.
     *
     * @param idCliente ID del cliente asociado.
     * @param nombreUsuario Nombre de usuario.
     * @param claveUsuario Contraseña del usuario.
     * @return Objeto Usuario registrado.
     */
    public Usuario registrarUsuario(int idCliente, String nombreUsuario, String claveUsuario) {
        return port.registrarUsuario(idCliente, nombreUsuario, claveUsuario);
    }

    /**
     * Registra una compra de boleto.
     *
     * @param idVuelo ID del vuelo.
     * @param idCliente ID del cliente.
     * @return ID de la compra registrada.
     */
    public int registrarCompra(int idVuelo, int idCliente) {
        return port.registrarCompra(idVuelo, idCliente);
    }

    /**
     * Registra un nuevo cliente.
     *
     * @param nombre Nombre del cliente.
     * @param email Email del cliente.
     * @param documentoIdentidad Documento de identidad.
     * @return Objeto Cliente registrado.
     */
    public Cliente registrarCliente(String nombre, String email, String documentoIdentidad) {
        return port.registrarCliente(nombre, email, documentoIdentidad);
    }
}