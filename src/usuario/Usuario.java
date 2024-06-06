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
		String[] lineas = Archivo.leerArchivo("casoDePrueba/criptomonedas.csv");
		String[] lineasMercado = Archivo.leerArchivo("casoDePrueba/mercado.csv");
		this.listaCripto = Criptomoneda.trozearString(lineas);
		this.listaMercado = Mercado.trozearString(lineasMercado);
	}
	public static Usuario trozearString(String str) {
		String[] partes = str.split(",");
		if(partes.length > 2)
			return new Administrador(partes[0], partes[1]);
		else
			return new Trader(partes[0], Integer.parseInt(partes[1]), partes[2], Float.parseFloat(partes[3]));	
	}
	public static List<Usuario> trozearString(String[] lista) {
		List<Usuario> listaCripto = new ArrayList<Usuario>();
		for (String str : lista) {
			String[] partes = str.split(",");
			if(partes.length > 2)
				listaCripto.add(new Administrador(partes[0], partes[1]));
			else
				listaCripto.add(new Trader(partes[0], Integer.parseInt(partes[1]), partes[2], Float.parseFloat(partes[3])));
		}
		return listaCripto;
	}
}
