package junit.pojos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;

/**
 * Este test prueba la clase Pelicula
 * 
 * @author leire
 *
 */
public class PeliculaTest {

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Pelicula pelicula = new Pelicula();

		int id = 1;
		pelicula.setId(id);

		assertEquals(pelicula.getId(), id);
	}

	/*
	 * Probar los metodos setTitulo() y getTitulo()
	 */
	@Test
	public void testTitulo() {
		Pelicula pelicula = new Pelicula();

		String titulo = "Handia";
		pelicula.setTitulo(titulo);

		assertEquals(pelicula.getTitulo(), titulo);
	}

	/*
	 * Probar los metodos setDuracion() y getDuracion()
	 */
	@Test
	public void testDuracion() {
		Pelicula pelicula = new Pelicula();

		int duracion = 200;
		pelicula.setDuracion(duracion);

		assertEquals(pelicula.getDuracion(), duracion);
	}

	/*
	 * Probar los metodos setGenero() y getGenero()
	 */
	@Test
	public void testGenero() {
		Pelicula pelicula = new Pelicula();

		String genero = "Drama";
		pelicula.setGenero(genero);

		assertEquals(pelicula.getGenero(), genero);
	}

	/*
	 * Probar los metodos setCoste() y getCoste()
	 */
	@Test
	public void testCoste() {
		Pelicula pelicula = new Pelicula();

		int coste = 100000;
		pelicula.setCoste(coste);

		assertEquals(pelicula.getCoste(), coste);
	}
	
	/*
	 * Probar los metodos setCaratula() y getCaratula()
	 */
	@Test
	public void testCaratula() {
		Pelicula pelicula = new Pelicula();

		String caractula = "img/p1.jpg";
		pelicula.setCaratula(caractula);

		assertEquals(pelicula.getCaratula(), caractula);
	}

	/*
	 * Probar los metodos setProyecciones() y getProyecciones()
	 */
	@Test
	public void testProyecciones() {
		Pelicula pelicula = new Pelicula();

		ArrayList<Proyeccion> proyecciones = null;
		pelicula.setProyecciones(proyecciones);

		assertNull(pelicula.getProyecciones());
	}

	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {
		int id = 1;
		String titulo = "Handia";
		int duracion = 200;
		String genero = "Drama";
		int coste = 100000;
		String caratula = "img/p1.jpg";
		ArrayList<Proyeccion> proyecciones = null;

		Pelicula pelicula = new Pelicula();

		pelicula.setId(id);
		pelicula.setTitulo(titulo);
		pelicula.setDuracion(duracion);
		pelicula.setGenero(genero);
		pelicula.setCoste(coste);
		pelicula.setCaratula(caratula);
		pelicula.setProyecciones(proyecciones);

		String esperado = "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero
				+ ", coste=" + coste + ", caratula=" + caratula + ", proyecciones=" + proyecciones + "]";

		assertEquals(pelicula.toString(), esperado);
	}

	/*
	 * Probar el metodo equals() con resultado verdadero
	 */
	@Test
	public void testEqualsTrue() {
		int id = 1;
		String titulo = "Handia";
		int duracion = 200;
		String genero = "Drama";
		int coste = 100000;
		String caratula = "img/p1.jpg";
		ArrayList<Proyeccion> proyecciones = null;

		Pelicula pelicula1 = new Pelicula();

		pelicula1.setId(id);
		pelicula1.setTitulo(titulo);
		pelicula1.setDuracion(duracion);
		pelicula1.setGenero(genero);
		pelicula1.setCoste(coste);
		pelicula1.setCaratula(caratula);
		pelicula1.setProyecciones(proyecciones);

		Pelicula pelicula2 = new Pelicula();

		pelicula2.setId(id);
		pelicula2.setTitulo(titulo);
		pelicula2.setDuracion(duracion);
		pelicula2.setGenero(genero);
		pelicula2.setCoste(coste);
		pelicula2.setCaratula(caratula);
		pelicula2.setProyecciones(proyecciones);

		assertTrue(pelicula1.equals(pelicula2));
		assertTrue(pelicula2.equals(pelicula1));
	}

	/*
	 * Probar el metodo equals() con resultado falso
	 */
	@Test
	public void testEqualsFalse() {
		int id = 1;
		int id2 = 2;
		String titulo = "Handia";
		int duracion = 200;
		String genero = "Drama";
		int coste = 100000;
		String caratula = "img/p1.jpg";
		ArrayList<Proyeccion> proyecciones = null;

		Pelicula pelicula1 = new Pelicula();

		pelicula1.setId(id);
		pelicula1.setTitulo(titulo);
		pelicula1.setDuracion(duracion);
		pelicula1.setGenero(genero);
		pelicula1.setCoste(coste);
		pelicula1.setCaratula(caratula);
		pelicula1.setProyecciones(proyecciones);

		Pelicula pelicula2 = new Pelicula();

		pelicula2.setId(id2);
		pelicula2.setTitulo(titulo);
		pelicula2.setDuracion(duracion);
		pelicula2.setGenero(genero);
		pelicula2.setCoste(coste);
		pelicula2.setCaratula(caratula);
		pelicula2.setProyecciones(proyecciones);

		assertFalse(pelicula1.equals(pelicula2));
		assertFalse(pelicula2.equals(pelicula1));
	}

}
