using Microsoft.AspNetCore.Mvc;
using ServiceReference1;
using CONUNI_CLIWEB_GR10.Modelo;
using Microsoft.AspNetCore.Http;

namespace CONUNI_CLIWEB_GR10.Controlador
{
    public class ConversionController : Controller
    {
        private readonly ConversionServiceClient _client;

        public ConversionController()
        {
            _client = new ConversionServiceClient();
        }

        [HttpGet]
        public IActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Login(string usuario, string contrasena)
        {
            try
            {
                bool success = await _client.LoginAsync(usuario, contrasena);
                if (success)
                {
                    HttpContext.Session.SetString("IsLoggedIn", "true");
                    HttpContext.Session.SetString("Username", usuario);
                    return RedirectToAction("Index");
                }
                else
                {
                    ViewBag.Error = "Usuario o contraseña incorrectos.";
                    return View();
                }
            }
            catch (Exception ex)
            {
                ViewBag.Error = $"Error al iniciar sesión: {ex.Message}";
                return View();
            }
        }

        [HttpGet]
        public IActionResult Index()
        {
            if (HttpContext.Session.GetString("IsLoggedIn") != "true")
            {
                return RedirectToAction("Login");
            }

            ViewBag.IsLoggedIn = true;
            ViewBag.Username = HttpContext.Session.GetString("Username");
            return View(new ConversionModel());
        }

        [HttpPost]
        public IActionResult Logout()
        {
            HttpContext.Session.Clear();
            return RedirectToAction("Login");
        }

        [HttpPost]
        public async Task<IActionResult> Convert(ConversionModel model)
        {
            if (HttpContext.Session.GetString("IsLoggedIn") != "true")
            {
                return RedirectToAction("Login");
            }

            ViewBag.IsLoggedIn = true;
            ViewBag.Username = HttpContext.Session.GetString("Username");

            if (!ModelState.IsValid || model.InputValue < 0)
            {
                if (model.InputValue < 0)
                {
                    ViewBag.Error = "Por favor, ingrese un valor no negativo.";
                }
                else
                {
                    ViewBag.Error = "Datos de entrada no válidos.";
                }
                return View("Index", model);
            }

            try
            {
                double result;
                string inputUnit = "", outputUnit = "";
                if (string.IsNullOrEmpty(model.ConversionType))
                {
                    ViewBag.Error = "Por favor, seleccione un tipo de conversión.";
                    return View("Index", model);
                }

                switch (model.ConversionType)
                {
                    case "pulgadasACentimetros":
                        result = await _client.pulgadasACentimetrosAsync(model.InputValue);
                        inputUnit = "pulgadas";
                        outputUnit = "cm";
                        break;
                    case "centimetrosAPulgadas":
                        result = await _client.centimetrosAPulgadasAsync(model.InputValue);
                        inputUnit = "cm";
                        outputUnit = "pulgadas";
                        break;
                    case "metrosAPies":
                        result = await _client.metrosAPiesAsync(model.InputValue);
                        inputUnit = "m";
                        outputUnit = "pies";
                        break;
                    case "piesAMetros":
                        result = await _client.piesAMetrosAsync(model.InputValue);
                        inputUnit = "pies";
                        outputUnit = "m";
                        break;
                    case "metrosAYardas":
                        result = await _client.metrosAYardasAsync(model.InputValue);
                        inputUnit = "m";
                        outputUnit = "yardas";
                        break;
                    case "yardasAMetros":
                        result = await _client.yardasAMetrosAsync(model.InputValue);
                        inputUnit = "yardas";
                        outputUnit = "m";
                        break;
                    default:
                        ViewBag.Error = "Tipo de conversión no válido.";
                        return View("Index", model);
                }
                model.Result = result;
                ViewBag.Resultado = $"{model.InputValue:F2} {inputUnit} = {result:F2} {outputUnit}";
            }
            catch (Exception ex)
            {
                ViewBag.Error = $"Error al realizar la conversión: {ex.Message}";
            }

            return View("Index", model);
        }
    }
}