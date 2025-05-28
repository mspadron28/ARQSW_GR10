using EurekaBankConsoleClient;
using EurekaServiceReference;
using System.Collections.Generic;

namespace EurekaBankConsoleClient.Servicio
{
    public class EurekaService
    {
        private readonly EurekaBankServiceClient port;

        public EurekaService()
        {
            port = new EurekaBankServiceClient();
        }

        public List<Movimiento> TraerMovimientos(string cuenta)
        {
            return new List<Movimiento>(port.LeerMovimientos(cuenta));
        }

        public int RegistrarDeposito(string cuenta, double importe, string codEmp)
        {
            return port.RegistrarDeposito(cuenta, importe, codEmp);
        }

        public int RegistrarRetiro(string cuenta, double importe, string codEmp)
        {
            return port.RegistrarRetiro(cuenta, importe, codEmp);
        }

        public int RealizarTransferencia(string cuentaOrigen, string cuentaDestino, double importe, string codEmp)
        {
            return port.RealizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
        }

        public double VerificarSaldo(string cuenta)
        {
            return port.VerificarSaldo(cuenta);
        }

        public double ObtenerCostoMovimiento(string cuenta)
        {
            return port.ObtenerCostoMovimiento(cuenta);
        }

        public Usuario IniciarSesion(string usuario, string clave)
        {
            return port.IniciarSesion(usuario, clave);
        }
    }
}