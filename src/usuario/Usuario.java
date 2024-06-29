package usuario;

import java.util.List;
import java.util.Scanner;

import Validaciones.Validaciones;
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

	public static String[] validarUsuario(String nomUsr) {
		return Archivo.buscarPorClaveYPosicion("casoDePrueba/usuarios.csv", nomUsr, 0);
	}

	public static void insertarUsuario(String registro) {
		Archivo.insertarRegistro("casoDePrueba/usuarios.csv", registro);
	}

	public static void inicializarListas() {
		// Manda a leer los dos archivos csv
		String[] lineas = Archivo.leerArchivo("casoDePrueba/criptomonedas.csv");
		String[] lineasMercado = Archivo.leerArchivo("casoDePrueba/mercado.csv");
		// Inserta el contenido leido en las listas static
		listaCripto = Criptomoneda.trozearString(lineas);
		listaMercado = Mercado.trozearString(lineasMercado);
	}

	protected void consultarCriptomoneda(Scanner scanner) {
		// PEDIR CRIPTO
		String simbolo;
		do {
			System.out.println("Ingrese el símbolo de la criptomoneda que desea consultar: ");
			simbolo = Validaciones.validarString(scanner);
		} while (simbolo == null);

		// BUSCARLA

		Criptomoneda cripto = Criptomoneda.buscarSimboloEnListaCripto(listaCripto, simbolo);

		if (cripto != null) {
			Mercado mer = Mercado.buscarSimboloEnLista(listaMercado, simbolo);

			System.out.println("Información de la criptomoneda consultada\n"+cripto);
			// No hago print de mer para no mostrar el símbolo de nuevo
			System.out.println("Datos del mercado:\nCapacidad: " + mer.getCapacidad() + ". Volumen en las últimas 24 horas "
					+ mer.getVolumen24Hs() + ". Variación en los últimos 7 días " + mer.getVar7dias());

		} else
			System.out.println("El símbolo de criptomoneda ingresado no existe");

	}

	protected void mostrarEstadoActualMercado() {
		System.out.println("Estado actual del mercado:");
		for (Mercado mer : listaMercado) {
			System.out.println(mer);
		}
	}
}
