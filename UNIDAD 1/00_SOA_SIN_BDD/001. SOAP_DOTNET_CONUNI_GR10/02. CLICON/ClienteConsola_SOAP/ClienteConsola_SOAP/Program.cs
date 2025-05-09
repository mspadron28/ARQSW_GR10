using System;
using System.IO;
using System.Threading;
using ClienteConsola_SOAP.ConversionService;

namespace ClienteConsola_SOAP
{
    class Program
    {
        static void Main(string[] args)
        {
            bool loggedIn = Login();
            if (loggedIn)
            {
                ShowConversionMenu();
            }
        }

        private static bool Login()
        {
            const string correctUsername = "MasterMonster";
            const string correctPassword = "Monster9";
            bool loggedIn = false;

            Console.WriteLine("╔══════════════════════╗");
            Console.WriteLine("║                      ║");
            Console.WriteLine("║     BIENVENIDO       ║");
            Console.WriteLine("║                      ║");
            Console.WriteLine("╚══════════════════════╝");

            do
            {
                Console.WriteLine("\nIngrese sus credenciales:");

                Console.Write("╔══════════════════════╗\n");
                Console.Write("║            Usuario:  ║\n");
                Console.Write("╚══════════════════════╝\n");
                Console.SetCursorPosition("║ Usuario:             ║".Length + 1, Console.CursorTop - 1);
                string username = Console.ReadLine();

                Console.Write("╔══════════════════════╗\n");
                Console.Write("║          Contraseña: ║\n");
                Console.Write("╚══════════════════════╝\n");
                Console.SetCursorPosition("║ Contraseña:          ║".Length + 1, Console.CursorTop - 1);
                string password = ReadPassword();

                if (username == correctUsername && password == correctPassword)
                {
                    loggedIn = true;
                    Console.WriteLine("\nInicio de sesión exitoso.\n");
                    break;
                }
                else if (username != correctUsername)
                {
                    Console.WriteLine("\nUsuario incorrecto. Intente nuevamente.\n");
                }
                else
                {
                    Console.WriteLine("\nContraseña incorrecta. Intente nuevamente.\n");
                }

            } while (true);

            return loggedIn;
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

        private static void ShowConversionMenu()
        {
            ConversionServiceClient client = new ConversionServiceClient();
            bool exit = false;

            do
            {
                Console.Clear();
                Console.WriteLine("╔══════════════════════════════════╗");
                Console.WriteLine("║   *** Conversor de Unidades ***  ║");
                Console.WriteLine("╠══════════════════════════════════╗");
                Console.WriteLine("║ Seleccione el tipo de Conversión:║");
                Console.WriteLine("║                                  ║");
                Console.WriteLine("║ 1. Centímetros a Pulgadas        ║");
                Console.WriteLine("║ 2. Pulgadas a Centímetros        ║");
                Console.WriteLine("║ 3. Metros a Pies                 ║");
                Console.WriteLine("║ 4. Pies a Metros                 ║");
                Console.WriteLine("║ 5. Salir                         ║");
                Console.WriteLine("╚══════════════════════════════════╝");

                int opcion;
                while (!int.TryParse(Console.ReadLine(), out opcion) || opcion < 1 || opcion > 5)
                {
                    Console.WriteLine("Opción no válida, por favor seleccione una opción válida (1-5):");
                }

                if (opcion == 5)
                {
                    exit = true;
                    break;
                }

                Console.WriteLine("Ingrese el valor a convertir:");
                double valor;
                while (!double.TryParse(Console.ReadLine(), out valor))
                {
                    Console.WriteLine("Valor no válido, por favor ingrese un valor numérico:");
                }

                double resultado = 0;
                string unidadOrigen = "";
                string unidadDestino = "";

                switch (opcion)
                {
                    case 1:
                        resultado = client.CentimetersToInches(valor);
                        unidadOrigen = "cm";
                        unidadDestino = "in";
                        break;
                    case 2:
                        resultado = client.InchesToCentimeters(valor);
                        unidadOrigen = "in";
                        unidadDestino = "cm";
                        break;
                    case 3:
                        resultado = client.MetersToYards(valor);
                        unidadOrigen = "m";
                        unidadDestino = "yd";
                        break;
                    case 4:
                        resultado = client.YardsToMeters(valor);
                        unidadOrigen = "yd";
                        unidadDestino = "m";
                        break;
                    default:
                        Console.WriteLine("Opción no válida");
                        break;
                }

                if (!string.IsNullOrEmpty(unidadOrigen) && !string.IsNullOrEmpty(unidadDestino))
                {
                    Console.WriteLine($"\n{valor} {unidadOrigen} es equivalente a {resultado:F5} {unidadDestino}.");
                }

                Console.WriteLine("\nPresione cualquier tecla para continuar...");
                Console.ReadKey();

            } while (!exit);

            client.Close();
            Console.WriteLine("Presione cualquier tecla para salir...");
            Console.ReadKey();
        }
    }
}
