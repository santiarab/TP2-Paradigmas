package usuario;

import java.util.ArrayList;
import java.util.List;

import archivo.Archivo;
import criptomoneda.Criptomoneda;
import criptomoneda.Mercado;

public class Usuario {
	protected String nombre;
	protected static List<Criptomoneda> listaCripto;
	protected static List<Mercado> listaMercado;

	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	public static String[] validarUsuario(String nombre){
		return  Archivo.buscarPorClaveYPosicion("casoDePrueba/usuarios.csv", nombre, 0);
	}
	
	public static void inicializarListas() {
		String[] lineas = Archivo.leerArchivo("casoDePrueba/criptomonedas.csv");
		String[] lineasMercado = Archivo.leerArchivo("casoDePrueba/mercado.csv");
		listaCripto = Criptomoneda.trozearString(lineas);
		listaMercado = Mercado.trozearString(lineasMercado);
	}

	protected void consultarCriptomoneda(){
		//PEDIR CRIPTO
		//BUSCARLA
	}

	protected void mostrarEstadoActualMercado() {
		// Posible Mejora seria ir recorriendo ambos archivos sin la necesidad de
		// abrirlos y guardarlo en una lista
		String[] lineas = Archivo.leerArchivo("casoDePrueba/criptomonedas.csv");
		String[] lineasMercado = Archivo.leerArchivo("casoDePrueba/mercado.csv");
		List<Criptomoneda> listaCripto = Criptomoneda.trozearString(lineas);
		List<Mercado> listaMercado = Mercado.trozearString(lineasMercado);

		for (Criptomoneda cripto : listaCripto) {
			System.out.printf("%-10s %-20s %-10s %-20s %-10s %-20s%n", "Nombre: ", cripto.getNombre(), " Símbolo: ",
					cripto.getSimbolo(), " Precio en dólares: ", cripto.getValor());
			System.out.println("Datos del Mercado:");
			Mercado mer = Mercado.find(listaMercado, cripto.getSimbolo());
			System.out.printf("%-20s %-40s %-30s%n", "Capacidad", "volumen en las últimas 24 horas",
					"Variación en los últimos 7 días");
			if (mer != null)
				System.out.printf("%-20s %-40s %-30s%n", mer.getCapacidad(), mer.getVolumen24Hs(), mer.getVar7dias());
			else
				System.out.printf("%-20s %-40s %-30s%n", null, null, null);
		}

	}
}
