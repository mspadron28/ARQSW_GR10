using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using VIAJECITOS_REST_CLICON_GR10.Modelos;

namespace VIAJECITOS_REST_CLICON_GR10.Servicio
{
    public class ViajecitosService
    {
        private static readonly HttpClient client = new HttpClient();
        private const string BaseUrl = "http://192.168.100.11:5158/api/";

        static ViajecitosService()
        {
            client.BaseAddress = new Uri(BaseUrl);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
        }

        public async Task<List<Vuelo>> BuscarVuelos(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            var url = $"vuelos?ciudadOrigen={Uri.EscapeDataString(ciudadOrigen)}&ciudadDestino={Uri.EscapeDataString(ciudadDestino)}&fecha={fecha:yyyy-MM-ddTHH:mm:ss}";
            var response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<Vuelo>>(content) ?? new List<Vuelo>();
        }

        public async Task<Vuelo> ObtenerVueloMasCaro(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            var url = $"vuelo-mas-caro?ciudadOrigen={Uri.EscapeDataString(ciudadOrigen)}&ciudadDestino={Uri.EscapeDataString(ciudadDestino)}&fecha={fecha:yyyy-MM-ddTHH:mm:ss}";
            var response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Vuelo>(content);
        }

        public async Task RegistrarCompra(int idVuelo, int idCliente)
        {
            var url = "compra";
            var data = new { idVuelo, idCliente };
            var json = JsonConvert.SerializeObject(data);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url, content);
            response.EnsureSuccessStatusCode();
        }

        public async Task<Cliente> RegistrarCliente(string nombre, string email, string documentoIdentidad)
        {
            var url = "cliente";
            var data = new { nombre, email, documentoIdentidad };
            var json = JsonConvert.SerializeObject(data);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url, content);
            response.EnsureSuccessStatusCode();
            var contentString = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Cliente>(contentString);
        }

        public async Task<Usuario> RegistrarUsuario(int idCliente, string nombreUsuario, string claveUsuario)
        {
            var url = "usuario";
            var data = new { idCliente, nombreUsuario, claveUsuario };
            var json = JsonConvert.SerializeObject(data);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url, content);
            response.EnsureSuccessStatusCode();
            var contentString = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Usuario>(contentString);
        }

        public async Task<Usuario> IniciarSesion(string nombreUsuario, string claveUsuario)
        {
            var url = $"login?nombreUsuario={Uri.EscapeDataString(nombreUsuario)}&claveUsuario={Uri.EscapeDataString(claveUsuario)}";
            var response = await client.PostAsync(url, null);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Usuario>(content);
        }

        public async Task<List<Compra>> ObtenerComprasCliente(int idCliente)
        {
            var url = $"compras/{idCliente}";
            var response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<Compra>>(content) ?? new List<Compra>();
        }
    }
}