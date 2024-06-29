package menuPrincipal;

import java.util.Scanner;
import usuario.Administrador;
import usuario.Trader;
import usuario.Usuario;

public class MenuPrincipal {

    private static final int OPCION_SALIR = 0;
    private static final int OPCION_INGRESAR_NOMBRE = 1;

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        Usuario.inicializarListas();

        while (true) {
            do {
                mostrarMenu();
                opcion = validarEnteros(scanner, OPCION_SALIR, OPCION_INGRESAR_NOMBRE);
            } while (opcion != OPCION_SALIR && opcion != OPCION_INGRESAR_NOMBRE);

            if (opcion == OPCION_SALIR) {
                System.out.println("Saliendo del programa...");
                break;
            }

            if (opcion == OPCION_INGRESAR_NOMBRE) {
                manejarIngresoNombreUsuario(scanner);
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println(
                "Menú Principal \n----------------------- \n1. Ingresar nombre de usuario \n0. Salir \n----------------------- \nIngrese su opción (0 o 1): ");
    }

    private static void manejarIngresoNombreUsuario(Scanner scanner) {
        String nombre;

        do {
            System.out.print("Ingrese el nombre de usuario: ");
            nombre = validarString(scanner);
        } while (nombre == null);

        String[] camposUsuario = Usuario.validarUsuario(nombre);

        if (camposUsuario != null) {
            crearYMostrarMenuUsuario(camposUsuario);
        } else {
            registrarNuevoUsuario(scanner, nombre);
        }
    }

    private static void crearYMostrarMenuUsuario(String[] camposUsuario) {
        if (camposUsuario.length > 1) {
            Trader userTrader = new Trader(camposUsuario[0], Integer.parseInt(camposUsuario[1]),
                    camposUsuario[2], Double.parseDouble(camposUsuario[3]));
            userTrader.menu();
        } else {
            Administrador userAdmin = new Administrador(camposUsuario[0], camposUsuario[1]);
            userAdmin.menu();
        }
    }

    private static void registrarNuevoUsuario(Scanner scanner, String nombre) {
        System.out.println(nombre + ", usted no está registrado, completaremos sus datos y lo registraremos en el sistema.");

        int numCuenta;
        do {
            System.out.println("Ingrese su número de cuenta (Se le recuerda que es un número entero positivo):");
            numCuenta = validarEnteros(scanner, 0, Integer.MAX_VALUE);
        } while (numCuenta < 0);

        String nomBanco;
        do {
            System.out.print("Ingrese el nombre del Banco: ");
            nomBanco = validarString(scanner);
        } while (nomBanco == null);

        double saldo;
        do {
            System.out.println("Ingrese el saldo de su cuenta (Se le recuerda que es un número positivo):");
            saldo = validarDouble(scanner, 0, Double.MAX_VALUE);
        } while (saldo < 0);

        System.out.println("Su cuenta se ha creado con éxito");
        Trader userTrader = new Trader(nombre, numCuenta, nomBanco, saldo);
        userTrader.menu();
    }

    private static int validarEnteros(Scanner scanner, int min, int max) {
        int entero = -1;
        try {
            entero = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner
            if (entero < min || entero > max) {
                System.out.println("Error: Opción fuera de rango.");
                entero = -1;
            }
        } catch (Exception e) {
            System.out.println("Error: Entrada no válida. Por favor, introduce un número entero.");
            scanner.nextLine(); // Limpiar el buffer del scanner
        }
        return entero;
    }

    private static String validarString(Scanner scanner) {
        String texto = scanner.nextLine();
        if (texto.isEmpty()) {
            System.out.println("Error: El texto no puede estar vacío. Por favor, ingrese un texto válido.");
            return null;
        }
        return texto;
    }

    private static double validarDouble(Scanner scanner, double min, double max) {
        double num = -1;
        try {
            num = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer del scanner
            if (num < min || num > max) {
                System.out.println("Error: Opción fuera de rango.");
                num = -1;
            }
        } catch (Exception e) {
            System.out.println("Error: Entrada no válida. Por favor, introduce un número.");
            scanner.nextLine(); // Limpiar el buffer del scanner
        }
        return num;
    }
}
