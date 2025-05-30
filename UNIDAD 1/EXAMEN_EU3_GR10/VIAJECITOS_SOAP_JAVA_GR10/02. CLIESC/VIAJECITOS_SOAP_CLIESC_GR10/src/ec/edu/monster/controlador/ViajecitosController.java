package ec.edu.monster.controlador;

import ec.edu.monster.servicio.ViajecitosService;
import ec.edu.monster.ws.Cliente;
import ec.edu.monster.ws.Compra;
import ec.edu.monster.ws.Usuario;
import ec.edu.monster.ws.Vuelo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Controller to coordinate between console input and service layer for Viajecitos SA.
 *
 * @author MATIAS
 */
public class ViajecitosController {

    private final ViajecitosService service;
    private Usuario usuarioAutenticado;

    public ViajecitosController() {
        this.service = new ViajecitosService();
    }

    /**
     * Inicia sesión para un usuario.
     *
     * @param nombreUsuario Nombre de usuario.
     * @param claveUsuario Contraseña del usuario.
     * @return true si la autenticación es exitosa, false si falla.
     * @throws Exception si ocurre un error en el proceso.
     */
    public boolean iniciarSesion(String nombreUsuario, String claveUsuario) throws Exception {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty() || claveUsuario == null || claveUsuario.trim().isEmpty()) {
            throw new Exception("Usuario y contraseña son requeridos.");
        }
        usuarioAutenticado = service.iniciarSesion(nombreUsuario, claveUsuario);
        return usuarioAutenticado != null;
    }

    /**
     * Obtiene el ID del cliente autenticado.
     *
     * @return ID del cliente.
     * @throws Exception si no hay usuario autenticado.
     */
    public int getIdClienteAutenticado() throws Exception {
        if (usuarioAutenticado == null) {
            throw new Exception("No hay usuario autenticado.");
        }
        return usuarioAutenticado.getIdCliente();
    }

    /**
     * Busca vuelos disponibles.
     *
     * @param ciudadOrigen Ciudad de origen.
     * @param ciudadDestino Ciudad de destino.
     * @param fechaStr Fecha del viaje (formato yyyy-MM-dd).
     * @return Lista de vuelos disponibles.
     * @throws Exception si ocurre un error o no se encuentran vuelos.
     */
    public List<Vuelo> buscarVuelos(String ciudadOrigen, String ciudadDestino, String fechaStr) throws Exception {
        if (ciudadOrigen == null || ciudadOrigen.trim().isEmpty() || ciudadDestino == null || ciudadDestino.trim().isEmpty()) {
            throw new Exception("Ciudad de origen y destino son requeridas.");
        }
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            throw new Exception("Fecha de viaje es requerida.");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse(fechaStr);
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(fecha);
        XMLGregorianCalendar xmlFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

        List<Vuelo> vuelos = service.buscarVuelos(ciudadOrigen, ciudadDestino, xmlFecha);
        if (vuelos.isEmpty()) {
            throw new Exception("No se encontraron vuelos disponibles.");
        }
        return vuelos;
    }

    /**
     * Obtiene el vuelo más caro.
     *
     * @param ciudadOrigen Ciudad de origen.
     * @param ciudadDestino Ciudad de destino.
     * @param fechaStr Fecha del viaje (formato yyyy-MM-dd).
     * @return Vuelo más caro.
     * @throws Exception si ocurre un error o no se encuentra el vuelo.
     */
    public Vuelo obtenerVueloMasCaro(String ciudadOrigen, String ciudadDestino, String fechaStr) throws Exception {
        if (ciudadOrigen == null || ciudadOrigen.trim().isEmpty() || ciudadDestino == null || ciudadDestino.trim().isEmpty()) {
            throw new Exception("Ciudad de origen y destino son requeridas.");
        }
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            throw new Exception("Fecha de viaje es requerida.");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse(fechaStr);
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(fecha);
        XMLGregorianCalendar xmlFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

        Vuelo vuelo = service.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, xmlFecha);
        if (vuelo == null) {
            throw new Exception("No se encontró un vuelo para los criterios especificados.");
        }
        return vuelo;
    }

    /**
     * Realiza la compra de un boleto.
     *
     * @param idVuelo ID del vuelo.
     * @param idCliente ID del cliente.
     * @return true si la compra es exitosa, false si falla.
     * @throws Exception si ocurre un error.
     */
    public boolean registrarCompra(int idVuelo, int idCliente) throws Exception {
        if (idVuelo <= 0) {
            throw new Exception("ID de vuelo inválido.");
        }
        if (idCliente <= 0) {
            throw new Exception("ID de cliente inválido.");
        }
        int idCompra = service.registrarCompra(idVuelo, idCliente);
        return idCompra > 0;
    }

    /**
     * Consulta las compras de un cliente.
     *
     * @param idCliente ID del cliente.
     * @return Lista de compras.
     * @throws Exception si ocurre un error o no se encuentran compras.
     */
    public List<Compra> obtenerComprasCliente(int idCliente) throws Exception {
        if (idCliente <= 0) {
            throw new Exception("ID de cliente inválido.");
        }
        List<Compra> compras = service.obtenerComprasCliente(idCliente);
        if (compras.isEmpty()) {
            throw new Exception("No se encontraron compras para el cliente.");
        }
        return compras;
    }

    /**
     * Registra un nuevo cliente.
     *
     * @param nombre Nombre del cliente.
     * @param email Email del cliente.
     * @param documentoIdentidad Documento de identidad.
     * @return ID del cliente registrado.
     * @throws Exception si ocurre un error.
     */
    public int registrarCliente(String nombre, String email, String documentoIdentidad) throws Exception {
        if (nombre == null || nombre.trim().isEmpty() || email == null || email.trim().isEmpty() || documentoIdentidad == null || documentoIdentidad.trim().isEmpty()) {
            throw new Exception("Todos los campos son requeridos.");
        }
        Cliente cliente = service.registrarCliente(nombre, email, documentoIdentidad);
        if (cliente == null) {
            throw new Exception("Error al registrar el cliente.");
        }
        return cliente.getIdCliente();
    }

    /**
     * Registra un nuevo usuario.
     *
     * @param idCliente ID del cliente asociado.
     * @param nombreUsuario Nombre de usuario.
     * @param claveUsuario Contraseña del usuario.
     * @return true si el registro es exitoso, false si falla.
     * @throws Exception si ocurre un error.
     */
    public boolean registrarUsuario(int idCliente, String nombreUsuario, String claveUsuario) throws Exception {
        if (idCliente <= 0) {
            throw new Exception("ID de cliente inválido.");
        }
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty() || claveUsuario == null || claveUsuario.trim().isEmpty()) {
            throw new Exception("Nombre de usuario y contraseña son requeridos.");
        }
        Usuario usuario = service.registrarUsuario(idCliente, nombreUsuario, claveUsuario);
        return usuario != null;
    }
}