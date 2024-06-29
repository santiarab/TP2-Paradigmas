package usuario;

import java.util.List;
import java.util.Scanner;

import Validaciones.Validaciones;
import archivo.Archivo;
import criptomoneda.Criptomoneda;
import criptomoneda.Mercado;

public class Administrador extends Usuario {
	private String perfil;

	public Administrador(String nombre, String perfil) {
		super(nombre);
		this.perfil = perfil;
	}

	public void menu() {
		Scanner scanner = new Scanner(System.in);
		int numero = 0;

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println();
			do {
				System.out.println(
						"Menú de opciones\n-----------------------\n1) Crear Criptomoneda\n2) Modificar Criptomoneda"
								+ "\n3) Eliminar Criptomoneda\n4) Consultar Criptomoneda\n5) Consultar estado actual"
								+ " del mercado\n6) Salir");
				System.out.print("Ingrese su opción (1 - 6): ");
				numero = Validaciones.validarEnteros(scanner, 1, 6);
			} while (numero < 1 || numero > 6);

			switch (numero) {
				case 1:
					// Funcionalidad FALTA
					System.out.println("Seleccionaste: Crear Criptomoneda");
					break;
				case 2:
					// Funcionalidad FALTA
					System.out.println("Seleccionaste: Modificar Criptomoneda");
					break;
				case 3:
					// Funcionalidad FALTA
					System.out.println("Seleccionaste: Eliminar Criptomoneda");
					break;
				case 4:
					System.out.println("Seleccionaste: Consultar Criptomoneda");
					this.consultarCriptomoneda(scanner);
					break;
				case 5:
					System.out.println("Seleccionaste: Consultar estado actual del mercado");
					this.mostrarEstadoActualMercado();
					break;
				case 6:
					System.out.println("Saliendo...");
					scanner.close();
					return;
			}
		}
	}
}
