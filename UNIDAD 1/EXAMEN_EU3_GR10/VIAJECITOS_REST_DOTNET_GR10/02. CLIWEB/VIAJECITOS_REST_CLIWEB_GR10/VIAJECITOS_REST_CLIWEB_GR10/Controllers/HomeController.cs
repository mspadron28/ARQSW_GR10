using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using VIAJECITOS_REST_CLIWEB_GR10.Models;
using VIAJECITOS_REST_CLIWEB_GR10.Servicio;
using System.Linq;
using ViajecitosRestServer.Models;
using System.Text.Json; // A�adido para JsonSerializer

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
                Console.WriteLine("[ERROR] Validaci�n fallida: Usuario o contrase�a vac�os");
                ViewBag.Error = "Usuario y contrase�a son requeridos.";
                return View(model);
            }

            try
            {
                Console.WriteLine($"[DEBUG] Iniciando sesi�n para usuario: {model.NombreUsuario}");
                var usuario = await _service.IniciarSesion(model.NombreUsuario, model.ClaveUsuario);
                if (usuario == null)
                {
                    Console.WriteLine("[ERROR] Respuesta nula del servicio en IniciarSesion");
                    ViewBag.Error = "Credenciales inv�lidas o usuario inactivo.";
                    return View(model);
                }

                HttpContext.Session.SetString("IdCliente", usuario.IdCliente.ToString());
                HttpContext.Session.SetString("NombreUsuario", usuario.NombreUsuario);
                Console.WriteLine($"[DEBUG] Sesi�n iniciada: IdCliente={usuario.IdCliente}, NombreUsuario={usuario.NombreUsuario}");
                return RedirectToAction("Index");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepci�n en Login: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al iniciar sesi�n: {ex.Message}";
                return View(model);
            }
        }

        [HttpGet]
        public IActionResult Logout()
        {
            Console.WriteLine("[DEBUG] Cerrando sesi�n");
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
                Console.WriteLine("[ERROR] Validaci�n fallida: Ciudad de origen o destino vac�a");
                ViewBag.Error = "Ciudad de origen y destino son requeridas.";
                return View(model);
            }
            if (string.IsNullOrWhiteSpace(model.Fecha))
            {
                Console.WriteLine("[ERROR] Validaci�n fallida: Fecha de viaje vac�a");
                ViewBag.Error = "Fecha de viaje es requerida.";
                return View(model);
            }
            if (!DateTime.TryParseExact(model.Fecha, "yyyy-MM-dd", null, System.Globalization.DateTimeStyles.None, out var fecha))
            {
                Console.WriteLine($"[ERROR] Validaci�n fallida: Formato de fecha inv�lido: {model.Fecha}");
                ViewBag.Error = "Formato de fecha inv�lido. Use yyyy-MM-dd.";
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
                HttpContext.Session.SetObject("SearchResults", vuelos); // Usar m�todo de extensi�n
                return View("ResultadoVuelos", vuelos);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepci�n en BuscarVuelos: {ex.Message}, InnerException: {ex.InnerException?.Message}");
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
                Console.WriteLine("[ERROR] Validaci�n fallida: Campos de cliente vac�os");
                return Json(new { success = false, message = "Todos los campos son requeridos." });
            }

            try
            {
                Console.WriteLine($"[DEBUG] Registrando cliente: Nombre={model.Nombre}, Email={model.Email}, Documento={model.DocumentoIdentidad}");
                var cliente = await _service.RegistrarCliente(model.Nombre, model.Email, model.DocumentoIdentidad);
                if (cliente == null)
                {
                    Console.WriteLine("[ERROR] Respuesta nula del servicio en RegistrarCliente");
                    return Json(new { success = false, message = "Error al registrar el cliente." });
                }
                Console.WriteLine($"[DEBUG] Cliente registrado: Id={cliente.IdCliente}, Nombre={cliente.Nombre}");
                return Json(new
                {
                    success = true,
                    idCliente = cliente.IdCliente,
                    nombre = cliente.Nombre,
                    documentoIdentidad = cliente.DocumentoIdentidad
                });
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepci�n en Register: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                return Json(new { success = false, message = $"Error al registrar el cliente: {ex.Message}" });
            }
        }

        [HttpGet]
        public async Task<IActionResult> Comprar()
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("IdCliente")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a Comprar sin autorizaci�n");
                return RedirectToAction("Login");
            }

            try
            {
                var clientes = await _service.ObtenerTodosClientes();
                var vuelos = HttpContext.Session.GetObject<List<Vuelo>>("SearchResults") ?? new List<Vuelo>();
                var model = new ComprarModel
                {
                    Clientes = clientes,
                    MetodosPago = new List<MetodoPagoModel>
                    {
                        new MetodoPagoModel { Id = 1, Nombre = "Tarjeta de Cr�dito" },
                        new MetodoPagoModel { Id = 2, Nombre = "Efectivo" },
                        new MetodoPagoModel { Id = 3, Nombre = "Tarjeta de D�bito" }
                    },
                    Vuelos = vuelos,
                    Detalles = new List<DetalleVueloModel>()
                };
                Console.WriteLine($"[DEBUG] Comprar GET: Clientes={clientes?.Count ?? 0}, Vuelos={vuelos?.Count ?? 0}"); // Corregido
                return View(model);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepci�n en Comprar (GET): {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al cargar el formulario de compra: {ex.Message}";
                return View(new ComprarModel());
            }
        }

        [HttpPost]
        public async Task<IActionResult> Comprar(ComprarModel model)
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[ERROR] Intento de compra sin autorizaci�n");
                return RedirectToAction("Login");
            }

            // Depuraci�n: Capturar datos del formulario
            var formData = Request.Form.ToDictionary(x => x.Key, x => x.Value.ToString());
            Console.WriteLine($"[DEBUG] Form Data Recibido:\n{JsonSerializer.Serialize(formData, new JsonSerializerOptions { WriteIndented = true })}");

            // Depuraci�n: Inspeccionar modelo recibido
            Console.WriteLine($"[DEBUG] Datos recibidos: NumeroFactura={model.NumeroFactura}, IdCliente={model.IdCliente}, IdMetodoPago={model.IdMetodoPago}, Descuento={model.Descuento}, Detalles={(model.Detalles != null ? model.Detalles.Count : 0)}");
            if (model.Detalles != null && model.Detalles.Any())
            {
                foreach (var detalle in model.Detalles)
                {
                    Console.WriteLine($"[DEBUG] Detalle: IdVuelo={detalle.IdVuelo}, Cantidad={detalle.Cantidad}");
                }
            }
            else
            {
                Console.WriteLine("[ERROR] model.Detalles es nulo o vac�o");
                var detailsInForm = formData.Where(k => k.Key.StartsWith("Detalles")).ToList();
                if (detailsInForm.Any())
                {
                    Console.WriteLine("[DEBUG] Detalles encontrados en Form Data:");
                    foreach (var detail in detailsInForm)
                    {
                        Console.WriteLine($"[DEBUG] {detail.Key} = {detail.Value}");
                    }
                }
                else
                {
                    Console.WriteLine("[ERROR] No se encontraron campos Detalles en Form Data");
                }
            }

            // Validaciones
            var errors = new List<string>();
            if (string.IsNullOrEmpty(model.NumeroFactura))
            {
                errors.Add("El n�mero de factura es requerido.");
                Console.WriteLine("[ERROR] Validaci�n fallida: N�mero de factura vac�o");
            }
            if (model.IdCliente <= 0)
            {
                errors.Add("ID de cliente inv�lido.");
                Console.WriteLine("[ERROR] Validaci�n fallida: ID de cliente inv�lido");
            }
            if (model.IdMetodoPago <= 0)
            {
                errors.Add("M�todo de pago inv�lido.");
                Console.WriteLine("[ERROR] Validaci�n fallida: M�todo de pago inv�lido");
            }
            if (model.Detalles == null || !model.Detalles.Any())
            {
                errors.Add("Debe incluir al menos un detalle de factura.");
                Console.WriteLine("[ERROR] Validaci�n fallida: Detalles de factura vac�os");
            }
            else
            {
                foreach (var detalle in model.Detalles)
                {
                    if (detalle.IdVuelo <= 0)
                    {
                        errors.Add($"ID de vuelo inv�lido: {detalle.IdVuelo}");
                        Console.WriteLine($"[ERROR] Validaci�n fallida: ID de vuelo inv�lido: {detalle.IdVuelo}");
                    }
                    if (detalle.Cantidad <= 0)
                    {
                        errors.Add($"Cantidad inv�lida: {detalle.Cantidad}");
                        Console.WriteLine($"[ERROR] Validaci�n fallida: Cantidad inv�lida: {detalle.Cantidad}");
                    }
                }
            }

            if (errors.Any())
            {
                ViewBag.Error = string.Join("; ", errors);
                ViewBag.DebugInfo = $"Detalles enviados: {(model.Detalles != null ? model.Detalles.Count : 0)}. Revise la consola del servidor para m�s detalles.";
                return await ReloadComprarView(model);
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
                Console.WriteLine($"[ERROR] Excepci�n en Comprar: {ex.Message}, InnerException: {ex.InnerException?.Message}, StackTrace: {ex.StackTrace}");
                ViewBag.Error = $"Error al crear la factura: {ex.Message}";
                ViewBag.DebugInfo = "Revise la consola del servidor para m�s detalles.";
                return await ReloadComprarView(model);
            }
        }

        [HttpGet]
        public async Task<IActionResult> Facturas(int idCliente)
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a Facturas sin autorizaci�n");
                return RedirectToAction("Login");
            }

            if (idCliente <= 0)
            {
                Console.WriteLine("[ERROR] Validaci�n fallida: ID de cliente inv�lido");
                ViewBag.Error = "ID de cliente inv�lido.";
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
                Console.WriteLine($"[ERROR] Excepci�n en Facturas: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al obtener facturas: {ex.Message}";
                return View(new List<Factura>());
            }
        }

        [HttpGet]
        public async Task<IActionResult> TodasFacturas()
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("IdCliente")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a TodasFacturas sin autorizaci�n");
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
                Console.WriteLine($"[ERROR] Excepci�n en TodasFacturas: {ex.Message}, InnerException: {ex.InnerException?.Message}");
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
            new MetodoPagoModel { Id = 1, Nombre = "Tarjeta de Cr�dito" },
            new MetodoPagoModel { Id = 2, Nombre = "Efectivo" },
            new MetodoPagoModel { Id = 3, Nombre = "Tarjeta de D�bito" }
        };
                model.Vuelos = HttpContext.Session.GetObject<List<Vuelo>>("SearchResults") ?? new List<Vuelo>();
                model.Detalles = model.Detalles ?? new List<DetalleVueloModel>();

                // Depuraci�n detallada
                var formData = Request.Form.ToDictionary(x => x.Key, x => x.Value.ToString());
                Console.WriteLine($"[DEBUG] ReloadComprarView: Clientes={model.Clientes?.Count ?? 0}, Vuelos={model.Vuelos?.Count ?? 0}, Detalles={model.Detalles?.Count ?? 0}");
                Console.WriteLine($"[DEBUG] Form Data: {JsonSerializer.Serialize(formData, new JsonSerializerOptions { WriteIndented = true })}");
                if (model.Detalles.Any())
                {
                    foreach (var detalle in model.Detalles)
                    {
                        Console.WriteLine($"[DEBUG] Detalle persistido: IdVuelo={detalle.IdVuelo}, Cantidad={detalle.Cantidad}");
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepci�n al recargar la vista Comprar: {ex.Message}, InnerException: {ex.InnerException?.Message}, StackTrace: {ex.StackTrace}");
                ViewBag.Error = $"Error al cargar datos: {ex.Message}";
            }
            return View("Comprar", model);
        }

        [HttpGet]
        public async Task<IActionResult> DetallesFactura(int idCliente, string numeroFactura)
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a DetallesFactura sin autorizaci�n");
                return RedirectToAction("Login");
            }
            if (idCliente <= 0 || string.IsNullOrWhiteSpace(numeroFactura))
            {
                Console.WriteLine("[ERROR] Validaci�n fallida: ID de cliente o n�mero de factura inv�lido");
                ViewBag.Error = "ID de cliente o n�mero de factura inv�lido.";
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
                Console.WriteLine($"[ERROR] Excepci�n en DetallesFactura: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al obtener detalles: {ex.Message}";
                return View(new Factura());
            }
        }

        [HttpGet]
        public async Task<IActionResult> SeleccionarClienteFacturas()
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a SeleccionarClienteFacturas sin autorizaci�n");
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
                Console.WriteLine($"[ERROR] Excepci�n en SeleccionarClienteFacturas (GET): {ex.Message}, InnerException: {ex.InnerException?.Message}");
                ViewBag.Error = $"Error al cargar la lista de clientes: {ex.Message}";
                return View(new SeleccionarClienteModel());
            }
        }

        [HttpPost]
        public async Task<IActionResult> SeleccionarClienteFacturas(SeleccionarClienteModel model)
        {
            if (string.IsNullOrEmpty(HttpContext.Session.GetString("NombreUsuario")))
            {
                Console.WriteLine("[DEBUG] Intento de acceder a SeleccionarClienteFacturas sin autorizaci�n");
                return RedirectToAction("Login");
            }

            if (model.IdCliente <= 0)
            {
                Console.WriteLine("[ERROR] Validaci�n fallida: ID de cliente inv�lido");
                model.Clientes = await _service.ObtenerTodosClientes() ?? new List<Cliente>();
                ViewBag.Error = "Debes seleccionar un cliente v�lido.";
                return View(model);
            }

            try
            {
                Console.WriteLine($"[DEBUG] Redirigiendo a Facturas para cliente: IdCliente={model.IdCliente}");
                return RedirectToAction("Facturas", new { idCliente = model.IdCliente });
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepci�n en SeleccionarClienteFacturas (POST): {ex.Message}, InnerException: {ex.InnerException?.Message}");
                model.Clientes = await _service.ObtenerTodosClientes() ?? new List<Cliente>();
                ViewBag.Error = $"Error al procesar la selecci�n: {ex.Message}";
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
        public List<Vuelo> Vuelos { get; set; } = new List<Vuelo>();
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

    // Clase de extensi�n para manejar objetos en la sesi�n
    public static class SessionExtensions
    {
        public static T GetObject<T>(this ISession session, string key)
        {
            var data = session.GetString(key);
            return data == null ? default(T) : JsonSerializer.Deserialize<T>(data);
        }

        public static void SetObject<T>(this ISession session, string key, T value)
        {
            session.SetString(key, JsonSerializer.Serialize(value));
        }
    }
}