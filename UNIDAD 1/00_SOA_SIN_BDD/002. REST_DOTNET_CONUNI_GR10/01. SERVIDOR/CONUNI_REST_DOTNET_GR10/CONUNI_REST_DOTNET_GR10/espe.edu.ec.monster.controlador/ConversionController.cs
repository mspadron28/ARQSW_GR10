using Microsoft.AspNetCore.Mvc;

namespace espe.edu.ec.monster.controlador
{
    [Route("api/[controller]")]
    [ApiController]
    public class ConversionController : ControllerBase
    {
        [HttpGet("pulgadas-a-centimetros")] public IActionResult PulgadasACentimetros([FromQuery] double pulgadas) { double result = pulgadas * 2.54; return Ok(result); }
        [HttpGet("centimetros-a-pulgadas")]
        public IActionResult CentimetrosAPulgadas([FromQuery] double centimetros)
        {
            double result = centimetros / 2.54;
            return Ok(result);
        }

        [HttpGet("metros-a-pies")]
        public IActionResult MetrosAPies([FromQuery] double metros)
        {
            double result = metros * 3.28084;
            return Ok(result);
        }

        [HttpGet("pies-a-metros")]
        public IActionResult PiesAMetros([FromQuery] double pies)
        {
            double result = pies / 3.28084;
            return Ok(result);
        }

        [HttpGet("metros-a-yardas")]
        public IActionResult MetrosAYardas([FromQuery] double metros)
        {
            double result = metros * 1.09361;
            return Ok(result);
        }

        [HttpGet("yardas-a-metros")]
        public IActionResult YardasAMetros([FromQuery] double yardas)
        {
            double result = yardas / 1.09361;
            return Ok(result);
        }

        [HttpGet("login")]
        public IActionResult Login([FromQuery] string usuario, [FromQuery] string contraseña)
        {
            bool result = usuario.Equals("MONSTER") && contraseña.Equals("MONSTER9");
            return Ok(result);
        }
    }
}