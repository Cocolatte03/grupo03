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
		assertNotNull(pelicula);
	}
	
	@Test
	public void testConstructorSobrecargado() {
		int id = 1;
		String titulo = "Handia";
		int duracion = 230;
		String genero = "Drama";
		
		Pelicula pelicula = new Pelicula(id, titulo, duracion, genero);
		assertEquals(pelicula.getId(), id);
		
	}

}
