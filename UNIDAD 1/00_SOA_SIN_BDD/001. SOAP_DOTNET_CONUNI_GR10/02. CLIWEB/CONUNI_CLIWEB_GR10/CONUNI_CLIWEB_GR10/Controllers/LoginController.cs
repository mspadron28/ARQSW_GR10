using CONUNI_CLIWEB_GR10.Models;
using ConversionServiceReference;
using Microsoft.AspNetCore.Mvc;

namespace CONUNI_CLIWEB_GR10.Controllers
{
    public class LoginController : Controller
    {
        private readonly ConversionServiceClient _client;

        public LoginController()
        {
            _client = new ConversionServiceClient();
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
                var success = await _client.LoginAsync(model.Usuario, model.Contrasena);
                if (success)
                {
                    return RedirectToAction("Index", "Conversion");
                }
                ViewBag.Error = "Usuario o contraseña incorrectos.";
            }
            else
            {
                ViewBag.Error = "Por favor, complete todos los campos.";
            }
            return View(model);
        }
    }
}
