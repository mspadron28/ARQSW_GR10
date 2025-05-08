using Microsoft.AspNetCore.Mvc;
using ServiceReference1;
using CLIWEB.Modelo;
using Microsoft.AspNetCore.Http;

namespace CLIWEB.Controlador
{
    public class ConversionController : Controller
    {
        private readonly ConversionServiceClient _client;

        public ConversionController()
        {
            _client = new ConversionServiceClient();
        }

        [HttpGet]
        public IActionResult Index()
        {
            ViewBag.IsLoggedIn = HttpContext.Session.GetString("IsLoggedIn") == "true";
            if (ViewBag.IsLoggedIn)
            {
                ViewBag.Username = HttpContext.Session.GetString("Username");
            }
            return View(new ConversionModel());
        }

        [HttpPost]
        public IActionResult Index(string username, string password, string action)
        {
            ViewBag.IsLoggedIn = HttpContext.Session.GetString("IsLoggedIn") == "true";
            if (ViewBag.IsLoggedIn)
            {
                ViewBag.Username = HttpContext.Session.GetString("Username");
            }

            if (action == "login" && username == "MasterMonster" && password == "Monster9")
            {
                HttpContext.Session.SetString("IsLoggedIn", "true");
                HttpContext.Session.SetString("Username", username);
                ViewBag.IsLoggedIn = true;
                ViewBag.Username = username;
            }
            else if (action == "logout")
            {
                HttpContext.Session.Clear();
                ViewBag.IsLoggedIn = false;
            }
            return View(new ConversionModel());
        }

        [HttpPost]
        public async Task<IActionResult> Convert(ConversionModel model)
        {
            ViewBag.IsLoggedIn = HttpContext.Session.GetString("IsLoggedIn") == "true";
            if (ViewBag.IsLoggedIn)
            {
                ViewBag.Username = HttpContext.Session.GetString("Username");
            }
            else
            {
                return RedirectToAction("Index");
            }

            if (!ModelState.IsValid)
            {
                return View("Index", model);
            }

            try
            {
                switch (model.ConversionType)
                {
                    case "CentimetersToFeet":
                        model.Result = await _client.CentimetersToFeetAsync(model.InputValue);
                        break;
                    case "FeetToCentimeters":
                        model.Result = await _client.FeetToCentimetersAsync(model.InputValue);
                        break;
                    case "MetersToYards":
                        model.Result = await _client.MetersToYardsAsync(model.InputValue);
                        break;
                    case "YardsToMeters":
                        model.Result = await _client.YardsToMetersAsync(model.InputValue);
                        break;
                    case "InchesToCentimeters":
                        model.Result = await _client.InchesToCentimetersAsync(model.InputValue);
                        break;
                    case "CentimetersToInches":
                        model.Result = await _client.CentimetersToInchesAsync(model.InputValue);
                        break;
                    default:
                        ModelState.AddModelError("", "Tipo de conversión no válido.");
                        return View("Index", model);
                }
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", $"Error: {ex.Message}");
            }

            return View("Index", model);
        }
    }
}