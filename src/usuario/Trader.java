package usuario;

import java.util.Scanner;

import Validaciones.Validaciones;
import archivo.Archivo;

public class Trader extends Usuario {
	private int nroCuentaBancaria;
	private double saldoActual;
	private String nombreBanco;

	public Trader(String nombre, int nroCuentaBancaria, String nombreBanco, double saldoActual) {
		super(nombre);
		this.nombreBanco = nombreBanco;
		this.nroCuentaBancaria = nroCuentaBancaria;
		this.saldoActual = saldoActual;
	}

	public void menu() {
		Scanner scanner = new Scanner(System.in);
		int numero = 0;
		String nombre;
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
					// Funcionalidad FALTA
					System.out.println("Seleccionaste: Comprar Criptomoneda");
					this.comprarCriptomoneda();
					break;
				case 2:
					// Funcionalidad FALTA
					System.out.println("Seleccionaste: Vender Criptomoneda");
					break;
				case 3:
					System.out.println("Seleccionaste: Consultar Criptomoneda");
					this.consultarCriptomoneda(scanner);
					break;
				case 4:
					// Funcionalidad FALTA
					System.out.println("Seleccionaste: Recomendar Criptomoneda");
					this.recomendarCripto();
					break;
				case 5:
					System.out.println("Seleccionaste: Consultar estado actual del mercado");
					this.mostrarEstadoActualMercado();
					break;
				case 6:
					// Funcionalidad FALTA
					System.out.println("Seleccionaste: Visualizar archivo de transacciones");
					break;
				case 7:
					System.out.println("Seleccionaste: Salir");
					scanner.close();
					return;
			}
		}
	}

	public void comprarCriptomoneda() {
		Scanner scanner = new Scanner(System.in);
		String simb;
		float val, cap, cant;
		do {
			simb = scanner.nextLine();
		} while (Archivo.buscarPorClaveYPosicion("casoDePrueba/criptomonedas.csv", simb, 1) == null);
		// BUSCO ESE SIMBOLO EN EL ARCHIVO
		val = Float.parseFloat(Archivo.buscarPorClaveYPosicion("casoDePrueba/criptomonedas.csv", simb, 1)[2]);
		cap = Float.parseFloat(Archivo.buscarPorClaveYPosicion("casoDePrueba/mercado.csv", simb, 0)[1]);
		System.out.println("Valor: " + val + " , Capacidad: " + cap);
		cant = scanner.nextInt();
		if (cant >= cap) {
			if (this.saldoActual > cant * val) {
				// CREAR O MODIFICAR NOMBREDEUSUARIO_HISTORICO.CSV
				// ACTUALIZAR SALDO EN CSV Y JAVA
				// RESTAR CAP DE MERCADO.CSV POR LA CANTIDAD COMPRADA, VOL Y VAR += 5%
				if (cant > 1000) {
					// EN CRIPTO.CSV HAGO VALOR+=10%
				}
			} else {
				System.out.println("Falta " + (cant * val - this.saldoActual)
						+ " dinero para completar la operación.\nIngrese dinero y vuelva a intentarlo.");
			}
		} else {
			System.out.println("ERROR: No alcanza");
		}
		scanner.close();
	}

	public void recomendarCripto() {
		int max = 0;
		String maxCripto;
		String[] lectura;
		// lectura = Archivo.leerArchivo()
		// WHILE LEO CRIPTO
		// {
		// if( ( CAPACIDAD / PRECIO ) * 100 > max)
		// {
		// max = ( CAPACIDAD / PRECIO ) * 100;
		// maxCripto = LA CRIPTO QUE ESTOY LEYENDO;
		// }
		// }
		// System.out.println("Cripto recomendada: " + maxCripto);
	}
}
