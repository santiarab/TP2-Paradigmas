package usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Validaciones.Validaciones;
import archivo.Archivo;
import criptomoneda.Criptomoneda;
import criptomoneda.Mercado;
import criptomoneda.Transaccion;

public class Trader extends Usuario {
	private int nroCuentaBancaria;
	private double saldoActual;
	private String nombreBanco;
	private List<Transaccion> listaTransaccion;

	public Trader(String nombre, int nroCuentaBancaria, String nombreBanco, double saldoActual) {
		super(nombre);
		this.nombreBanco = nombreBanco;
		this.nroCuentaBancaria = nroCuentaBancaria;
		this.saldoActual = saldoActual;
		String[] lineasHistorial = Archivo.leerArchivo("casoDePrueba/" + nombre + "_historico.csv");
		if (lineasHistorial.length > 0) {
			listaTransaccion = Transaccion.trozearString(lineasHistorial);
		} else
			listaTransaccion = new ArrayList<>();
	}

	public void menu(Scanner scanner) {
		int numero = 0;

		while (true) {
			do {
				System.out.println(
						"Menú de opciones\n-----------------------\n1) Comprar Criptomonedas\n2) Vender Criptomonedas"
								+ "\n3) Consultar Criptomoneda\n4) Recomendar Criptomonedas\n5) Consultar estado actual del "
								+ "mercado\n6) Visualizar archivo de transacciones (histórico)\n7) Salir\nIngrese su opción (1 - 7): ");
				numero = Validaciones.validarEnteros(scanner, 1, 7);
			} while (numero < 1 || numero > 7);

			switch (numero) {
				case 1:
					System.out.println("Seleccionaste: Comprar Criptomoneda\n");
					this.comprarCriptomoneda(scanner);
					break;
				case 2:
					System.out.println("Seleccionaste: Vender Criptomoneda\n");
					this.venderCriptomoneda(scanner);
					break;
				case 3:
					System.out.println("Seleccionaste: Consultar Criptomoneda\n");
					this.consultarCriptomoneda(scanner);
					break;
				case 4:
					System.out.println("Seleccionaste: Recomendar Criptomoneda\n");
					this.recomendarCripto();
					break;
				case 5:
					System.out.println("Seleccionaste: Consultar estado actual del mercado\n");
					this.mostrarEstadoActualMercado();
					break;
				case 6:
					System.out.println("Seleccionaste: Visualizar archivo de transacciones (histórico)\n");
					this.mostrarHistorico();
					break;
				case 7:
					System.out.println("Seleccionaste: Salir\n");
					actualizarHistorialCSV();
					this.actualizarUsuario(this.nroCuentaBancaria + "," + this.nombreBanco + "," + this.saldoActual);
					return;
			}
			System.out.println("\nPresione Enter para continuar...");
			scanner.nextLine();
		}
	}

	private void comprarCriptomoneda(Scanner scanner) {
		String simbolo;

		do {
			System.out.println("Ingresar simbolo de la criptomoneda que desea comprar");
			simbolo = Validaciones.validarString(scanner);
		} while (simbolo == null);

		Criptomoneda cripto = Criptomoneda.buscarSimboloEnLista(listaCripto, simbolo);

		if (cripto != null) {
			Mercado mercado = Mercado.buscarSimboloEnLista(listaMercado, simbolo);
			System.out.println(cripto.getSimbolo() + ": Valor en dólares - " + cripto.getValor()
					+ ". Total disponible para comprar - " + mercado.getCapacidad());

			double cantAComprar;
			do {
				System.out.println("Ingrese la cantidad que desea comprar (puede no ser un entero):");
				cantAComprar = Validaciones.validarDouble(scanner, 0, mercado.getCapacidad());
			} while (cantAComprar < 0 || cantAComprar > mercado.getCapacidad());

			if ((cantAComprar * cripto.getValor()) > this.saldoActual) {
				int opcion;
				do {
					System.out.println("Usted no posee saldo suficiente para realizar la compra, el dinero faltante es "
							+ ((cantAComprar * cripto.getValor()) - this.saldoActual)
							+ "\nDesea depositarlo?\n1. Si\n0. No");
					opcion = Validaciones.validarEnteros(scanner, 0, 1);
				} while (opcion < 0 || opcion > 1);
				if (opcion == 0) {
					return;
				}
				this.saldoActual = cantAComprar * cripto.getValor();
				System.out.println("Deposito exitoso");
			}

			mercado.actualizarPorCompra(cantAComprar);
			this.saldoActual -= cantAComprar * cripto.getValor();
			if (cantAComprar > 1000)
				cripto.setValor(cripto.getValor() + cripto.getValor() * 0.1);

			Transaccion transaccion = Transaccion.buscarSimboloEnLista(listaTransaccion, simbolo);

			if (transaccion != null) {
				transaccion.setCantidad(transaccion.getCantidad() + cantAComprar);
			} else {
				listaTransaccion.add(new Transaccion(simbolo, cantAComprar));
			}
			System.out.println("Su compra se ha realizado con éxito");
		} else
			System.out.println("La criptomoneda que desea comprar no existe");
	}

	private void venderCriptomoneda(Scanner scanner) {
		String simbolo;

		do {
			System.out.println("Ingresar simbolo de la criptomoneda que desea vender");
			simbolo = Validaciones.validarString(scanner);
		} while (simbolo == null);

		Criptomoneda cripto = Criptomoneda.buscarSimboloEnLista(listaCripto, simbolo);
		if (cripto != null) {
			Transaccion transaccion = Transaccion.buscarSimboloEnLista(listaTransaccion, simbolo);

			if (transaccion != null && transaccion.getCantidad() != 0) {
				System.out.println("Usted posee " + transaccion.getCantidad() +
						" en su cuenta en la criptomoneda " + transaccion.getSimbolo());
				double cantVender;
				do {
					System.out.println("Cuanto desea vender?");
					cantVender = Validaciones.validarDouble(scanner, 0, transaccion.getCantidad());
				} while (cantVender <= 0 || cantVender > transaccion.getCantidad());

				Mercado mercado = Mercado.buscarSimboloEnLista(listaMercado, simbolo);
				mercado.actualizarPorVenta(cantVender);
				this.saldoActual += cantVender * cripto.getValor();
				transaccion.setCantidad(transaccion.getCantidad() - cantVender);
				System.out.println("Su venta se ha realizado con éxito");
			} else
				System.out.println("Usted no posee fondos de la criptomoneda que desea vender");
		} else
			System.out.println("La criptomoneda que desea vender no existe");

	}

	private void recomendarCripto() {
		double max = 0;
		String maxCripto = null;
		for (Criptomoneda cripto : Usuario.listaCripto) {
			Mercado mercado = Mercado.buscarSimboloEnLista(Usuario.listaMercado, cripto.getSimbolo());
			if ((mercado.getCapacidad() / cripto.getValor()) * 100 > max) {
				max = (mercado.getCapacidad() / cripto.getValor()) * 100;
				maxCripto = cripto.getSimbolo();
			}
		}
		System.out.println("Cripto recomendada: " + maxCripto);
	}

	private void mostrarHistorico() {
		if (!listaTransaccion.isEmpty()) {
			for (Transaccion transaccion : listaTransaccion) {
				System.out.println(transaccion);
			}
		} else
			System.out.println("No tiene transacciones realizadas");
	}

	private void actualizarHistorialCSV() {
		if (!listaTransaccion.isEmpty()) {
			Archivo.escribirListaEnCSV(Transaccion.toStringArray(listaTransaccion),
					"casoDePrueba/" + nombre + "_historico.csv");
		}
	}

}
