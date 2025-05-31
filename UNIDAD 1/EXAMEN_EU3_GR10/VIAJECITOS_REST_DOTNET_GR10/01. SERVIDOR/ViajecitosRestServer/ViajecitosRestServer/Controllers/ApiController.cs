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
        public async Task<ActionResult<List<Vuelo>>> BuscarVuelos(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            try
            {
                var vuelos = await _service.BuscarVuelos(ciudadOrigen, ciudadDestino, fecha);
                return Ok(vuelos);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("vuelo-mas-caro")]
        public async Task<ActionResult<Vuelo>> ObtenerVueloMasCaro(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            try
            {
                var vuelo = await _service.ObtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
                if (vuelo == null)
                    return NotFound(new { error = "Vuelo no encontrado." });
                return Ok(vuelo);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        // Nuevo modelo para el cuerpo de la solicitud
        public class CompraRequest
        {
            public int IdVuelo { get; set; }
            public int IdCliente { get; set; }
        }

        [HttpPost("compra")]
        public async Task<ActionResult> RegistrarCompra([FromBody] CompraRequest request)
        {
            try
            {
                // Validar que el vuelo exista
                var vuelo = await _context.Vuelos.FindAsync(request.IdVuelo);
                if (vuelo == null)
                {
                    return NotFound(new { error = $"El vuelo con ID {request.IdVuelo} no existe." });
                }

                // Validar que el cliente exista
                var cliente = await _context.Clientes.FindAsync(request.IdCliente);
                if (cliente == null)
                {
                    return NotFound(new { error = $"El cliente con ID {request.IdCliente} no existe." });
                }

                // Registrar la compra
                await _service.RegistrarCompra(request.IdVuelo, request.IdCliente);
                return Ok(new { message = "Compra registrada exitosamente." });
            }
            catch (DbUpdateException ex)
            {
                return StatusCode(500, new { error = "Error al registrar la compra.", detail = ex.InnerException?.Message });
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { error = "Error inesperado.", detail = ex.Message });
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
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpPost("usuario")]
        public async Task<ActionResult<Usuario>> RegistrarUsuario(int idCliente, string nombreUsuario, string claveUsuario)
        {
            try
            {
                var usuario = await _service.RegistrarUsuario(idCliente, nombreUsuario, claveUsuario);
                return Ok(usuario);
            }
            catch (Exception ex)
            {
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

        [HttpGet("compras/{idCliente}")]
        public async Task<ActionResult<List<Compra>>> ObtenerComprasCliente(int idCliente)
        {
            try
            {
                var compras = await _service.ObtenerComprasCliente(idCliente);
                return Ok(compras);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }
    }
}