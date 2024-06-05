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

	public static Criptomoneda trozearString(String str) {
		String[] partes = str.split("\\s+");
		String[] trozearValor = partes[2].split("\\.");
		if (trozearValor.length > 1)
			return new Criptomoneda(partes[0], partes[1],
					Float.parseFloat(trozearValor[0]) * 1000 + Float.parseFloat(trozearValor[1].replace(',', '.')));
		else
			return new Criptomoneda(partes[0], partes[1], Float.parseFloat(trozearValor[0].replace(',', '.')));
	}

	public static List<Criptomoneda> trozearString(String[] lista) {
		List<Criptomoneda> listaCripto = new ArrayList<Criptomoneda>();
		for (String str : lista) {
			String[] partes = str.split("\\s+");
			String[] trozearValor = partes[2].split("\\.");
			if (trozearValor.length > 1)
				listaCripto.add(new Criptomoneda(partes[0], partes[1], Float.parseFloat(trozearValor[0]) * 1000
						+ Float.parseFloat(trozearValor[1].replace(',', '.'))));
			else
				listaCripto.add(
						new Criptomoneda(partes[0], partes[1], Float.parseFloat(trozearValor[0].replace(',', '.'))));
		}
		return listaCripto;
	}

	@Override
	public String toString() {
		return "Criptomoneda [nombre=" + nombre + ", simbolo=" + simbolo + ", valor=" + valor + "]";
	}
}
