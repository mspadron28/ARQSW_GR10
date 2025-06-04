package ec.edu.monster.controlador;

import ec.edu.monster.modelo.Cliente;
import ec.edu.monster.modelo.ClienteFacturas;
import ec.edu.monster.modelo.Factura;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.modelo.Vuelo;
import ec.edu.monster.servicio.ViajecitosService;

import java.util.ArrayList;
import java.util.List;

public class AppControlador {
    private final ViajecitosService service;

    private Usuario usuarioAutenticado;

    public AppControlador() {
        service = new ViajecitosService();
    }

    public Usuario login(String nombreUsuario, String claveUsuario) throws Exception {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty() || claveUsuario == null || claveUsuario.trim().isEmpty()) {
            throw new Exception("Usuario y contraseña son requeridos.");
        }
        usuarioAutenticado = service.iniciarSesion(nombreUsuario, claveUsuario);
        if (usuarioAutenticado == null) {
            throw new Exception("Credenciales inválidas o usuario inactivo.");
        }
        return usuarioAutenticado;
    }

    public int getIdClienteAutenticado() throws Exception {
        if (usuarioAutenticado == null) {
            throw new Exception("No hay usuario autenticado.");
        }
        return usuarioAutenticado.getIdCliente();
    }

    public Cliente registrarCliente(String nombre, String email, String documentoIdentidad) throws Exception {
        if (nombre == null || nombre.trim().isEmpty() || email == null || email.trim().isEmpty() || documentoIdentidad == null || documentoIdentidad.trim().isEmpty()) {
            throw new Exception("Todos los campos son requeridos.");
        }
        Cliente cliente = service.registrarCliente(nombre, email, documentoIdentidad);
        if (cliente == null) {
            throw new Exception("Error al registrar el cliente.");
        }
        return cliente;
    }

    public List<Cliente> obtenerTodosClientes() throws Exception {
        List<Cliente> clientes = service.obtenerTodosClientes();
        if (clientes == null || clientes.isEmpty()) {
            throw new Exception("No se encontraron clientes.");
        }
        return clientes;
    }

    public List<Factura> obtenerFacturasCliente(int idCliente) throws Exception {
        if (idCliente <= 0) {
            throw new Exception("ID de cliente inválido.");
        }
        List<Factura> facturas = service.obtenerFacturasCliente(idCliente);
        if (facturas == null || facturas.isEmpty()) {
            throw new Exception("No se encontraron facturas para el cliente.");
        }
        return facturas;
    }

    public List<ClienteFacturas> obtenerTodasFacturasPorCliente() throws Exception {
        List<ClienteFacturas> clientesFacturas = service.obtenerTodasFacturasPorCliente();
        if (clientesFacturas == null || clientesFacturas.isEmpty()) {
            throw new Exception("No se encontraron clientes con facturas.");
        }
        return clientesFacturas;
    }

    public List<Vuelo> buscarVuelos(String ciudadOrigen, String ciudadDestino, java.util.Date fecha) throws Exception {
        if (ciudadOrigen == null || ciudadOrigen.trim().isEmpty() || ciudadDestino == null || ciudadDestino.trim().isEmpty()) {
            throw new Exception("Ciudad origen y destino son requeridos.");
        }
        return service.obtenerVuelosOrdenados(ciudadOrigen, ciudadDestino, fecha);
    }
}