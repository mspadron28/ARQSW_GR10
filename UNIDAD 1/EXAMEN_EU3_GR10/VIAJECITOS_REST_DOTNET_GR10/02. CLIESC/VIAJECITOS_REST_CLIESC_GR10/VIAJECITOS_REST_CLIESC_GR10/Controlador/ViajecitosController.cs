using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;
using VIAJECITOS_REST_CLIESC_GR10.Servicio;

namespace VIAJECITOS_REST_CLIESC_GR10.Controlador
{
    public class ViajecitosController
    {
        private readonly ViajecitosService _service;
        private Usuario _usuarioAutenticado;

        public ViajecitosController()
        {
            _service = new ViajecitosService();
        }

        public Usuario UsuarioAutenticado => _usuarioAutenticado;

        public async Task<bool> IniciarSesionAsync(string nombreUsuario, string claveUsuario)
        {
            if (string.IsNullOrWhiteSpace(nombreUsuario) || string.IsNullOrWhiteSpace(claveUsuario))
                throw new ArgumentException("Usuario y contraseña son requeridos.");

            _usuarioAutenticado = await _service.IniciarSesion(nombreUsuario, claveUsuario);
            return _usuarioAutenticado != null;
        }

        public int ObtenerIdClienteAutenticado()
        {
            if (_usuarioAutenticado == null)
                throw new InvalidOperationException("No hay usuario autenticado.");

            return _usuarioAutenticado.IdCliente;
        }

        public async Task<List<Vuelo>> BuscarVuelosAsync(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            if (string.IsNullOrWhiteSpace(ciudadOrigen) || string.IsNullOrWhiteSpace(ciudadDestino))
                throw new ArgumentException("Ciudad de origen y destino son requeridas.");

            var vuelos = await _service.BuscarVuelos(ciudadOrigen, ciudadDestino, fecha);
            if (vuelos == null || vuelos.Count == 0)
                throw new Exception("No se encontraron vuelos disponibles.");

            return vuelos;
        }

        public async Task<Cliente> RegistrarClienteAsync(string nombre, string email, string documentoIdentidad)
        {
            if (string.IsNullOrWhiteSpace(nombre) || string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(documentoIdentidad))
                throw new ArgumentException("Todos los campos son requeridos.");

            var cliente = await _service.RegistrarCliente(nombre, email, documentoIdentidad);
            if (cliente == null)
                throw new Exception("Error al registrar el cliente.");

            return cliente;
        }

        public async Task<Factura> CrearFacturaAsync(string numeroFactura, int idEmpleado, int idCliente, int idMetodoPago, decimal descuento, List<(int IdVuelo, int Cantidad)> detalles)
        {
            if (string.IsNullOrWhiteSpace(numeroFactura))
                throw new ArgumentException("El número de factura es requerido.");
            if (idCliente <= 0)
                throw new ArgumentException("ID de cliente inválido.");
            if (idMetodoPago <= 0)
                throw new ArgumentException("Método de pago inválido.");
            if (detalles == null || detalles.Count == 0)
                throw new ArgumentException("Debe incluir al menos un detalle de factura.");

            var factura = await _service.CrearFactura(numeroFactura, idEmpleado, idCliente, idMetodoPago, descuento, detalles);
            return factura;
        }

        public async Task<List<Cliente>> ObtenerTodosClientesAsync()
        {
            var clientes = await _service.ObtenerTodosClientes();
            return clientes ?? new List<Cliente>();
        }

        public async Task<List<Factura>> ObtenerFacturasClienteAsync(int idCliente)
        {
            if (idCliente <= 0)
                throw new ArgumentException("ID de cliente inválido.");

            var facturas = await _service.ObtenerFacturasCliente(idCliente);
            return facturas ?? new List<Factura>();
        }

        public async Task<List<ClienteFacturas>> ObtenerTodasFacturasPorClienteAsync()
        {
            var clientesFacturas = await _service.ObtenerTodasFacturasPorCliente();
            return clientesFacturas ?? new List<ClienteFacturas>();
        }

       
    }
}
