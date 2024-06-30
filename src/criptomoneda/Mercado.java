package criptomoneda;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

	public void actualizarPorCompra(double cant) {
		this.capacidad -= cant;
		this.var7dias += 5;
		this.volumen24Hs += 5;
	}

	public void actualizarPorVenta(double cant) {
		this.capacidad += cant;
		this.var7dias -= 7;
		this.volumen24Hs -= 7;
	}

	public static List<Mercado> trozearString(String[] lista) {
		List<Mercado> listaMercado = new ArrayList<>();
		for (String str : lista) {
			String[] partes = str.split(",");
			String nombre = partes[0];
			double valor1 = Double.parseDouble(partes[1]);

			// Quitar el porcentaje del tercer elemento y convertirlo a double
			double valor2 = Double.parseDouble(partes[2].replace("%", ""));

			// Quitar el porcentaje y el signo del cuarto elemento y convertirlo a double
			double valor3 = Double.parseDouble(partes[3].replace("%", "").replace("+", "").replace("-", ""));
			if (partes[3].contains("-")) {
				valor3 = -valor3;
			}

			listaMercado.add(new Mercado(nombre, valor1, valor2, valor3));
		}
		return listaMercado;
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
			// Formatear volumen24Hs con un % al final
			String volumen24Hs = String.format(Locale.US, "%.2f%%", mercado.getVolumen24Hs());

			// Formatear var7dias con el signo correspondiente y un % al final
			String var7dias;
			if (mercado.getVar7dias() >= 0) {
				var7dias = String.format(Locale.US, "+%.2f%%", mercado.getVar7dias());
			} else {
				var7dias = String.format(Locale.US, "%.2f%%", mercado.getVar7dias());
			}

			// Construir el string final
			resultado[i++] = mercado.getSimbolo() + "," + mercado.getCapacidad() + "," + volumen24Hs + "," + var7dias;
		}

		return resultado;
	}

	@Override
	public String toString() {
		return "Simbolo: " + simbolo + ". Capacidad: " + capacidad + ". Volumen en las últimas 24 horas: " + volumen24Hs
				+ ". Variación en los últimos 7 días: " + var7dias;
	}
}
