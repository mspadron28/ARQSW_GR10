package ec.edu.monster.controlador;

import ec.edu.monster.modelo.Cliente;
import ec.edu.monster.modelo.Compra;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.modelo.Vuelo;
import ec.edu.monster.servicio.ViajecitosService;
import java.util.Date;
import java.util.List;

public class AppControlador {

    private final ViajecitosService service;

    public AppControlador() {
        this.service = new ViajecitosService();
    }

    public Usuario login(String nombreUsuario, String claveUsuario) throws Exception {
        return service.iniciarSesion(nombreUsuario, claveUsuario);
    }

    public Vuelo obtenerVueloMasCaro(String ciudadOrigen, String ciudadDestino, Date fecha) throws Exception {
        return service.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
    }

    public List<Vuelo> buscarVuelos(String ciudadOrigen, String ciudadDestino, Date fecha) throws Exception {
        return service.buscarVuelos(ciudadOrigen, ciudadDestino, fecha);
    }

    public Cliente registrarCliente(String nombre, String email, String documentoIdentidad) throws Exception {
        return service.registrarCliente(nombre, email, documentoIdentidad);
    }

    public Usuario registrarUsuario(int idCliente, String nombreUsuario, String claveUsuario) throws Exception {
        return service.registrarUsuario(idCliente, nombreUsuario, claveUsuario);
    }

    public List<Compra> obtenerComprasCliente(int idCliente) throws Exception {
        return service.obtenerComprasCliente(idCliente);
    }

    public int registrarCompra(int idVuelo, int idCliente) throws Exception {
        return service.registrarCompra(idVuelo, idCliente);
    }
}