using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VIAJECITOS_REST_CLICON_GR10.Modelos;
using VIAJECITOS_REST_CLICON_GR10.Servicio;

namespace VIAJECITOS_REST_CLICON_GR10.Controlador
{
    public class ViajecitosController
    {
        private readonly ViajecitosService service;
        private Usuario usuarioAutenticado;

        public ViajecitosController()
        {
            this.service = new ViajecitosService();
        }

        public async Task<bool> IniciarSesion(string nombreUsuario, string claveUsuario)
        {
            if (string.IsNullOrWhiteSpace(nombreUsuario) || string.IsNullOrWhiteSpace(claveUsuario))
            {
                throw new Exception("Usuario y contraseña son requeridos.");
            }
            usuarioAutenticado = await service.IniciarSesion(nombreUsuario, claveUsuario);
            return usuarioAutenticado != null;
        }

        public int GetIdClienteAutenticado()
        {
            if (usuarioAutenticado == null)
            {
                throw new Exception("No hay usuario autenticado.");
            }
            return usuarioAutenticado.IdCliente;
        }

        public async Task<List<Vuelo>> BuscarVuelos(string ciudadOrigen, string ciudadDestino, string fechaStr)
        {
            if (string.IsNullOrWhiteSpace(ciudadOrigen) || string.IsNullOrWhiteSpace(ciudadDestino))
            {
                throw new Exception("Ciudad de origen y destino son requeridas.");
            }
            if (string.IsNullOrWhiteSpace(fechaStr))
            {
                throw new Exception("Fecha de viaje es requerida.");
            }
            var fecha = DateTime.ParseExact(fechaStr, "yyyy-MM-dd", null);
            var vuelos = await service.BuscarVuelos(ciudadOrigen, ciudadDestino, fecha);
            if (vuelos == null || !vuelos.Any())
            {
                throw new Exception("No se encontraron vuelos disponibles.");
            }
            return vuelos;
        }

        public async Task<Vuelo> ObtenerVueloMasCaro(string ciudadOrigen, string ciudadDestino, string fechaStr)
        {
            if (string.IsNullOrWhiteSpace(ciudadOrigen) || string.IsNullOrWhiteSpace(ciudadDestino))
            {
                throw new Exception("Ciudad de origen y destino son requeridas.");
            }
            if (string.IsNullOrWhiteSpace(fechaStr))
            {
                throw new Exception("Fecha de viaje es requerida.");
            }
            var fecha = DateTime.ParseExact(fechaStr, "yyyy-MM-dd", null);
            var vuelo = await service.ObtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
            if (vuelo == null)
            {
                throw new Exception("No se encontró un vuelo para los criterios especificados.");
            }
            return vuelo;
        }

        public async Task<bool> RegistrarCompra(int idVuelo, int idCliente)
        {
            if (idVuelo <= 0)
            {
                throw new Exception("ID de vuelo inválido.");
            }
            if (idCliente <= 0)
            {
                throw new Exception("ID de cliente inválido.");
            }
            await service.RegistrarCompra(idVuelo, idCliente);
            return true;
        }

        public async Task<List<Compra>> ObtenerComprasCliente(int idCliente)
        {
            if (idCliente <= 0)
            {
                throw new Exception("ID de cliente inválido.");
            }
            var compras = await service.ObtenerComprasCliente(idCliente);
            if (compras == null || !compras.Any())
            {
                throw new Exception("No se encontraron compras para el cliente.");
            }
            return compras;
        }

        public async Task<int> RegistrarCliente(string nombre, string email, string documentoIdentidad)
        {
            if (string.IsNullOrWhiteSpace(nombre) || string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(documentoIdentidad))
            {
                throw new Exception("Todos los campos son requeridos.");
            }
            var cliente = await service.RegistrarCliente(nombre, email, documentoIdentidad);
            if (cliente == null)
            {
                throw new Exception("Error al registrar el cliente.");
            }
            return cliente.IdCliente;
        }

        public async Task<bool> RegistrarUsuario(int idCliente, string nombreUsuario, string claveUsuario)
        {
            if (idCliente <= 0)
            {
                throw new Exception("ID de cliente inválido.");
            }
            if (string.IsNullOrWhiteSpace(nombreUsuario) || string.IsNullOrWhiteSpace(claveUsuario))
            {
                throw new Exception("Nombre de usuario y contraseña son requeridos.");
            }
            var usuario = await service.RegistrarUsuario(idCliente, nombreUsuario, claveUsuario);
            return usuario != null;
        }
    }
}