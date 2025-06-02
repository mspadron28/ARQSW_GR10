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

            var factura = new Factura
            {
                NumeroFactura = numeroFactura,
                FechaEmision = DateTime.Now,
                IdEmpleado = idEmpleado,
                IdCliente = idCliente,
                IdMetodoPago = idMetodoPago,
                Descuento = descuento,
                DetallesFactura = new List<DetalleFactura>()
            };

            decimal subtotal = 0;
            foreach (var (idVuelo, cantidad) in detalles)
            {
                var vuelo = await _context.Vuelos.FindAsync(idVuelo);
                if (vuelo == null)
                    throw new Exception($"El vuelo con ID {idVuelo} no existe.");

                var detalle = new DetalleFactura
                {
                    IdVuelo = idVuelo,
                    Cantidad = cantidad,
                    ValorUnitario = vuelo.Valor,
                    Total = cantidad * vuelo.Valor
                };
                factura.DetallesFactura.Add(detalle);
                subtotal += detalle.Total;
            }

            const decimal ivaRate = 0.15m; // 15% IVA
            factura.Subtotal = subtotal;
            factura.Iva = subtotal * ivaRate;
            factura.Total = subtotal - factura.Descuento + factura.Iva;

            _context.Facturas.Add(factura);
            await _context.SaveChangesAsync();
            return factura;
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
    }
}