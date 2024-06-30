package Validaciones;

import java.util.Scanner;

public class Validaciones {
	
	public static int validarEnteros(Scanner scanner, int min, int max) {
        int entero = -1;
        try {
            entero = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner
            if (entero < min || entero > max) {
                System.out.println("Error: Opción fuera de rango.");
            }
        } catch (Exception e) {
            System.out.println("Error: Entrada no válida. Por favor, introduce un número entero.");
            scanner.nextLine(); // Limpiar el buffer del scanner
        }
        return entero;
    }

    public static String validarString(Scanner scanner) {
        String texto = scanner.nextLine();
        if (texto.isEmpty()) {
            System.out.println("Error: El texto no puede estar vacío. Por favor, ingrese un texto válido.");
            return null;
        }
        return texto;
    }

    public static double validarDouble(Scanner scanner, double min, double max) {
        double num = -1;
        try {
            num = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer del scanner
            if (num < min || num > max) {
                System.out.println("Error: Opción fuera de rango.");
            }
        } catch (Exception e) {
            System.out.println("Error: Entrada no válida. Por favor, introduce un número.");
            scanner.nextLine(); // Limpiar el buffer del scanner
        }
        return num;
    }

}
