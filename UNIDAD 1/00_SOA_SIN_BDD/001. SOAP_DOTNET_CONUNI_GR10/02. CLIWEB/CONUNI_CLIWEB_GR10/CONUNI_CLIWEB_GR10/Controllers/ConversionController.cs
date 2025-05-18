using CONUNI_CLIWEB_GR10.Models;
using ConversionServiceReference;
using Microsoft.AspNetCore.Mvc;

namespace CONUNI_CLIWEB_GR10.Controllers
{
    public class ConversionController : Controller
    {
        private readonly ConversionServiceClient _client;

        public ConversionController()
        {
            _client = new ConversionServiceClient();
        }

        public IActionResult Index()
        {
            return View(new ConversionModel());
        }

        [HttpPost]
        public async Task<IActionResult> Index(IFormCollection form)
        {
            var model = new ConversionModel();
            model.Accion = form["Accion"].ToString();

            // Extract the string from StringValues
            string valorInput = form["Valor"].ToString()?.Trim();
            double valor;

            // Validate that the value is not null or empty
            if (string.IsNullOrEmpty(valorInput))
            {
                model.Error = "Por favor, ingrese un valor.";
                return View(model);
            }

            // Normalize: replace comma with period
            valorInput = valorInput.Replace(",", ".");

            // Try to parse the normalized value
            if (double.TryParse(valorInput, System.Globalization.NumberStyles.Any, System.Globalization.CultureInfo.InvariantCulture, out valor))
            {
                if (valor >= 0)
                {
                    model.Valor = valor;
                    double result = 0;

                    switch (model.Accion)
                    {
                        case "pulgadasACentimetros":
                            result = await _client.pulgadasACentimetrosAsync(valor);
                            model.Resultado = $"{valor:F2} in = {result:F2} cm.";
                            break;
                        case "centimetrosAPulgadas":
                            result = await _client.centimetrosAPulgadasAsync(valor);
                            model.Resultado = $"{valor:F2} cm = {result:F2} in.";
                            break;
                        case "metrosAPies":
                            result = await _client.metrosAPiesAsync(valor);
                            model.Resultado = $"{valor:F2} m = {result:F2} ft.";
                            break;
                        case "piesAMetros":
                            result = await _client.piesAMetrosAsync(valor);
                            model.Resultado = $"{valor:F2} ft = {result:F2} m.";
                            break;
                        case "metrosAYardas":
                            result = await _client.metrosAYardasAsync(valor);
                            model.Resultado = $"{valor:F2} m = {result:F2} yd.";
                            break;
                        case "yardasAMetros":
                            result = await _client.yardasAMetrosAsync(valor);
                            model.Resultado = $"{valor:F2} yd = {result:F2} m.";
                            break;
                        default:
                            model.Error = "Acción no reconocida.";
                            return View(model);
                    }
                }
                else
                {
                    model.Error = "Por favor, ingrese un valor no negativo.";
                }
            }
            else
            {
                model.Error = "Ingrese un valor numérico válido (use punto o coma como separador decimal).";
            }

            return View(model);
        }
    }
}