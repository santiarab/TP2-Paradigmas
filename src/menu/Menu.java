package menu;

import java.util.Scanner;

public class Menu {
	public static void menuOpciones() {
		Scanner scanner = new Scanner(System.in);
		int numero = 0;

		while (numero != 6) {
			do {
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Menú de opciones");
				System.out.println("-----------------------");
				System.out.println("1) Crear Criptomoneda");
				System.out.println("2) Modificar Criptomoneda");
				System.out.println("3) Eliminar Criptomoneda");
				System.out.println("4) Consultar Criptomoneda");
				System.out.println("5) Consultar estado actual del mercado");
				System.out.println("6) Salir");
				System.out.println("-----------------------");
				System.out.print("Ingrese su opción (1 - 6): ");
				try {
					numero = scanner.nextInt();
					if (numero < 1 || numero > 6) {
						System.out.println("Error: Opción fuera de rango. Por favor, ingrese un número entre 1 y 6.");
					}
				} catch (Exception e) {
					System.out.println("Error: Entrada no válida. Por favor, introduce un número entero.");
					scanner.nextLine(); // Limpiar el buffer del scanner
				}
			} while (numero < 1 || numero > 6);

			switch (numero) {
			case 1:
				// Funcionalidad
				System.out.println("Seleccionaste: Crear Criptomoneda");
				break;
			case 2:
				// Funcionalidad
				System.out.println("Seleccionaste: Modificar Criptomoneda");
				break;
			case 3:
				// Funcionalidad
				System.out.println("Seleccionaste: Eliminar Criptomoneda");
				break;
			case 4:
				// Funcionalidad
				System.out.println("Seleccionaste: Consultar Criptomoneda");
				break;
			case 5:
				// Funcionalidad
				System.out.println("Seleccionaste: Consultar estado actual del mercado");
				break;
			default:
				System.out.println("Saliendo...");
				break;
			}
		}
		scanner.close(); // Cerrar Scanner al salir del bucle
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
