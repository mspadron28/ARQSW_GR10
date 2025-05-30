package ec.edu.monster.vista;

import ec.edu.monster.controlador.ViajecitosController;
import ec.edu.monster.ws.Compra;
import ec.edu.monster.ws.Vuelo;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based interface for Viajecitos SA client.
 *
 * @author MATIAS
 */
public class MainConsole {

    private final ViajecitosController controlador;
    private final Scanner scanner;
    private final SimpleDateFormat dateFormat;

    public MainConsole() {
        controlador = new ViajecitosController();
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));
    }

    public void start() {
        mostrarBanner();
        if (!mostrarMenuInicial()) {
            System.out.println("Saliendo del programa. ¡Hasta pronto!");
            return;
        }
        limpiarConsola();
        mostrarMenuPrincipal();
    }

    private void mostrarBanner() {
        limpiarConsola();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         Viajecitos SA                ║");
        System.out.println("║  Encuentra y compra tus boletos      ║");
        System.out.println("║      de avión de forma fácil         ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private void limpiarConsola() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    private void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
        limpiarConsola();
    }

    private boolean preguntarContinuar() {
        while (true) {
            System.out.print("\n¿Desea continuar? (S/N): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("s")) {
                return true;
            } else if (respuesta.equals("n")) {
                return false;
            } else {
                System.out.println("Por favor, ingrese 'S' para sí o 'N' para no.");
            }
        }
    }

    private boolean mostrarMenuInicial() {
        while (true) {
            mostrarBanner();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║    Bienvenido a Viajecitos SA        ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ Seleccione una opción:               ║");
            System.out.println("║                                      ║");
            System.out.println("║ 1. Iniciar Sesión                    ║");
            System.out.println("║ 2. Registrarse                       ║");
            System.out.println("║ 3. Salir                             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("\nSeleccione una opción (1-3): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    return iniciarSesion();
                case "2":
                    if (registrarClienteYUsuario()) {
                        System.out.println("\nRegistro exitoso. Por favor, inicie sesión.");
                        pausa();
                        continue;
                    }
                    return false;
                case "3":
                    return false;
                default:
                    System.out.println("\nOpción inválida. Por favor, seleccione una opción válida.");
                    pausa();
            }
        }
    }

    private boolean iniciarSesion() {
        while (true) {
            mostrarBanner();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         Iniciar Sesión               ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("\nIngrese sus credenciales:\n");
            System.out.println("╔══════════════════════════╗");
            System.out.print("║ Usuario:                 ║ ");
            String nombreUsuario = scanner.nextLine().trim();
            System.out.println("╚══════════════════════════╝");
            System.out.println("╔══════════════════════════╗");
            System.out.print("║ Contraseña:              ║ ");
            String claveUsuario = scanner.nextLine().trim();
            System.out.println("╚══════════════════════════╝");
            try {
                if (nombreUsuario.isEmpty() || claveUsuario.isEmpty()) {
                    System.out.println("\nPor favor, complete todos los campos.");
                    if (!preguntarContinuar()) {
                        return false;
                    }
                    continue;
                }
                boolean success = controlador.iniciarSesion(nombreUsuario, claveUsuario);
                if (success) {
                    System.out.println("\nInicio de sesión exitoso.");
                    pausa();
                    return true;
                } else {
                    System.out.println("\nUsuario o contraseña incorrectos.");
                    if (!preguntarContinuar()) {
                        return false;
                    }
                }
            } catch (Exception e) {
                System.out.println("\nError al iniciar sesión: " + e.getMessage());
                if (!preguntarContinuar()) {
                    return false;
                }
            }
        }
    }

    private boolean registrarClienteYUsuario() {
        mostrarBanner();
        System.out.println("║ Registrarse                          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\nIngrese sus datos:\n");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Nombre:                  ║ ");
        String nombre = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Email:                   ║ ");
        String email = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Documento de Identidad:  ║ ");
        String documentoIdentidad = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Nombre de Usuario:       ║ ");
        String nombreUsuario = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Contraseña:              ║ ");
        String claveUsuario = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        try {
            int idCliente = controlador.registrarCliente(nombre, email, documentoIdentidad);
            boolean exito = controlador.registrarUsuario(idCliente, nombreUsuario, claveUsuario);
            if (exito) {
                return true;
            } else {
                System.out.println("\nError al registrar el usuario.");
            }
        } catch (Exception e) {
            System.out.println("\nError al registrarse: " + e.getMessage());
        }
        return preguntarContinuar();
    }

    private void mostrarMenuPrincipal() {
        while (true) {
            mostrarBanner();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║    Viajecitos SA Console Client      ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ Seleccione una opción:               ║");
            System.out.println("║                                      ║");
            System.out.println("║ 1. Buscar Vuelos                     ║");
            System.out.println("║ 2. Obtener Vuelo Más Caro            ║");
            System.out.println("║ 3. Comprar Boleto                    ║");
            System.out.println("║ 4. Consultar Compras                 ║");
            System.out.println("║ 5. Salir                             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("\nSeleccione una opción (1-5): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    limpiarConsola();
                    buscarVuelos();
                    break;
                case "2":
                    limpiarConsola();
                    obtenerVueloMasCaro();
                    break;
                case "3":
                    limpiarConsola();
                    comprarBoleto();
                    break;
                case "4":
                    limpiarConsola();
                    consultarCompras();
                    break;
                case "5":
                    System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
                    pausa();
                    return;
                default:
                    System.out.println("\nOpción inválida. Por favor, seleccione una opción válida.");
                    pausa();
            }
        }
    }

    private void buscarVuelos() {
        mostrarBanner();
        System.out.println("║ Buscar Vuelos                        ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\nIngrese los detalles del vuelo:\n");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Ciudad Origen:           ║ ");
        String ciudadOrigen = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Ciudad Destino:          ║ ");
        String ciudadDestino = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Fecha (yyyy-MM-dd):      ║ ");
        String fecha = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        try {
            List<Vuelo> vuelos = controlador.buscarVuelos(ciudadOrigen, ciudadDestino, fecha);
            System.out.println("\nVuelos disponibles:");
            System.out.printf("%-10s %-15s %-15s %-10s %-15s%n",
                    "ID VUELO", "ORIGEN", "DESTINO", "VALOR", "HORA SALIDA");
            System.out.println("-------------------------------------------------------");
            for (Vuelo vuelo : vuelos) {
                System.out.printf("%-10d %-15s %-15s %-10.2f %-15s%n",
                        vuelo.getIdVuelo(),
                        vuelo.getCiudadOrigen(),
                        vuelo.getCiudadDestino(),
                        vuelo.getValor(),
                        vuelo.getHoraSalida());
            }
        } catch (Exception e) {
            System.out.println("\nError al buscar vuelos: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
    }

    private void obtenerVueloMasCaro() {
        mostrarBanner();
        System.out.println("║ Obtener Vuelo Más Caro               ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\nIngrese los detalles del vuelo:\n");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Ciudad Origen:           ║ ");
        String ciudadOrigen = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Ciudad Destino:          ║ ");
        String ciudadDestino = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        System.out.println("╔══════════════════════════╗");
        System.out.print("║ Fecha (yyyy-MM-dd):      ║ ");
        String fecha = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        try {
            Vuelo vuelo = controlador.obtenerVueloMasCaro(ciudadOrigen, ciudadDestino, fecha);
            System.out.println("\nVuelo más caro:");
            System.out.printf("%-10s %-15s %-15s %-10s %-15s%n",
                    "ID VUELO", "ORIGEN", "DESTINO", "VALOR", "HORA SALIDA");
            System.out.println("-------------------------------------------------------");
            System.out.printf("%-10d %-15s %-15s %-10.2f %-15s%n",
                    vuelo.getIdVuelo(),
                    vuelo.getCiudadOrigen(),
                    vuelo.getCiudadDestino(),
                    vuelo.getValor(),
                    vuelo.getHoraSalida());
        } catch (Exception e) {
            System.out.println("\nError al obtener el vuelo más caro: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
    }

    private void comprarBoleto() {
        mostrarBanner();
        System.out.println("║ Comprar Boleto                       ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\nIngrese los detalles de la compra:\n");
        System.out.println("╔══════════════════════════");
        System.out.print("║ ID del Vuelo:            ║ ");
        String idVueloText = scanner.nextLine().trim();
        System.out.println("╚══════════════════════════╝");
        try {
            int idVuelo = Integer.parseInt(idVueloText);
            int idCliente = controlador.getIdClienteAutenticado();
            boolean success = controlador.registrarCompra(idVuelo, idCliente);
            System.out.println(success ? "\nCompra realizada exitosamente." : "\nError al realizar la compra.");
        } catch (NumberFormatException e) {
            System.out.println("\nError: Ingrese un ID de vuelo válido.");
        } catch (Exception e) {
            System.out.println("\nError al comprar boleto: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
    }

    private void consultarCompras() {
    mostrarBanner();
    System.out.println("║ Consultar Compras                    ║");
    System.out.println("╚══════════════════════════════════════╝");
    try {
        int idCliente = controlador.getIdClienteAutenticado();
        List<Compra> compras = controlador.obtenerComprasCliente(idCliente);
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════");
        System.out.println("║ Compras realizadas                                                   ");
        System.out.println("╠══════════════════════════════════════════════════════════════════════");
        System.out.printf("║ %-10s %-25s %-10s %-30s %-30s %n",
                "ID COMPRA", "VUELO", "VALOR", "FECHA COMPRA", "HORA SALIDA");
        System.out.println("╠══════════════════════════════════════════════════════════════════════");
        for (Compra compra : compras) {
            String fechaCompra = compra.getFechaCompra().toString(); // Usa toString() para formato completo
            String horaSalida = compra.getVuelo().getHoraSalida().toString(); // Usa toString() para formato completo
            System.out.printf("║ %-10d %-25s %-10.2f %-30s %-30s %n",
                    compra.getIdCompra(),
                    compra.getVuelo().getCiudadOrigen() + " a " + compra.getVuelo().getCiudadDestino(),
                    compra.getVuelo().getValor(),
                    fechaCompra,
                    horaSalida);
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════");
    } catch (Exception e) {
        System.out.println("\nError al consultar compras: " + e.getMessage());
    }
    if (!preguntarContinuar()) {
        System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
        System.exit(0);
    }
}
}
