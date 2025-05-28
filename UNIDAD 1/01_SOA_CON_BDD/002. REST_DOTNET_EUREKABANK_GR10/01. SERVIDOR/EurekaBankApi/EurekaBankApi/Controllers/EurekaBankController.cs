using EurekaBankApi.Models;
using EurekaBankApi.Services;
using Microsoft.AspNetCore.Mvc;

namespace EurekaBankApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EurekaBankController : ControllerBase
    {
        private readonly IEurekaBankService _service;

        public EurekaBankController(IEurekaBankService service)
        {
            _service = service;
        }

        [HttpGet("movimientos/{cuenta}")]
        public ActionResult<List<Movimiento>> LeerMovimientos(string cuenta)
        {
            try
            {
                var movimientos = _service.LeerMovimientos(cuenta);
                return Ok(movimientos);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost("deposito")]
        public ActionResult<int> RegistrarDeposito([FromBody] DepositoRequest request)
        {
            try
            {
                var result = _service.RegistrarDeposito(request.Cuenta, request.Importe, request.CodEmp);
                return Ok(result);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost("retiro")]
        public ActionResult<int> RegistrarRetiro([FromBody] RetiroRequest request)
        {
            try
            {
                var result = _service.RegistrarRetiro(request.Cuenta, request.Importe, request.CodEmp);
                return Ok(result);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost("transferencia")]
        public ActionResult<int> RealizarTransferencia([FromBody] TransferenciaRequest request)
        {
            try
            {
                var result = _service.RealizarTransferencia(request.CuentaOrigen, request.CuentaDestino, request.Importe, request.CodEmp);
                return Ok(result);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("saldo/{cuenta}")]
        public ActionResult<double> VerificarSaldo(string cuenta)
        {
            try
            {
                var saldo = _service.VerificarSaldo(cuenta);
                return Ok(saldo);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("costo-movimiento/{cuenta}")]
        public ActionResult<double> ObtenerCostoMovimiento(string cuenta)
        {
            try
            {
                var costo = _service.ObtenerCostoMovimiento(cuenta);
                return Ok(costo);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost("login")]
        public ActionResult<Usuario> IniciarSesion([FromBody] LoginRequest request)
        {
            try
            {
                var usuario = _service.IniciarSesion(request.Username, request.Clave);
                if (usuario == null)
                    return Unauthorized("Credenciales inválidas");
                return Ok(usuario);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }

    public class DepositoRequest
    {
        public string Cuenta { get; set; }
        public double Importe { get; set; }
        public string CodEmp { get; set; }
    }

    public class RetiroRequest
    {
        public string Cuenta { get; set; }
        public double Importe { get; set; }
        public string CodEmp { get; set; }
    }

    public class TransferenciaRequest
    {
        public string CuentaOrigen { get; set; }
        public string CuentaDestino { get; set; }
        public double Importe { get; set; }
        public string CodEmp { get; set; }
    }

    public class LoginRequest
    {
        public string Username { get; set; }
        public string Clave { get; set; }
    }
}
