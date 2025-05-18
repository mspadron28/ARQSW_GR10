using CONUNI_CLIWEB_GR10.Models;
using Microsoft.AspNetCore.Mvc;
using System.Net.Http;
using System.Text.Json;
using System.Threading.Tasks;

namespace CONUNI_CLIWEB_GR10.Controllers
{
    public class ConversionController : Controller
    {
        private readonly HttpClient _client;
        public ConversionController(HttpClient client)
        {
            _client = client;
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
                    string url = string.Empty;

                    try
                    {
                        switch (model.Accion)
                        {
                            case "pulgadasACentimetros":
                                url = $"pulgadas-a-centimetros?pulgadas={valor}";
                                break;
                            case "centimetrosAPulgadas":
                                url = $"centimetros-a-pulgadas?centimetros={valor}";
                                break;
                            case "metrosAPies":
                                url = $"metros-a-pies?metros={valor}";
                                break;
                            case "piesAMetros":
                                url = $"pies-a-metros?pies={valor}";
                                break;
                            case "metrosAYardas":
                                url = $"metros-a-yardas?metros={valor}";
                                break;
                            case "yardasAMetros":
                                url = $"yardas-a-metros?yardas={valor}";
                                break;
                            default:
                                model.Error = "Acción no reconocida.";
                                return View(model);
                        }

                        HttpResponseMessage response = await _client.GetAsync(url);
                        response.EnsureSuccessStatusCode();
                        string responseBody = await response.Content.ReadAsStringAsync();
                        result = JsonSerializer.Deserialize<double>(responseBody);

                        switch (model.Accion)
                        {
                            case "pulgadasACentimetros":
                                model.Resultado = $"{valor:F2} in = {result:F2} cm.";
                                break;
                            case "centimetrosAPulgadas":
                                model.Resultado = $"{valor:F2} cm = {result:F2} in.";
                                break;
                            case "metrosAPies":
                                model.Resultado = $"{valor:F2} m = {result:F2} ft.";
                                break;
                            case "piesAMetros":
                                model.Resultado = $"{valor:F2} ft = {result:F2} m.";
                                break;
                            case "metrosAYardas":
                                model.Resultado = $"{valor:F2} m = {result:F2} yd.";
                                break;
                            case "yardasAMetros":
                                model.Resultado = $"{valor:F2} yd = {result:F2} m.";
                                break;
                        }
                    }
                    catch (HttpRequestException)
                    {
                        model.Error = "Error al realizar la conversión. Verifique que el servidor esté en ejecución.";
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