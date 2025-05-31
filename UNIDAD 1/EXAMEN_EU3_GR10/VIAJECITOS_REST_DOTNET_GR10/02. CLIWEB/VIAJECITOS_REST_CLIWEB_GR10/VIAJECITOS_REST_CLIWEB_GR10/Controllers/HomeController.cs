using Microsoft.AspNetCore.Mvc;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using VIAJECITOS_REST_CLIWEB_GR10.Models;
using ViajecitosRestServer.Models;

namespace VIAJECITOS_REST_CLIWEB_GR10.Controllers
{
    public class HomeController : Controller
    {
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl = "https://localhost:7013/api"; // URL base actualizada al puerto 7013

        public HomeController(IHttpClientFactory httpClientFactory)
        {
            _httpClient = httpClientFactory.CreateClient();
            _httpClient.BaseAddress = new Uri(_apiBaseUrl); // Configura la URL base
            Console.WriteLine($"API URL configurada: {_httpClient.BaseAddress}");
        }

        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> BuscarVuelos(string ciudadOrigen, string ciudadDestino, string fecha)
        {
            try
            {
                var url = $"{_apiBaseUrl}/vuelo-mas-caro?ciudadOrigen={Uri.EscapeDataString(ciudadOrigen)}&ciudadDestino={Uri.EscapeDataString(ciudadDestino)}&fecha={fecha}";
                var response = await _httpClient.GetAsync(url);
                if (response.IsSuccessStatusCode)
                {
                    var vuelo = await response.Content.ReadFromJsonAsync<Vuelo>();
                    return View("Resultado", vuelo);
                }
                ViewBag.Error = "No se encontró el vuelo";
            }
            catch (Exception ex)
            {
                ViewBag.Error = $"Error al buscar vuelos: {ex.Message}";
            }
            return View("Index");
        }

        public IActionResult Resultado(Vuelo vuelo)
        {
            if (vuelo == null)
            {
                ViewBag.Error = "No se proporcionó un vuelo válido.";
                return View(new Vuelo());
            }
            return View(vuelo);
        }

        [HttpPost]
        public async Task<IActionResult> Login(string nombreUsuario, string claveUsuario)
        {
            try
            {
                var url = $"{_apiBaseUrl}/login?nombreUsuario={Uri.EscapeDataString(nombreUsuario)}&claveUsuario={Uri.EscapeDataString(claveUsuario)}";
                var response = await _httpClient.PostAsync(url, null);
                var responseContent = await response.Content.ReadAsStringAsync();
                Console.WriteLine($"Respuesta del servidor: {response.StatusCode} - {responseContent}");
                if (response.IsSuccessStatusCode)
                {
                    var usuario = await response.Content.ReadFromJsonAsync<Usuario>();
                    HttpContext.Session.SetString("Usuario", usuario.IdCliente.ToString());
                    HttpContext.Session.SetString("NombreUsuario", usuario.NombreUsuario);
                    return RedirectToAction("Index");
                }
                ViewBag.Error = "Credenciales inválidas";
            }
            catch (Exception ex)
            {
                ViewBag.Error = $"Error al iniciar sesión: {ex.Message}";
            }
            return View("Login");
        }

        public IActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Register(string nombre, string email, string documentoIdentidad, string nombreUsuario, string claveUsuario)
        {
            try
            {
                // Registrar cliente primero
                var clienteUrl = $"{_apiBaseUrl}/cliente?nombre={Uri.EscapeDataString(nombre)}&email={Uri.EscapeDataString(email)}&documentoIdentidad={Uri.EscapeDataString(documentoIdentidad)}";
                var clienteResponse = await _httpClient.PostAsync(clienteUrl, null);
                if (!clienteResponse.IsSuccessStatusCode)
                {
                    ViewBag.Error = "Error al registrar el cliente";
                    return View("Register");
                }

                var cliente = await clienteResponse.Content.ReadFromJsonAsync<Cliente>();
                var idCliente = cliente.IdCliente;

                // Registrar usuario
                var usuarioUrl = $"{_apiBaseUrl}/usuario?idCliente={idCliente}&nombreUsuario={Uri.EscapeDataString(nombreUsuario)}&claveUsuario={Uri.EscapeDataString(claveUsuario)}";
                var usuarioResponse = await _httpClient.PostAsync(usuarioUrl, null);
                var usuarioResponseContent = await usuarioResponse.Content.ReadAsStringAsync();
                Console.WriteLine($"Respuesta del servidor (Register): {usuarioResponse.StatusCode} - {usuarioResponseContent}");
                if (usuarioResponse.IsSuccessStatusCode)
                {
                    var usuario = await usuarioResponse.Content.ReadFromJsonAsync<Usuario>();
                    HttpContext.Session.SetString("Usuario", usuario.IdCliente.ToString());
                    HttpContext.Session.SetString("NombreUsuario", usuario.NombreUsuario);
                    return RedirectToAction("Index");
                }
                ViewBag.Error = "Error al registrar el usuario";
            }
            catch (Exception ex)
            {
                ViewBag.Error = $"Error al registrarse: {ex.Message}";
            }
            return View("Register");
        }

        public IActionResult Register()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Comprar(int idVuelo, string vuelo)
        {
            var usuarioId = HttpContext.Session.GetString("Usuario");
            if (string.IsNullOrEmpty(usuarioId))
            {
                return RedirectToAction("Login");
            }

            try
            {
                var content = new StringContent(JsonSerializer.Serialize(new { idVuelo, idCliente = int.Parse(usuarioId) }), Encoding.UTF8, "application/json");
                var response = await _httpClient.PostAsync($"{_apiBaseUrl}/compra", content);
                var responseContent = await response.Content.ReadAsStringAsync();
                Console.WriteLine($"Respuesta del servidor (Comprar): {response.StatusCode} - {responseContent}");
                if (response.IsSuccessStatusCode)
                {
                    return RedirectToAction("Compras");
                }
                ViewBag.Error = "Error al realizar la compra";
            }
            catch (Exception ex)
            {
                ViewBag.Error = $"Error al procesar la compra: {ex.Message}";
            }

            // En caso de error, restauramos el vuelo para mostrar la vista Resultado
            var decodedVuelo = System.Net.WebUtility.UrlDecode(vuelo);
            var vueloObj = JsonSerializer.Deserialize<Vuelo>(decodedVuelo);
            return View("Resultado", vueloObj);
        }

        public async Task<IActionResult> Compras()
        {
            var usuarioId = HttpContext.Session.GetString("Usuario");
            if (string.IsNullOrEmpty(usuarioId))
            {
                return RedirectToAction("Login");
            }

            try
            {
                var url = $"{_apiBaseUrl}/compras/{usuarioId}";
                var response = await _httpClient.GetAsync(url);
                if (response.IsSuccessStatusCode)
                {
                    var compras = await response.Content.ReadFromJsonAsync<List<Compra>>();
                    return View(compras);
                }
                ViewBag.Error = "No se pudieron cargar las compras";
            }
            catch (Exception ex)
            {
                ViewBag.Error = $"Error al cargar las compras: {ex.Message}";
            }
            return View(new List<Compra>());
        }
    }
}