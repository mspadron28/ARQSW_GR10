package ec.edu.monster.controlador;

import ec.edu.monster.modelo.Cliente;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.modelo.Vuelo;
import ec.edu.monster.servicio.ViajecitosService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SOAP Web Service for Aerolíneas Cóndor operations.
 * @author MATIAS
 */
@WebService(serviceName = "WSAerolineasCondor")
public class WSAerolineasCondor {

    private final ViajecitosService viajecitosService;

    public WSAerolineasCondor() {
        this.viajecitosService = new ViajecitosService();
    }

    /**
     * Retrieves a list of flights based on origin, destination, and date.
     * @param ciudadOrigen Origin city
     * @param ciudadDestino Destination city
     * @param fecha Travel date
     * @return List of available flights
     */
    @WebMethod(operationName = "buscarVuelos")
    @WebResult(name = "Vuelo")
    public List<Vuelo> buscarVuelos(
            @WebParam(name = "ciudadOrigen") String ciudadOrigen,
            @WebParam(name = "ciudadDestino") String ciudadDestino,
            @WebParam(name = "fecha") Date fecha) {
        List<Vuelo> lista;
        try {
            lista = viajecitosService.buscarVuelos(ciudadOrigen, ciudadDestino, fecha);
        } catch (Exception e) {
            lista = new ArrayList<>();
        }
        return lista;
    }

    /**
     * Retrieves the most expensive flight based on origin, destination, and date.
     * @param ciudadOrigen Origin city
     * @param ciudadDestino Destination city
     * @param fecha Travel date
     * @return Most expensive flight or null if none available
     */
    @WebMethod(operationName = "obtenerVueloMasCaro")
    @WebResult(name = "Vuelo")
    public Vuelo obtenerVueloMasCaro(
            @WebParam(name = "ciudadOrigen") String ciudadOrigen,
            @WebParam(name = "ciudadDestino") String ciudadDestino,
            @WebParam(name = "fecha") Date fecha) {
        try {
            return viajecitosService.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Registers a ticket purchase.
     * @param idVuelo Flight ID
     * @param idCliente Client ID
     * @return Status (1 for success, -1 for failure)
     */
    @WebMethod(operationName = "registrarCompra")
    @WebResult(name = "estado")
    public int registrarCompra(
            @WebParam(name = "idVuelo") int idVuelo,
            @WebParam(name = "idCliente") int idCliente) {
        try {
            viajecitosService.registrarCompra(idVuelo, idCliente);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Registers a new client.
     * @param nombre Client name
     * @param email Client email
     * @param documentoIdentidad Client ID document
     * @return Registered client or null if failure
     */
    @WebMethod(operationName = "registrarCliente")
    @WebResult(name = "Cliente")
    public Cliente registrarCliente(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "email") String email,
            @WebParam(name = "documentoIdentidad") String documentoIdentidad) {
        try {
            return viajecitosService.registrarCliente(nombre, email, documentoIdentidad);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Registers a new user.
     * @param idCliente Client ID
     * @param nombreUsuario Username
     * @param claveUsuario Password
     * @return Registered user or null if failure
     */
    @WebMethod(operationName = "registrarUsuario")
    @WebResult(name = "Usuario")
    public Usuario registrarUsuario(
            @WebParam(name = "idCliente") int idCliente,
            @WebParam(name = "nombreUsuario") String nombreUsuario,
            @WebParam(name = "claveUsuario") String claveUsuario) {
        try {
            return viajecitosService.registrarUsuario(idCliente, nombreUsuario, claveUsuario);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Logs in a user.
     * @param nombreUsuario Username
     * @param claveUsuario Password
     * @return Authenticated user or null if failure
     */
    @WebMethod(operationName = "iniciarSesion")
    @WebResult(name = "Usuario")
    public Usuario iniciarSesion(
            @WebParam(name = "nombreUsuario") String nombreUsuario,
            @WebParam(name = "claveUsuario") String claveUsuario) {
        try {
            return viajecitosService.iniciarSesion(nombreUsuario, claveUsuario);
        } catch (Exception e) {
            return null;
        }
    }
}