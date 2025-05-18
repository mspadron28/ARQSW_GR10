using CONUNI_CLIWEB_GR10.Models;
using Microsoft.AspNetCore.Mvc;
using System.Net.Http;
using System.Text.Json;
using System.Threading.Tasks;

namespace CONUNI_CLIWEB_GR10.Controllers
{
    public class LoginController : Controller
    {
        private readonly HttpClient _client;
        public LoginController(HttpClient client)
        {
            _client = client;
        }

        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Index(LoginModel model)
        {
            if (!string.IsNullOrWhiteSpace(model.Usuario) && !string.IsNullOrWhiteSpace(model.Contrasena))
            {
                try
                {
                    string url = $"login?usuario={Uri.EscapeDataString(model.Usuario)}&contraseña={Uri.EscapeDataString(model.Contrasena)}";
                    HttpResponseMessage response = await _client.GetAsync(url);
                    response.EnsureSuccessStatusCode();
                    string responseBody = await response.Content.ReadAsStringAsync();
                    bool success = JsonSerializer.Deserialize<bool>(responseBody);

                    if (success)
                    {
                        return RedirectToAction("Index", "Conversion");
                    }
                    ViewBag.Error = "Usuario o contraseña incorrectos.";
                }
                catch (HttpRequestException)
                {
                    ViewBag.Error = "Error al intentar iniciar sesión. Verifique que el servidor esté en ejecución.";
                }
            }
            else
            {
                ViewBag.Error = "Por favor, complete todos los campos.";
            }
            return View(model);
        }
    }
}