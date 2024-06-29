package archivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Archivo {
	// Leer archivo csv y devolver registros
	public static String[] leerArchivo(String nombre) {
		ArrayList<String> lineas = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(nombre))) {
			scanner.useLocale(Locale.ENGLISH);
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				lineas.add(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arrayLineas = new String[lineas.size()];
		arrayLineas = lineas.toArray(arrayLineas);
		return arrayLineas;
	}
	
	// Para encontrar la linea coincidente con "clave" en pos "n" en el archivo "nombre"
	public static String[] buscarPorClaveYPosicion(String nombre, String clave, int n) {
        try (Scanner scanner = new Scanner(new File(nombre))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(","); // Separar por comas
                if (partes.length > n && partes[n].trim().equals(clave)) { // Trim para eliminar espacios en blanco
                    return partes; // Devuelve las partes si se encuentra la clave en la posición especificada
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Devuelve null si no se encuentra la clave en ninguna línea del archivo
    }

	// Método para insertar un registro al final de un archivo CSV
    public static void insertarRegistro(String nombre, String registro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombre, true))) {
            writer.newLine(); // Salto de línea para asegurarse de que el nuevo registro comience en una nueva línea
            writer.write(registro); // Escribe el registro al final del archivo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}

