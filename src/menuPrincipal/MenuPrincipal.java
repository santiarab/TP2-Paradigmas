package menuPrincipal;

import java.util.Locale;
import java.util.Scanner;

import Validaciones.Validaciones;
import usuario.Administrador;
import usuario.Trader;
import usuario.Usuario;

public class MenuPrincipal {
    private static final int OPCION_SALIR = 0;
    private static final int OPCION_INGRESAR_NOMBRE = 1;

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ENGLISH);
        int opcion;

        Usuario.inicializarListas();

        scanner.nextLine();
        while (true) {
            do {
                System.out.println(
                        "Menú Principal \n----------------------- \n1. Ingresar nombre de usuario \n0. Salir "
                                + "\n----------------------- \nIngrese su opción (0 o 1): ");
                opcion = Validaciones.validarEnteros(scanner, OPCION_SALIR, OPCION_INGRESAR_NOMBRE);
            } while (opcion != OPCION_SALIR && opcion != OPCION_INGRESAR_NOMBRE);

            if (opcion == OPCION_SALIR) {
                System.out.println("Saliendo del programa...");
                scanner.close();

                Usuario.actualizarCSVs();
                break;
            }

            if (opcion == OPCION_INGRESAR_NOMBRE) {
                manejarIngresoNombreUsuario(scanner);
            }
        }
    }

    // Si ingresa un 1 - Ingresar nombre de usuario
    private static void manejarIngresoNombreUsuario(Scanner scanner) {
        String nombre;

        do {
            System.out.println("Ingrese el nombre de usuario: ");
            nombre = Validaciones.validarString(scanner);
        } while (nombre == null);

        String[] camposUsuario = Usuario.validarUsuario(nombre);

        if (camposUsuario != null) { // Si el usuario ingresado ya está registrado
            crearYMostrarMenuUsuario(camposUsuario, scanner);
        } else { // Si el usuario ingresado no está registrado
            registrarNuevoUsuario(scanner, nombre);
        }
    }

    // Si el usuario ingresado ya está registrado crea el objeto según cuál sea y
    // llama a su menu
    private static void crearYMostrarMenuUsuario(String[] camposUsuario, Scanner scanner) {
        if (camposUsuario.length == 4) {
            Trader userTrader = new Trader(camposUsuario[0], Integer.parseInt(camposUsuario[1]),
                    camposUsuario[2], Double.parseDouble(camposUsuario[3]));
            userTrader.menu(scanner);
        } else {
            Administrador userAdmin = new Administrador(camposUsuario[0], camposUsuario[1]);
            userAdmin.menu(scanner);
        }
    }

    // Si el usuario no está registrado se solicitan los datos para registrarlo,
    // siempre Trader
    private static void registrarNuevoUsuario(Scanner scanner, String nombre) {
        System.out.println("\n"
                + nombre + ", usted no está registrado, completaremos sus datos y lo registraremos en el sistema.\n");

        int numCuenta;
        do {
            System.out.println("Ingrese su número de cuenta (Se le recuerda que es un número entero positivo):");
            numCuenta = Validaciones.validarEnteros(scanner, 0, Integer.MAX_VALUE);
        } while (numCuenta < 0);

        String nomBanco;
        do {
            System.out.println("Ingrese el nombre del Banco: ");
            nomBanco = Validaciones.validarString(scanner);
        } while (nomBanco == null);

        double saldo;
        do {
            System.out.println("Ingrese el saldo de su cuenta (Se le recuerda que es un número positivo):");
            saldo = Validaciones.validarDouble(scanner, 0, Double.MAX_VALUE);
        } while (saldo < 0);

        System.out.println("Su cuenta se ha creado con éxito");
        Trader userTrader = new Trader(nombre, numCuenta, nomBanco, saldo);

        // Inserta el nuevo usuario en el .csv
        Usuario.insertarUsuario(String.join(",", nombre, String.valueOf(numCuenta), nomBanco, String.valueOf(saldo)));

        userTrader.menu(scanner);
    }

}
