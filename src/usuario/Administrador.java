package usuario;

import java.util.List;
import java.util.Scanner;

import archivo.Archivo;
import criptomoneda.Criptomoneda;
import criptomoneda.Mercado;

public class Administrador extends Usuario{
	private String perfil;
	public Administrador(String nombre, String perfil) {
		super(nombre);
		this.perfil = perfil;
		// TODO Auto-generated constructor stub
	}
	public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        
        while (numero != 6) {
        	System.out.println();
            System.out.println();
            System.out.println();
            do {
                System.out.println("Menú de opciones");
                System.out.println("-----------------------");
                System.out.println("1) Crear Criptomoneda");
                System.out.println("2) Modificar Criptomoneda");
                System.out.println("3) Eliminar Criptomoneda");
                System.out.println("4) Consultar Criptomoneda");
                System.out.println("5) Consultar estado actual del mercado");
                System.out.println("6) Salir");
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
                	System.out.println();
                    System.out.println();
                    System.out.println();
                    mostrarEstadoActualMercado();
                    System.out.println("Seleccionaste: Consultar estado actual del mercado");
                    break;
                default:
                    System.out.println("Saliendo...");
                    break;
            }
        }
        scanner.close(); // Cerrar Scanner al salir del bucle
        
    }
	private static void mostrarEstadoActualMercado() {
		String[] lineas = Archivo.leerArchivo("casoDePrueba/criptomonedas.csv");
		String[] lineasMercado = Archivo.leerArchivo("casoDePrueba/mercado.csv");
		List<Criptomoneda> listaCripto = Criptomoneda.trozearString(lineas);
		List<Mercado> listaMercado = Mercado.trozearString(lineasMercado);
		
		for(Criptomoneda cripto : listaCripto) {
			System.out.printf("%-10s %-20s %-10s %-20s %-10s %-20s%n" ,"Nombre: ",cripto.getNombre(),
					" Símbolo: ",cripto.getSimbolo()," Precio en dólares: ",cripto.getValor());
			System.out.println("Datos del Mercado:");
			Mercado mer =  Mercado.find(listaMercado, cripto.getSimbolo());
			System.out.printf("%-20s %-40s %-30s%n","Capacidad", "volumen en las últimas 24 horas","Variación en los últimos 7 días");
			if(mer != null)
				System.out.printf("%-20s %-40s %-30s%n", mer.getCapacidad(), mer.getVolumen24Hs(), mer.getVar7dias());
			else
				System.out.printf("%-20s %-40s %-30s%n", null, null,null);
		}
		
		
	}
}






