using System.Diagnostics;
using System.Net.Http;
using System.Text;
using System.Text.Json;

namespace CONUNI_CLICON_GR10
{
    class Program
    {
        private static readonly HttpClient client = new HttpClient(); private const string BaseUrl = "http://10.40.23.154:5000/api/Conversion/";
        static async Task Main(string[] args)
        {
            // Configurar la salida para usar UTF-8
            Console.OutputEncoding = Encoding.UTF8;

            // Paso 1: Solicitar inicio de sesión
            bool loggedIn = await IniciarSesionAsync();

            if (!loggedIn)
            {
                Console.WriteLine("No se pudo iniciar sesión. El programa se cerrará.");
                return;
            }

            // Limpiar y mostrar menú tras login
            LimpiarConsola();

            await MostrarMenuAsync();
        }

        private static void LimpiarConsola()
        {
            try
            {
                // Ejecutar el comando 'cls' en Windows para limpiar la consola
                ProcessStartInfo psi = new ProcessStartInfo("cmd", "/c cls")
                {
                    RedirectStandardOutput = true,
                    UseShellExecute = false,
                    CreateNoWindow = true
                };
                Process process = Process.Start(psi);
                process.WaitForExit();
            }
            catch (Exception)
            {
                // Si falla, usar líneas en blanco como alternativa
                for (int i = 0; i < 50; i++)
                {
                    Console.WriteLine();
                }
            }
        }

        private static async Task<bool> IniciarSesionAsync()
        {
            while (true)
            {
                LimpiarConsola();
                MostrarSullivan();
                Console.WriteLine("╔══════════════════════════════════════╗");
                Console.WriteLine("║         Iniciar Sesión               ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.WriteLine("\nIngrese sus credenciales:\n");

                Console.WriteLine("╔══════════════════════════╗");
                Console.WriteLine("║ Usuario:                 ║ ");
                Console.WriteLine("╚══════════════════════════╝");
                string usuario = Console.ReadLine();

                Console.WriteLine("╔══════════════════════════╗");
                Console.WriteLine("║ Contraseña:              ║ ");
                Console.WriteLine("╚══════════════════════════╝");
                string contraseña = ReadPassword();

                try
                {
                    string url = $"{BaseUrl}login?usuario={Uri.EscapeDataString(usuario)}&contraseña={Uri.EscapeDataString(contraseña)}";
                    HttpResponseMessage response = await client.GetAsync(url);
                    response.EnsureSuccessStatusCode();
                    string responseBody = await response.Content.ReadAsStringAsync();
                    bool success = JsonSerializer.Deserialize<bool>(responseBody);

                    if (success)
                    {
                        Console.WriteLine("\nInicio de sesión exitoso.");
                        Pausa();
                        return true;
                    }
                    else
                    {
                        Console.WriteLine("\nUsuario o contraseña incorrectos. Intente de nuevo.");
                        Console.Write("\n¿Desea continuar? (S/N): ");
                        string respuesta = Console.ReadLine()?.Trim().ToLower();
                        if (respuesta != "s")
                        {
                            Console.WriteLine("\nNo se pudo iniciar sesión. El programa se cerrará.");
                            return false;
                        }
                    }
                }
                catch (HttpRequestException e)
                {
                    Console.WriteLine("\nError al intentar iniciar sesión: " + e.Message);
                    Console.Write("\n¿Desea continuar? (S/N): ");
                    string respuesta = Console.ReadLine()?.Trim().ToLower();
                    if (respuesta != "s")
                    {
                        Console.WriteLine("\nNo se pudo iniciar sesión. El programa se cerrará.");
                        return false;
                    }
                }
            }
        }

        private static string ReadPassword()
        {
            string password = string.Empty;
            ConsoleKeyInfo info;
            do
            {
                info = Console.ReadKey(true);
                if (info.Key != ConsoleKey.Backspace && info.Key != ConsoleKey.Enter)
                {
                    password += info.KeyChar;
                }
                else if (info.Key == ConsoleKey.Backspace && password.Length > 0)
                {
                    password = password.Substring(0, password.Length - 1);
                }
            } while (info.Key != ConsoleKey.Enter);

            Console.WriteLine();
            return password;
        }

        private static async Task MostrarMenuAsync()
        {
            while (true)
            {
                LimpiarConsola();
                MostrarSullivan();
                Console.WriteLine("╔══════════════════════════════════════╗");
                Console.WriteLine("║    *** Conversor de Unidades ***     ║");
                Console.WriteLine("╠══════════════════════════════════════╣");
                Console.WriteLine("║ Seleccione el tipo de conversión:    ║");
                Console.WriteLine("║                                      ║");
                Console.WriteLine("║ 1. Pulgadas a Centímetros            ║");
                Console.WriteLine("║ 2. Centímetros a Pulgadas            ║");
                Console.WriteLine("║ 3. Metros a Pies                     ║");
                Console.WriteLine("║ 4. Pies a Metros                     ║");
                Console.WriteLine("║ 5. Metros a Yardas                   ║");
                Console.WriteLine("║ 6. Yardas a Metros                   ║");
                Console.WriteLine("║ 7. Salir                             ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.Write("\nSeleccione una opción (1-7): ");

                string opcion = Console.ReadLine();

                switch (opcion)
                {
                    case "1":
                        LimpiarConsola();
                        await ConvertirPulgadasACentimetrosAsync();
                        break;
                    case "2":
                        LimpiarConsola();
                        await ConvertirCentimetrosAPulgadasAsync();
                        break;
                    case "3":
                        LimpiarConsola();
                        await ConvertirMetrosAPiesAsync();
                        break;
                    case "4":
                        LimpiarConsola();
                        await ConvertirPiesAMetrosAsync();
                        break;
                    case "5":
                        LimpiarConsola();
                        await ConvertirMetrosAYardasAsync();
                        break;
                    case "6":
                        LimpiarConsola();
                        await ConvertirYardasAMetrosAsync();
                        break;
                    case "7":
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

        private static void Pausa()
        {
            Console.WriteLine("Presione Enter para continuar...");
            Console.ReadLine();
            LimpiarConsola();
        }

        private static bool PreguntarContinuar()
        {
            while (true)
            {
                Console.Write("\n¿Desea continuar? (S/N): ");
                string respuesta = Console.ReadLine()?.Trim().ToLower();
                if (respuesta == "s")
                {
                    return true;
                }
                else if (respuesta == "n")
                {
                    return false;
                }
                else
                {
                    Console.WriteLine("Por favor, ingrese 'S' para sí o 'N' para no.");
                }
            }
        }

        private static async Task ConvertirPulgadasACentimetrosAsync()
        {
            MostrarSullivan();
            Console.WriteLine("║ Convertir Pulgadas a Centímetros     ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el valor en pulgadas: ");
            try
            {
                double pulgadas = double.Parse(Console.ReadLine());
                if (pulgadas < 0)
                {
                    Console.WriteLine("\nPor favor, ingrese un valor no negativo.");
                }
                else
                {
                    string url = $"{BaseUrl}pulgadas-a-centimetros?pulgadas={pulgadas}";
                    HttpResponseMessage response = await client.GetAsync(url);
                    response.EnsureSuccessStatusCode();
                    string responseBody = await response.Content.ReadAsStringAsync();
                    double centimetros = JsonSerializer.Deserialize<double>(responseBody);
                    Console.WriteLine($"\n{pulgadas:F2} pulgadas = {centimetros:F2} centímetros");
                }
            }
            catch (FormatException)
            {
                Console.WriteLine("\nError: Por favor, ingrese un número válido.");
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine("\nError al realizar la conversión: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
            LimpiarConsola();
        }

        private static async Task ConvertirCentimetrosAPulgadasAsync()
        {
            MostrarSullivan();
            Console.WriteLine("║ Convertir Centímetros a Pulgadas     ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el valor en centímetros: ");
            try
            {
                double centimetros = double.Parse(Console.ReadLine());
                if (centimetros < 0)
                {
                    Console.WriteLine("\nPor favor, ingrese un valor no negativo.");
                }
                else
                {
                    string url = $"{BaseUrl}centimetros-a-pulgadas?centimetros={centimetros}";
                    HttpResponseMessage response = await client.GetAsync(url);
                    response.EnsureSuccessStatusCode();
                    string responseBody = await response.Content.ReadAsStringAsync();
                    double pulgadas = JsonSerializer.Deserialize<double>(responseBody);
                    Console.WriteLine($"\n{centimetros:F2} centímetros = {pulgadas:F2} pulgadas");
                }
            }
            catch (FormatException)
            {
                Console.WriteLine("\nError: Por favor, ingrese un número válido.");
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine("\nError al realizar la conversión: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
            LimpiarConsola();
        }

        private static async Task ConvertirMetrosAPiesAsync()
        {
            MostrarSullivan();
            Console.WriteLine("║ Convertir Metros a Pies              ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el valor en metros: ");
            try
            {
                double metros = double.Parse(Console.ReadLine());
                if (metros < 0)
                {
                    Console.WriteLine("\nPor favor, ingrese un valor no negativo.");
                }
                else
                {
                    string url = $"{BaseUrl}metros-a-pies?metros={metros}";
                    HttpResponseMessage response = await client.GetAsync(url);
                    response.EnsureSuccessStatusCode();
                    string responseBody = await response.Content.ReadAsStringAsync();
                    double pies = JsonSerializer.Deserialize<double>(responseBody);
                    Console.WriteLine($"\n{metros:F2} metros = {pies:F2} pies");
                }
            }
            catch (FormatException)
            {
                Console.WriteLine("\nError: Por favor, ingrese un número válido.");
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine("\nError al realizar la conversión: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
            LimpiarConsola();
        }

        private static async Task ConvertirPiesAMetrosAsync()
        {
            MostrarSullivan();
            Console.WriteLine("║ Convertir Pies a Metros              ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el valor en pies: ");
            try
            {
                double pies = double.Parse(Console.ReadLine());
                if (pies < 0)
                {
                    Console.WriteLine("\nPor favor, ingrese un valor no negativo.");
                }
                else
                {
                    string url = $"{BaseUrl}pies-a-metros?pies={pies}";
                    HttpResponseMessage response = await client.GetAsync(url);
                    response.EnsureSuccessStatusCode();
                    string responseBody = await response.Content.ReadAsStringAsync();
                    double metros = JsonSerializer.Deserialize<double>(responseBody);
                    Console.WriteLine($"\n{pies:F2} pies = {metros:F2} metros");
                }
            }
            catch (FormatException)
            {
                Console.WriteLine("\nError: Por favor, ingrese un número válido.");
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine("\nError al realizar la conversión: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
            LimpiarConsola();
        }

        private static async Task ConvertirMetrosAYardasAsync()
        {
            MostrarSullivan();
            Console.WriteLine("║ Convertir Metros a Yardas            ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el valor en metros: ");
            try
            {
                double metros = double.Parse(Console.ReadLine());
                if (metros < 0)
                {
                    Console.WriteLine("\nPor favor, ingrese un valor no negativo.");
                }
                else
                {
                    string url = $"{BaseUrl}metros-a-yardas?metros={metros}";
                    HttpResponseMessage response = await client.GetAsync(url);
                    response.EnsureSuccessStatusCode();
                    string responseBody = await response.Content.ReadAsStringAsync();
                    double yardas = JsonSerializer.Deserialize<double>(responseBody);
                    Console.WriteLine($"\n{metros:F2} metros = {yardas:F2} yardas");
                }
            }
            catch (FormatException)
            {
                Console.WriteLine("\nError: Por favor, ingrese un número válido.");
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine("\nError al realizar la conversión: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
            LimpiarConsola();
        }

        private static async Task ConvertirYardasAMetrosAsync()
        {
            MostrarSullivan();
            Console.WriteLine("║ Convertir Yardas a Metros            ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el valor en yardas: ");
            try
            {
                double yardas = double.Parse(Console.ReadLine());
                if (yardas < 0)
                {
                    Console.WriteLine("\nPor favor, ingrese un valor no negativo.");
                }
                else
                {
                    string url = $"{BaseUrl}yardas-a-metros?yardas={yardas}";
                    HttpResponseMessage response = await client.GetAsync(url);
                    response.EnsureSuccessStatusCode();
                    string responseBody = await response.Content.ReadAsStringAsync();
                    double metros = JsonSerializer.Deserialize<double>(responseBody);
                    Console.WriteLine($"\n{yardas:F2} yardas = {metros:F2} metros");
                }
            }
            catch (FormatException)
            {
                Console.WriteLine("\nError: Por favor, ingrese un número válido.");
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine("\nError al realizar la conversión: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
            LimpiarConsola();
        }

        private static void MostrarSullivan()
        {
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⡛⠷⣂⣄⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠟⢀⣼⣿⡿⠿⢷⡿⠓⠿⣖⣤⣤⣤⢄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣦⡸⣤⣀⣀⣀⡾⢥⡀⠀⠙⡿⣽⢿⣌⠉⢻⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣼⠟⢋⣭⡟⣏⠷⠖⣽⣦⢀⡇⡬⢿⠿⡈⢹⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣽⣶⣶⠾⠟⠚⣽⠒⠉⢦⠽⠟⠸⡐⣿⡨⠈⠉⠀⡇⠀⢣⠈⣺⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⣿⠁⠠⡀⠀⠀⠙⠧⣤⣬⣆⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⣾⡶⠗⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢿⣏⠀⠀⠈⠲⣄⠀⠀⠀⠀⠀⢀⡀⠀⢀⠽⠒⠀⠀⠀⠀⢹⣣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢹⣯⠀⠀⠀⠀⠀⠙⠲⢄⡴⣢⣆⠼⠚⠁⠀⠀⠀⡄⠀⠀⠀⠻⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣤⣴⣶⣄⣤⣤⣤⣼⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⠀⠙⢾⣆⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⣿⠶⠀⠉⠀⠀⠀⠀⠘⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⠀⠀⢰⠀⠀⠀⠈⠻⢮⣖⠄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣵⠖⠀⠀⠀⠀⠀⠀⠀⠈⠙⢦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠇⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠈⠛⠾⣔⡄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⢀⣤⣴⡷⠧⠤⠤⣤⣄⡀⡀⠀⠀⠀⠀⠀⢳⠈⠉⠀⠀⠀⠀⠠⣤⡤⠴⡺⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⠀⠈⠙⠾⣕⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⢀⢰⣿⡵⠛⠲⠦⠤⣄⠀⠀⠙⣧⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠈⠻⢯⡦⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⢠⣾⣯⠼⠤⠤⣄⠀⠀⠈⣷⣀⣀⣼⣧⡤⠴⠶⠶⠶⠶⠶⠒⡒⠒⠒⠒⠲⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣯⣤⡀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⣰⡟⣹⡇⠀⠀⣀⣬⠷⠚⠛⠉⠉⠁⢰⠀⠀⠀⠀⠀⠀⠀⢀⠔⠁⠀⠀⠀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠀⡰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠃⣼⣼⣿⠉⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⢀⣧⠟⠁⢙⠷⠋⠁⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣄⠀⠀⢠⡞⠁⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣻⠿⠃⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⣰⡿⠁⠀⠀⡔⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠀⠀⠀⠈⢣⡴⠋⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣻⠁⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⣼⠞⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠠⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠖⠛⠛⠛⠒⠲⠶⢯⣤⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡻⠃⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠰⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡻⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠞⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠘⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⠞⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠝⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠻⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡴⠟⠻⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠹⡿⣄⠀⢀⣀⣀⣀⣀⣠⣤⣴⡶⠛⠉⠁⠀⠀⠀⠈⠳⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡯⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠈⠫⢷⣾⣿⢻⠿⠛⠉⠉⣿⠇⠀⠀⠠⠀⠂⠈⠀⢀⠈⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠿⣿⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⢠⣿⠀⠀⠐⠀⠀⠄⠐⠈⠀⠀⠀⠀⠙⢷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⠋⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⣿⠀⠀⠄⠂⠀⠠⠀⢀⠐⠈⠀⠐⠀⠀⠈⠛⠷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠏⠀⠀⠀⢿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⣟⠀⠀⢀⠠⠐⠀⢀⠀⠀⠠⠐⠀⠀⠁⡀⠀⠀⠀⢉⣻⡷⢆⣀⣀⠀⣠⡶⠋⠀⢀⠀⠀⠀⢸⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⣟⠀⠀⠀⠀⡀⠠⠀⠀⠈⠀⢀⠠⠈⠀⠀⡀⠁⠠⠀⠈⠉⠉⣽⠷⠚⠉⠀⠀⠀⠈⠒⠐⠊⠀⣷⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠈⠀⠀⢀⠀⠈⠀⠐⠀⠀⢀⠀⠁⠀⠀⠄⠀⠄⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⣿⣷⡀⠀⠁⠀⢀⠀⠁⠀⠄⠈⠀⠀⠠⠈⠀⠀⠄⠀⠠⠀⠐⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⢹⡏⢫⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢼⡏⠙⢦⣄⠂⠀⠀⠐⠀⡀⠄⠂⠁⠀⡀⠐⠀⠠⠐⠀⠀⠄⠀⠀⠀⠀⠀⠁⠀⠉⠲⢄⠀⠈⣷⢾⣿⣀⣀⣀⣀⠀⠀⠀⢀⡀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡇⡄⠀⠈⠓⠦⠨⣀⠀⠀⠀⡀⠄⠀⢀⠠⠐⠀⠀⡀⠂⡠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⡀⢿⡄⠙⠿⣍⡉⠹⡵⡀⣴⠟⠙⣧⠶⣶");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢿⠁⠘⡄⠀⠀⠀⠀⠀⠀⠈⠦⣀⡀⠠⠀⠀⢀⠠⠀⠀⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⢸⣧⠀⠀⠈⠙⠓⠳⠛⠛⠉⠁⠀⡴⠏");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⢀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⣷⣶⠶⢶⣶⢶⡋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠁⠀⠀⣿⠆⠀⡠⠒⠰⠄⠀⠀⠀⢠⢾⠋⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⣽⣿⡀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⡟⡇⠀⠀⠀⢿⣇⠀⠀⠀⠀⠀⠀⣀⣤⡀⠀⠀⠀⣠⢷⡏⠀⠀⠀⡠⠊⠀⠀⢀⣴⡟⠁⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠈⢽⣷⡄⠀⠀⠀⠏⠀⠉⠑⢦⠀⠀⠀⢿⣧⠀⠀⠀⢿⠇⠀⠀⠀⠀⠀⢀⠀⠀⠈⡇⠀⣴⣏⣀⠀⠀⣀⣀⣤⡤⣶⠿⠛⠁⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡟⠋⣉⠵⠂⢀⣀⠄⠀⠀⠀⢀⠰⠁⠀⠀⠘⣿⡄⠀⣼⡟⠀⠀⠀⠀⠀⠀⠀⠁⢢⣈⠀⠐⠤⣬⠉⣻⣿⡟⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡿⠉⢺⡗⠒⣄⣏⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⡁⠀⢿⣅⣀⠀⠀⠀⠀⠀⠀⠀⠀⡠⢽⣦⠚⠚⢯⠀⠱⣵⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣀⣤⡎⠀⢀⡞⠁⠀⢱⠀⠀⠀⢀⣀⣤⢶⡾⠛⠋⠁⠀⠀⠉⠋⠿⣶⣤⣄⡀⠀⠀⢰⠃⠀⠙⣆⣀⠈⣷⠶⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            Console.WriteLine("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠉⠹⠾⣽⣿⡤⣴⢶⣿⣴⣶⡟⡯⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠹⢿⣶⣶⣿⠶⣦⣿⡉⠛⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        }
    }
}