package archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Archivo {
	// Leer archivo csv y devolver registros
    public static String[] leerArchivo(String nombre) {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = new File(nombre);
        
        // Verificar si el archivo existe antes de intentar leerlo
        if (!archivo.exists()) {
            return new String[0]; // Retornar un array vacío
        }

        try (Scanner scanner = new Scanner(archivo)) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                lineas.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lineas.toArray(new String[0]);
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
        File archivo = new File(nombre);
        boolean archivoVacio = archivo.length() == 0;
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            if (!archivoVacio) {
                writer.newLine(); // Salto de línea solo si el archivo no está vacío
            }
            writer.write(registro); // Escribe el registro al final del archivo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void escribirListaEnCSV(String[] lineas, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {

            // Escribir cada string en una nueva línea
            for (String linea : lineas) {
                writer.append(linea).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Para actualizar usuarios.csv
    public static void escribirOActualizarStringEnCSV(String linea, String nombreArchivo, String clave, int posicion) {
        List<String> lineas = new ArrayList<>();
        boolean actualizado = false;

        // Leer el archivo y buscar la clave
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String lineaArchivo;
            while ((lineaArchivo = reader.readLine()) != null) {
                String[] campos = lineaArchivo.split(",");
                if (campos.length > posicion && campos[posicion].equals(clave)) {
                    lineas.add(linea); // Reemplazar la línea existente con la nueva
                    actualizado = true;
                } else {
                    lineas.add(lineaArchivo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Si no se encontró la clave, agregar la nueva línea
        if (!actualizado) {
            lineas.add(linea);
        }

        // Escribir todas las líneas de nuevo al archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String l : lineas) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}