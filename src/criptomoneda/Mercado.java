package criptomoneda;

import java.util.ArrayList;
import java.util.List;

public class Mercado {
	private String simbolo, capacidad,volTotal ,volumen24Hs, var24Hs, var7dias;
	public Mercado(String simbolo, String capacidad, String volumen24Hs,String volTotal ,String var24Hs, String var7dias) {
		this.simbolo = simbolo;
		this.capacidad = capacidad;
		this.var24Hs = var24Hs;
		this.var7dias = var7dias;
		this.volumen24Hs = volumen24Hs;
		this.volTotal = volTotal;
	}
	public static Mercado trozearString(String str) {
		String[] partes = str.split("\\s+");
		return new Mercado(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
	}
	public static List<Mercado> trozearString(String[] lista) {
		List<Mercado> listaCripto = new ArrayList<Mercado>();
		for (String str : lista) {
			String[] partes = str.split("\\s+");
			if(partes.length == 6)
				listaCripto.add(new Mercado(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]));
		}
		return listaCripto;
	}
	@Override
	public String toString() {
		return "Mercado [simbolo=" + simbolo + ", capacidad=" + capacidad + ", volTotal=" + volTotal + ", volumen24Hs="
				+ volumen24Hs + ", var24Hs=" + var24Hs + ", var7dias=" + var7dias + "]";
	}
}
