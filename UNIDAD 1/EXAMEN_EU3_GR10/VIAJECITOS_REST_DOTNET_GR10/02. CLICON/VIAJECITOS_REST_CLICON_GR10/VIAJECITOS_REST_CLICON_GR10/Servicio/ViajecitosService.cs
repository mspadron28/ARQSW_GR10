using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using VIAJECITOS_REST_CLICON_GR10.Modelos;

namespace VIAJECITOS_REST_CLICON_GR10.Servicio
{
    public class ViajecitosService
    {
        private static readonly HttpClient client;
        private const string BaseUrl = "http://localhost:5158/api/";

        static ViajecitosService()
        {
            var handler = new HttpClientHandler
            {
                ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true
            };
            client = new HttpClient(handler);
            client.BaseAddress = new Uri(BaseUrl);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            Console.WriteLine($"[DEBUG] HttpClient inicializado con BaseUrl: {BaseUrl}");
        }

        public async Task<List<Vuelo>> BuscarVuelos(string ciudadOrigen, string ciudadDestino, DateTime fecha)
        {
            try
            {
                var url = $"vuelos?ciudadOrigen={Uri.EscapeDataString(ciudadOrigen)}&ciudadDestino={Uri.EscapeDataString(ciudadDestino)}&fecha={fecha:yyyy-MM-ddTHH:mm:ss}";
                Console.WriteLine($"[DEBUG] Enviando GET a: {BaseUrl}{url}");

                var response = await client.GetAsync(url);
                Console.WriteLine($"[DEBUG] Respuesta recibida: StatusCode={response.StatusCode}");

                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                Console.WriteLine($"[DEBUG] Contenido recibido: {content}");

                var vuelos = JsonConvert.DeserializeObject<List<Vuelo>>(content) ?? new List<Vuelo>();
                Console.WriteLine($"[DEBUG] Vuelos deserializados: {vuelos.Count} encontrados");
                return vuelos;
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"[ERROR] HttpRequestException en BuscarVuelos: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error en la solicitud HTTP: {ex.Message}", ex);
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"[ERROR] JsonException en BuscarVuelos: {ex.Message}");
                throw new Exception($"Error al deserializar la respuesta: {ex.Message}", ex);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción general en BuscarVuelos: {ex.Message}, StackTrace: {ex.StackTrace}");
                throw new Exception($"Error inesperado: {ex.Message}", ex);
            }
        }

        public async Task<Cliente> RegistrarCliente(string nombre, string email, string documentoIdentidad)
        {
            try
            {
                var url = $"cliente?nombre={Uri.EscapeDataString(nombre)}&email={Uri.EscapeDataString(email)}&documentoIdentidad={Uri.EscapeDataString(documentoIdentidad)}";
                Console.WriteLine($"[DEBUG] Enviando POST a: {BaseUrl}{url}");

                var response = await client.PostAsync(url, null);
                Console.WriteLine($"[DEBUG] Respuesta recibida: StatusCode={response.StatusCode}");

                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                Console.WriteLine($"[DEBUG] Contenido recibido: {content}");

                var cliente = JsonConvert.DeserializeObject<Cliente>(content);
                Console.WriteLine($"[DEBUG] Cliente deserializado: Id={cliente?.IdCliente}, Nombre={cliente?.Nombre}");
                return cliente;
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"[ERROR] HttpRequestException en RegistrarCliente: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error en la solicitud HTTP: {ex.Message}", ex);
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"[ERROR] JsonException en RegistrarCliente: {ex.Message}");
                throw new Exception($"Error al deserializar la respuesta: {ex.Message}", ex);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción general en RegistrarCliente: {ex.Message}, StackTrace: {ex.StackTrace}");
                throw new Exception($"Error inesperado: {ex.Message}", ex);
            }
        }

        public async Task<Usuario> IniciarSesion(string nombreUsuario, string claveUsuario)
        {
            try
            {
                var url = $"login?nombreUsuario={Uri.EscapeDataString(nombreUsuario)}&claveUsuario={Uri.EscapeDataString(claveUsuario)}";
                Console.WriteLine($"[DEBUG] Enviando POST a: {BaseUrl}{url}");

                var response = await client.PostAsync(url, null);
                Console.WriteLine($"[DEBUG] Respuesta recibida: StatusCode={response.StatusCode}");

                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                Console.WriteLine($"[DEBUG] Contenido recibido: {content}");

                var usuario = JsonConvert.DeserializeObject<Usuario>(content);
                Console.WriteLine($"[DEBUG] Usuario deserializado: Id={usuario?.IdUsuario}, NombreUsuario={usuario?.NombreUsuario}");
                return usuario;
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"[ERROR] HttpRequestException en IniciarSesion: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error en la solicitud HTTP: {ex.Message}", ex);
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"[ERROR] JsonException en IniciarSesion: {ex.Message}");
                throw new Exception($"Error al deserializar la respuesta: {ex.Message}", ex);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción general en IniciarSesion: {ex.Message}, StackTrace: {ex.StackTrace}");
                throw new Exception($"Error inesperado: {ex.Message}", ex);
            }
        }

        public async Task<List<Cliente>> ObtenerTodosClientes()
        {
            try
            {
                var url = "clientes";
                Console.WriteLine($"[DEBUG] Enviando GET a: {BaseUrl}{url}");

                var response = await client.GetAsync(url);
                Console.WriteLine($"[DEBUG] Respuesta recibida: StatusCode={response.StatusCode}");

                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                Console.WriteLine($"[DEBUG] Contenido recibido: {content}");

                var clientes = JsonConvert.DeserializeObject<List<Cliente>>(content) ?? new List<Cliente>();
                Console.WriteLine($"[DEBUG] Clientes deserializados: {clientes.Count} encontrados");
                return clientes;
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"[ERROR] HttpRequestException en ObtenerTodosClientes: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error en la solicitud HTTP: {ex.Message}", ex);
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"[ERROR] JsonException en ObtenerTodosClientes: {ex.Message}");
                throw new Exception($"Error al deserializar la respuesta: {ex.Message}", ex);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción general en ObtenerTodosClientes: {ex.Message}, StackTrace: {ex.StackTrace}");
                throw new Exception($"Error inesperado: {ex.Message}", ex);
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
                Console.WriteLine($"[DEBUG] Enviando POST a: {BaseUrl}{url}, Contenido: {json}");

                var content = new StringContent(json, Encoding.UTF8, "application/json");
                var response = await client.PostAsync(url, content);
                Console.WriteLine($"[DEBUG] Respuesta recibida: StatusCode={response.StatusCode}");

                if (!response.IsSuccessStatusCode)
                {
                    var errorContent = await response.Content.ReadAsStringAsync();
                    Console.WriteLine($"[ERROR] Error en la respuesta del servidor: {errorContent}");
                    throw new HttpRequestException($"El código de estado de la respuesta no indica un resultado correcto: {response.StatusCode}. Contenido: {errorContent}");
                }

                var responseContent = await response.Content.ReadAsStringAsync();
                Console.WriteLine($"[DEBUG] Contenido recibido: {responseContent}");

                var factura = JsonConvert.DeserializeObject<Factura>(responseContent);
                Console.WriteLine($"[DEBUG] Factura deserializada: NumeroFactura={factura?.NumeroFactura}, Subtotal={factura?.Subtotal}, Total={factura?.Total}");
                return factura;
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"[ERROR] Excepción de HttpRequestException en CrearFactura: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error en la solicitud HTTP: {ex.Message}", ex);
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"[ERROR] Excepción de JsonException en CrearFactura: {ex.Message}");
                throw new Exception($"Error al deserializar la respuesta: {ex.Message}", ex);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción general en CrearFactura: {ex.Message}, StackTrace: {ex.StackTrace}");
                throw new Exception($"Error inesperado: {ex.Message}", ex);
            }
        }
        public async Task<List<Factura>> ObtenerFacturasCliente(int idCliente)
        {
            try
            {
                var url = $"facturas/{idCliente}";
                Console.WriteLine($"[DEBUG] Enviando GET a: {BaseUrl}{url}");

                var response = await client.GetAsync(url);
                Console.WriteLine($"[DEBUG] Respuesta recibida: StatusCode={response.StatusCode}");

                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                Console.WriteLine($"[DEBUG] Contenido recibido: {content}");

                var facturas = JsonConvert.DeserializeObject<List<Factura>>(content) ?? new List<Factura>();
                Console.WriteLine($"[DEBUG] Facturas deserializadas: {facturas.Count} encontradas");
                return facturas;
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"[ERROR] HttpRequestException en ObtenerFacturasCliente: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error en la solicitud HTTP: {ex.Message}", ex);
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"[ERROR] JsonException en ObtenerFacturasCliente: {ex.Message}");
                throw new Exception($"Error al deserializar la respuesta: {ex.Message}", ex);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción general en ObtenerFacturasCliente: {ex.Message}, StackTrace: {ex.StackTrace}");
                throw new Exception($"Error inesperado: {ex.Message}", ex);
            }
        }


        public async Task<List<ClienteFacturas>> ObtenerTodasFacturasPorCliente()
        {
            try
            {
                var url = "facturas";
                Console.WriteLine($"[DEBUG] Enviando GET a: {BaseUrl}{url}");

                var response = await client.GetAsync(url);
                Console.WriteLine($"[DEBUG] Respuesta recibida: StatusCode={response.StatusCode}");

                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                Console.WriteLine($"[DEBUG] Contenido recibido: {content}");

                var clientesFacturas = JsonConvert.DeserializeObject<List<ClienteFacturas>>(content) ?? new List<ClienteFacturas>();
                Console.WriteLine($"[DEBUG] Clientes con facturas deserializados: {clientesFacturas.Count} encontrados");
                return clientesFacturas;
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"[ERROR] HttpRequestException en ObtenerTodasFacturasPorCliente: {ex.Message}, InnerException: {ex.InnerException?.Message}");
                throw new Exception($"Error en la solicitud HTTP: {ex.Message}", ex);
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"[ERROR] JsonException en ObtenerTodasFacturasPorCliente: {ex.Message}");
                throw new Exception($"Error al deserializar la respuesta: {ex.Message}", ex);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERROR] Excepción general en ObtenerTodasFacturasPorCliente: {ex.Message}, StackTrace: {ex.StackTrace}");
                throw new Exception($"Error inesperado: {ex.Message}", ex);
            }
        }
    }
}