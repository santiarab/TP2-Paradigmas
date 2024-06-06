package criptomoneda;

import java.util.ArrayList;
import java.util.List;

public class Mercado {
	private String simbolo, volumen24Hs, var7dias;
	private float capacidad;
	public Mercado(String simbolo, float capacidad, String volumen24Hs, String var7dias) {
		this.simbolo = simbolo;
		this.capacidad = capacidad;
		this.var7dias = var7dias;
		this.volumen24Hs = volumen24Hs;
	}
	public static Mercado trozearString(String str) {
		String[] partes = str.split(",");
		return new Mercado(partes[0], Float.parseFloat(partes[1]), partes[2], partes[3]);
	}
	public static List<Mercado> trozearString(String[] lista) {
		List<Mercado> listaCripto = new ArrayList<Mercado>();
		for (String str : lista) {
			String[] partes = str.split(",");
			listaCripto.add(new Mercado(partes[0], Float.parseFloat(partes[1]), partes[2], partes[3]));
		}
		return listaCripto;
	}
	@Override
	public String toString() {
		return "Mercado [simbolo=" + simbolo + ", capacidad=" + capacidad + ", volumen24Hs=" + volumen24Hs
				+ ", var7dias=" + var7dias + "]";
	}
	
}
