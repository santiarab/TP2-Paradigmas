package usuario;

import java.util.Scanner;

import Validaciones.Validaciones;
import criptomoneda.Criptomoneda;
import criptomoneda.Mercado;

public class Administrador extends Usuario {
	private String perfil;

	public Administrador(String nombre, String perfil) {
		super(nombre);
		this.perfil = perfil;
	}

	public void menu(Scanner scanner) {
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
					System.out.println("Seleccionaste: Crear Criptomoneda\n");
					this.crearCriptomoneda(scanner);
					break;
				case 2:
					System.out.println("Seleccionaste: Modificar Criptomoneda\n");
					this.modificarCriptomoneda(scanner);
					break;
				case 3:
					System.out.println("Seleccionaste: Eliminar Criptomoneda\n");
					this.eliminarCriptomoneda(scanner);
					break;
				case 4:
					System.out.println("Seleccionaste: Consultar Criptomoneda\n");
					this.consultarCriptomoneda(scanner);
					break;
				case 5:
					System.out.println("Seleccionaste: Consultar estado actual del mercado\n");
					this.mostrarEstadoActualMercado();
					break;
				case 6:
					System.out.println("Saliendo...\n");
					this.actualizarUsuario(this.perfil);
					return;
			}
			System.out.println("\nPresione Enter para continuar...");
			scanner.nextLine();
		}
	}

	private void crearCriptomoneda(Scanner scanner) {
		String simbolo;
		int opcion;

		do {
			System.out.println("Ingresar simbolo de la nueva criptomoneda");
			simbolo = Validaciones.validarString(scanner);
		} while (simbolo == null);

		Criptomoneda cripto = Criptomoneda.buscarSimboloEnLista(listaCripto, simbolo);

		if (cripto != null) {
			do {
				System.out.println("Esa criptomoneda ya existe, desea modificarla?\n1. Si\n0. No");
				opcion = Validaciones.validarEnteros(scanner, 0, 1);
			} while (opcion < 0 || opcion > 1);
			if (opcion == 1) {
				this.modificarCriptomoneda(cripto, scanner);
			}
		} else {
			String nombre;
			do {
				System.out.println("Ingresar nombre de la nueva criptomoneda");
				nombre = Validaciones.validarString(scanner);
			} while (nombre == null);

			double precio;
			do {
				System.out.println("Ingresar precio en dólares de la nueva criptomoneda");
				precio = Validaciones.validarDouble(scanner, 0, Double.MAX_VALUE);
			} while (precio < 0);

			Usuario.listaCripto.add(new Criptomoneda(nombre, simbolo, precio));
			Usuario.listaMercado.add(new Mercado(simbolo, 500, 1, 1));
			System.out.println("Criptomoneda creada con éxito.");
		}
	}

	// Si entra desde CrearCriptomoneda() o desde modificarCriptomoneda(Scanner)
	private void modificarCriptomoneda(Criptomoneda cripto, Scanner scanner) {
		String nombre;
		do {
			System.out.println(
					"Ingresar el nuevo nombre de la criptomoneda o '-' si no desea modificarlo, (nombre actual: "
							+ cripto.getNombre() + ")");
			nombre = Validaciones.validarString(scanner);
		} while (nombre == null);

		String simbolo;
		do {
			System.out.println(
					"Ingresar el nuevo simbolo de la criptomoneda o '-' si no desea modificarlo, (simbolo actual: "
							+ cripto.getSimbolo() + ")");
			simbolo = Validaciones.validarString(scanner);
		} while (simbolo == null);

		double precio;
		do {
			System.out.println(
					"Ingresar el nuevo precio en dólares de la nueva criptomoneda o -1 si no desea modificarlo (precio actual: "
							+ cripto.getValor() + ")");
			precio = Validaciones.validarDouble(scanner, -1, Double.MAX_VALUE);
		} while (precio < 0 && precio != -1);

		if (!nombre.equals("-")) {
			cripto.setNombre(nombre);
		}
		if (!simbolo.equals("-")) {
			Mercado mercado = Mercado.buscarSimboloEnLista(listaMercado, cripto.getSimbolo());
			cripto.setSimbolo(simbolo);
			mercado.setSimbolo(simbolo);
		}
		if (precio >= 0) {
			cripto.setValor(precio);
		}

		System.out.println("Criptomoneda modificada con éxito");
	}

	// Si entra desde menu()
	private void modificarCriptomoneda(Scanner scanner) {
		String simbolo;
		do {
			System.out.println("Ingresar simbolo de la criptomoneda a modificar");
			simbolo = Validaciones.validarString(scanner);
		} while (simbolo == null);

		Criptomoneda cripto = Criptomoneda.buscarSimboloEnLista(listaCripto, simbolo);
		if (cripto != null)
			modificarCriptomoneda(cripto, scanner);
		else
			System.out.println("La criptomoneda ingresada no existe.");
	}

	private void eliminarCriptomoneda(Scanner scanner) {
		String simbolo;
		do {
			System.out.println("Ingresar simbolo de la criptomoneda a eliminar");
			simbolo = Validaciones.validarString(scanner);
		} while (simbolo == null);

		Criptomoneda cripto = Criptomoneda.buscarSimboloEnLista(listaCripto, simbolo);
		if (cripto != null) {
			Usuario.listaCripto.remove(cripto);
			Usuario.listaMercado.remove(Mercado.buscarSimboloEnLista(listaMercado, simbolo));
			System.out.println("Criptomoneda eliminada.");
		} else
			System.out.println("La criptomoneda ingresada no existe.");
	}
}