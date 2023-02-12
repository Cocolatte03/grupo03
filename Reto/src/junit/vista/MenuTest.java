package junit.vista;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.controlador.Controlador;

public class MenuTest {
	
	private static Controlador controlador = null;
	
	@BeforeClass
	public static void setUpCines() throws AWTException {
		controlador = new Controlador();
		
	}

	@Test
	public void testCines() {
		
		ArrayList<Cine> cines = null;
		cines =	controlador.guardarArrayListCines();
		Cine cine = cines.get(0);
		
		int id = cine.getId();
		String nombre = cine.getNombre();
		String direccion = cine.getDireccion();
		
		int idEsp = 1;
		String nombreEsp = "Cine Elorrieta Bilbao"; 
		String direccionEsp = "Gran Vía de Don Diego López de Haro, 13, Bilbao";
		
		assertNotNull(cines);
		
		assertEquals(idEsp, id);
		assertEquals(nombreEsp, nombre);
		assertEquals(direccionEsp, direccion);
		
	}
	
	@Test
	public void testPeliculasCine() {
		
		ArrayList<Cine> cines = controlador.guardarArrayListCines();
		Cine cine = cines.get(0);
		
		ArrayList<Pelicula> peliculas = null;
		peliculas = controlador.guardarArrayListPeliculas(cine);
		Pelicula pelicula = peliculas.get(0);
		
		int id = pelicula.getId();
		String titulo = pelicula.getTitulo();
		String genero = pelicula.getGenero();
		int duracion = pelicula.getDuracion();
		int coste = pelicula.getCoste();
		String caratula = pelicula.getCaratula();
		
		int idEsp = 1;
		String tituloEsp = "Handia"; 
		String generoEsp = "Drama";
		int duracionEsp = 116;
		int costeEsp = 3500000;
		String caratulaEsp = "img/pd1.jpg";
		
		assertNotNull(peliculas);
		
		assertEquals(idEsp, id);
		assertEquals(tituloEsp, titulo);
		assertEquals(generoEsp, genero);
		assertEquals(duracionEsp, duracion);
		assertEquals(costeEsp, coste);
		assertEquals(caratulaEsp, caratula);
		
	}

}
