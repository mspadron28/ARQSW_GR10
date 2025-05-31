using System;
using System.Collections.Generic;
using System.Globalization;
using System.Threading.Tasks;
using VIAJECITOS_REST_CLICON_GR10.Controlador;
using VIAJECITOS_REST_CLICON_GR10.Modelos;

namespace VIAJECITOS_REST_CLICON_GR10.Vista
{
    public class MainConsole
    {
        private readonly ViajecitosController controlador;
        private readonly Scanner scanner;
        private readonly IFormatProvider dateFormat;

        public MainConsole()
        {
            controlador = new ViajecitosController();
            scanner = new Scanner();
            dateFormat = CultureInfo.InvariantCulture;
        }

        public void Start()
        {
            MostrarBanner();
            if (!MostrarMenuInicial().Result)
            {
                Console.WriteLine("Saliendo del programa. ¡Hasta pronto!");
                return;
            }
            LimpiarConsola();
            MostrarMenuPrincipal().Wait();
        }

        private void MostrarBanner()
        {
            LimpiarConsola();
            Console.WriteLine("╔══════════════════════════════════════╗");
            Console.WriteLine("║         Viajecitos SA                ║");
            Console.WriteLine("║  Encuentra y compra tus boletos      ║");
            Console.WriteLine("║      de avión de forma fácil         ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
        }

        private void LimpiarConsola()
        {
            Console.Clear();
        }

        private void Pausa()
        {
            Console.WriteLine("\nPresione Enter para continuar...");
            Console.ReadLine();
            LimpiarConsola();
        }

        private bool PreguntarContinuar()
        {
            while (true)
            {
                Console.Write("\n¿Desea continuar? (S/N): ");
                string respuesta = Console.ReadLine().Trim().ToLower();
                if (respuesta == "s") return true;
                else if (respuesta == "n") return false;
                else Console.WriteLine("Por favor, ingrese 'S' para sí o 'N' para no.");
            }
        }

        private async Task<bool> MostrarMenuInicial()
        {
            while (true)
            {
                MostrarBanner();
                Console.WriteLine("╔══════════════════════════════════════╗");
                Console.WriteLine("║    Bienvenido a Viajecitos SA        ║");
                Console.WriteLine("╠══════════════════════════════════════╣");
                Console.WriteLine("║ Seleccione una opción:               ║");
                Console.WriteLine("║                                      ║");
                Console.WriteLine("║ 1. Iniciar Sesión                    ║");
                Console.WriteLine("║ 2. Registrarse                       ║");
                Console.WriteLine("║ 3. Salir                             ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.Write("\nSeleccione una opción (1-3): ");
                string choice = Console.ReadLine();
                switch (choice)
                {
                    case "1":
                        return await IniciarSesion();
                    case "2":
                        if (await RegistrarClienteYUsuario())
                        {
                            Console.WriteLine("\nRegistro exitoso. Por favor, inicie sesión.");
                            Pausa();
                            continue;
                        }
                        return false;
                    case "3":
                        return false;
                    default:
                        Console.WriteLine("\nOpción inválida. Por favor, seleccione una opción válida.");
                        Pausa();
                        break;
                }
            }
        }

        private async Task<bool> IniciarSesion()
        {
            while (true)
            {
                MostrarBanner();
                Console.WriteLine("╔══════════════════════════════════════╗");
                Console.WriteLine("║         Iniciar Sesión               ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.WriteLine("\nIngrese sus credenciales:\n");
                Console.WriteLine("╔══════════════════════════╗");
                Console.Write("║ Usuario:                 ║ ");
                string nombreUsuario = Console.ReadLine().Trim();
                Console.WriteLine("╚══════════════════════════╝");
                Console.WriteLine("╔══════════════════════════╗");
                Console.Write("║ Contraseña:              ║ ");
                string claveUsuario = Console.ReadLine().Trim();
                Console.WriteLine("╚══════════════════════════╝");
                try
                {
                    if (string.IsNullOrWhiteSpace(nombreUsuario) || string.IsNullOrWhiteSpace(claveUsuario))
                    {
                        Console.WriteLine("\nPor favor, complete todos los campos.");
                        if (!PreguntarContinuar()) return false;
                        continue;
                    }
                    if (await controlador.IniciarSesion(nombreUsuario, claveUsuario))
                    {
                        Console.WriteLine("\nInicio de sesión exitoso.");
                        Pausa();
                        return true;
                    }
                    else
                    {
                        Console.WriteLine("\nUsuario o contraseña incorrectos.");
                        if (!PreguntarContinuar()) return false;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("\nError al iniciar sesión: " + e.Message);
                    if (!PreguntarContinuar()) return false;
                }
            }
        }

        private async Task<bool> RegistrarClienteYUsuario()
        {
            MostrarBanner();
            Console.WriteLine("║ Registrarse                          ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.WriteLine("\nIngrese sus datos:\n");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Nombre:                  ║ ");
            string nombre = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Email:                   ║ ");
            string email = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Documento de Identidad:  ║ ");
            string documentoIdentidad = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Nombre de Usuario:       ║ ");
            string nombreUsuario = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Contraseña:              ║ ");
            string claveUsuario = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            try
            {
                int idCliente = await controlador.RegistrarCliente(nombre, email, documentoIdentidad);
                bool exito = await controlador.RegistrarUsuario(idCliente, nombreUsuario, claveUsuario);
                if (exito) return true;
                else Console.WriteLine("\nError al registrar el usuario.");
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al registrarse: " + e.Message);
            }
            return PreguntarContinuar();
        }

        private async Task MostrarMenuPrincipal()
        {
            while (true)
            {
                MostrarBanner();
                Console.WriteLine("╔══════════════════════════════════════╗");
                Console.WriteLine("║    Viajecitos SA Console Client      ║");
                Console.WriteLine("╠══════════════════════════════════════╣");
                Console.WriteLine("║ Seleccione una opción:               ║");
                Console.WriteLine("║                                      ║");
                Console.WriteLine("║ 1. Buscar Vuelos                     ║");
                Console.WriteLine("║ 2. Obtener Vuelo Más Caro            ║");
                Console.WriteLine("║ 3. Comprar Boleto                    ║");
                Console.WriteLine("║ 4. Consultar Compras                 ║");
                Console.WriteLine("║ 5. Salir                             ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.Write("\nSeleccione una opción (1-5): ");
                string choice = Console.ReadLine();
                switch (choice)
                {
                    case "1":
                        LimpiarConsola();
                        await BuscarVuelos();
                        break;
                    case "2":
                        LimpiarConsola();
                        await ObtenerVueloMasCaro();
                        break;
                    case "3":
                        LimpiarConsola();
                        await ComprarBoleto();
                        break;
                    case "4":
                        LimpiarConsola();
                        await ConsultarCompras();
                        break;
                    case "5":
                        Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                        Pausa();
                        return;
                    default:
                        Console.WriteLine("\nOpción inválida. Por favor, seleccione una opción válida.");
                        Pausa();
                        break;
                }
            }
        }

        private async Task BuscarVuelos()
        {
            MostrarBanner();
            Console.WriteLine("║ Buscar Vuelos                        ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.WriteLine("\nIngrese los detalles del vuelo:\n");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Ciudad Origen:           ║ ");
            string ciudadOrigen = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Ciudad Destino:          ║ ");
            string ciudadDestino = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Fecha (yyyy-MM-dd):      ║ ");
            string fecha = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            try
            {
                var vuelos = await controlador.BuscarVuelos(ciudadOrigen, ciudadDestino, fecha);
                Console.WriteLine("\nVuelos disponibles:");
                Console.WriteLine("{0,-10} {1,-15} {2,-15} {3,-10} {4,-15}", "ID VUELO", "ORIGEN", "DESTINO", "VALOR", "HORA SALIDA");
                Console.WriteLine("-------------------------------------------------------");
                foreach (var vuelo in vuelos)
                {
                    Console.WriteLine("{0,-10} {1,-15} {2,-15} {3,-10:C} {4,-15}",
                        vuelo.IdVuelo,
                        vuelo.CiudadOrigen,
                        vuelo.CiudadDestino,
                        vuelo.Valor,
                        vuelo.HoraSalida.ToString("yyyy-MM-dd HH:mm:ss"));
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al buscar vuelos: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
        }

        private async Task ObtenerVueloMasCaro()
        {
            MostrarBanner();
            Console.WriteLine("║ Obtener Vuelo Más Caro               ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.WriteLine("\nIngrese los detalles del vuelo:\n");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Ciudad Origen:           ║ ");
            string ciudadOrigen = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Ciudad Destino:          ║ ");
            string ciudadDestino = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            Console.WriteLine("╔══════════════════════════╗");
            Console.Write("║ Fecha (yyyy-MM-dd):      ║ ");
            string fecha = Console.ReadLine().Trim();
            Console.WriteLine("╚══════════════════════════╝");
            try
            {
                var vuelo = await controlador.ObtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
                Console.WriteLine("\nVuelo más caro:");
                Console.WriteLine("{0,-10} {1,-15} {2,-15} {3,-10} {4,-15}", "ID VUELO", "ORIGEN", "DESTINO", "VALOR", "HORA SALIDA");
                Console.WriteLine("-------------------------------------------------------");
                Console.WriteLine("{0,-10} {1,-15} {2,-15} {3,-10:C} {4,-15}",
                    vuelo.IdVuelo,
                    vuelo.CiudadOrigen,
                    vuelo.CiudadDestino,
                    vuelo.Valor,
                    vuelo.HoraSalida.ToString("yyyy-MM-dd HH:mm:ss"));
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al obtener el vuelo más caro: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
        }

        private async Task ComprarBoleto()
        {
            try
            {
                MostrarBanner();
                Console.WriteLine("║ Comprar Boleto                       ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.WriteLine("\nIngrese los detalles de la compra:\n");

                // Solicitar y validar el IdVuelo
                Console.WriteLine("╔══════════════════════════╗");
                Console.Write("║ ID del Vuelo:            ║ ");
                string idVueloText = Console.ReadLine().Trim();
                Console.WriteLine("╚══════════════════════════╝");

                if (!int.TryParse(idVueloText, out int idVuelo) || idVuelo <= 0)
                {
                    Console.WriteLine("\nError: Ingrese un ID de vuelo válido (debe ser un número mayor que 0).");
                    return;
                }

                // Confirmar el valor de idVuelo (para depuración)
                //Console.WriteLine($"\nDEBUG: IdVuelo ingresado: {idVuelo}");

                // Obtener el IdCliente
                int idCliente = controlador.GetIdClienteAutenticado();
                if (idCliente <= 0)
                {
                    Console.WriteLine("\nError: No hay un cliente autenticado. Por favor, inicie sesión.");
                    return;
                }

                // Confirmar el valor de idCliente (para depuración)
                //Console.WriteLine($"DEBUG: IdCliente autenticado: {idCliente}");

                // Registrar la compra
                await controlador.RegistrarCompra(idVuelo, idCliente);
                Console.WriteLine("\nCompra realizada exitosamente.");
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al comprar boleto: " + e.Message);
            }
            finally
            {
                if (!PreguntarContinuar())
                {
                    Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                    Environment.Exit(0);
                }
            }
        }

        private async Task ConsultarCompras()
        {
            MostrarBanner();
            Console.WriteLine("║ Consultar Compras                    ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            try
            {
                int idCliente = controlador.GetIdClienteAutenticado();
                var compras = await controlador.ObtenerComprasCliente(idCliente);
                Console.WriteLine("\n╔══════════════════════════════════════════════════════════════════════");
                Console.WriteLine("║ Compras realizadas                                                   ");
                Console.WriteLine("╠══════════════════════════════════════════════════════════════════════");
                Console.WriteLine("║ {0,-10} {1,-25} {2,-10} {3,-30} {4,-30}", "ID COMPRA", "VUELO", "VALOR", "FECHA COMPRA", "HORA SALIDA");
                Console.WriteLine("╠══════════════════════════════════════════════════════════════════════");
                foreach (var compra in compras)
                {
                    Console.WriteLine("║ {0,-10} {1,-25} {2,-10:C} {3,-30} {4,-30}",
                        compra.IdCompra,
                        $"{compra.Vuelo?.CiudadOrigen} a {compra.Vuelo?.CiudadDestino}",
                        compra.Vuelo?.Valor ?? 0m,
                        compra.FechaCompra.ToString("yyyy-MM-dd HH:mm:ss"),
                        compra.Vuelo?.HoraSalida.ToString("yyyy-MM-dd HH:mm:ss") ?? "");
                }
                Console.WriteLine("╚══════════════════════════════════════════════════════════════════════");
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al consultar compras: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
        }
    }

    // Clase auxiliar para simular Scanner de Java
    public class Scanner
    {
        public string NextLine() => Console.ReadLine();
    }
}