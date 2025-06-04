using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using VIAJECITOS_REST_CLIESC_GR10.Modelos;

namespace VIAJECITOS_REST_CLIESC_GR10.Servicio
{
    public class ViajecitosService
    {
        private readonly HttpClient _httpClient;
        private readonly string _baseUrl = "http://localhost:5158/api/";

        public ViajecitosService()
        {
            _httpClient = new HttpClient();
            _httpClient.BaseAddress = new Uri(_baseUrl);
            _httpClient.DefaultRequestHeaders.Accept.Clear();
            _httpClient.DefaultRequestHeaders.Accept.Add(
                new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
        }

        public async Task<List<Vuelo>> BuscarVuelos(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            try
            {
                var url = $"vuelos?ciudadOrigen={Uri.EscapeDataString(ciudadOrigen)}&ciudadDestino={Uri.EscapeDataString(ciudadDestino)}&fecha={fecha:yyyy-MM-ddTHH:mm:ss}";
                var response = await _httpClient.GetAsync(url);
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var vuelos = JsonConvert.DeserializeObject<List<Vuelo>>(content) ?? new List<Vuelo>();
                return vuelos;
            }
            catch (Exception ex)
            {
                throw new Exception($"Error en BuscarVuelos: {ex.Message}", ex);
            }
        }

        public async Task<Cliente> RegistrarCliente(string nombre, string email, string documentoIdentidad)
        {
            try
            {
                var url = $"cliente?nombre={Uri.EscapeDataString(nombre)}&email={Uri.EscapeDataString(email)}&documentoIdentidad={Uri.EscapeDataString(documentoIdentidad)}";
                var response = await _httpClient.PostAsync(url, null);
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var cliente = JsonConvert.DeserializeObject<Cliente>(content);
                return cliente;
            }
            catch (Exception ex)
            {
                throw new Exception($"Error en RegistrarCliente: {ex.Message}", ex);
            }
        }

        public async Task<Usuario> IniciarSesion(string nombreUsuario, string claveUsuario)
        {
            try
            {
                var url = $"login?nombreUsuario={Uri.EscapeDataString(nombreUsuario)}&claveUsuario={Uri.EscapeDataString(claveUsuario)}";
                var response = await _httpClient.PostAsync(url, null);
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var usuario = JsonConvert.DeserializeObject<Usuario>(content);
                return usuario;
            }
            catch (Exception ex)
            {
                throw new Exception($"Error en IniciarSesion: {ex.Message}", ex);
            }
        }

        public async Task<List<Cliente>> ObtenerTodosClientes()
        {
            try
            {
                var url = "clientes";
                var response = await _httpClient.GetAsync(url);
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var clientes = JsonConvert.DeserializeObject<List<Cliente>>(content) ?? new List<Cliente>();
                return clientes;
            }
            catch (Exception ex)
            {
                throw new Exception($"Error en ObtenerTodosClientes: {ex.Message}", ex);
            }
        }

        public async Task<Factura> CrearFactura(string numeroFactura, int idEmpleado, int idCliente, int idMetodoPago, decimal descuento, List<(int IdVuelo, int Cantidad)> detalles)
        {
            try
            {
                var url = "factura";
                var request = new
                {
                    NumeroFactura = numeroFactura,
                    IdEmpleado = idEmpleado,
                    IdCliente = idCliente,
                    IdMetodoPago = idMetodoPago,
                    Descuento = descuento,
                    Detalles = detalles.Select(d => new { IdVuelo = d.IdVuelo, Cantidad = d.Cantidad }).ToList()
                };
                var json = JsonConvert.SerializeObject(request);
                var content = new StringContent(json, Encoding.UTF8, "application/json");
                var response = await _httpClient.PostAsync(url, content);

                if (!response.IsSuccessStatusCode)
                {
                    var errorContent = await response.Content.ReadAsStringAsync();
                    throw new HttpRequestException($"Error servidor: {response.StatusCode}. Detalle: {errorContent}");
                }

                var responseContent = await response.Content.ReadAsStringAsync();
                var factura = JsonConvert.DeserializeObject<Factura>(responseContent);
                return factura;
            }
            catch (Exception ex)
            {
                throw new Exception($"Error en CrearFactura: {ex.Message}", ex);
            }
        }

        public async Task<List<Factura>> ObtenerFacturasCliente(int idCliente)
        {
            try
            {
                var url = $"facturas/{idCliente}";
                var response = await _httpClient.GetAsync(url);
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var facturas = JsonConvert.DeserializeObject<List<Factura>>(content) ?? new List<Factura>();
                return facturas;
            }
            catch (Exception ex)
            {
                throw new Exception($"Error en ObtenerFacturasCliente: {ex.Message}", ex);
            }
        }

        public async Task<List<ClienteFacturas>> ObtenerTodasFacturasPorCliente()
        {
            try
            {
                var url = "facturas";
                var response = await _httpClient.GetAsync(url);
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var clientesFacturas = JsonConvert.DeserializeObject<List<ClienteFacturas>>(content) ?? new List<ClienteFacturas>();
                return clientesFacturas;
            }
            catch (Exception ex)
            {
                throw new Exception($"Error en ObtenerTodasFacturasPorCliente: {ex.Message}", ex);
            }
        }
    }
}
