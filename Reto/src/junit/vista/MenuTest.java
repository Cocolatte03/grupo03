package junit.vista;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import cine.bbdd.gestor.GestorCine;
import cine.bbdd.gestor.GestorPelicula;
import cine.bbdd.gestor.GestorProyeccion;
import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.bbdd.pojos.Sala;

public class MenuTest {

	private static GestorCine gestorCine = null;
	private static GestorPelicula gestorPelicula = null;
	private static GestorProyeccion gestorProyeccion = null;

	@BeforeClass
	public static void setUpCines() throws AWTException {
		gestorCine = new GestorCine();
		gestorPelicula = new GestorPelicula();
		gestorProyeccion = new GestorProyeccion();

	}

	/**
	 * Comprueba que el listado de cines se carga correctamente.
	 */
	@Test
	public void testCines() {

		ArrayList<Cine> cines = null;
		cines = gestorCine.getAllCines();

		assertNotNull(cines);
		System.out.println("testCines --> El ArrayList cines no es nulo\n");

	}

	/**
	 * Comprueba que un cine existe en el listado.
	 */
	@Test
	public void testCineDisponible() {

		ArrayList<Cine> cines = gestorCine.getAllCines();

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
		System.out.println("testCineDisponible --> El " + cineDisponible + " está en el listado de cines\n");

	}

	/**
	 * Comprueba que un cine no existe en el listado.
	 */
	@Test
	public void testCineNoDisponible() {

		ArrayList<Cine> cines = gestorCine.getAllCines();

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
		System.out.println("testCineNoDisponible --> El " + cineNoDisponible + " no está en el listado de cines\n");

	}

	/**
	 * Comprueba que el listado de películas del cine se carga correctamente.
	 */
	@Test
	public void testPeliculasCine() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = gestorPelicula.getPeliculasPorCine(cine);

		assertNotNull(peliculas);
		System.out.println("testPeliculasCine --> El ArrayList peliculas no es nulo\n");

	}

	/**
	 * Comprueba que una película no existe en el listado del cine.
	 */
	@Test
	public void testPeliculaNoDisponibleCine() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = gestorPelicula.getPeliculasPorCine(cine);

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
		System.out.println("testPeliculaNoDisponibleCine --> La película " + tituloNoDisponible + " no se emite en el "
				+ cine.getNombre() + "\n");

	}

	/**
	 * Comprueba que una película existe en el listado del cine.
	 */
	@Test
	public void testPeliculaDisponibleCine() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = gestorPelicula.getPeliculasPorCine(cine);

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
		System.out.println("testPeliculaDisponibleCine --> La película " + tituloDisponible + " se emite en el "
				+ cine.getNombre() + "\n");

	}

	/**
	 * Comprueba que las películas están ordenadas según la fecha de emisión.
	 */
	@Test
	public void testPeliculasOrden() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = gestorPelicula.getPeliculasPorCine(cine);

		Pelicula primeraPeli = peliculas.get(0);

		int indiceUltimaPeli = peliculas.size() - 1;
		Pelicula ultimaPeli = peliculas.get(indiceUltimaPeli);

		ArrayList<Proyeccion> proyecciones1 = null;
		proyecciones1 = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine, primeraPeli);
		primeraPeli.setProyecciones(proyecciones1);
		Proyeccion proyeccion1 = proyecciones1.get(0);
		LocalDate fecha1 = proyeccion1.getFecha();

		ArrayList<Proyeccion> proyecciones2 = null;
		proyecciones2 = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine, ultimaPeli);
		ultimaPeli.setProyecciones(proyecciones2);
		Proyeccion proyeccion2 = proyecciones2.get(0);
		LocalDate fecha2 = proyeccion2.getFecha();

		assertTrue(fecha1.isBefore(fecha2));
		System.out.println("testPeliculasOrden --> La película cuya emisión es el " + fecha1
				+ " sale antes que la que se emite el " + fecha2 + "\n");

	}

	/**
	 * Comprueba que el listado de proyecciones se carga correctamente, agrupándolo
	 * por fechas para que no salgan fechas repetidas en caso de que una película
	 * tenga diferentes horas de emisión en un día.
	 */
	@Test
	public void testProyecciones() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		boolean sonFechasDiferentes = true;

		for (int i = 0; i < proyecciones.size(); i++) {
			LocalDate fecha1 = proyecciones.get(i).getFecha();
			if (i != 0) {
				LocalDate fecha2 = proyecciones.get(i - 1).getFecha();
				if (fecha1.equals(fecha2)) {
					sonFechasDiferentes = false;
				}
			}

		}

		assertNotNull(proyecciones);
		assertTrue(sonFechasDiferentes);

		System.out
				.println("testProyecciones --> Solamente se muestran las fechas de emisión diferentes de una película");
		for (int j = 0; j < proyecciones.size(); j++) {
			LocalDate fecha = proyecciones.get(j).getFecha();
			System.out.println("	Fecha: " + fecha);
		}

		System.out.println();

	}

	/**
	 * Comprueba que el listado de proyecciones se carga correctamente, mostrando
	 * los diferentes horarios de emisión ordenados.
	 */
	@Test
	public void testProyeccionesPorFecha() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones1 = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);
		LocalDate fecha = proyecciones1.get(0).getFecha();

		ArrayList<Proyeccion> proyecciones2 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha.toString());

		LocalTime primeraHora = proyecciones2.get(0).getHora();
		int indiceUltimaHora = proyecciones2.size() - 1;
		LocalTime ultimaHora = proyecciones2.get(indiceUltimaHora).getHora();

		assertNotNull(primeraHora);
		assertNotNull(ultimaHora);
		assertTrue(primeraHora.isBefore(ultimaHora));

		System.out.println("testProyeccionesPorFecha --> La emisión de las " + primeraHora
				+ " sale antes que la de las " + ultimaHora + "\n");

	}

	/**
	 * Comprueba que el listado de proyecciones se carga correctamente, con el
	 * precio correspondiente que puede ser diferente dependiendo del día y hora de
	 * emisión.
	 */
	@Test
	public void testProyeccionesConPrecioDiferentePorFecha() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		LocalDate fecha1 = proyecciones.get(0).getFecha();
		LocalDate fecha2 = proyecciones.get(1).getFecha();

		ArrayList<Proyeccion> proyecciones1 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha1.toString());
		ArrayList<Proyeccion> proyecciones2 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha2.toString());

		Proyeccion proyeccion1 = proyecciones1.get(0);
		Proyeccion proyeccion2 = proyecciones2.get(2);

		String titulo1 = proyeccion1.getPelicula().getTitulo();
		String titulo2 = proyeccion2.getPelicula().getTitulo();

		LocalTime hora1 = proyeccion1.getHora();
		LocalTime hora2 = proyeccion2.getHora();

		double precio1 = proyeccion1.getPrecio();
		double precio2 = proyeccion2.getPrecio();

		assertNotNull(precio1);
		assertNotNull(precio2);
		assertNotEquals(precio1, precio2);
		assertEquals(titulo1, titulo2);

		System.out.println("testProyeccionesConPrecioDiferentePorFecha --> La emisión de la película " + titulo1
				+ " del día " + fecha1 + " a las " + hora1 + " tiene un precio de " + precio1
				+ "€. \nMientras que la del día " + fecha2 + " a las " + hora2 + " tiene un precio de " + precio2
				+ "€\n");

	}

	/**
	 * Comprueba que se asignan correctamente las salas en las proyecciones.
	 */
	@Test
	public void testSalas() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		LocalDate fecha = proyecciones.get(0).getFecha();

		ArrayList<Proyeccion> proyecciones1 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha.toString());

		Sala sala = proyecciones1.get(0).getSala();
		
		assertNotNull(sala);
		System.out.println("testSalas --> La sala se asigna correctamente, no tiene un valor nulo.\n");

	}

}
