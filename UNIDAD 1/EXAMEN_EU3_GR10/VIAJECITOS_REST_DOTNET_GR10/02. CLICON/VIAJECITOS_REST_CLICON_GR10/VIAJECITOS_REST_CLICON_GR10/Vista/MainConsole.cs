using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;
using VIAJECITOS_REST_CLICON_GR10.Controlador;
using VIAJECITOS_REST_CLICON_GR10.Modelos;

namespace VIAJECITOS_REST_CLICON_GR10.Vista
{
    public class Scanner
    {
        public string NextLine()
        {
            return Console.ReadLine();
        }
    }

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

        private bool PreguntarContinuar(string mensaje = "¿Desea continuar? (S/N)")
        {
            while (true)
            {
                Console.Write($"\n{mensaje}: ");
                string respuesta = Console.ReadLine()?.Trim().ToLower();
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
                Console.WriteLine("║ 2. Salir                             ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.Write("\nSeleccione una opción (1-2): ");
                string choice = scanner.NextLine()?.Trim();
                switch (choice)
                {
                    case "1":
                        return await IniciarSesion();
                    case "2":
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
                Console.Write("Nombre de usuario: ");
                string nombreUsuario = scanner.NextLine()?.Trim();
                Console.Write("Contraseña: ");
                string claveUsuario = scanner.NextLine()?.Trim();
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
                    Console.WriteLine($"\nError al iniciar sesión: {e.Message}");
                    if (!PreguntarContinuar()) return false;
                }
            }
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
                Console.WriteLine("║ 1. Buscar Vuelos y Comprar           ║");
                Console.WriteLine("║ 2. Consultar Facturas por Cliente    ║");
                Console.WriteLine("║ 3. Consultar Todas las Facturas      ║");
                Console.WriteLine("║ 4. Salir                             ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.Write("\nSeleccione una opción (1-4): ");
                string choice = scanner.NextLine()?.Trim();
                switch (choice)
                {
                    case "1":
                        LimpiarConsola();
                        await BuscarVuelosYComprar();
                        break;
                    case "2":
                        LimpiarConsola();
                        await ConsultarFacturas();
                        break;
                    case "3":
                        LimpiarConsola();
                        await ConsultarTodasFacturasPorCliente();
                        break;
                    case "4":
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

        private async Task ConsultarTodasFacturasPorCliente()
        {
            MostrarBanner();
            Console.WriteLine("╔══════════════════════════════════════╗");
            Console.WriteLine("║ Consultar Todas las Facturas         ║");
            Console.WriteLine("╚══════════════════════════════════════╝");

            try
            {
                var clientesFacturas = await controlador.ObtenerTodasFacturasPorCliente();
                if (!clientesFacturas.Any())
                {
                    Console.WriteLine("\nNo se encontraron facturas.");
                    Pausa();
                    return;
                }

                Console.WriteLine("\nFacturas organizadas por cliente:");
                foreach (var cliente in clientesFacturas.OrderBy(c => c.Nombre))
                {
                    Console.WriteLine($"\nCliente: {cliente.Nombre} (ID: {cliente.ClienteId})");
                    Console.WriteLine($"Email: {cliente.Email}");
                    Console.WriteLine($"Documento: {cliente.DocumentoIdentidad}");
                    Console.WriteLine("╔══════════════════════════════════════════════════════════════════════╗");
                    Console.WriteLine("║ {0,-15} {1,-19} {2,-12} {3,-12} {4,-12} {5,-12} ║",
                        "NÚMERO FACTURA", "FECHA", "SUBTOTAL", "DESCUENTO", "IVA", "TOTAL");
                    Console.WriteLine("╠══════════════════════════════════════════════════════════════════════╩");
                    if (cliente.Facturas.Any())
                    {
                        foreach (var factura in cliente.Facturas.OrderBy(f => f.FechaEmision))
                        {
                            Console.WriteLine("║ {0,-15} {1,-19:yyyy-MM-dd HH:mm:ss} {2,-12:C} {3,-12:C} {4,-12:C} {5,-12:C} ║",
                                factura.NumeroFactura,
                                factura.FechaEmision,
                                factura.Subtotal,
                                factura.Descuento,
                                factura.Iva,
                                factura.Total);
                        }
                    }
                    else
                    {
                        Console.WriteLine("║ No hay facturas para este cliente.                                    ║");
                    }
                    Console.WriteLine("╚══════════════════════════════════════════════════════════════════════╝");
                }

                if (PreguntarContinuar("¿Desea ver los detalles de una factura? (S/N)"))
                {
                    Console.Write("\nIngrese el número de factura (ej. FAC20250602210148): ");
                    string numeroFactura = scanner.NextLine()?.Trim();
                    if (string.IsNullOrWhiteSpace(numeroFactura))
                    {
                        Console.WriteLine("\nError: Número de factura inválido.");
                        Pausa();
                        return;
                    }

                    var facturaSeleccionada = clientesFacturas
                        .SelectMany(c => c.Facturas)
                        .FirstOrDefault(f => f.NumeroFactura == numeroFactura);

                    if (facturaSeleccionada == null)
                    {
                        Console.WriteLine("\nError: Factura no encontrada.");
                        Pausa();
                        return;
                    }

                    MostrarDetallesFactura(facturaSeleccionada, clientesFacturas.First(c => c.Facturas.Contains(facturaSeleccionada)));
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"\nError al consultar facturas: {e.Message}");
            }
            Pausa();
        }
        private void MostrarDetallesFactura(Factura factura, ClienteFacturas cliente)
        {
            MostrarBanner();
            Console.WriteLine("╔══════════════════════════════════════╗");
            Console.WriteLine("║ Detalles de Factura - Viajecitos SA  ║");
            Console.WriteLine("╚══════════════════════════════════════╝");

            string metodoPago;
            if (factura.IdMetodoPago == 1)
            {
                metodoPago = "Tarjeta de Crédito";
            }
            else if (factura.IdMetodoPago == 2)
            {
                metodoPago = "Efectivo";
            }
            else if (factura.IdMetodoPago == 3)
            {
                metodoPago = "Tarjeta de Débito";
            }
            else
            {
                metodoPago = "Desconocido";
            }

            Console.WriteLine($"\nNúmero de Factura: {factura.NumeroFactura}");
            Console.WriteLine($"Fecha de Emisión: {factura.FechaEmision:yyyy-MM-dd HH:mm:ss}");
            Console.WriteLine($"Cliente: {cliente.Nombre} (ID: {cliente.ClienteId})");
            Console.WriteLine($"Email: {cliente.Email}");
            Console.WriteLine($"Documento: {cliente.DocumentoIdentidad}");
            Console.WriteLine($"Método de Pago: {metodoPago}");
            Console.WriteLine($"\nDetalles de la factura:");
            Console.WriteLine("╔══════════════════════════════════════════════════════════════════════╗");
            Console.WriteLine("║ {0,-10} {1,-15} {2,-15} {3,-10} {4,-12} {5,-12} ║",
                "ID VUELO", "ORIGEN", "DESTINO", "CANTIDAD", "VALOR UNIT.", "TOTAL");
            Console.WriteLine("╠══════════════════════════════════════════════════════════════════════╩");
            if (factura.DetallesFactura.Any())
            {
                foreach (var detalle in factura.DetallesFactura)
                {
                    var vuelo = detalle.Vuelo ?? new Vuelo { CiudadOrigen = "N/A", CiudadDestino = "N/A" };
                    Console.WriteLine("║ {0,-10} {1,-15} {2,-15} {3,-10} {4,-12:C} {5,-12:C} ║",
                        detalle.IdVuelo,
                        vuelo.CiudadOrigen,
                        vuelo.CiudadDestino,
                        detalle.Cantidad,
                        detalle.ValorUnitario,
                        detalle.Total);
                }
            }
            else
            {
                Console.WriteLine("║ No hay detalles para esta factura.                                    ║");
            }
            Console.WriteLine("╚══════════════════════════════════════════════════════════════════════╝");
            Console.WriteLine($"\nSubtotal: {factura.Subtotal:C}");
            Console.WriteLine($"Descuento: {factura.Descuento:C}");
            Console.WriteLine($"IVA (15%): {factura.Iva:C}");
            Console.WriteLine($"Total: {factura.Total:C}");
        }
        private async Task BuscarVuelosYComprar()
        {
            MostrarBanner();
            Console.WriteLine("╔══════════════════════════════════════╗");
            Console.WriteLine("║ Buscar Vuelos                        ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.WriteLine("\nIngrese los detalles del vuelo:\n");
            Console.Write("Ciudad Origen: ");
            string ciudadOrigen = scanner.NextLine()?.Trim();
            Console.Write("Ciudad Destino: ");
            string ciudadDestino = scanner.NextLine()?.Trim();
            Console.Write("Fecha (yyyy-MM-dd): ");
            string fecha = scanner.NextLine()?.Trim();
            List<Vuelo> vuelos = null;
            try
            {
                vuelos = await controlador.BuscarVuelos(ciudadOrigen, ciudadDestino, fecha);
                Console.WriteLine("\nVuelos disponibles (ordenados de mayor a menor precio):");
                Console.WriteLine("╔══════════════════════════════════════════════════════════════════════╗");
                Console.WriteLine("║ {0,-10} {1,-15} {2,-15} {3,-12} {4,-19} ║", "ID VUELO", "ORIGEN", "DESTINO", "VALOR", "HORA SALIDA");
                Console.WriteLine("╠══════════════════════════════════════════════════════════════════════╣");
                if (vuelos.Any())
                {
                    var vueloMasCaro = vuelos.OrderByDescending(v => v.Valor).First();
                    Console.WriteLine("║                  VUELO MÁS CARO                                      ║");
                    Console.WriteLine("║ {0,-10} {1,-15} {2,-15} {3,-12:C} {4,-19} ║",
                        vueloMasCaro.IdVuelo,
                        vueloMasCaro.CiudadOrigen,
                        vueloMasCaro.CiudadDestino,
                        vueloMasCaro.Valor,
                        vueloMasCaro.HoraSalida.ToString("yyyy-MM-dd HH:mm:ss"));
                    Console.WriteLine("╠══════════════════════════════════════════════════════════════════════╣");
                    foreach (var vuelo in vuelos.OrderByDescending(v => v.Valor).Skip(1))
                    {
                        Console.WriteLine("║ {0,-10} {1,-15} {2,-15} {3,-12:C} {4,-19} ║",
                            vuelo.IdVuelo,
                            vuelo.CiudadOrigen,
                            vuelo.CiudadDestino,
                            vuelo.Valor,
                            vuelo.HoraSalida.ToString("yyyy-MM-dd HH:mm:ss"));
                    }
                }
                Console.WriteLine("╚══════════════════════════════════════════════════════════════════════╝");
            }
            catch (Exception e)
            {
                Console.WriteLine($"\nError al buscar vuelos: {e.Message}");
                Pausa();
                return;
            }

            if (!PreguntarContinuar("¿Desea comprar un vuelo? (S/N)"))
            {
                Pausa();
                return;
            }

            await GestionarCompra(vuelos);
        }

        private async Task GestionarCompra(List<Vuelo> vuelos)
        {
            int idCliente = await SeleccionarORegistrarCliente();
            if (idCliente <= 0)
            {
                Console.WriteLine("\nNo se seleccionó un cliente válido.");
                Pausa();
                return;
            }

            List<(int IdVuelo, int Cantidad)> detallesTemporales = new List<(int, int)>();

            while (true)
            {
                MostrarBanner();
                Console.WriteLine("╔══════════════════════════════════════╗");
                Console.WriteLine("║ Gestionar Compra                     ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.WriteLine("\nVuelos disponibles:");
                Console.WriteLine("╔══════════════════════════════════════════════════════════════════════╗");
                Console.WriteLine("║ {0,-10} {1,-15} {2,-15} {3,-12} {4,-19} ║", "ID VUELO", "ORIGEN", "DESTINO", "VALOR", "HORA SALIDA");
                Console.WriteLine("╠══════════════════════════════════════════════════════════════════════╣");
                foreach (var vuelo in vuelos.OrderByDescending(v => v.Valor))
                {
                    Console.WriteLine("║ {0,-10} {1,-15} {2,-15} {3,-12:C} {4,-19} ║",
                        vuelo.IdVuelo,
                        vuelo.CiudadOrigen,
                        vuelo.CiudadDestino,
                        vuelo.Valor,
                        vuelo.HoraSalida.ToString("yyyy-MM-dd HH:mm:ss"));
                }
                Console.WriteLine("╚══════════════════════════════════════════════════════════════════════╝");

                Console.WriteLine("\nDetalles actuales de la compra:");
                MostrarDetallesTemporales(detallesTemporales, vuelos);

                Console.WriteLine("\nAgregar detalle a la factura:");
                Console.Write("ID del Vuelo: ");
                string idVueloText = scanner.NextLine()?.Trim();
                Console.Write("Cantidad: ");
                string cantidadText = scanner.NextLine()?.Trim();

                try
                {
                    if (!int.TryParse(idVueloText, out int idVuelo) || idVuelo <= 0)
                    {
                        Console.WriteLine("\nError: ID de vuelo inválido.");
                        Pausa();
                        continue;
                    }
                    if (!int.TryParse(cantidadText, out int cantidad) || cantidad <= 0)
                    {
                        Console.WriteLine("\nError: Cantidad inválida.");
                        Pausa();
                        continue;
                    }

                    var vuelo = vuelos.FirstOrDefault(v => v.IdVuelo == idVuelo);
                    if (vuelo == null)
                    {
                        Console.WriteLine("\nError: Vuelo no encontrado.");
                        Pausa();
                        continue;
                    }

                    detallesTemporales.Add((idVuelo, cantidad));

                    Console.WriteLine("\nDetalle agregado. Vista previa de detalles:");
                    MostrarDetallesTemporales(detallesTemporales, vuelos);
                }
                catch (Exception e)
                {
                    Console.WriteLine($"\nError al agregar detalle: {e.Message}");
                    Pausa();
                    continue;
                }

                if (!PreguntarContinuar("¿Desea agregar otro detalle? (S/N)"))
                {
                    break;
                }
            }

            if (!detallesTemporales.Any())
            {
                Console.WriteLine("\nNo se agregaron detalles a la factura.");
                Pausa();
                return;
            }

            int idMetodoPago = SeleccionarMetodoPago();
            if (idMetodoPago <= 0)
            {
                Console.WriteLine("\nNo se seleccionó un método de pago válido.");
                Pausa();
                return;
            }

            decimal descuento = 0; // Dato quemado
            int idEmpleado = 1; // Dato quemado, asumiendo un empleado válido
            string numeroFactura = $"FAC{DateTime.Now:yyyyMMddHHmmss}"; // Generar número único

            // Mostrar factura temporal (usamos valores estimados para la vista previa)
            decimal subtotalEstimado = detallesTemporales.Sum(d => vuelos.First(v => v.IdVuelo == d.IdVuelo).Valor * d.Cantidad);
            decimal ivaEstimado = subtotalEstimado * 0.15m;
            decimal totalEstimado = subtotalEstimado - descuento + ivaEstimado;
            await MostrarFacturaTemporal(idCliente, detallesTemporales, subtotalEstimado, descuento, ivaEstimado, totalEstimado, idMetodoPago, idEmpleado, vuelos);

            if (PreguntarContinuar("¿Confirmar pago? (S/N)"))
            {
                try
                {
                    var factura = await controlador.CrearFactura(numeroFactura, idEmpleado, idCliente, idMetodoPago, descuento, detallesTemporales);
                    Console.WriteLine($"\nFactura creada exitosamente. Número de factura: {factura.NumeroFactura}");
                    Console.WriteLine($"Subtotal: {factura.Subtotal:C}");
                    Console.WriteLine($"Descuento: {factura.Descuento:C}");
                    Console.WriteLine($"IVA (15%): {factura.Iva:C}");
                    Console.WriteLine($"Total: {factura.Total:C}");
                    Console.WriteLine("\nDetalles de la factura:");
                    foreach (var detalle in factura.DetallesFactura)
                    {
                        var vuelo = vuelos.FirstOrDefault(v => v.IdVuelo == detalle.IdVuelo) ?? new Vuelo { CiudadOrigen = "N/A", CiudadDestino = "N/A" };
                        Console.WriteLine($"ID Vuelo: {detalle.IdVuelo}, Origen: {vuelo.CiudadOrigen}, Destino: {vuelo.CiudadDestino}, Cantidad: {detalle.Cantidad}, Valor Unitario: {detalle.ValorUnitario:C}, Total: {detalle.Total:C}");
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine($"\nError al crear la factura: {e.Message}");
                    var innerMessage = e.InnerException?.Message ?? "No hay detalles adicionales.";
                    Console.WriteLine($"Detalles adicionales: {innerMessage}");
                }
            }
            else
            {
                Console.WriteLine("\nCompra cancelada.");
            }
            Pausa();
        }


        private async Task<int> SeleccionarORegistrarCliente()
        {
            while (true)
            {
                MostrarBanner();
                Console.WriteLine("╔══════════════════════════════════════╗");
                Console.WriteLine("║ Seleccionar o Registrar Cliente      ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.WriteLine("\nOpciones:");
                Console.WriteLine("1. Seleccionar cliente existente");
                Console.WriteLine("2. Registrar nuevo cliente");
                Console.Write("\nSeleccione una opción (1-2): ");
                string choice = scanner.NextLine()?.Trim();

                try
                {
                    switch (choice)
                    {
                        case "1":
                            List<Cliente> clientes = await controlador.ObtenerTodosClientes();
                            Console.WriteLine("\nClientes existentes:");
                            Console.WriteLine("╔══════════════════════════════════════════════════════════════════════╗");
                            Console.WriteLine("║ {0,-10} {1,-20} {2,-20} {3,-20} ║", "ID CLIENTE", "NOMBRE", "EMAIL", "DOCUMENTO");
                            Console.WriteLine("╠══════════════════════════════════════════════════════════════════════╣");
                            foreach (var cliente in clientes)
                            {
                                Console.WriteLine("║ {0,-10} {1,-20} {2,-20} {3,-20} ║",
                                    cliente.IdCliente,
                                    cliente.Nombre,
                                    cliente.Email,
                                    cliente.DocumentoIdentidad);
                            }
                            Console.WriteLine("╚══════════════════════════════════════════════════════════════════════╝");
                            Console.Write("ID del Cliente: ");
                            string idClienteText = scanner.NextLine()?.Trim();

                            if (int.TryParse(idClienteText, out int idCliente) && clientes.Any(c => c.IdCliente == idCliente))
                            {
                                return idCliente;
                            }
                            Console.WriteLine("\nError: ID de cliente inválido o no encontrado.");
                            Pausa();
                            break;

                        case "2":
                            Console.WriteLine("\nRegistrar nuevo cliente:");
                            Console.Write("Nombre: ");
                            string nombre = scanner.NextLine()?.Trim();
                            Console.Write("Email: ");
                            string email = scanner.NextLine()?.Trim();
                            Console.Write("Documento de Identidad: ");
                            string documentoIdentidad = scanner.NextLine()?.Trim();

                            int nuevoIdCliente = await controlador.RegistrarCliente(nombre, email, documentoIdentidad);
                            Console.WriteLine("\nCliente registrado exitosamente.");
                            Pausa();
                            return nuevoIdCliente;

                        default:
                            Console.WriteLine("\nOpción inválida. Por favor, seleccione una opción válida.");
                            Pausa();
                            break;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine($"\nError: {e.Message}");
                    Pausa();
                }
            }
        }

        private int SeleccionarMetodoPago()
        {
            MostrarBanner();
            Console.WriteLine("╔══════════════════════════════════════╗");
            Console.WriteLine("║ Seleccionar Método de Pago           ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.WriteLine("\nMétodos de pago disponibles:");
            Console.WriteLine("╔══════════════════════════════════════╗");
            Console.WriteLine("║ {0,-5} {1,-30} ║", "ID", "MÉTODO");
            Console.WriteLine("╠══════════════════════════════════════╣");
            Console.WriteLine("║ {0,-5} {1,-30} ║", 1, "Tarjeta de Débito");
            Console.WriteLine("║ {0,-5} {1,-30} ║", 2, "Efectivo");
            Console.WriteLine("║ {0,-5} {1,-30} ║", 3, "Tarjeta de Crédito");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("ID del Método: ");
            string idMetodoPagoText = scanner.NextLine()?.Trim();

            if (int.TryParse(idMetodoPagoText, out int idMetodoPago) && idMetodoPago >= 1 && idMetodoPago <= 3)
            {
                return idMetodoPago;
            }
            Console.WriteLine("\nError: ID de método de pago inválido.");
            Pausa();
            return 0;
        }

        private void MostrarDetallesTemporales(List<(int IdVuelo, int Cantidad)> detalles, List<Vuelo> vuelos)
        {
            Console.WriteLine("╔══════════════════════════════════════════════════════════════════════╗");
            Console.WriteLine("║ {0,-10} {1,-15} {2,-15} {3,-10} {4,-12} {5,-12} ║", "ID VUELO", "ORIGEN", "DESTINO", "CANTIDAD", "VALOR UNIT.", "TOTAL");
            Console.WriteLine("╠══════════════════════════════════════════════════════════════════════╣");
            if (!detalles.Any())
            {
                Console.WriteLine("║ No hay detalles agregados.                                            ║");
            }
            else
            {
                foreach (var detalle in detalles)
                {
                    var vuelo = vuelos.FirstOrDefault(v => v.IdVuelo == detalle.IdVuelo) ?? new Vuelo { CiudadOrigen = "N/A", CiudadDestino = "N/A", Valor = 0 };
                    decimal total = detalle.Cantidad * vuelo.Valor;
                    Console.WriteLine("║ {0,-10} {1,-15} {2,-15} {3,-10} {4,-12:C} {5,-12:C} ║",
                        detalle.IdVuelo, vuelo.CiudadOrigen, vuelo.CiudadDestino, detalle.Cantidad, vuelo.Valor, total);
                }
            }
            Console.WriteLine("╚══════════════════════════════════════════════════════════════════════╝");
        }
        private async Task MostrarFacturaTemporal(int idCliente, List<(int IdVuelo, int Cantidad)> detalles, decimal subtotal, decimal descuento, decimal iva, decimal total, int idMetodoPago, int idEmpleado, List<Vuelo> vuelos)
        {
            MostrarBanner();
            Console.WriteLine("╔══════════════════════════════════════╗");
            Console.WriteLine("║ FACTURA TEMPORAL - Viajecitos SA     ║");
            Console.WriteLine("╚══════════════════════════════════════╝");

            var clientes = await controlador.ObtenerTodosClientes();
            var cliente = clientes.FirstOrDefault(c => c.IdCliente == idCliente);
            string nombreCliente = cliente?.Nombre ?? "Desconocido";
            string emailCliente = cliente?.Email ?? "N/A";
            string documentoCliente = cliente?.DocumentoIdentidad ?? "N/A";
            string metodoPago;
            if (idMetodoPago == 1)
            {
                metodoPago = "Tarjeta de Débito";
            }
            else if (idMetodoPago == 2)
            {
                metodoPago = "Efectivo";
            }
            else if (idMetodoPago == 3)
            {
                metodoPago = "Tarjeta de Crédito";
            }
            else
            {
                metodoPago = "Desconocido";
            }

            Console.WriteLine($"\nFecha: {DateTime.Now:yyyy-MM-dd HH:mm:ss}");
            Console.WriteLine($"Cliente: {nombreCliente}");
            Console.WriteLine($"Email: {emailCliente}");
            Console.WriteLine($"Documento: {documentoCliente}");
            Console.WriteLine($"ID Empleado: {idEmpleado}");
            Console.WriteLine($"Método de Pago: {metodoPago}");
            Console.WriteLine("\nDetalles de la factura:");
            MostrarDetallesTemporales(detalles, vuelos);
            Console.WriteLine($"\nSubtotal: {subtotal:C}");
            Console.WriteLine($"Descuento: {descuento:C}");
            Console.WriteLine($"IVA (15%): {iva:C}");
            Console.WriteLine($"Total: {total:C}");
        }
        private async Task ConsultarFacturas()
        {
            MostrarBanner();
            Console.WriteLine("╔══════════════════════════════════════╗");
            Console.WriteLine("║ Consultar Facturas por Cliente       ║");
            Console.WriteLine("╚══════════════════════════════════════╝");

            int idCliente = await SeleccionarORegistrarCliente();
            if (idCliente <= 0)
            {
                Console.WriteLine("\nNo se seleccionó un cliente válido.");
                Pausa();
                return;
            }

            try
            {
                var facturas = await controlador.ObtenerFacturasCliente(idCliente);
                var cliente = (await controlador.ObtenerTodosClientes()).FirstOrDefault(c => c.IdCliente == idCliente);
                if (cliente == null)
                {
                    Console.WriteLine("\nError: No se encontró información del cliente.");
                    Pausa();
                    return;
                }

                Console.WriteLine($"\nCliente: {cliente.Nombre} (ID: {idCliente})");
                Console.WriteLine($"Email: {cliente.Email}");
                Console.WriteLine($"Documento: {cliente.DocumentoIdentidad}");
                Console.WriteLine("\nFacturas encontradas:");
                Console.WriteLine("╔══════════════════════════════════════════════════════════════════════╗");
                Console.WriteLine("║ {0,-15} {1,-19} {2,-12} {3,-12} {4,-12} {5,-12} ║",
                    "NÚMERO FACTURA", "FECHA", "SUBTOTAL", "DESCUENTO", "IVA", "TOTAL");
                Console.WriteLine("╠══════════════════════════════════════════════════════════════════════╩");
                if (facturas.Any())
                {
                    foreach (var factura in facturas.OrderBy(f => f.FechaEmision))
                    {
                        Console.WriteLine("║ {0,-15} {1,-19:yyyy-MM-dd HH:mm:ss} {2,-12:C} {3,-12:C} {4,-12:C} {5,-12:C} ║",
                            factura.NumeroFactura,
                            factura.FechaEmision,
                            factura.Subtotal,
                            factura.Descuento,
                            factura.Iva,
                            factura.Total);
                    }
                }
                else
                {
                    Console.WriteLine("║ No hay facturas para este cliente.                                    ║");
                }
                Console.WriteLine("╚══════════════════════════════════════════════════════════════════════╝");

                if (facturas.Any() && PreguntarContinuar("¿Desea ver los detalles de una factura? (S/N)"))
                {
                    Console.Write("\nIngrese el número de factura (ej. FAC20250602222303): ");
                    string numeroFactura = scanner.NextLine()?.Trim();
                    if (string.IsNullOrWhiteSpace(numeroFactura))
                    {
                        Console.WriteLine("\nError: Número de factura inválido.");
                        Pausa();
                        return;
                    }

                    var facturaSeleccionada = facturas.FirstOrDefault(f => f.NumeroFactura == numeroFactura);
                    if (facturaSeleccionada == null)
                    {
                        Console.WriteLine("\nError: Factura no encontrada.");
                        Pausa();
                        return;
                    }

                    var clienteFacturas = new ClienteFacturas
                    {
                        ClienteId = cliente.IdCliente,
                        Nombre = cliente.Nombre,
                        Email = cliente.Email,
                        DocumentoIdentidad = cliente.DocumentoIdentidad
                    };
                    MostrarDetallesFactura(facturaSeleccionada, clienteFacturas);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"\nError al consultar facturas: {e.Message}");
            }
            Pausa();
        }
    }
}