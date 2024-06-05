package app;

import java.util.List;

import archivo.Archivo;
import criptomoneda.Criptomoneda;
import criptomoneda.Mercado;

public class App {
	public static void main(String[] args) {
		String[] lineas = Archivo.leerArchivo("casoDePrueba/criptomonedas.in");
		for (String string : lineas) {
			System.out.println("---" + string + "---");
		}
		List<Criptomoneda> listaCripto = Criptomoneda.trozearString(lineas);
		for (Criptomoneda cripto : listaCripto) {
			System.out.println(cripto);
		}
		System.out.println("-----------");
		System.out.println("-----------");

		String[] lineasMercado = Archivo.leerArchivo("casoDePrueba/mercado.in");
		for (String string : lineasMercado) {
			System.out.println("---" + string + "---");
		}
		List<Mercado> listaMercado = Mercado.trozearString(lineasMercado);
		for (Mercado merca : listaMercado) {
			System.out.println(merca);
		}
	}

}
