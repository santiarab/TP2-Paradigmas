package criptomoneda;

import java.util.ArrayList;
import java.util.List;

public class Criptomoneda {
	private String simbolo, nombre;
	private float valor;

	public Criptomoneda(String nombre, String simbolo, float valor) {
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.valor = valor;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public static List<Criptomoneda> trozearString(String[] lista) {
		List<Criptomoneda> listaCripto = new ArrayList<Criptomoneda>();
		for (String str : lista) {
			String[] partes = str.split(",");

			listaCripto.add(new Criptomoneda(partes[0], partes[1], Float.parseFloat(partes[2])));
		}
		return listaCripto;
	}

	public static Criptomoneda buscarSimboloEnListaCripto(List<Criptomoneda> listaCripto, String simbolo){
		for (Criptomoneda cripto : listaCripto){
			if(cripto.getSimbolo().equalsIgnoreCase(simbolo)){
				return cripto;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ". Simbolo: " + simbolo + ". Valor: " + valor;
	}

}
