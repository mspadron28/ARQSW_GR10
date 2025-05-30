using Microsoft.EntityFrameworkCore;
using ViajecitosRestServer.Data;
using ViajecitosRestServer.Models;

namespace ViajecitosRestServer.Services
{
    public class ViajecitosService
    {
        private readonly VuelosDbContext _context;

        public ViajecitosService(VuelosDbContext context)
        {
            _context = context;
        }

        public async Task<List<Vuelo>> BuscarVuelos(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            return await _context.Vuelos
                .Where(v => v.CiudadOrigen == ciudadOrigen &&
                            v.CiudadDestino == ciudadDestino &&
                            v.HoraSalida.Date == fecha.Date)
                .ToListAsync();
        }

        public async Task<Vuelo> ObtenerVueloMasCaro(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            return await _context.Vuelos
                .Where(v => v.CiudadOrigen == ciudadOrigen &&
                            v.CiudadDestino == ciudadDestino &&
                            v.HoraSalida.Date == fecha.Date)
                .OrderByDescending(v => v.Valor)
                .FirstOrDefaultAsync();
        }

        public async Task RegistrarCompra(int idVuelo, int idCliente)
        {
            var compra = new Compra
            {
                IdVuelo = idVuelo,
                IdCliente = idCliente,
                FechaCompra = DateTime.Now
            };
            _context.Compras.Add(compra);
            await _context.SaveChangesAsync();
        }

        public async Task<Cliente> RegistrarCliente(string nombre, string email, string documentoIdentidad)
        {
            var cliente = new Cliente
            {
                Nombre = nombre,
                Email = email,
                DocumentoIdentidad = documentoIdentidad
            };
            _context.Clientes.Add(cliente);
            await _context.SaveChangesAsync();
            return cliente;
        }

        public async Task<Usuario> RegistrarUsuario(int idCliente, string nombreUsuario, string claveUsuario)
        {
            var usuario = new Usuario
            {
                IdCliente = idCliente,
                NombreUsuario = nombreUsuario,
                ClaveUsuario = claveUsuario,
                EstadoUsuario = "Activo"
            };
            _context.Usuarios.Add(usuario);
            await _context.SaveChangesAsync();
            return usuario;
        }

        public async Task<Usuario> IniciarSesion(string nombreUsuario, string claveUsuario)
        {
            return await _context.Usuarios
                .Include(u => u.Cliente)
                .FirstOrDefaultAsync(u => u.NombreUsuario == nombreUsuario &&
                                        u.ClaveUsuario == claveUsuario &&
                                        u.EstadoUsuario == "Activo");
        }

        public async Task<List<Compra>> ObtenerComprasCliente(int idCliente)
        {
            return await _context.Compras
                .Include(c => c.Vuelo)
                .Include(c => c.Cliente)
                .Where(c => c.IdCliente == idCliente)
                .ToListAsync();
        }
    }
}