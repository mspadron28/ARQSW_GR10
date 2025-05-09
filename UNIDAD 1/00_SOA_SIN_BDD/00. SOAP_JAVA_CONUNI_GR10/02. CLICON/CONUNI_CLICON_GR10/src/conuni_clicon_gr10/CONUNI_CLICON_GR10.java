/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conuni_clicon_gr10;

import ec.edu.monster.controlador.AppControlador;
import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author MATIAS
 */
public class CONUNI_CLICON_GR10 {

    private static AppControlador controlador = new AppControlador();
    private static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    public static void main(String[] args) {
        // Configurar la salida para usar UTF-8
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));

        // Mostrar encabezado inicial
        mostrarSullivan();

        // Paso 1: Solicitar inicio de sesión
        boolean loggedIn = iniciarSesion();

        if (!loggedIn) {
            System.out.println("No se pudo iniciar sesión. El programa se cerrará.");
            return;
        }

        // Limpiar y mostrar menú tras login
        limpiarConsola();
        mostrarSullivan();
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

    private static void limpiarConsola() {
        try {
            // Ejecutar el comando 'cls' en Windows para limpiar la consola
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // Si falla en otros sistemas, usar líneas en blanco como alternativa
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

   private static boolean iniciarSesion() {
    while (true) {
        limpiarConsola();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         Iniciar Sesión               ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\nIngrese sus credenciales:\n");
        
        System.out.println("╔══════════════════════════╗");
        System.out.println("║ Usuario:                 ║ ");
        System.out.println("╚══════════════════════════╝");
        String usuario = scanner.nextLine();
        
        
        System.out.println("╔══════════════════════════╗");
        System.out.println("║ Contraseña:              ║ ");
        System.out.println("╚══════════════════════════╝");
        String contraseña;
        Console console = System.console();
        if (console != null) {
            char[] passwordArray = console.readPassword();
            contraseña = new String(passwordArray);
            
        } else {
            // Respaldo para entornos sin consola (como NetBeans)
            System.out.println("\n(Advertencia: La contraseña será visible en este entorno)");
            System.out.print("║ Contraseña:              ║ ");
            contraseña = scanner.nextLine();
            System.out.println("╚══════════════════════════╝");
        }

        try {
            boolean success = controlador.login(usuario, contraseña);
            if (success) {
                System.out.println("\nInicio de sesión exitoso.");
                pausa();
                return true;
            } else {
                System.out.println("\nUsuario o contraseña incorrectos. Intente de nuevo.");
                System.out.print("\n¿Desea continuar? (S/N): ");
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (!respuesta.equals("s")) {
                    System.out.println("\nNo se pudo iniciar sesión. El programa se cerrará.");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("\nError al intentar iniciar sesión: " + e.getMessage());
            System.out.print("\n¿Desea continuar? (S/N): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (!respuesta.equals("s")) {
                System.out.println("\nNo se pudo iniciar sesión. El programa se cerrará.");
                return false;
            }
        }
    }
}

    private static void mostrarMenu() {
        while (true) {
            limpiarConsola();
            mostrarSullivan();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║    *** Conversor de Unidades ***     ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ Seleccione el tipo de conversión:    ║");
            System.out.println("║                                      ║");
            System.out.println("║ 1. Pulgadas a Centímetros            ║");
            System.out.println("║ 2. Centímetros a Pulgadas            ║");
            System.out.println("║ 3. Metros a Pies                     ║");
            System.out.println("║ 4. Pies a Metros                     ║");
            System.out.println("║ 5. Metros a Yardas                   ║");
            System.out.println("║ 6. Yardas a Metros                   ║");
            System.out.println("║ 7. Salir                             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("\nSeleccione una opción (1-7): ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    limpiarConsola();
                    convertirPulgadasACentimetros();
                    break;
                case "2":
                    limpiarConsola();
                    convertirCentimetrosAPulgadas();
                    break;
                case "3":
                    limpiarConsola();
                    convertirMetrosAPies();
                    break;
                case "4":
                    limpiarConsola();
                    convertirPiesAMetros();
                    break;
                case "5":
                    limpiarConsola();
                    convertirMetrosAYardas();
                    break;
                case "6":
                    limpiarConsola();
                    convertirYardasAMetros();
                    break;
                case "7":
                    System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
                    pausa();
                    return;
                default:
                    System.out.println("\nOpción inválida. Por favor, seleccione una opción válida.");
                    pausa();
            }
        }
    }

    private static void pausa() {
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
        limpiarConsola();
    }

    private static boolean preguntarContinuar() {
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

    private static void convertirPulgadasACentimetros() {
        mostrarSullivan();
        System.out.println("║ Convertir Pulgadas a Centímetros     ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el valor en pulgadas: ");
        try {
            double pulgadas = Double.parseDouble(scanner.nextLine());
            if (pulgadas < 0) {
                System.out.println("\nPor favor, ingrese un valor no negativo.");
            } else {
                double centimetros = controlador.pulgadasACentimetros(pulgadas);
                System.out.printf("\n%.2f pulgadas = %.2f centímetros%n", pulgadas, centimetros);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Por favor, ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("\nError al realizar la conversión: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
        limpiarConsola();
    }

    private static void convertirCentimetrosAPulgadas() {
        mostrarSullivan();
        System.out.println("║ Convertir Centímetros a Pulgadas     ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el valor en centímetros: ");
        try {
            double centimetros = Double.parseDouble(scanner.nextLine());
            if (centimetros < 0) {
                System.out.println("\nPor favor, ingrese un valor no negativo.");
            } else {
                double pulgadas = controlador.centimetrosAPulgadas(centimetros);
                System.out.printf("\n%.2f centímetros = %.2f pulgadas%n", centimetros, pulgadas);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Por favor, ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("\nError al realizar la conversión: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
        limpiarConsola();
    }

    private static void convertirMetrosAPies() {
        mostrarSullivan();
        System.out.println("║ Convertir Metros a Pies              ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el valor en metros: ");
        try {
            double metros = Double.parseDouble(scanner.nextLine());
            if (metros < 0) {
                System.out.println("\nPor favor, ingrese un valor no negativo.");
            } else {
                double pies = controlador.metrosAPies(metros);
                System.out.printf("\n%.2f metros = %.2f pies%n", metros, pies);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Por favor, ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("\nError al realizar la conversión: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
        limpiarConsola();
    }

    private static void convertirPiesAMetros() {
        mostrarSullivan();
        System.out.println("║ Convertir Pies a Metros              ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el valor en pies: ");
        try {
            double pies = Double.parseDouble(scanner.nextLine());
            if (pies < 0) {
                System.out.println("\nPor favor, ingrese un valor no negativo.");
            } else {
                double metros = controlador.piesAMetros(pies);
                System.out.printf("\n%.2f pies = %.2f metros%n", pies, metros);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Por favor, ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
        limpiarConsola();
    }

    private static void convertirMetrosAYardas() {
        mostrarSullivan();
        System.out.println("║ Convertir Metros a Yardas            ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el valor en metros: ");
        try {
            double metros = Double.parseDouble(scanner.nextLine());
            if (metros < 0) {
                System.out.println("\nPor favor, ingrese un valor no negativo.");
            } else {
                double yardas = controlador.metrosAYardas(metros);
                System.out.printf("\n%.2f metros = %.2f yardas%n", metros, yardas);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Por favor, ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("\nError al realizar la conversión: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
        limpiarConsola();
    }

    private static void convertirYardasAMetros() {
        mostrarSullivan();
        System.out.println("║ Convertir Yardas a Metros            ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("\nIngrese el valor en yardas: ");
        try {
            double yardas = Double.parseDouble(scanner.nextLine());
            if (yardas < 0) {
                System.out.println("\nPor favor, ingrese un valor no negativo.");
            } else {
                double metros = controlador.yardasAMetros(yardas);
                System.out.printf("\n%.2f yardas = %.2f metros%n", yardas, metros);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Por favor, ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("\nError al realizar la conversión: " + e.getMessage());
        }
        if (!preguntarContinuar()) {
            System.out.println("\nSaliendo del programa. ¡Hasta pronto!");
            System.exit(0);
        }
        limpiarConsola();
    }

}
