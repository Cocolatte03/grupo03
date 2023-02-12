package junit.vista;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.controlador.Controlador;

public class MenuTest {

	private static Controlador controlador = null;

	@BeforeClass
	public static void setUpCines() throws AWTException {
		controlador = new Controlador();

	}

	/**
	 * Comprueba que el listado de cines se carga correctamente.
	 */
	@Test
	public void testCines() {

		ArrayList<Cine> cines = null;
		cines = controlador.guardarArrayListCines();

		assertNotNull(cines);
		System.out.println("testPeliculasCine --> El ArrayList cines no es nulo");
		
	}
	
	/**
	 * Comprueba que un cine existe en el listado.
	 */
	@Test
	public void testCineDisponible() {

		ArrayList<Cine> cines = controlador.guardarArrayListCines();

		String cineDisponible = "Cine Elorrieta Bilbao";
		boolean estaElCineEnElListado = false;

		for (int i = 0; i < cines.size(); i++) {
			Cine cine = cines.get(i);
			String nombre = cine.getNombre();
			if (nombre.equalsIgnoreCase(cineDisponible)) {
				estaElCineEnElListado = true;
			}
		}

		assertTrue(estaElCineEnElListado);
		System.out.println("testCineDisponible --> El " + cineDisponible + " está en el listado de cines");
		
	}
	
	/**
	 * Comprueba que un cine no existe en el listado.
	 */
	@Test
	public void testCineNoDisponible() {

		ArrayList<Cine> cines = controlador.guardarArrayListCines();

		String cineNoDisponible = "Cine Elorrieta Gorliz";
		boolean estaElCineEnElListado = false;

		for (int i = 0; i < cines.size(); i++) {
			Cine cine = cines.get(i);
			String nombre = cine.getNombre();
			if (nombre.equalsIgnoreCase(cineNoDisponible)) {
				estaElCineEnElListado = true;
			}
		}

		assertFalse(estaElCineEnElListado);
		System.out.println("testCineDisponible --> El " + cineNoDisponible + " no está en el listado de cines");
		
	}

	/**
	 * Comprueba que el listado de películas del cine se carga correctamente.
	 */
	@Test
	public void testPeliculasCine() {

		ArrayList<Cine> cines = controlador.guardarArrayListCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = controlador.guardarArrayListPeliculas(cine);

		assertNotNull(peliculas);
		System.out.println("testPeliculasCine --> El ArrayList peliculas no es nulo");

	}

	/**
	 * Comprueba que una película no existe en el listado del cine.
	 */
	@Test
	public void testPeliculaNoDisponibleCine() {

		ArrayList<Cine> cines = controlador.guardarArrayListCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = controlador.guardarArrayListPeliculas(cine);

		String tituloNoDisponible = "La Cenicienta";
		boolean estaLaPeliculaEnElListado = false;

		for (int i = 0; i < peliculas.size(); i++) {
			Pelicula pelicula = peliculas.get(i);
			String titulo = pelicula.getTitulo();
			if (titulo.equalsIgnoreCase(tituloNoDisponible)) {
				estaLaPeliculaEnElListado = true;
			}
		}

		assertFalse(estaLaPeliculaEnElListado);
		System.out.println("testPeliculaNoDisponibleCine --> La película " + tituloNoDisponible + " no se emite en el " + cine.getNombre());

	}

	/**
	 * Comprueba que una película existe en el listado del cine.
	 */
	@Test
	public void testPeliculaDisponibleCine() {

		ArrayList<Cine> cines = controlador.guardarArrayListCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = controlador.guardarArrayListPeliculas(cine);

		String tituloDisponible = "Handia";
		boolean estaLaPeliculaEnElListado = false;

		for (int i = 0; i < peliculas.size(); i++) {
			Pelicula pelicula = peliculas.get(i);
			String titulo = pelicula.getTitulo();
			if (titulo.equalsIgnoreCase(tituloDisponible)) {
				estaLaPeliculaEnElListado = true;
			}
		}

		assertTrue(estaLaPeliculaEnElListado);
		System.out.println("testPeliculaDisponibleCine --> La película " + tituloDisponible + " se emite en el " + cine.getNombre());

	}

	/**
	 * Comprueba que las películas están ordenadas según la fecha de emisión.
	 */
	@Test
	public void testPeliculasOrden() {

		ArrayList<Cine> cines = controlador.guardarArrayListCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = controlador.guardarArrayListPeliculas(cine);

		Pelicula pelicula1 = peliculas.get(0);

		int indiceUltimaPeli = peliculas.size() - 1;
		Pelicula pelicula2 = peliculas.get(indiceUltimaPeli);

		ArrayList<Proyeccion> proyecciones1 = null;
		proyecciones1 = controlador.guardarArrayListProyeccionesAgrupadas(cine, pelicula1);
		pelicula1.setProyecciones(proyecciones1);
		Proyeccion proyeccion1 = proyecciones1.get(0);
		LocalDate fecha1 = proyeccion1.getFecha();

		ArrayList<Proyeccion> proyecciones2 = null;
		proyecciones2 = controlador.guardarArrayListProyeccionesAgrupadas(cine, pelicula2);
		pelicula2.setProyecciones(proyecciones2);
		Proyeccion proyeccion2 = proyecciones2.get(0);
		LocalDate fecha2 = proyeccion2.getFecha();

		assertTrue(fecha1.isBefore(fecha2));
		System.out.println("testPeliculasOrden --> La película cuya emisión es el " + fecha1 + " sale antes que la que se emite el " + fecha2);

	}

}
