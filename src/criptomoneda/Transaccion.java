package criptomoneda;

import java.util.ArrayList;
import java.util.List;

public class Transaccion {
    private String simbolo;
    private double cantidad;

    public String getSimbolo() {
        return this.simbolo;
    }

    public double getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(double cant) {
        this.cantidad = cant;
    }

    public Transaccion(String simbolo, double cantidad) {
        this.simbolo = simbolo;
        this.cantidad = cantidad;
    }

    public static List<Transaccion> trozearString(String[] lista) {
        List<Transaccion> listaTransaccion = new ArrayList<Transaccion>();
        for (String str : lista) {
            String[] partes = str.split(",");

            listaTransaccion.add(new Transaccion(partes[0], Double.parseDouble(partes[1])));
        }
        return listaTransaccion;
    }

    public static Transaccion buscarSimboloEnLista(List<Transaccion> listaTransaccion, String simbolo) {
		for (Transaccion transaccion : listaTransaccion) {
			if (transaccion.getSimbolo().equalsIgnoreCase(simbolo)) {
				return transaccion;
			}
		}
		return null;
	}
    
    public static String[] toStringArray(List<Transaccion> listaTransaccion) {
        String[] resultado = new String[listaTransaccion.size()];
        int i = 0;

        for (Transaccion transaccion : listaTransaccion) {
            resultado[i++] = transaccion.getSimbolo() + "," + transaccion.getCantidad();
        }

        return resultado;
    }

    @Override
    public String toString(){
        return "Simbolo: " + simbolo + ", Cantidad: " + cantidad;
    }
    
}
