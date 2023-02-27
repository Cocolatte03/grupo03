package junit.pojos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;

import cine.bbdd.pojos.Entrada;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.bbdd.pojos.Sala;

/**
 * Este test prueba la clase Proyeccion
 */
public class ProyeccionTest {

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Proyeccion proyeccion = new Proyeccion();

		int id = 1;
		proyeccion.setId(id);

		assertEquals(proyeccion.getId(), id);
	}

	/*
	 * Probar los metodos setPrecio() y getPrecio()
	 */
	@Test
	public void testPrecio() {
		Proyeccion proyeccion = new Proyeccion();

		double precio = 8.5;
		proyeccion.setPrecio(precio);

		assertEquals(proyeccion.getPrecio(), precio, 0);
	}

	/*
	 * Probar los metodos setFecha() y getFecha()
	 */
	@Test
	public void testFecha() {
		Proyeccion proyeccion = new Proyeccion();

		LocalDate fecha = LocalDate.of(2023, 02, 02);
		proyeccion.setFecha(fecha);

		assertEquals(proyeccion.getFecha(), fecha);
	}

	/*
	 * Probar los metodos setHora() y getHora()
	 */
	@Test
	public void testHora() {
		Proyeccion proyeccion = new Proyeccion();

		LocalTime hora = LocalTime.of(12, 0);
		proyeccion.setHora(hora);

		assertEquals(proyeccion.getHora(), hora);
	}
	
	/*
	 * Probar los metodos setEntradas() y getEntradas()
	 */
	@Test
	public void testEntradas() {
		Proyeccion proyeccion = new Proyeccion();

		ArrayList<Entrada> entradas = null;
		proyeccion.setEntradas(entradas);

		assertNull(proyeccion.getEntradas());
	}

	/*
	 * Probar los metodos setPelicula() y getPelicula()
	 */
	@Test
	public void testPelicula() {
		Proyeccion proyeccion = new Proyeccion();

		Pelicula pelicula = null;
		proyeccion.setPelicula(pelicula);

		assertNull(proyeccion.getPelicula());
	}

	/*
	 * Probar los metodos setSala() y getSala()
	 */
	@Test
	public void testSala() {
		Proyeccion proyeccion = new Proyeccion();

		Sala sala = null;
		proyeccion.setSala(sala);

		assertNull(proyeccion.getSala());
	}
	
	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {
		int id = 1;
		double precio = 8.5;
		LocalDate fecha = LocalDate.of(2023, 02, 02);
		LocalTime hora = LocalTime.of(12, 0);
		ArrayList<Entrada> entradas = null;
		Pelicula pelicula = null;
		Sala sala = null;

		Proyeccion proyeccion = new Proyeccion();

		proyeccion.setId(id);
		proyeccion.setPrecio(precio);
		proyeccion.setFecha(fecha);
		proyeccion.setHora(hora);
		proyeccion.setEntradas(entradas);
		proyeccion.setPelicula(pelicula);
		proyeccion.setSala(sala);

		String esperado = "Proyeccion [id=" + id + ", precio=" + precio + ", fecha=" + fecha + ", hora=" + hora
				+ " entradas=" + entradas + ", pelicula=" + pelicula + ", sala=" + sala + "]";

		assertEquals(proyeccion.toString(), esperado);
	}
	
	/*
	 * Probar el metodo equals() con resultado verdadero
	 */
	@Test
	public void testEqualsTrue() {
		int id = 1;
		double precio = 8.5;
		LocalDate fecha = LocalDate.of(2023, 02, 02);
		LocalTime hora = LocalTime.of(12, 0);
		ArrayList<Entrada> entradas = null;
		Pelicula pelicula = null;
		Sala sala = null;

		Proyeccion proyeccion1 = new Proyeccion();

		proyeccion1.setId(id);
		proyeccion1.setPrecio(precio);
		proyeccion1.setFecha(fecha);
		proyeccion1.setHora(hora);
		proyeccion1.setEntradas(entradas);
		proyeccion1.setPelicula(pelicula);
		proyeccion1.setSala(sala);

		Proyeccion proyeccion2 = new Proyeccion();

		proyeccion2.setId(id);
		proyeccion2.setPrecio(precio);
		proyeccion2.setFecha(fecha);
		proyeccion2.setHora(hora);
		proyeccion2.setEntradas(entradas);
		proyeccion2.setPelicula(pelicula);
		proyeccion2.setSala(sala);

		assertTrue(proyeccion1.equals(proyeccion2));
		assertTrue(proyeccion2.equals(proyeccion1));
	}
	
	/*
	 * Probar el metodo equals() con resultado falso
	 */
	@Test
	public void testEqualsFalse() {
		int id = 1;
		int id2 = 2;
		double precio = 8.5;
		LocalDate fecha = LocalDate.of(2023, 02, 02);
		LocalTime hora = LocalTime.of(12, 0);
		ArrayList<Entrada> entradas = null;
		Pelicula pelicula = null;
		Sala sala = null;

		Proyeccion proyeccion1 = new Proyeccion();

		proyeccion1.setId(id);
		proyeccion1.setPrecio(precio);
		proyeccion1.setFecha(fecha);
		proyeccion1.setHora(hora);
		proyeccion1.setEntradas(entradas);
		proyeccion1.setPelicula(pelicula);
		proyeccion1.setSala(sala);

		Proyeccion proyeccion2 = new Proyeccion();

		proyeccion2.setId(id2);
		proyeccion2.setPrecio(precio);
		proyeccion2.setFecha(fecha);
		proyeccion2.setHora(hora);
		proyeccion2.setEntradas(entradas);
		proyeccion2.setPelicula(pelicula);
		proyeccion2.setSala(sala);

		assertFalse(proyeccion1.equals(proyeccion2));
		assertFalse(proyeccion2.equals(proyeccion1));
	}

}