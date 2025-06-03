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

        public async Task<List<Vuelo>> BuscarVuelosOrdenados(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            return await _context.Vuelos
                .Where(v => v.CiudadOrigen == ciudadOrigen &&
                            v.CiudadDestino == ciudadDestino &&
                            v.HoraSalida.Date == fecha.Date)
                .OrderByDescending(v => v.Valor)
                .ToListAsync();
        }

        public async Task<Cliente> RegistrarCliente(string nombre, string email, string documentoIdentidad)
        {
            // Verificar si el documento de identidad ya existe
            var clienteExistente = await _context.Clientes
                .FirstOrDefaultAsync(c => c.DocumentoIdentidad == documentoIdentidad);
            if (clienteExistente != null)
            {
                throw new Exception("El documento de identidad ya está registrado.");
            }

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
        public async Task<Usuario> IniciarSesion(string nombreUsuario, string claveUsuario)
        {
            return await _context.Usuarios
                .FirstOrDefaultAsync(u => u.NombreUsuario == nombreUsuario &&
                                         u.ClaveUsuario == claveUsuario &&
                                         u.EstadoUsuario == "Activo");
        }

        public async Task<Factura> CrearFactura(string numeroFactura, int idEmpleado, int idCliente, int idMetodoPago, decimal descuento, List<(int IdVuelo, int Cantidad)> detalles)
        {
            var empleado = await _context.Empleados.FindAsync(idEmpleado);
            if (empleado == null)
                throw new Exception($"El empleado con ID {idEmpleado} no existe.");

            var cliente = await _context.Clientes.FindAsync(idCliente);
            if (cliente == null)
                throw new Exception($"El cliente con ID {idCliente} no existe.");

            var metodoPago = await _context.MetodosPago.FindAsync(idMetodoPago);
            if (metodoPago == null)
                throw new Exception($"El método de pago con ID {idMetodoPago} no existe.");

            if (detalles == null || !detalles.Any())
                throw new Exception("Debe incluir al menos un detalle para crear la factura.");

            // Calcular el Subtotal basado en los detalles
            decimal subtotal = 0;
            foreach (var (idVuelo, cantidad) in detalles)
            {
                var vuelo = await _context.Vuelos.FindAsync(idVuelo);
                if (vuelo == null)
                    throw new Exception($"El vuelo con ID {idVuelo} no existe.");
                subtotal += cantidad * vuelo.Valor;
            }

            // Crear la factura con los cálculos
            var factura = new Factura
            {
                NumeroFactura = numeroFactura,
                FechaEmision = DateTime.Now,
                IdEmpleado = idEmpleado,
                IdCliente = idCliente,
                IdMetodoPago = idMetodoPago,
                Subtotal = subtotal,
                Descuento = descuento,
                Iva = subtotal * 0.15m, // 15% IVA
                Total = subtotal - descuento + (subtotal * 0.15m),
                DetallesFactura = new List<DetalleFactura>()
            };

            _context.Facturas.Add(factura);
            await _context.SaveChangesAsync();

            // Agregar los detalles por separado
            foreach (var (idVuelo, cantidad) in detalles)
            {
                var vuelo = await _context.Vuelos.FindAsync(idVuelo); // Ya validado antes
                var detalle = new DetalleFactura
                {
                    IdFactura = factura.IdFactura,
                    IdVuelo = idVuelo,
                    Cantidad = cantidad,
                    ValorUnitario = vuelo.Valor,
                    Total = cantidad * vuelo.Valor
                };
                _context.DetallesFactura.Add(detalle);
            }

            await _context.SaveChangesAsync();
            return factura;
        }

        public async Task<DetalleFactura> AgregarDetalleFactura(int idFactura, int idVuelo, int cantidad)
        {
            var vuelo = await _context.Vuelos.FindAsync(idVuelo);
            if (vuelo == null)
                throw new Exception($"El vuelo con ID {idVuelo} no existe.");

            var factura = await _context.Facturas.FindAsync(idFactura);
            if (factura == null)
                throw new Exception($"La factura con ID {idFactura} no existe.");

            var detalle = new DetalleFactura
            {
                IdFactura = idFactura,
                IdVuelo = idVuelo,
                Cantidad = cantidad,
                ValorUnitario = vuelo.Valor,
                Total = cantidad * vuelo.Valor
            };

            _context.DetallesFactura.Add(detalle);
            await _context.SaveChangesAsync();
            return detalle;
        }


        public async Task<List<Factura>> ObtenerFacturasCliente(int idCliente)
        {
            return await _context.Facturas
                .Include(f => f.Empleado)
                .Include(f => f.Cliente)
                .Include(f => f.MetodoPago)
                .Include(f => f.DetallesFactura)
                .ThenInclude(d => d.Vuelo)
                .Where(f => f.IdCliente == idCliente)
                .ToListAsync();
        }

        public async Task<List<object>> ObtenerTodasFacturasPorCliente()
        {
            var facturas = await _context.Facturas
                .Include(f => f.Empleado)
                .Include(f => f.Cliente)
                .Include(f => f.MetodoPago)
                .Include(f => f.DetallesFactura)
                .ThenInclude(d => d.Vuelo)
                .ToListAsync();

            var result = facturas
                .GroupBy(f => f.Cliente)
                .Select(g => new
                {
                    ClienteId = g.Key.IdCliente,
                    Nombre = g.Key.Nombre,
                    Email = g.Key.Email,
                    DocumentoIdentidad = g.Key.DocumentoIdentidad,
                    Facturas = g.OrderBy(f => f.FechaEmision).ToList()
                })
                .ToList();

            return result.Cast<object>().ToList();
        }

        public async Task<List<Cliente>> ObtenerTodosClientes()
        {
            return await _context.Clientes
                .OrderBy(c => c.Nombre)
                .ToListAsync();
        }
    }
}