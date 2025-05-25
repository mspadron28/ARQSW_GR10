package ec.edu.monster.vista;

import ec.edu.monster.controlador.EurekaController;
import ec.edu.monster.ws.Movimiento;
import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based interface for EurekaBank client.
 *
 * @author MATIAS
 */
public class MainConsole {

    private final EurekaController controlador;
    private final Scanner scanner;
    private final SimpleDateFormat dateFormat;

    public MainConsole() {
        controlador = new EurekaController();
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));
    }

    public void start() {
        mostrarSullivan();
        if (!iniciarSesion()) {
            System.out.println("No se pudo iniciar sesión. El programa se cerrará.");
            return;
        }
        limpiarConsola();
        mostrarMenu();
    }

     private static void mostrarSullivan() {
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⡛⠷⣂⣄⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠟⢀⣼⣿⡿⠿⢷⡿⠓⠿⣖⣤⣤⣤⢄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣦⡸⣤⣀⣀⣀⡾⢥⡀⠀⠙⡿⣽⢿⣌⠉⢻⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣼⠟⢋⣭⡟⣏⠷⠖⣽⣦⢀⡇⡬⢿⠿⡈⢹⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣽⣶⣶⠾⠟⠚⣽⠒⠉⢦⠽⠟⠸⡐⣿⡨⠈⠉⠀⡇⠀⢣⠈⣺⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⣿⠁⠠⡀⠀⠀⠙⠧⣤⣬⣆⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⣾⡶⠗⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢿⣏⠀⠀⠈⠲⣄⠀⠀⠀⠀⠀⢀⡀⠀⢀⠽⠒⠀⠀⠀⠀⢹⣣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢹⣯⠀⠀⠀⠀⠀⠙⠲⢄⡴⣢⣆⠼⠚⠁⠀⠀⠀⡄⠀⠀⠀⠻⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣤⣴⣶⣄⣤⣤⣤⣼⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⠀⠙⢾⣆⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⣿⠶⠀⠉⠀⠀⠀⠀⠘⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⠀⠀⢰⠀⠀⠀⠈⠻⢮⣖⠄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣵⠖⠀⠀⠀⠀⠀⠀⠀⠈⠙⢦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠇⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠈⠛⠾⣔⡄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⢀⣤⣴⡷⠧⠤⠤⣤⣄⡀⡀⠀⠀⠀⠀⠀⢳⠈⠉⠀⠀⠀⠀⠠⣤⡤⠴⡺⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⠀⠈⠙⠾⣕⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⢀⢰⣿⡵⠛⠲⠦⠤⣄⠀⠀⠙⣧⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠈⠻⢯⡦⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⢠⣾⣯⠼⠤⠤⣄⠀⠀⠈⣷⣀⣀⣼⣧⡤⠴⠶⠶⠶⠶⠶⠒⡒⠒⠒⠒⠲⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣯⣤⡀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⣰⡟⣹⡇⠀⠀⣀⣬⠷⠚⠛⠉⠉⠁⢰⠀⠀⠀⠀⠀⠀⠀⢀⠔⠁⠀⠀⠀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠀⡰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠃⣼⣼⣿⠉⠀⠀⠀⠀");
        System.out.println("⠀⠀⢀⣧⠟⠁⢙⠷⠋⠁⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣄⠀⠀⢠⡞⠁⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣻⠿⠃⠀⠀⠀⠀⠀");
        System.out.println("⠀⣰⡿⠁⠀⠀⡔⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠀⠀⠀⠈⢣⡴⠋⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣻⠁⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⣼⠞⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠠⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠖⠛⠛⠛⠒⠲⠶⢯⣤⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡻⠃⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠰⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡻⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠞⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠘⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⠞⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠝⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠻⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡴⠟⠻⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠹⡿⣄⠀⢀⣀⣀⣀⣀⣠⣤⣴⡶⠛⠉⠁⠀⠀⠀⠈⠳⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡯⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠈⠫⢷⣾⣿⢻⠿⠛⠉⠉⣿⠇⠀⠀⠠⠀⠂⠈⠀⢀⠈⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠿⣿⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⢠⣿⠀⠀⠐⠀⠀⠄⠐⠈⠀⠀⠀⠀⠙⢷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⠋⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⣿⠀⠀⠄⠂⠀⠠⠀⢀⠐⠈⠀⠐⠀⠀⠈⠛⠷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠏⠀⠀⠀⢿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⣟⠀⠀⢀⠠⠐⠀⢀⠀⠀⠠⠐⠀⠀⠁⡀⠀⠀⠀⢉⣻⡷⢆⣀⣀⠀⣠⡶⠋⠀⢀⠀⠀⠀⢸⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⣟⠀⠀⠀⠀⡀⠠⠀⠀⠈⠀⢀⠠⠈⠀⠀⡀⠁⠠⠀⠈⠉⠉⣽⠷⠚⠉⠀⠀⠀⠈⠒⠐⠊⠀⣷⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠈⠀⠀⢀⠀⠈⠀⠐⠀⠀⢀⠀⠁⠀⠀⠄⠀⠄⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⣿⣷⡀⠀⠁⠀⢀⠀⠁⠀⠄⠈⠀⠀⠠⠈⠀⠀⠄⠀⠠⠀⠐⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⢹⡏⢫⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢼⡏⠙⢦⣄⠂⠀⠀⠐⠀⡀⠄⠂⠁⠀⡀⠐⠀⠠⠐⠀⠀⠄⠀⠀⠀⠀⠀⠁⠀⠉⠲⢄⠀⠈⣷⢾⣿⣀⣀⣀⣀⠀⠀⠀⢀⡀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡇⡄⠀⠈⠓⠦⠨⣀⠀⠀⠀⡀⠄⠀⢀⠠⠐⠀⠀⡀⠂⡠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⡀⢿⡄⠙⠿⣍⡉⠹⡵⡀⣴⠟⠙⣧⠶⣶");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢿⠁⠘⡄⠀⠀⠀⠀⠀⠀⠈⠦⣀⡀⠠⠀⠀⢀⠠⠀⠀⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⢸⣧⠀⠀⠈⠙⠓⠳⠛⠛⠉⠁⠀⡴⠏");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⢀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⣷⣶⠶⢶⣶⢶⡋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠁⠀⠀⣿⠆⠀⡠⠒⠰⠄⠀⠀⠀⢠⢾⠋⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⣽⣿⡀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⡟⡇⠀⠀⠀⢿⣇⠀⠀⠀⠀⠀⠀⣀⣤⡀⠀⠀⠀⣠⢷⡏⠀⠀⠀⡠⠊⠀⠀⢀⣴⡟⠁⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠈⢽⣷⡄⠀⠀⠀⠏⠀⠉⠑⢦⠀⠀⠀⢿⣧⠀⠀⠀⢿⠇⠀⠀⠀⠀⠀⢀⠀⠀⠈⡇⠀⣴⣏⣀⠀⠀⣀⣀⣤⡤⣶⠿⠛⠁⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡟⠋⣉⠵⠂⢀⣀⠄⠀⠀⠀⢀⠰⠁⠀⠀⠘⣿⡄⠀⣼⡟⠀⠀⠀⠀⠀⠀⠀⠁⢢⣈⠀⠐⠤⣬⠉⣻⣿⡟⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡿⠉⢺⡗⠒⣄⣏⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⡁⠀⢿⣅⣀⠀⠀⠀⠀⠀⠀⠀⠀⡠⢽⣦⠚⠚⢯⠀⠱⣵⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣀⣤⡎⠀⢀⡞⠁⠀⢱⠀⠀⠀⢀⣀⣤⢶⡾⠛⠋⠁⠀⠀⠉⠋⠿⣶⣤⣄⡀⠀⠀⢰⠃⠀⠙⣆⣀⠈⣷⠶⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠉⠹⠾⣽⣿⡤⣴⢶⣿⣴⣶⡟⡯⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠹⢿⣶⣶⣿⠶⣦⣿⡉⠛⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
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

    private boolean iniciarSesion() {
        while (true) {
            limpiarConsola();
            mostrarSullivan();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         Iniciar Sesión               ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("\nIngrese sus credenciales:\n");
            System.out.println("╔══════════════════════════╗");
            System.out.print("║ Usuario:                 ║ ");
            String usuario = scanner.nextLine().trim();
            System.out.println("╚══════════════════════════╝");
            System.out.println("╔══════════════════════════╗");
            System.out.print("║ Contraseña:              ║ ");
            String contraseña;
            Console console = System.console();
            if (console != null) {
                char[] passwordArray = console.readPassword();
                contraseña = new String(passwordArray);
            } else {
                System.out.println("\n(Advertencia: La contraseña será visible en este entorno)");
                System.out.print("║ Contraseña:              ║ ");
                contraseña = scanner.nextLine();
                System.out.println("╚══════════════════════════╝");
            }
            try {
                if (usuario.isEmpty() || contraseña.isEmpty()) {
                    System.out.println("\nPor favor, complete todos los campos.");
                    if (!preguntarContinuar()) {
                        return false;
                    }
                    continue;
                }
                boolean success = controlador.iniciarSesion(usuario, contraseña);
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

    private void mostrarMenu() {
        while (true) {
            limpiarConsola();
            mostrarSullivan();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║      EurekaBank Console Client       ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ Seleccione una opción:               ║");
            System.out.println("║                                      ║");
            System.out.println("║ 1. Consultar Movimientos             ║");
            System.out.println("║ 2. Registrar Depósito                ║");
            System.out.println("║ 3. Registrar Retiro                  ║");
            System.out.println("║ 4. Realizar Transferencia            ║");
            System.out.println("║ 5. Verificar Saldo                   ║");
            System.out.println("║ 6. Salir                             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("\nSeleccione una opción (1-6): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    limpiarConsola();
                    consultarMovimientos();
                    break;
                case "2":
                    limpiarConsola();
                    registrarDeposito();
                    break;
                case "3":
                    limpiarConsola();
                    registrarRetiro();
                    break;
                case "4":
                    limpiarConsola();
                    realizarTransferencia();
                    break;
                case "5":
                    limpiarConsola();
                    verificarSaldo();
                    break;
                case "6":
                    System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
                    pausa();
                    return;
                default:
                    System.out.println("\nOpción inválida. Por favor, seleccione una opción válida.");
                    pausa();
            }
        }
    }

    private void consultarMovimientos() {
        mostrarSullivan();
        System.out.println("║ Consultar Movimientos                ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el número de cuenta: ");
        String cuenta = scanner.nextLine().trim();
        try {
            List<Movimiento> movimientos = controlador.consultarMovimientos(cuenta);
            System.out.println("\nMovimientos de la cuenta " + cuenta + ":");
            System.out.printf("%-10s %-10s %-12s %-10s %-20s %-10s%n",
                    "CUENTA", "NROMOV", "FECHA", "TIPO", "ACCIÓN", "IMPORTE");
            System.out.println("------------------------------------------------------------");
            for (Movimiento mov : movimientos) {
                System.out.printf("%-10s %-10d %-12s %-10s %-20s %-10.2f%n",
                        mov.getCuenta(),
                        mov.getNroMov(),
                        mov.getFecha() != null ? dateFormat.format(mov.getFecha().toGregorianCalendar().getTime()) : "N/A",
                        mov.getTipo(),
                        mov.getAccion(),
                        mov.getImporte());
            }
            if (movimientos.isEmpty()) {
                System.out.println("\nNo se encontraron movimientos para la cuenta.");
            }
        } catch (Exception e) {
            System.out.println("\nError al consultar movimientos: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
    }

    private void registrarDeposito() {
        mostrarSullivan();
        System.out.println("║ Registrar Depósito                   ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el código de empleado: ");
        String codEmp = scanner.nextLine().trim();
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine().trim();
        System.out.print("Ingrese el importe: ");
        String importeText = scanner.nextLine().trim().replace(",", ".");
        try {
            double importe = Double.parseDouble(importeText);
            if (importe <= 0) {
                System.out.println("\nEl importe debe ser mayor que 0.");
            } else {
                boolean exito = controlador.registrarDeposito(cuenta, importe, codEmp);
                System.out.println(exito ? "\nDepósito registrado exitosamente." : "\nError al registrar el depósito.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Ingrese un importe válido (use punto como separador decimal).");
        } catch (Exception e) {
            System.out.println("\nError al registrar el depósito: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
    }

    private void registrarRetiro() {
        mostrarSullivan();
        System.out.println("║ Registrar Retiro                     ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el número de cuenta: ");
        String cuenta = scanner.nextLine().trim();
        System.out.print("Ingrese el importe: ");
        String importeText = scanner.nextLine().trim().replace(",", ".");
        System.out.print("Ingrese el código de empleado: ");
        String codEmp = scanner.nextLine().trim();
        try {
            double importe = Double.parseDouble(importeText);
            if (importe <= 0) {
                System.out.println("\nEl importe debe ser mayor que 0.");
                return;
            }
            double costo = controlador.obtenerCostoMovimiento(cuenta);
            System.out.printf("\nEl costo del movimiento es: %.2f", costo);
            System.out.print("\n¿Desea continuar? (S/N): ");
            if (!scanner.nextLine().trim().toLowerCase().equals("s")) {
                System.out.println("\nOperación cancelada.");
                return;
            }
            boolean exito = controlador.registrarRetiro(cuenta, importe, codEmp);
            System.out.println(exito ? "\nRetiro registrado exitosamente." : "\nError al registrar el retiro.");
        } catch (NumberFormatException e) {
            System.out.println("\nError: Ingrese un importe válido (use punto como separador decimal).");
        } catch (Exception e) {
            System.out.println("\nError al registrar el retiro: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
    }

    private void realizarTransferencia() {
        mostrarSullivan();
        System.out.println("║ Realizar Transferencia               ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese la cuenta origen: ");
        String cuentaOrigen = scanner.nextLine().trim();
        System.out.print("Ingrese la cuenta destino: ");
        String cuentaDestino = scanner.nextLine().trim();
        System.out.print("Ingrese el importe: ");
        String importeText = scanner.nextLine().trim().replace(",", ".");
        System.out.print("Ingrese el código de empleado: ");
        String codEmp = scanner.nextLine().trim();
        try {
            double importe = Double.parseDouble(importeText);
            if (importe <= 0) {
                System.out.println("\nEl importe debe ser mayor que 0.");
                return;
            }
            double costo = controlador.obtenerCostoMovimiento(cuentaOrigen);
            System.out.printf("\nEl costo del movimiento es: %.2f", costo);
            System.out.print("\n¿Desea continuar? (S/N): ");
            if (!scanner.nextLine().trim().toLowerCase().equals("s")) {
                System.out.println("\nOperación cancelada.");
                return;
            }
            boolean exito = controlador.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
            System.out.println(exito ? "\nTransferencia realizada exitosamente." : "\nError al realizar la transferencia.");
        } catch (NumberFormatException e) {
            System.out.println("\nError: Ingrese un importe válido (use punto como separador decimal).");
        } catch (Exception e) {
            System.out.println("\nError al realizar la transferencia: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
    }

    private void verificarSaldo() {
        mostrarSullivan();
        System.out.println("║ Verificar Saldo                      ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el número de cuenta: ");
        String cuenta = scanner.nextLine().trim();
        try {
            double saldo = controlador.verificarSaldo(cuenta);
            System.out.printf("\nEl saldo de la cuenta %s es: %.2f", cuenta, saldo);
        } catch (Exception e) {
            System.out.println("\nError al verificar el saldo: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MainConsole().start();
    }
}
