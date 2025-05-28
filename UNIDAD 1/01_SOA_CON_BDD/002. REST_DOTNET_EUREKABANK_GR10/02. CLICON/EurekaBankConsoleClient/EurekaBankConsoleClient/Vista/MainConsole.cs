using EurekaBankConsoleClient.Controlador;
using EurekaBankConsoleClient.Models;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EurekaBankConsoleClient.Vista
{
    public class MainConsole
    {
        private readonly EurekaController _controlador;
        private readonly System.IO.TextReader _reader;

        public MainConsole()
        {
            _controlador = new EurekaController();
            _reader = Console.In;
            Console.OutputEncoding = Encoding.UTF8;
        }

        public void Start()
        {
            MostrarSullivan();
            if (!IniciarSesion())
            {
                Console.WriteLine("No se pudo iniciar sesión. El programa se cerrará.");
                return;
            }
            LimpiarConsola();
            MostrarMenu();
        }

        private bool IniciarSesion()
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
                Console.Write("║ Usuario:                 ║ ");
                string usuario = _reader.ReadLine()?.Trim();
                Console.WriteLine("╚══════════════════════════╝");
                Console.WriteLine("╔══════════════════════════╗");
                Console.Write("║ Contraseña:              ║ ");
                string contraseña = _reader.ReadLine()?.Trim();
                Console.WriteLine("╚══════════════════════════╝");

                try
                {
                    if (string.IsNullOrEmpty(usuario) || string.IsNullOrEmpty(contraseña))
                    {
                        Console.WriteLine("\nPor favor, complete todos los campos.");
                        if (!PreguntarContinuar())
                        {
                            return false;
                        }
                        continue;
                    }
                    bool success = _controlador.IniciarSesion(usuario, contraseña).GetAwaiter().GetResult();
                    if (success)
                    {
                        Console.WriteLine("\nInicio de sesión exitoso.");
                        Pausa();
                        return true;
                    }
                    else
                    {
                        Console.WriteLine("\nUsuario o contraseña incorrectos.");
                        if (!PreguntarContinuar())
                        {
                            return false;
                        }
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("\nError al iniciar sesión: " + e.Message);
                    if (!PreguntarContinuar())
                    {
                        return false;
                    }
                }
            }
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

        private void LimpiarConsola()
        {
            try
            {
                Process.Start(new ProcessStartInfo("cmd", "/c cls") { RedirectStandardOutput = true, UseShellExecute = false, CreateNoWindow = true }).WaitForExit();
            }
            catch
            {
                for (int i = 0; i < 50; i++)
                {
                    Console.WriteLine();
                }
            }
        }

        private void Pausa()
        {
            Console.WriteLine("\nPresione Enter para continuar...");
            _reader.ReadLine();
            LimpiarConsola();
        }

        private bool PreguntarContinuar()
        {
            while (true)
            {
                Console.Write("\n¿Desea continuar? (S/N): ");
                string respuesta = _reader.ReadLine()?.Trim().ToLower();
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

        private void MostrarMenu()
        {
            while (true)
            {
                LimpiarConsola();
                MostrarSullivan();
                Console.WriteLine("╔══════════════════════════════════════╗");
                Console.WriteLine("║      EurekaBank Console Client       ║");
                Console.WriteLine("╠══════════════════════════════════════╣");
                Console.WriteLine("║ Seleccione una opción:               ║");
                Console.WriteLine("║                                      ║");
                Console.WriteLine("║ 1. Consultar Movimientos             ║");
                Console.WriteLine("║ 2. Registrar Depósito                ║");
                Console.WriteLine("║ 3. Registrar Retiro                  ║");
                Console.WriteLine("║ 4. Realizar Transferencia            ║");
                Console.WriteLine("║ 5. Verificar Saldo                   ║");
                Console.WriteLine("║ 6. Salir                             ║");
                Console.WriteLine("╚══════════════════════════════════════╝");
                Console.Write("\nSeleccione una opción (1-6): ");
                string choice = _reader.ReadLine();
                switch (choice)
                {
                    case "1":
                        LimpiarConsola();
                        ConsultarMovimientos();
                        break;
                    case "2":
                        LimpiarConsola();
                        RegistrarDeposito();
                        break;
                    case "3":
                        LimpiarConsola();
                        RegistrarRetiro();
                        break;
                    case "4":
                        LimpiarConsola();
                        RealizarTransferencia();
                        break;
                    case "5":
                        LimpiarConsola();
                        VerificarSaldo();
                        break;
                    case "6":
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

        private void ConsultarMovimientos()
        {
            MostrarSullivan();
            Console.WriteLine("║ Consultar Movimientos                ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el número de cuenta: ");
            string cuenta = _reader.ReadLine()?.Trim();
            try
            {
                List<Movimiento> movimientos = _controlador.ConsultarMovimientos(cuenta).GetAwaiter().GetResult();
                Console.WriteLine($"\nMovimientos de la cuenta {cuenta}:");
                Console.WriteLine("{0,-10} {1,-10} {2,-12} {3,-10} {4,-20} {5,-10}", "CUENTA", "NROMOV", "FECHA", "TIPO", "ACCIÓN", "IMPORTE");
                Console.WriteLine("------------------------------------------------------------");
                foreach (var mov in movimientos)
                {
                    Console.WriteLine("{0,-10} {1,-10} {2,-12:yyyy-MM-dd} {3,-10} {4,-20} {5,-10:F2}",
                        mov.Cuenta,
                        mov.NroMov,
                        mov.Fecha,
                        mov.Tipo,
                        mov.Accion,
                        mov.Importe);
                }
                if (movimientos.Count == 0)
                {
                    Console.WriteLine("\nNo se encontraron movimientos para la cuenta.");
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al consultar movimientos: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
        }

        private void RegistrarDeposito()
        {
            MostrarSullivan();
            Console.WriteLine("║ Registrar Depósito                   ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el código de empleado: ");
            string codEmp = _reader.ReadLine()?.Trim();
            Console.Write("Ingrese el número de cuenta: ");
            string cuenta = _reader.ReadLine()?.Trim();
            Console.Write("Ingrese el importe: ");
            string importeText = _reader.ReadLine()?.Trim().Replace(",", ".");
            try
            {
                double importe = double.Parse(importeText);
                if (importe <= 0)
                {
                    Console.WriteLine("\nEl importe debe ser mayor que 0.");
                }
                else
                {
                    bool exito = _controlador.RegistrarDeposito(cuenta, importe, codEmp).GetAwaiter().GetResult();
                    Console.WriteLine(exito ? "\nDepósito registrado exitosamente." : "\nError al registrar el depósito.");
                }
            }
            catch (FormatException)
            {
                Console.WriteLine("\nError: Ingrese un importe válido (use punto como separador decimal).");
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al registrar el depósito: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
        }

        private void RegistrarRetiro()
        {
            MostrarSullivan();
            Console.WriteLine("║ Registrar Retiro                     ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el número de cuenta: ");
            string cuenta = _reader.ReadLine()?.Trim();
            Console.Write("Ingrese el importe: ");
            string importeText = _reader.ReadLine()?.Trim().Replace(",", ".");
            Console.Write("Ingrese el código de empleado: ");
            string codEmp = _reader.ReadLine()?.Trim();
            try
            {
                double importe = double.Parse(importeText);
                if (importe <= 0)
                {
                    Console.WriteLine("\nEl importe debe ser mayor que 0.");
                    return;
                }
                double costo = _controlador.ObtenerCostoMovimiento(cuenta).GetAwaiter().GetResult();
                Console.WriteLine($"\nEl costo del movimiento es: {costo:F2}");
                Console.Write("\n¿Desea continuar? (S/N): ");
                if (_reader.ReadLine()?.Trim().ToLower() != "s")
                {
                    Console.WriteLine("\nOperación cancelada.");
                    return;
                }
                bool exito = _controlador.RegistrarRetiro(cuenta, importe, codEmp).GetAwaiter().GetResult();
                Console.WriteLine(exito ? "\nRetiro registrado exitosamente." : "\nError al registrar el retiro.");
            }
            catch (FormatException)
            {
                Console.WriteLine("\nError: Ingrese un importe válido (use punto como separador decimal).");
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al registrar el retiro: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
        }

        private void RealizarTransferencia()
        {
            MostrarSullivan();
            Console.WriteLine("║ Realizar Transferencia               ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese la cuenta origen: ");
            string cuentaOrigen = _reader.ReadLine()?.Trim();
            Console.Write("Ingrese la cuenta destino: ");
            string cuentaDestino = _reader.ReadLine()?.Trim();
            Console.Write("Ingrese el importe: ");
            string importeText = _reader.ReadLine()?.Trim().Replace(",", ".");
            Console.Write("Ingrese el código de empleado: ");
            string codEmp = _reader.ReadLine()?.Trim();
            try
            {
                double importe = double.Parse(importeText);
                if (importe <= 0)
                {
                    Console.WriteLine("\nEl importe debe ser mayor que 0.");
                    return;
                }
                double costo = _controlador.ObtenerCostoMovimiento(cuentaOrigen).GetAwaiter().GetResult();
                Console.WriteLine($"\nEl costo del movimiento es: {costo:F2}");
                Console.Write("\n¿Desea continuar? (S/N): ");
                if (_reader.ReadLine()?.Trim().ToLower() != "s")
                {
                    Console.WriteLine("\nOperación cancelada.");
                    return;
                }
                bool exito = _controlador.RealizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp).GetAwaiter().GetResult();
                Console.WriteLine(exito ? "\nTransferencia realizada exitosamente." : "\nError al realizar la transferencia.");
            }
            catch (FormatException)
            {
                Console.WriteLine("\nError: Ingrese un importe válido (use punto como separador decimal).");
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al realizar la transferencia: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
        }

        private void VerificarSaldo()
        {
            MostrarSullivan();
            Console.WriteLine("║ Verificar Saldo                      ║");
            Console.WriteLine("╚══════════════════════════════════════╝");
            Console.Write("\nIngrese el número de cuenta: ");
            string cuenta = _reader.ReadLine()?.Trim();
            try
            {
                double saldo = _controlador.VerificarSaldo(cuenta).GetAwaiter().GetResult();
                Console.WriteLine($"\nEl saldo de la cuenta {cuenta} es: {saldo:F2}");
            }
            catch (Exception e)
            {
                Console.WriteLine("\nError al verificar el saldo: " + e.Message);
            }
            if (!PreguntarContinuar())
            {
                Console.WriteLine("\nSaliendo del programa. ¡Hasta pronto!");
                Environment.Exit(0);
            }
        }
    }
}
