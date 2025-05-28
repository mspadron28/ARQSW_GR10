using EurekaBankConsoleClient.Servicio;
using EurekaServiceReference;
using System;
using System.Collections.Generic;

namespace EurekaBankConsoleClient.Controlador
{
    public class EurekaController
    {
        private readonly EurekaService service;

        public EurekaController()
        {
            this.service = new EurekaService();
        }

        public List<Movimiento> ConsultarMovimientos(string cuenta)
        {
            if (string.IsNullOrWhiteSpace(cuenta))
            {
                throw new Exception("El número de cuenta es requerido.");
            }
            List<Movimiento> movimientos = service.TraerMovimientos(cuenta);
            if (movimientos.Count == 0)
            {
                throw new Exception("No se encontraron movimientos para la cuenta.");
            }
            return movimientos;
        }

        public bool RegistrarDeposito(string cuenta, double importe, string codEmp)
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
            return service.RegistrarDeposito(cuenta, importe, codEmp) == 1;
        }

        public bool RegistrarRetiro(string cuenta, double importe, string codEmp)
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
            double costo = service.ObtenerCostoMovimiento(cuenta);
            if (costo == -1.0)
            {
                throw new Exception("Error al obtener el costo del movimiento.");
            }
            return service.RegistrarRetiro(cuenta, importe, codEmp) == 1;
        }

        public bool RealizarTransferencia(string cuentaOrigen, string cuentaDestino, double importe, string codEmp)
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
            double costo = service.ObtenerCostoMovimiento(cuentaOrigen);
            if (costo == -1.0)
            {
                throw new Exception("Error al obtener el costo del movimiento.");
            }
            return service.RealizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp) == 1;
        }

        public double VerificarSaldo(string cuenta)
        {
            if (string.IsNullOrWhiteSpace(cuenta))
            {
                throw new Exception("El número de cuenta es requerido.");
            }
            double saldo = service.VerificarSaldo(cuenta);
            if (saldo == -1.0)
            {
                throw new Exception("Error al verificar el saldo.");
            }
            return saldo;
        }

        public double ObtenerCostoMovimiento(string cuenta)
        {
            if (string.IsNullOrWhiteSpace(cuenta))
            {
                throw new Exception("El número de cuenta es requerido.");
            }
            double costo = service.ObtenerCostoMovimiento(cuenta);
            if (costo == -1.0)
            {
                throw new Exception("Error al obtener el costo del movimiento.");
            }
            return costo;
        }

        public bool IniciarSesion(string usuario, string clave)
        {
            if (string.IsNullOrWhiteSpace(usuario) || string.IsNullOrWhiteSpace(clave))
            {
                throw new Exception("Usuario y contraseña son requeridos.");
            }
            var user = service.IniciarSesion(usuario, clave);
            return user != null;
        }
    }
}