using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;

namespace VIAJECITOS_REST_CLIESC_GR10.Servicio
{
    public class ViajecitosService
    {
        private readonly HttpClient _httpClient;
        private readonly string _baseUrl = "http://192.168.100.11:5158/api"; // Ajusta según la URL de tu servicio REST

        public ViajecitosService()
        {
            _httpClient = new HttpClient();
        }

        public async Task<List<Vuelo>> BuscarVuelos(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            var url = $"{_baseUrl}/vuelos?ciudadOrigen={Uri.EscapeDataString(ciudadOrigen)}&ciudadDestino={Uri.EscapeDataString(ciudadDestino)}&fecha={fecha:yyyy-MM-dd}";
            var response = await _httpClient.GetAsync(url);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<List<Vuelo>>(content, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
        }

        public async Task<Vuelo> ObtenerVueloMasCaro(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            var url = $"{_baseUrl}/vuelo-mas-caro?ciudadOrigen={Uri.EscapeDataString(ciudadOrigen)}&ciudadDestino={Uri.EscapeDataString(ciudadDestino)}&fecha={fecha:yyyy-MM-dd}";
            var response = await _httpClient.GetAsync(url);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<Vuelo>(content, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
        }

        public async Task<int> RegistrarCompra(int idVuelo, int idCliente)
        {
            var url = $"{_baseUrl}/compra";
            var requestBody = new { IdVuelo = idVuelo, IdCliente = idCliente };
            var content = new StringContent(JsonSerializer.Serialize(requestBody), Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync(url, content);
            response.EnsureSuccessStatusCode();
            return 1; // El servicio REST no devuelve el ID de la compra, pero devolvemos 1 para indicar éxito
        }

        public async Task<List<Compra>> ObtenerComprasCliente(int idCliente)
        {
            var url = $"{_baseUrl}/compras/{idCliente}";
            var response = await _httpClient.GetAsync(url);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<List<Compra>>(content, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
        }

        public async Task<Cliente> RegistrarCliente(string nombre, string email, string documentoIdentidad)
        {
            var url = $"{_baseUrl}/cliente?nombre={Uri.EscapeDataString(nombre)}&email={Uri.EscapeDataString(email)}&documentoIdentidad={Uri.EscapeDataString(documentoIdentidad)}";
            var response = await _httpClient.PostAsync(url, null);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<Cliente>(content, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
        }

        public async Task<Usuario> RegistrarUsuario(int idCliente, string nombreUsuario, string claveUsuario)
        {
            var url = $"{_baseUrl}/usuario?idCliente={idCliente}&nombreUsuario={Uri.EscapeDataString(nombreUsuario)}&claveUsuario={Uri.EscapeDataString(claveUsuario)}";
            var response = await _httpClient.PostAsync(url, null);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<Usuario>(content, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
        }

        public async Task<Usuario> IniciarSesion(string nombreUsuario, string claveUsuario)
        {
            var url = $"{_baseUrl}/login?nombreUsuario={Uri.EscapeDataString(nombreUsuario)}&claveUsuario={Uri.EscapeDataString(claveUsuario)}";
            var response = await _httpClient.PostAsync(url, null);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<Usuario>(content, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
        }
    }
}