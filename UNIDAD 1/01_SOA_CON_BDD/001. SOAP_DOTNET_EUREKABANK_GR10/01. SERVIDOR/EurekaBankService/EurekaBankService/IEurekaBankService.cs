using EurekaBankService.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace EurekaBankService
{
    [ServiceContract]
    public interface IEurekaBankService
    {
        [OperationContract]
        List<Movimiento> LeerMovimientos(string cuenta);

        [OperationContract]
        int RegistrarDeposito(string cuenta, double importe, string codEmp);

        [OperationContract]
        int RegistrarRetiro(string cuenta, double importe, string codEmp);

        [OperationContract]
        int RealizarTransferencia(string cuentaOrigen, string cuentaDestino, double importe, string codEmp);

        [OperationContract]
        double VerificarSaldo(string cuenta);

        [OperationContract]
        double ObtenerCostoMovimiento(string cuenta);

        [OperationContract]
        Usuario IniciarSesion(string username, string clave);
    }
}