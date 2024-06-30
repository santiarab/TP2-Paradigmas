package criptomoneda;

import java.util.ArrayList;
import java.util.List;

public class Mercado {
	private String simbolo;
	private double capacidad, volumen24Hs, var7dias;

	public Mercado(String simbolo, double capacidad, double volumen24Hs, double var7dias) {
		this.simbolo = simbolo;
		this.capacidad = capacidad;
		this.volumen24Hs = volumen24Hs;
		this.var7dias = var7dias;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public double getVolumen24Hs() {
		return volumen24Hs;
	}

	public void setVolumen24Hs(double volumen24Hs) {
		this.volumen24Hs = volumen24Hs;
	}

	public double getVar7dias() {
		return var7dias;
	}

	public void setVar7dias(double var7dias) {
		this.var7dias = var7dias;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public void actualizarPorCompra(double cant){
		this.capacidad-=cant;
		this.var7dias+=5;
		this.volumen24Hs+=5;
	}

	public void actualizarPorVenta(double cant){
		this.capacidad+=cant;
		this.var7dias-=7;
		this.volumen24Hs-=7;
	}

	public static List<Mercado> trozearString(String[] lista) {
		List<Mercado> listaCripto = new ArrayList<Mercado>();
		for (String str : lista) {
			String[] partes = str.split(",");
			listaCripto.add(new Mercado(partes[0], Double.parseDouble(partes[1]), Double.parseDouble(partes[2]),
					Double.parseDouble(partes[3])));
		}
		return listaCripto;
	}

	public static Mercado buscarSimboloEnLista(List<Mercado> listaMercado, String simbolo) {
		for (Mercado mercado : listaMercado) {
			if (mercado.getSimbolo().equalsIgnoreCase(simbolo)) {
				return mercado;
			}
		}
		return null;
	}

	public static String[] toStringArray(List<Mercado> listaMercado) {
		String[] resultado = new String[listaMercado.size()];
		int i = 0;

		for (Mercado mercado : listaMercado) {
			resultado[i++] = mercado.getSimbolo() + "," + mercado.getCapacidad() + "," +
					mercado.getVolumen24Hs() + "," + mercado.getVar7dias();
		}

		return resultado;
	}

	@Override
	public String toString() {
		return "Simbolo: " + simbolo + ". Capacidad: " + capacidad + ". Volumen en las últimas 24 horas: " + volumen24Hs
				+ ". Variación en los últimos 7 días: " + var7dias;
	}
}
