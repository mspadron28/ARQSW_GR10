using EurekaBankConsoleClient.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Text;
using System.Threading.Tasks;

namespace EurekaBankConsoleClient.Servicio
{
    public class EurekaService
    {
        private readonly HttpClient _client;
        private readonly string _baseUrl = "https://localhost:7053/api/EurekaBank";

        public EurekaService()
        {
            _client = new HttpClient();
        }

        public async Task<List<Movimiento>> TraerMovimientos(string cuenta)
        {
            var response = await _client.GetAsync($"{_baseUrl}/movimientos/{cuenta}");
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadFromJsonAsync<List<Movimiento>>();
        }

        public async Task<int> RegistrarDeposito(string cuenta, double importe, string codEmp)
        {
            var request = new { Cuenta = cuenta, Importe = importe, CodEmp = codEmp };
            var response = await _client.PostAsJsonAsync($"{_baseUrl}/deposito", request);
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadFromJsonAsync<int>();
        }

        public async Task<int> RegistrarRetiro(string cuenta, double importe, string codEmp)
        {
            var request = new { Cuenta = cuenta, Importe = importe, CodEmp = codEmp };
            var response = await _client.PostAsJsonAsync($"{_baseUrl}/retiro", request);
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadFromJsonAsync<int>();
        }

        public async Task<int> RealizarTransferencia(string cuentaOrigen, string cuentaDestino, double importe, string codEmp)
        {
            var request = new { CuentaOrigen = cuentaOrigen, CuentaDestino = cuentaDestino, Importe = importe, CodEmp = codEmp };
            var response = await _client.PostAsJsonAsync($"{_baseUrl}/transferencia", request);
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadFromJsonAsync<int>();
        }

        public async Task<double> VerificarSaldo(string cuenta)
        {
            var response = await _client.GetAsync($"{_baseUrl}/saldo/{cuenta}");
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadFromJsonAsync<double>();
        }

        public async Task<double> ObtenerCostoMovimiento(string cuenta)
        {
            var response = await _client.GetAsync($"{_baseUrl}/costo-movimiento/{cuenta}");
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadFromJsonAsync<double>();
        }

        public async Task<Usuario> IniciarSesion(string usuario, string clave)
        {
            var request = new { Username = usuario, Clave = clave };
            var response = await _client.PostAsJsonAsync($"{_baseUrl}/login", request);
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadFromJsonAsync<Usuario>();
        }
    }
}
