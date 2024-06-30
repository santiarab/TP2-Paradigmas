package criptomoneda;

import java.util.ArrayList;
import java.util.List;

public class Criptomoneda {
	private String simbolo, nombre;
	private double valor;

	public Criptomoneda(String nombre, String simbolo, double valor) {
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public static List<Criptomoneda> trozearString(String[] lista) {
		List<Criptomoneda> listaCripto = new ArrayList<Criptomoneda>();
		for (String str : lista) {
			String[] partes = str.split(",");

			listaCripto.add(new Criptomoneda(partes[0], partes[1], Double.parseDouble(partes[2])));
		}
		return listaCripto;
	}

	public static Criptomoneda buscarSimboloEnLista(List<Criptomoneda> listaCripto, String simbolo){
		for (Criptomoneda cripto : listaCripto){
			if(cripto.getSimbolo().equalsIgnoreCase(simbolo)){
				return cripto;
			}
		}
		return null;
	}

	public static String[] toStringArray(List<Criptomoneda> listaCripto) {
        String[] resultado = new String[listaCripto.size()];
        int i = 0;

        for (Criptomoneda cripto : listaCripto) {
            resultado[i++] = cripto.getNombre() + "," + cripto.getSimbolo() + "," +
                             cripto.getValor();
        }

        return resultado;
    }

	@Override
	public String toString() {
		return "Nombre: " + nombre + ". Simbolo: " + simbolo + ". Valor: " + valor;
	}

}
