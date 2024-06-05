package archivo;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Archivo {
	// Constructor
	public static String[] leerArchivo(String nombre) {
		ArrayList<String> lineas = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(nombre))){
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
}