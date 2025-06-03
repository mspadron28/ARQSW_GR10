using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using VIAJECITOS_REST_CLIWEB_GR10.Models;
using VIAJECITOS_REST_CLIWEB_GR10.Servicio;
using System.Linq;
using ViajecitosRestServer.Models;

namespace VIAJECITOS_REST_CLIWEB_GR10.Controllers
{
    public class HomeController : Controller
    {
        private readonly ViajecitosService _service;

        public HomeController(ViajecitosService service)
        {
            _service = service;
            Console.WriteLine("[DEBUG] HomeController inicializado");
        }

        public IActionResult Index()
        {
            return View();
        }

        [HttpGet]
        public IActionResult Login()
        {
            return View(new LoginModel());
        }

        [HttpPost]
        public async Task<IActionResult> Login(LoginModel model)
        {
            if (string.IsNullOrWhiteSpace(model.NombreUsuario) || string.IsNullOrWhiteSpace(model.ClaveUsuario))
            {
                Console.WriteLine("[ERROR] Validación fallida: Usuario o contraseña vacíos");
                ViewBag.Error = "Usuario y contraseña son requeridos.";
                return View(model);
            }

            try
            {
                Console.WriteLine($"[DEBUG] Iniciando sesión para usuario: {model.NombreUsuario}");
                var usuario = await _service.IniciarSesion(model.NombreUsuario, model.ClaveUsuario);
                if (usuario == null)
                {
                    Console.WriteLine("[ERROR] Respuesta nula del servicio en IniciarSesion");
                    ViewBag.Error = "Credenciales inválidas o usuario inactivo.";
                    return View(model);
                }

                HttpContext.Session.SetString("IdCliente", usuario.IdCliente.ToString());
                HttpContext.Session.SetString("NombreUsuario", usuario.NombreUsuario);
                Console.WriteLine($"[DEBUG] Sesión iniciada: IdCliente={usuario.IdCliente}, NombreUsuario={usuario.NombreUsuario}");
                return RedirectToAction("Index");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en Login: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al iniciar sesión: {ex.Message}";
                return View(model);
            }
        }

        [HttpGet]
        public IActionResult Logout()
        {
            Console.WriteLine("[DEBUG] Cerrando sesión");
            HttpContext.Session.Clear();
            return RedirectToAction("Index");
        }

        [HttpGet]
        public IActionResult BuscarVuelos()
        {
            return View(new BuscarVuelosModel());
        }

        [HttpPost]
        public async Task<IActionResult> BuscarVuelos(BuscarVuelosModel model)
        {
            if (string.IsNullOrWhiteSpace(model.CiudadOrigen) || string.IsNullOrWhiteSpace(model.CiudadDestino))
            {
                Console.WriteLine("[ERROR] Validación fallida: Ciudad de origen o destino vacía");
                ViewBag.Error = "Ciudad de origen y destino son requeridas.";
                return View(model);
            }
            if (string.IsNullOrWhiteSpace(model.Fecha))
            {
                Console.WriteLine("[ERROR] Validación fallida: Fecha de viaje vacía");
                ViewBag.Error = "Fecha de viaje es requerida.";
                return View(model);
            }
            if (!DateTime.TryParseExact(model.Fecha, "yyyy-MM-dd", null, System.Globalization.DateTimeStyles.None, out var fecha))
            {
                Console.WriteLine($"[ERROR] Validación fallida: Formato de fecha inválido: {model.Fecha}");
                ViewBag.Error = "Formato de fecha inválido. Use yyyy-MM-dd.";
                return View(model);
            }

            try
            {
                Console.WriteLine($"[DEBUG] Buscando: Origen={model.CiudadOrigen}, Destino={model.CiudadDestino}, Fecha={fecha:yyyy-MM-dd}");
                var vuelos = await _service.BuscarVuelos(model.CiudadOrigen, model.CiudadDestino, fecha);
                if (vuelos == null || !vuelos.Any())
                {
                    Console.WriteLine("[ERROR] No se encontraron vuelos disponibles");
                    ViewBag.Error = "No se encontraron vuelos disponibles.";
                    return View(model);
                }
                Console.WriteLine($"[DEBUG] Vuelos encontrados: {vuelos.Count}");
                return View("ResultadoVuelos", vuelos);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en BuscarVuelos: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al buscar vuelos: {ex.Message}";
                return View(model);
            }
        }

        [HttpGet]
        public IActionResult Register()
        {
            return View(new RegisterModel());
        }

        [HttpPost]
        public async Task<IActionResult> Register(RegisterModel model)
        {
            if (string.IsNullOrWhiteSpace(model.Nombre) || string.IsNullOrWhiteSpace(model.Email) || string.IsNullOrWhiteSpace(model.DocumentoIdentidad))
            {
                Console.WriteLine("[ERROR] Validación fallida: Campos de cliente vacíos");
                ViewBag.Error = "Todos los campos son requeridos.";
                return View(model);
            }

            try
            {
                Console.WriteLine($"[DEBUG] Registrando cliente: Nombre={model.Nombre}, Email={model.Email}, Documento={model.DocumentoIdentidad}");
                var cliente = await _service.RegistrarCliente(model.Nombre, model.Email, model.DocumentoIdentidad);
                if (cliente == null)
                {
                    Console.WriteLine("[ERROR] Respuesta nula del servicio en RegistrarCliente");
                    ViewBag.Error = "Error al registrar el cliente.";
                    return View(model);
                }
                HttpContext.Session.SetString("IdCliente", cliente.IdCliente.ToString());
                HttpContext.Session.SetString("NombreUsuario", model.Nombre);
                Console.WriteLine($"[DEBUG] Cliente registrado: Id={cliente.IdCliente}, Nombre={cliente.Nombre}");
                return RedirectToAction("Index");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en Register: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al registrar el cliente: {ex.Message}";
                return View(model);
            }
        }

        [HttpGet]
        public async Task<IActionResult> Comprar()
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("IdCliente")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a Comprar sin autorización");
                return RedirectToAction("Login");
            }

            try
            {
                var clientes = await _service.ObtenerTodosClientes();
                var model = new ComprarModel
                {
                    Clientes = clientes,
                    MetodosPago = new List<MetodoPagoModel>
                    {
                        new MetodoPagoModel { Id = 1, Nombre = "Tarjeta de Crédito" },
                        new MetodoPagoModel { Id = 2, Nombre = "Efectivo" },
                        new MetodoPagoModel { Id = 3, Nombre = "Tarjeta de Débito" }
                    }
                };
                return View(model);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en Comprar (GET): {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al cargar el formulario de compra: {ex.Message}";
                return View(new ComprarModel());
            }
        }

        [HttpPost]
        public async Task<IActionResult> Comprar(ComprarModel model)
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[ERROR] Intento de compra sin autorización");
                return RedirectToAction("Login");
            }

            if (string.IsNullOrEmpty(model.NumeroFactura))
            {
                Console.WriteLine("[ERROR] Validación fallida: Número de factura vacío");
                ViewBag.Error = "El número de factura es requerido.";
                return await ReloadComprarView(model);
            }
            if (model.IdCliente <= 0)
            {
                Console.WriteLine("[ERROR] Validación fallida: ID de cliente inválido");
                ViewBag.Error = "ID de cliente inválido.";
                return await ReloadComprarView(model);
            }
            if (model.IdMetodoPago <= 0)
            {
                Console.WriteLine("[ERROR] Validación fallida: Método de pago inválido");
                ViewBag.Error = "Método de pago inválido.";
                return await ReloadComprarView(model);
            }
            if (model.Detalles == null || !model.Detalles.Any())
            {
                Console.WriteLine("[ERROR] Validación fallida: Detalles de factura vacíos");
                ViewBag.Error = "Debe incluir al menos un detalle de factura.";
                return await ReloadComprarView(model);
            }
            foreach (var detalle in model.Detalles)
            {
                if (detalle.IdVuelo <= 0)
                {
                    Console.WriteLine($"[ERROR] Validación fallida: ID de vuelo inválido: {detalle.IdVuelo}");
                    ViewBag.Error = "ID de vuelo inválido en los detalles.";
                    return await ReloadComprarView(model);
                }
                if (detalle.Cantidad <= 0)
                {
                    Console.WriteLine($"[ERROR] Validación fallida: Cantidad inválida: {detalle.Cantidad}");
                    ViewBag.Error = "Cantidad inválida en los detalles.";
                    return await ReloadComprarView(model);
                }
            }

            try
            {
                var detalles = model.Detalles.Select(d => (d.IdVuelo, d.Cantidad)).ToList();
                Console.WriteLine($"[DEBUG] Creando factura: NumeroFactura={model.NumeroFactura}, IdEmpleado=1, IdCliente={model.IdCliente}, IdMetodoPago={model.IdMetodoPago}, Descuento={model.Descuento}, Detalles={detalles.Count}");
                var factura = await _service.CrearFactura(model.NumeroFactura, 1, model.IdCliente, model.IdMetodoPago, model.Descuento, detalles);
                Console.WriteLine($"[DEBUG] Factura creada: IdFactura={factura.IdFactura}, NumeroFactura={factura.NumeroFactura}, Total={factura.Total}");
                return RedirectToAction("DetallesFactura", new { idCliente = model.IdCliente, numeroFactura = factura.NumeroFactura });
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en Comprar: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al crear la factura: {ex.Message}";
                return await ReloadComprarView(model);
            }
        }
        [HttpGet]
        public async Task<IActionResult> Facturas(int idCliente)
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a Facturas sin autorización");
                return RedirectToAction("Login");
            }

            if (idCliente <= 0)
            {
                Console.WriteLine("[ERROR] Validación fallida: ID de cliente inválido");
                ViewBag.Error = "ID de cliente inválido.";
                return View(new List<Factura>());
            }

            try
            {
                Console.WriteLine($"[DEBUG] Obteniendo facturas para cliente: IdCliente={idCliente}");
                var facturas = await _service.ObtenerFacturasCliente(idCliente);
                if (facturas == null || !facturas.Any())
                {
                    Console.WriteLine("[ERROR] No se encontraron facturas para el cliente");
                    ViewBag.Error = "No se encontraron facturas para el cliente.";
                    return View(new List<Factura>());
                }
                Console.WriteLine($"[DEBUG] Facturas obtenidas: {facturas.Count}");
                return View(facturas);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en Facturas: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al obtener facturas: {ex.Message}";
                return View(new List<Factura>());
            }
        }

        [HttpGet]
        public async Task<IActionResult> TodasFacturas()
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("IdCliente")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a TodasFacturas sin autorización");
                return RedirectToAction("Login");
            }

            try
            {
                Console.WriteLine("[DEBUG] Obteniendo todas las facturas por cliente");
                var clientesFacturas = await _service.ObtenerTodasFacturasPorCliente();
                if (clientesFacturas == null || !clientesFacturas.Any())
                {
                    Console.WriteLine("[ERROR] No se encontraron clientes con facturas");
                    ViewBag.Error = "No se encontraron clientes con facturas.";
                    return View(new List<ClienteFacturas>());
                }
                Console.WriteLine($"[DEBUG] Clientes con facturas obtenidos: {clientesFacturas.Count}");
                return View(clientesFacturas);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en TodasFacturas: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al obtener facturas por cliente: {ex.Message}";
                return View(new List<ClienteFacturas>());
            }
        }

        private async Task<IActionResult> ReloadComprarView(ComprarModel model)
        {
            try
            {
                model.Clientes = await _service.ObtenerTodosClientes();
                model.MetodosPago = new List<MetodoPagoModel>
                {
                    new MetodoPagoModel { Id = 1, Nombre = "Tarjeta de Crédito" },
                    new MetodoPagoModel { Id = 2, Nombre = "Efectivo" },
                    new MetodoPagoModel { Id = 3, Nombre = "Tarjeta de Débito" }
                };
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción al recargar la vista Comprar: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al cargar datos: {ex.Message}";
            }
            return View(model);
        }


        [HttpGet]
        public async Task<IActionResult> DetallesFactura(int idCliente, string numeroFactura)
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a DetallesFactura sin autorización");
                return RedirectToAction("Login");
            }
            if (idCliente <= 0 || string.IsNullOrWhiteSpace(numeroFactura))
            {
                Console.WriteLine("[ERROR] Validación fallida: ID de cliente o número de factura inválido");
                ViewBag.Error = "ID de cliente o número de factura inválido.";
                return View(new Factura());
            }

            try
            {
                Console.WriteLine($"[DEBUG] Obteniendo detalles de factura: IdCliente={idCliente}, NumeroFactura={numeroFactura}");
                var facturas = await _service.ObtenerFacturasCliente(idCliente);
                var factura = facturas?.FirstOrDefault(f => f.NumeroFactura == numeroFactura);
                if (factura == null)
                {
                    Console.WriteLine($"[ERROR] Factura no encontrada: IdCliente={idCliente}, NumeroFactura={numeroFactura}");
                    ViewBag.Error = "Factura no encontrada.";
                    return View(new Factura());
                }
                Console.WriteLine($"[DEBUG] Factura encontrada: IdFactura={factura.IdFactura}, NumeroFactura={factura.NumeroFactura}, IdCliente={factura.IdCliente}");
                return View(factura);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en DetallesFactura: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al obtener detalles: {ex.Message}";
                return View(new Factura());
            }
        }
        [HttpGet]
        public async Task<IActionResult> SeleccionarClienteFacturas()
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a SeleccionarClienteFacturas sin autorización");
                return RedirectToAction("Login");
            }

            try
            {
                Console.WriteLine("[DEBUG] Obteniendo lista de clientes");
                var clientes = await _service.ObtenerTodosClientes();
                var model = new SeleccionarClienteModel
                {
                    Clientes = clientes ?? new List<Cliente>()
                };
                if (!model.Clientes.Any())
                {
                    Console.WriteLine("[ERROR] No se encontraron clientes");
                    ViewBag.Error = "No se encontraron clientes disponibles.";
                }
                return View(model);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en SeleccionarClienteFacturas (GET): {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al cargar la lista de clientes: {ex.Message}";
                return View(new SeleccionarClienteModel());
            }
        }

        [HttpPost]
        public async Task<IActionResult> SeleccionarClienteFacturas(SeleccionarClienteModel model)
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a SeleccionarClienteFacturas sin autorización");
                return RedirectToAction("Login");
            }

            if (model.IdCliente <= 0)
            {
                Console.WriteLine("[ERROR] Validación fallida: ID de cliente inválido");
                model.Clientes = await _service.ObtenerTodosClientes() ?? new List<Cliente>();
                ViewBag.Error = "Debes seleccionar un cliente válido.";
                return View(model);
            }

            try
            {
                Console.WriteLine($"[DEBUG] Redirigiendo a Facturas para cliente: IdCliente={model.IdCliente}");
                return RedirectToAction("Facturas", new { idCliente = model.IdCliente });
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción en SeleccionarClienteFacturas (POST): {ex.Message}, InnerException: {ex.InnerException?.Message}");
                model.Clientes = await _service.ObtenerTodosClientes() ?? new List<Cliente>();
                ViewBag.Error = $"Error al procesar la selección: {ex.Message}";
                return View(model);
            }
        }
    }

    public class BuscarVuelosModel
    {
        public string CiudadOrigen { get; set; }
        public string CiudadDestino { get; set; }
        public string Fecha { get; set; }
    }

    public class LoginModel
    {
        public string NombreUsuario { get; set; }
        public string ClaveUsuario { get; set; }
    }

    public class RegisterModel
    {
        public string Nombre { get; set; }
        public string Email { get; set; }
        public string DocumentoIdentidad { get; set; }
    }

    public class ComprarModel
    {
        public string NumeroFactura { get; set; }
        public int IdCliente { get; set; }
        public int IdMetodoPago { get; set; }
        public decimal Descuento { get; set; }
        public List<DetalleVueloModel> Detalles { get; set; } = new List<DetalleVueloModel>();
        public List<Cliente> Clientes { get; set; }
        public List<MetodoPagoModel> MetodosPago { get; set; }
    }

    public class DetalleVueloModel
    {
        public int IdVuelo { get; set; }
        public int Cantidad { get; set; }
    }

    public class MetodoPagoModel
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
    }

    public class SeleccionarClienteModel
    {
        public int IdCliente { get; set; }
        public List<Cliente> Clientes { get; set; } = new List<Cliente>();
    }
}