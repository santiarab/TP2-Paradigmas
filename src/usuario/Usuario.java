package usuario;

import java.util.ArrayList;
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
		String[] lineasCripto = Archivo.leerArchivo("casoDePrueba/criptomonedas.csv");
		String[] lineasMercado = Archivo.leerArchivo("casoDePrueba/mercado.csv");
		// Inserta el contenido leido en las listas static
		if (lineasCripto.length > 0) {
			listaCripto = Criptomoneda.trozearString(lineasCripto);
			listaMercado = Mercado.trozearString(lineasMercado);
		}
		else{
			listaCripto = new ArrayList<>();
			listaMercado = new ArrayList<>();
		}
	}

	public static void actualizarCSVs(){
		if(!listaCripto.isEmpty()){
			// Actualizo (piso) el .csv de Criptos
			Archivo.escribirListaEnCSV(Criptomoneda.toStringArray(listaCripto),"casoDePrueba/criptomonedas.csv");
			// Actualizo (piso) el .csv de Mercado
			Archivo.escribirListaEnCSV(Mercado.toStringArray(listaMercado),"casoDePrueba/mercado.csv");
		}
	}

	protected void actualizarUsuario(String usuario){
		Archivo.escribirStringEnCSV(this.nombre+","+usuario, "casoDePrueba/usuarios.csv",this.nombre,0);
	}

	protected void consultarCriptomoneda(Scanner scanner) {
		// PEDIR CRIPTO
		String simbolo;
		do {
			System.out.println("Ingrese el símbolo de la criptomoneda que desea consultar: ");
			simbolo = Validaciones.validarString(scanner);
		} while (simbolo == null);

		// BUSCARLA

		Criptomoneda cripto = Criptomoneda.buscarSimboloEnLista(listaCripto, simbolo);

		if (cripto != null) {
			Mercado mer = Mercado.buscarSimboloEnLista(listaMercado, simbolo);

			System.out.println("\nInformación de la criptomoneda consultada\n\n"+cripto);
			// No hago print de mer para no mostrar el símbolo de nuevo
			System.out.println("\nDatos del mercado:\nCapacidad: " + mer.getCapacidad() + ". Volumen en las últimas 24 horas "
					+ mer.getVolumen24Hs() + ". Variación en los últimos 7 días " + mer.getVar7dias());

		} else
			System.out.println("El símbolo de criptomoneda ingresado no existe");
	}

	protected void mostrarEstadoActualMercado() {
		System.out.println("\nEstado actual del mercado:");
		for (Mercado mer : listaMercado) {
			System.out.println(mer);
		}
	}
}
