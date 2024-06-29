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

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getVolumen24Hs() {
		return volumen24Hs;
	}

	public void setVolumen24Hs(String volumen24Hs) {
		this.volumen24Hs = volumen24Hs;
	}

	public String getVar7dias() {
		return var7dias;
	}

	public void setVar7dias(String var7dias) {
		this.var7dias = var7dias;
	}

	public float getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(float capacidad) {
		this.capacidad = capacidad;
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

	public static Mercado find(List<Mercado> ls, String simbol) {
		for (Mercado mer : ls) {
			if (mer.simbolo.equalsIgnoreCase(simbol))
				return mer;
		}
		return null;
	}
}
