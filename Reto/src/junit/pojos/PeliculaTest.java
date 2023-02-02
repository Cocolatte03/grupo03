package junit.pojos;

import static org.junit.Assert.*;

import org.junit.Test;

import cine.bbdd.pojos.Pelicula;

public class PeliculaTest {

	/*
	 * Probar el constructor vacío
	 */
	@Test
	public void testConstructorVacio() {
		Pelicula pelicula = new Pelicula();

		assertEquals(pelicula.getId(), 0);
		assertNull(pelicula.getTitulo());
		assertEquals(pelicula.getDuracion(), 0);
		assertNull(pelicula.getGenero());
		assertEquals(pelicula.getCoste(), 0);
		assertNull(pelicula.getProyecciones());
	}

	/*
	 * Probar el constructor sobrecargado
	 */
	@Test
	public void testConstructorSobrecargado() {
		int id = 1;
		String titulo = "Handia";
		int duracion = 200;
		String genero = "Drama";
		int coste = 100000;

		Pelicula pelicula = new Pelicula(id, titulo, duracion, genero, coste);
		
		assertEquals(pelicula.getId(), id);
		assertEquals(pelicula.getTitulo(), titulo);
		assertEquals(pelicula.getDuracion(), duracion);
		assertEquals(pelicula.getGenero(), genero);
		assertEquals(pelicula.getCoste(), coste);
	}

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
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {
		int id = 1;
		String titulo = "Handia";
		int duracion = 200;
		String genero = "Drama";
		int coste = 100000;

		Pelicula pelicula = new Pelicula(id, titulo, duracion, genero, coste);

		String esperado = "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero
				+ ", coste=" + coste + "]";

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

		Pelicula pelicula1 = new Pelicula(id, titulo, duracion, genero, coste);
		Pelicula pelicula2 = new Pelicula(id, titulo, duracion, genero, coste);

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

		Pelicula pelicula1 = new Pelicula(id, titulo, duracion, genero, coste);
		Pelicula pelicula2 = new Pelicula(id2, titulo, duracion, genero, coste);

		assertFalse(pelicula1.equals(pelicula2));
		assertFalse(pelicula2.equals(pelicula1));
	}

}
