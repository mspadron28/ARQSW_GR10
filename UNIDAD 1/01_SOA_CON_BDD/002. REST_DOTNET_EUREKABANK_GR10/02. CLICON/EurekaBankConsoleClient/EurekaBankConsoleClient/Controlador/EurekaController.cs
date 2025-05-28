using EurekaBankConsoleClient.Models;
using EurekaBankConsoleClient.Servicio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EurekaBankConsoleClient.Controlador
{
    public class EurekaController
    {
        private readonly EurekaService _service;

        public EurekaController()
        {
            _service = new EurekaService();
        }

        public async Task<List<Movimiento>> ConsultarMovimientos(string cuenta)
        {
            if (string.IsNullOrWhiteSpace(cuenta))
            {
                throw new Exception("El número de cuenta es requerido.");
            }
            List<Movimiento> movimientos = await _service.TraerMovimientos(cuenta);
            if (movimientos == null || movimientos.Count == 0)
            {
                throw new Exception("No se encontraron movimientos para la cuenta.");
            }
            return movimientos;
        }

        public async Task<bool> RegistrarDeposito(string cuenta, double importe, string codEmp)
        {
            if (string.IsNullOrWhiteSpace(cuenta))
            {
                throw new Exception("El número de cuenta es requerido.");
            }
            if (importe <= 0)
            {
                throw new Exception("El importe debe ser mayor que 0.");
            }
            if (string.IsNullOrWhiteSpace(codEmp))
            {
                throw new Exception("El código de empleado es requerido.");
            }
            return await _service.RegistrarDeposito(cuenta, importe, codEmp) == 1;
        }

        public async Task<bool> RegistrarRetiro(string cuenta, double importe, string codEmp)
        {
            if (string.IsNullOrWhiteSpace(cuenta))
            {
                throw new Exception("El número de cuenta es requerido.");
            }
            if (importe <= 0)
            {
                throw new Exception("El importe debe ser mayor que 0.");
            }
            if (string.IsNullOrWhiteSpace(codEmp))
            {
                throw new Exception("El código de empleado es requerido.");
            }
            double costo = await _service.ObtenerCostoMovimiento(cuenta);
            if (costo == -1.0)
            {
                throw new Exception("Error al obtener el costo del movimiento.");
            }
            return await _service.RegistrarRetiro(cuenta, importe, codEmp) == 1;
        }

        public async Task<bool> RealizarTransferencia(string cuentaOrigen, string cuentaDestino, double importe, string codEmp)
        {
            if (string.IsNullOrWhiteSpace(cuentaOrigen) || string.IsNullOrWhiteSpace(cuentaDestino))
            {
                throw new Exception("Los números de cuenta son requeridos.");
            }
            if (importe <= 0)
            {
                throw new Exception("El importe debe ser mayor que 0.");
            }
            if (string.IsNullOrWhiteSpace(codEmp))
            {
                throw new Exception("El código de empleado es requerido.");
            }
            double costo = await _service.ObtenerCostoMovimiento(cuentaOrigen);
            if (costo == -1.0)
            {
                throw new Exception("Error al obtener el costo del movimiento.");
            }
            return await _service.RealizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp) == 1;
        }

        public async Task<double> VerificarSaldo(string cuenta)
        {
            if (string.IsNullOrWhiteSpace(cuenta))
            {
                throw new Exception("El número de cuenta es requerido.");
            }
            double saldo = await _service.VerificarSaldo(cuenta);
            if (saldo == -1.0)
            {
                throw new Exception("Error al verificar el saldo.");
            }
            return saldo;
        }

        public async Task<double> ObtenerCostoMovimiento(string cuenta)
        {
            if (string.IsNullOrWhiteSpace(cuenta))
            {
                throw new Exception("El número de cuenta es requerido.");
            }
            double costo = await _service.ObtenerCostoMovimiento(cuenta);
            if (costo == -1.0)
            {
                throw new Exception("Error al obtener el costo del movimiento.");
            }
            return costo;
        }

        public async Task<bool> IniciarSesion(string usuario, string clave)
        {
            if (string.IsNullOrWhiteSpace(usuario) || string.IsNullOrWhiteSpace(clave))
            {
                throw new Exception("Usuario y contraseña son requeridos.");
            }
            var user = await _service.IniciarSesion(usuario, clave);
            return user != null;
        }
    }
}
