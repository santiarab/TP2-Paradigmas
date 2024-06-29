package usuario;

import java.util.Scanner;

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
			} while (numero < 1 || numero > 7);
			switch (numero) {
			case 1:
				// Funcionalidad FALTA
				System.out.println("Seleccionaste: Comprar Criptomoneda");
				this.comprarCriptomoneda();
				break;
			case 2:
				// Funcionalidad
				System.out.println("Seleccionaste: Vender Criptomoneda");
				break;
			case 3:
				// Funcionalidad FALTA
				System.out.println("Seleccionaste: Consultar Criptomoneda");
				this.consultarCriptomoneda();
				break;
			case 4:
				// Funcionalidad FALTA
				System.out.println("Seleccionaste: Recomendar Criptomoneda");
				this.recomendarCripto();
				break;
			case 5:
				// Funcionalidad
				System.out.println("Seleccionaste: Consultar estado actual del mercado");
				break;
			case 6:
				// Funcionalidad
				System.out.println("Seleccionaste: Visualizar archivo de transacciones");
				break;
			case 7:
				// Funcionalidad LISTO
				System.out.println("Seleccionaste: Salir");
				break;
			default:
				System.out.println("Saliendo...");
				break;
			}
		}
		scanner.close(); // Cerrar Scanner al salir del bucle
	}

	public void comprarCriptomoneda(){
		Scanner scanner = new Scanner(System.in);
		String simb;
		float val,cap,cant;
		do{
			simb = scanner.nextLine();
		}while(Archivo.buscarPorClaveYPosicion("casoDePrueba/criptomonedas.csv",simb,1) == null);
		//BUSCO ESE SIMBOLO EN EL ARCHIVO
		val = Float.parseFloat(Archivo.buscarPorClaveYPosicion("casoDePrueba/criptomonedas.csv",simb,1)[2]);
		cap = Float.parseFloat(Archivo.buscarPorClaveYPosicion("casoDePrueba/mercado.csv",simb,0)[1]);
		System.out.println("Valor: " + val + " , Capacidad: " + cap);
		cant = scanner.nextInt();
		if(cant>=cap){
			if(this.saldoActual > cant * val){
				//CREAR O MODIFICAR NOMBREDEUSUARIO_HISTORICO.CSV
				//ACTUALIZAR SALDO EN CSV Y JAVA
				//RESTAR CAP DE MERCADO.CSV POR LA CANTIDAD COMPRADA, VOL Y VAR += 5%
				if(cant>1000){
					//EN CRIPTO.CSV HAGO VALOR+=10%
				}
			}
			else{
				System.out.println("Falta " + (cant * val - this.saldoActual) + " dinero para completar la operación.\nIngrese dinero y vuelva a intentarlo.");
			}
		}
		else{
			System.out.println("ERROR: No alcanza");
		}
		scanner.close();
	}

	public void recomendarCripto(){
		int max = 0;
		String maxCripto;
		String[] lectura;
		//lectura = Archivo.leerArchivo()
		//WHILE LEO CRIPTO
		//{
			//if( ( CAPACIDAD / PRECIO ) * 100 > max)
			//{
				//max = ( CAPACIDAD / PRECIO ) * 100;
				//maxCripto = LA CRIPTO QUE ESTOY LEYENDO;
			//}
		//}
		//System.out.println("Cripto recomendada: " + maxCripto);
	}
}

