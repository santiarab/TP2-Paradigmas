package menuPrincipal;

import java.util.Scanner;

import Validaciones.Validaciones;
import archivo.Archivo;
import usuario.Administrador;
import usuario.Trader;
import usuario.Usuario;

public class MenuPrincipal {
    private static final int OPCION_SALIR = 0;
    private static final int OPCION_INGRESAR_NOMBRE = 1;

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        Usuario.inicializarListas();

        while (true) {
            do {
                mostrarMenu();
                opcion = Validaciones.validarEnteros(scanner, OPCION_SALIR, OPCION_INGRESAR_NOMBRE);
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
                "Menú Principal \n----------------------- \n1. Ingresar nombre de usuario \n0. Salir "
                        + "\n----------------------- \nIngrese su opción (0 o 1): ");
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
            crearYMostrarMenuUsuario(camposUsuario);
        } else { // Si el usuario ingresado no está registrado
            registrarNuevoUsuario(scanner, nombre);
        }
    }

    // Si el usuario ingresado ya está registrado crea el objeto según cuál sea y
    // llama a su menu
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

    // Si el usuario no está registrado se solicitan los datos para registrarlo,
    // siempre Trader
    private static void registrarNuevoUsuario(Scanner scanner, String nombre) {
        System.out.println(
                nombre + ", usted no está registrado, completaremos sus datos y lo registraremos en el sistema.");

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

        userTrader.menu();
    }

}
