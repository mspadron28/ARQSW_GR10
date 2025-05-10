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
        public async Task<IActionResult> Index(ConversionModel model)
        {
            if (model.Valor >= 0)
            {
                double result = 0;
                switch (model.Accion)
                {
                    case "pulgadasACentimetros":
                        result = await _client.pulgadasACentimetrosAsync(model.Valor);
                        model.Resultado = $"{model.Valor} in es equivalente a {result:F2} cm.";
                        break;
                    case "centimetrosAPulgadas":
                        result = await _client.centimetrosAPulgadasAsync(model.Valor);
                        model.Resultado = $"{model.Valor} cm es equivalente a {result:F2} in.";
                        break;
                    case "metrosAPies":
                        result = await _client.metrosAPiesAsync(model.Valor);
                        model.Resultado = $"{model.Valor} m es equivalente a {result:F2} ft.";
                        break;
                    case "piesAMetros":
                        result = await _client.piesAMetrosAsync(model.Valor);
                        model.Resultado = $"{model.Valor} ft es equivalente a {result:F2} m.";
                        break;
                    case "metrosAYardas":
                        result = await _client.metrosAYardasAsync(model.Valor);
                        model.Resultado = $"{model.Valor} m es equivalente a {result:F2} yd.";
                        break;
                    case "yardasAMetros":
                        result = await _client.yardasAMetrosAsync(model.Valor);
                        model.Resultado = $"{model.Valor} yd es equivalente a {result:F2} m.";
                        break;
                }
            }
            else
            {
                model.Error = "Por favor, ingrese un valor no negativo.";
            }
            return View(model);
        }
    }
}
