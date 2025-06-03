using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VIAJECITOS_REST_CLICON_GR10.Modelos;
using VIAJECITOS_REST_CLICON_GR10.Servicio;

namespace VIAJECITOS_REST_CLICON_GR10.Controlador
{
    public class ViajecitosController
    {
        private readonly ViajecitosService service;
        private Usuario usuarioAutenticado;

        public ViajecitosController()
        {
            service = new ViajecitosService();
            Console.WriteLine("[DEBUG] ViajecitosController inicializado");
        }

        public async Task<bool> IniciarSesion(string nombreUsuario, string claveUsuario)
        {
            if (string.IsNullOrWhiteSpace(nombreUsuario) || string.IsNullOrWhiteSpace(claveUsuario))
            {
                Console.WriteLine("[ERROR] Validación fallida: Usuario o contraseña vacíos");
                throw new Exception("Usuario y contraseña son requeridos.");
            }

            try
            {
                Console.WriteLine($"[DEBUG] Iniciando sesión para usuario: {nombreUsuario}");
                usuarioAutenticado = await service.IniciarSesion(nombreUsuario, claveUsuario);
                if (usuarioAutenticado == null)
                {
                    Console.WriteLine("[ERROR] Respuesta nula del servicio en IniciarSesion");
                    throw new Exception("Credenciales inválidas o usuario inactivo.");
                }
                Console.WriteLine($"[DEBUG] Sesión iniciada: IdUsuario={usuarioAutenticado.IdUsuario}, NombreUsuario={usuarioAutenticado.NombreUsuario}");
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en IniciarSesion: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error al iniciar sesión: {ex.Message}", ex);
            }
        }

        public int GetIdClienteAutenticado()
        {
            if (usuarioAutenticado == null)
            {
                Console.WriteLine("[ERROR] Intento de obtener ID de cliente sin usuario autenticado");
                throw new Exception("No hay usuario autenticado.");
            }
            Console.WriteLine($"[DEBUG] Obteniendo ID de cliente autenticado: {usuarioAutenticado.IdCliente}");
            return usuarioAutenticado.IdCliente;
        }

        public async Task<List<Vuelo>> BuscarVuelos(string ciudadOrigen, string ciudadDestino, string fechaStr)
        {
            if (string.IsNullOrWhiteSpace(ciudadOrigen) || string.IsNullOrWhiteSpace(ciudadDestino))
            {
                Console.WriteLine("[ERROR] Validación fallida: Ciudad de origen o destino vacía");
                throw new Exception("Ciudad de origen y destino son requeridas.");
            }
            if (string.IsNullOrWhiteSpace(fechaStr))
            {
                Console.WriteLine("[ERROR] Validación fallida: Fecha de viaje vacía");
                throw new Exception("Fecha de viaje es requerida.");
            }
            if (!DateTime.TryParseExact(fechaStr, "yyyy-MM-dd", null, System.Globalization.DateTimeStyles.None, out var fecha))
            {
                Console.WriteLine($"[ERROR] Validación fallida: Formato de fecha inválido: {fechaStr}");
                throw new Exception("Formato de fecha inválido. Use yyyy-MM-dd.");
            }

            try
            {
                Console.WriteLine($"[DEBUG] Buscaando: Origen={ciudadOrigen}, Destino={ciudadDestino}, Fecha={fecha:yyyy-MM-dd}");
                var vuelos = await service.BuscarVuelos(ciudadOrigen, ciudadDestino, fecha);
                if (vuelos == null || !vuelos.Any())
                {
                    Console.WriteLine("[ERROR] No se encontraron vuelos disponibles");
                    throw new Exception("No se encontraron vuelos disponibles.");
                }
                Console.WriteLine($"[DEBUG] Vuelos encontrados: {vuelos.Count}");
                return vuelos;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en BuscarVuelos: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error al buscar vuelos: {ex.Message}", ex);
            }
        }

        public async Task<int> RegistrarCliente(string nombre, string email, string documentoIdentidad)
        {
            if (string.IsNullOrWhiteSpace(nombre) || string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(documentoIdentidad))
            {
                Console.WriteLine("[ERROR] Validación fallida: Campos de cliente vacíos");
                throw new Exception("Todos los campos son requeridos.");
            }

            try
            {
                Console.WriteLine($"[DEBUG] Registrando cliente: Nombre={nombre}, Email={email}, Documento={documentoIdentidad}");
                var cliente = await service.RegistrarCliente(nombre, email, documentoIdentidad);
                if (cliente == null)
                {
                    Console.WriteLine("[ERROR] Respuesta nula del servicio en RegistrarCliente");
                    throw new Exception("Error al registrar el cliente.");
                }
                Console.WriteLine($"[DEBUG] Cliente registrado: Id={cliente.IdCliente}, Nombre={cliente.Nombre}");
                return cliente.IdCliente;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en RegistrarCliente: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error al registrar el cliente: {ex.Message}", ex);
            }
        }

        public async Task<List<Cliente>> ObtenerTodosClientes()
        {
            try
            {
                Console.WriteLine("[DEBUG] Obteniendo todos los clientes");
                var clientes = await service.ObtenerTodosClientes();
                if (clientes == null || !clientes.Any())
                {
                    Console.WriteLine("[ERROR] No se encontraron clientes");
                    throw new Exception("No se encontraron clientes.");
                }
                Console.WriteLine($"[DEBUG] Clientes obtenidos: {clientes.Count}");
                return clientes;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en ObtenerTodosClientes: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error al obtener clientes: {ex.Message}", ex);
            }
        }

        public async Task<Factura> CrearFactura(string numeroFactura, int idEmpleado, int idCliente, int idMetodoPago, decimal descuento, List<(int IdVuelo, int Cantidad)> detalles)
        {
            if (string.IsNullOrEmpty(numeroFactura))
            {
                Console.WriteLine("[ERROR] Validación fallida: Número de factura");
                throw new Exception("El número de factura es requerido.");
            }
            if (idEmpleado <= 0)
            {
                Console.WriteLine("[ERROR] Validación fallida: ID de empleado inválido");
                throw new Exception("ID de empleado inválido.");
            }
            if (idCliente <= 0)
            {
                Console.WriteLine("[ERROR] Validación fallida: ID de cliente inválido");
                throw new Exception("ID de cliente inválido.");
            }
            if (idMetodoPago <= 0)
            {
                Console.WriteLine("[ERROR] Validación fallida: Método de pago inválido");
                throw new Exception("Método de pago inválido.");
            }
            if (detalles == null || !detalles.Any())
            {
                Console.WriteLine("[ERROR] Validación fallida: Detalles de factura vacíos");
                throw new Exception("Debe incluir al menos un detalle de factura.");
            }
            foreach (var detalle in detalles)
            {
                if (detalle.IdVuelo <= 0)
                {
                    Console.WriteLine($"[ERROR] Validación fallida: ID de vuelo inválido: {detalle.IdVuelo}");
                    throw new Exception("ID de vuelo inválido en los detalles.");
                }
                if (detalle.Cantidad <= 0)
                {
                    Console.WriteLine($"[ERROR] Validación fallida: Cantidad inválida: {detalle.Cantidad}");
                    throw new Exception("Cantidad inválida en los detalles.");
                }
            }

            try
            {
                Console.WriteLine($"[DEBUG] Creando factura: NumeroFactura={numeroFactura}, IdEmpleado={idEmpleado}, IdCliente={idCliente}, IdMetodoPago={idMetodoPago}, Descuento={descuento}, Detalles={detalles.Count}");
                var factura = await service.CrearFactura(numeroFactura, idEmpleado, idCliente, idMetodoPago, descuento, detalles);
                if (factura == null)
                {
                    Console.WriteLine("[ERROR] Respuesta nula del servicio al crear factura");
                    throw new Exception("Error al crear la factura.");
                }
                Console.WriteLine($"[DEBUG] Factura creada: IdFactura={factura.IdFactura}, NumeroFactura={factura.NumeroFactura}, Total={factura.Total}");
                return factura;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en CrearFactura: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error al crear la factura: {ex.Message}", ex);
            }
        }

        public async Task<List<Factura>> ObtenerFacturasCliente(int idCliente)
        {
            if (idCliente <= 0)
            {
                Console.WriteLine("[ERROR] Validación fallida: ID de cliente inválido");
                throw new Exception("ID de cliente inválido.");
            }

            try
            {
                Console.WriteLine($"[DEBUG] Obteniendo facturas para cliente: IdCliente={idCliente}");
                var facturas = await service.ObtenerFacturasCliente(idCliente);
                if (facturas == null || !facturas.Any())
                {
                    Console.WriteLine("[ERROR] No se encontraron facturas para el cliente");
                    throw new Exception("No se encontraron facturas para el cliente.");
                }
                Console.WriteLine($"[DEBUG] Facturas obtenidas: {facturas.Count}");
                return facturas;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en ObtenerFacturasCliente: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error al obtener facturas: {ex.Message}", ex);
            }
        }
        public async Task<List<ClienteFacturas>> ObtenerTodasFacturasPorCliente()
        {
            try
            {
                Console.WriteLine("[DEBUG] Obteniendo todas las facturas por cliente");
                var clientesFacturas = await service.ObtenerTodasFacturasPorCliente();
                if (clientesFacturas == null || !clientesFacturas.Any())
                {
                    Console.WriteLine("[ERROR] No se encontraron clientes con facturas");
                    throw new Exception("No se encontraron clientes con facturas.");
                }
                Console.WriteLine($"[DEBUG] Clientes con facturas obtenidos: {clientesFacturas.Count}");
                return clientesFacturas;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en ObtenerTodasFacturasPorCliente: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error al obtener facturas por cliente: {ex.Message}", ex);
            }
        }
    }
}