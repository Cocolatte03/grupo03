package cine.controlador.fichero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GestorDeFicheros {

	private File fichero;
	
	public GestorDeFicheros(File fichero) {
		this.fichero = fichero;
	}

	/*
	 * Para escribir algo en el fichero debemos usar un objeto FileWriter al que
	 * asignamos un objeto de tipo File asociado con el fichero vamos a usar para la
	 * escritura
	 */
	public void escribir(String texto) throws IOException {
		FileWriter fW = new FileWriter(fichero);
		print(fW, texto);
		fW.close();
	}

	/*
	 * Para facilitar el proceso de escritura debemos crear un buffer de memoria
	 * asociado al fichero para lo que creamos un objeto PrintWriter y lo asociamos
	 * al objeto FileWriter
	 */
	private void print(FileWriter fW, String texto) {
		PrintWriter pW = new PrintWriter(fW);
		pW.println(texto);
		pW.close();
	}

	/*
	 * Para leer el contenido de un fichero debemos usar un objeto FileReader al que
	 * asignamos un objeto de tipo File asociado con el fichero vamos a usar para la
	 * lectura.
	 * 
	 * Además, para facilitar el proceso de lectura debemos crear un buffer de
	 * memoria asociado al fichero para lo que creamos un objeto BufferedReader y lo
	 * asociamos al objeto FileReader.
	 * 
	 * Una vez abierto el fichero de texto en modo lectura leemos su contenido
	 * línea a línea desde el buffer usando el método readLine escribiendo.
	 */
	public String leer() throws IOException {
		String ret = "";
		FileReader fR = null;
		BufferedReader bR = null;

		try {
			fR = new FileReader(fichero);
			bR = new BufferedReader(fR);

			String linea = null;
			while ((linea = bR.readLine()) != null) {
				ret += linea + "\n";
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != bR)
					bR.close();
			} catch (IOException e) {
			}
			try {
				if (null != fR)
					fR.close();
			} catch (IOException e) {
			}
		}
		return ret;
	}
	
	public void anadir(String text) throws IOException {
		FileWriter fW = new FileWriter(fichero, true);
		print(fW, text);
		fW.close();
	}

}
