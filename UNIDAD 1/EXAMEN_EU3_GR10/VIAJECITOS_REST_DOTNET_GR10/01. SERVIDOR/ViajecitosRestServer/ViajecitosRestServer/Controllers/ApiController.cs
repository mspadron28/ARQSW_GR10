using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ViajecitosRestServer.Models;
using ViajecitosRestServer.Services;
using ViajecitosRestServer.Data;

namespace ViajecitosRestServer.Controllers
{
    [Route("api")]
    [ApiController]
    public class ApiController : ControllerBase
    {
        private readonly ViajecitosService _service;
        private readonly VuelosDbContext _context;

        public ApiController(ViajecitosService service, VuelosDbContext context)
        {
            _service = service;
            _context = context;
        }

        [HttpGet("vuelos")]
        public async Task<ActionResult<List<Vuelo>>> BuscarVuelosOrdenados(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            try
            {
                var vuelos = await _service.BuscarVuelosOrdenados(ciudadOrigen, ciudadDestino, fecha);
                return Ok(vuelos);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpPost("cliente")]
        public async Task<ActionResult<Cliente>> RegistrarCliente(string nombre, string email, string documentoIdentidad)
        {
            try
            {
                var cliente = await _service.RegistrarCliente(nombre, email, documentoIdentidad);
                return Ok(cliente);
            }
            catch (Exception ex)
            {
                if (ex.Message == "El documento de identidad ya está registrado.")
                {
                    return Conflict(new { error = ex.Message });
                }
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpPost("login")]
        public async Task<ActionResult<Usuario>> IniciarSesion(string nombreUsuario, string claveUsuario)
        {
            try
            {
                var usuario = await _service.IniciarSesion(nombreUsuario, claveUsuario);
                if (usuario == null)
                    return Unauthorized(new { error = "Credenciales inválidas o usuario inactivo." });
                return Ok(usuario);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }


        public class DetalleRequest
        {
            public int IdVuelo { get; set; }
            public int Cantidad { get; set; }
        }

        public class FacturaRequest
        {
            public string NumeroFactura { get; set; } = string.Empty;
            public int IdEmpleado { get; set; }
            public int IdCliente { get; set; }
            public int IdMetodoPago { get; set; }
            public decimal Descuento { get; set; }
            public List<DetalleRequest> Detalles { get; set; } = new List<DetalleRequest>();
        }

        [HttpPost("factura")]
        public async Task<ActionResult<Factura>> CrearFactura([FromBody] FacturaRequest request)
        {
            try
            {
                // Convertir DetalleRequest a List<(int IdVuelo, int Cantidad)>
                var detalles = request.Detalles.Select(d => (d.IdVuelo, d.Cantidad)).ToList();

                var factura = await _service.CrearFactura(
                    request.NumeroFactura,
                    request.IdEmpleado,
                    request.IdCliente,
                    request.IdMetodoPago,
                    request.Descuento,
                    detalles
                );
                return Ok(factura);
            }
            catch (DbUpdateException ex)
            {
                return StatusCode(500, new { error = "Error al crear la factura.", detail = ex.InnerException?.Message });
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("facturas/{idCliente}")]
        public async Task<ActionResult<List<Factura>>> ObtenerFacturasCliente(int idCliente)
        {
            try
            {
                var facturas = await _service.ObtenerFacturasCliente(idCliente);
                return Ok(facturas);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("facturas")]
        public async Task<ActionResult<List<object>>> ObtenerTodasFacturasPorCliente()
        {
            try
            {
                var facturasPorCliente = await _service.ObtenerTodasFacturasPorCliente();
                return Ok(facturasPorCliente);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("clientes")]
        public async Task<ActionResult<List<Cliente>>> ObtenerTodosClientes()
        {
            try
            {
                var clientes = await _service.ObtenerTodosClientes();
                return Ok(clientes);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }
    }
}