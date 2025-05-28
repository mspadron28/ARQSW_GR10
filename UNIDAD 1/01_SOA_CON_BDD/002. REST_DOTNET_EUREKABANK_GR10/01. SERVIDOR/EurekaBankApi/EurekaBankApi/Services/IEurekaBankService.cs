using EurekaBankApi.Models;
using System.Collections.Generic;

namespace EurekaBankApi.Services
{
    public interface IEurekaBankService
    {
        List<Movimiento> LeerMovimientos(string cuenta);
        int RegistrarDeposito(string cuenta, double importe, string codEmp);
        int RegistrarRetiro(string cuenta, double importe, string codEmp);
        int RealizarTransferencia(string cuentaOrigen, string cuentaDestino, double importe, string codEmp);
        double VerificarSaldo(string cuenta);
        double ObtenerCostoMovimiento(string cuenta);
        Usuario IniciarSesion(string username, string clave);
    }
}