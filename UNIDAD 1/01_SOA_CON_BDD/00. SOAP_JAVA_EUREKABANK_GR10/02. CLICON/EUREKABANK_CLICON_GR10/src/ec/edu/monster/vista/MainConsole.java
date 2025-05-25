package ec.edu.monster.vista;

import ec.edu.monster.controlador.EurekaController;
import ec.edu.monster.ws.Movimiento;
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
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void start() {
        while (true) {
            displayMenu();
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        consultarMovimientos();
                        break;
                    case "2":
                        registrarDeposito();
                        break;
                    case "3":
                        registrarRetiro();
                        break;
                    case "4":
                        realizarTransferencia();
                        break;
                    case "5":
                        verificarSaldo();
                        break;
                    case "6":
                        System.out.println("Saliendo de la aplicación...");
                        return;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción entre 1 y 6.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine();
        }
    }

    private void displayMenu() {
        System.out.println("=================================");
        System.out.println("   EurekaBank Console Client");
        System.out.println("=================================");
        System.out.println("1. Consultar Movimientos");
        System.out.println("2. Registrar Depósito");
        System.out.println("3. Registrar Retiro");
        System.out.println("4. Realizar Transferencia");
        System.out.println("5. Verificar Saldo");
        System.out.println("6. Salir");
        System.out.println("=================================");
        System.out.print("Seleccione una opción: ");
    }

    private void consultarMovimientos() throws Exception {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine().trim();
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
    }

    private void registrarDeposito() throws Exception {
        System.out.print("Ingrese el código de empleado: ");
        String codEmp = scanner.nextLine().trim();
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine().trim();
        System.out.print("Ingrese el importe: ");
        String importeText = scanner.nextLine().trim().replace(",", ".");
        double importe = Double.parseDouble(importeText);

        boolean exito = controlador.registrarDeposito(cuenta, importe, codEmp);
        if (exito) {
            System.out.println("Depósito registrado exitosamente.");
        } else {
            System.out.println("Error al registrar el depósito.");
        }
    }

    private void registrarRetiro() throws Exception {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine().trim();
        System.out.print("Ingrese el importe: ");
        String importeText = scanner.nextLine().trim().replace(",", ".");
        double importe = Double.parseDouble(importeText);
        System.out.print("Ingrese el código de empleado: ");
        String codEmp = scanner.nextLine().trim();

        double costo = controlador.obtenerCostoMovimiento(cuenta);
        System.out.printf("El costo del movimiento es: %.2f%n", costo);
        System.out.print("¿Desea continuar? (s/n): ");
        if (!scanner.nextLine().trim().toLowerCase().equals("s")) {
            System.out.println("Operación cancelada.");
            return;
        }

        boolean exito = controlador.registrarRetiro(cuenta, importe, codEmp);
        if (exito) {
            System.out.println("Retiro registrado exitosamente.");
        } else {
            System.out.println("Error al registrar el retiro.");
        }
    }

    private void realizarTransferencia() throws Exception {
        System.out.print("Ingrese la cuenta origen: ");
        String cuentaOrigen = scanner.nextLine().trim();
        System.out.print("Ingrese la cuenta destino: ");
        String cuentaDestino = scanner.nextLine().trim();
        System.out.print("Ingrese el importe: ");
        String importeText = scanner.nextLine().trim().replace(",", ".");
        double importe = Double.parseDouble(importeText);
        System.out.print("Ingrese el código de empleado: ");
        String codEmp = scanner.nextLine().trim();

        double costo = controlador.obtenerCostoMovimiento(cuentaOrigen);
        System.out.printf("El costo del movimiento es: %.2f%n", costo);
        System.out.print("¿Desea continuar? (s/n): ");
        if (!scanner.nextLine().trim().toLowerCase().equals("s")) {
            System.out.println("Operación cancelada.");
            return;
        }

        boolean exito = controlador.realizarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
        if (exito) {
            System.out.println("Transferencia realizada exitosamente.");
        } else {
            System.out.println("Error al realizar la transferencia.");
        }
    }

    private void verificarSaldo() throws Exception {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine().trim();
        double saldo = controlador.verificarSaldo(cuenta);
        System.out.printf("El saldo de la cuenta %s es: %.2f%n", cuenta, saldo);
    }

    public static void main(String[] args) {
        new MainConsole().start();
    }
}
