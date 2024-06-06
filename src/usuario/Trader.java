package usuario;

import java.util.Scanner;

public class Trader extends Usuario{
	private int nroCuentaBancaria;
	private float saldoActual;
	private String nombreBanco;
	public Trader(String nombre, int nroCuentaBancaria ,
			String nombreBanco, float saldoActual) {
		super(nombre);
		this.nombreBanco = nombreBanco;
		this.nroCuentaBancaria = nroCuentaBancaria;
		this.saldoActual = saldoActual;
		// TODO Auto-generated constructor stub
	}
	public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        while (numero != 7) {
            do {
                System.out.println("Menú de opciones");
                System.out.println("-----------------------");
                System.out.println("1) Comprar Criptomonedas");
                System.out.println("2) Vender Criptomonedas");
                System.out.println("3) Consultar Criptomoneda");
                System.out.println("4) Recomendar Criptomonedas");
                System.out.println("5) Consultar estado actual del mercado");
                System.out.println("6) Visualizar archivo de transacciones (histórico)");
                System.out.println("7) Salir");
                System.out.print("Ingrese su opción (1 - 7): ");
                try {
                    numero = scanner.nextInt();
                    if (numero < 1 || numero > 7) {
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
    }
}
