package junit.pojos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import cine.bbdd.pojos.Proyeccion;

public class ProyeccionTest {

	/*
	 * Probar el constructor vacío
	 */
	@Test
	public void testConstructorVacio() {
		Proyeccion proyeccion = new Proyeccion();

		assertEquals(proyeccion.getId(), 0);
		assertEquals(proyeccion.getPrecio(), 0);
		assertNull(proyeccion.getFecha());
		assertNull(proyeccion.getHora());
		assertEquals(proyeccion.getIdPelicula(), 0);
		assertEquals(proyeccion.getIdSala(), 0);
	}

	/*
	 * Probar el constructor sobrecargado
	 */
	@Test
	public void testConstructorSobrecargado() {
		int id = 1;
		int precio = 10;
		LocalDate fecha = LocalDate.of(2023, 02, 02);
		LocalTime hora = LocalTime.of(12, 0);
		int idPelicula = 1;
		int idSala = 1;

		Proyeccion proyeccion = new Proyeccion(id, precio, fecha, hora, idPelicula, idSala);

		assertEquals(proyeccion.getId(), id);
		assertEquals(proyeccion.getPrecio(), precio);
		assertEquals(proyeccion.getFecha(), fecha);
		assertEquals(proyeccion.getHora(), hora);
		assertEquals(proyeccion.getIdPelicula(), idPelicula);
		assertEquals(proyeccion.getIdSala(), idSala);
	}

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Proyeccion proyeccion = new Proyeccion();

		int id = 0;
		proyeccion.setId(id);

		assertEquals(proyeccion.getId(), id);
	}

	/*
	 * Probar los metodos setPrecio() y getPrecio()
	 */
	@Test
	public void testPrecio() {
		Proyeccion proyeccion = new Proyeccion();

		int precio = 10;
		proyeccion.setPrecio(precio);

		assertEquals(proyeccion.getPrecio(), precio);
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
	 * Probar los metodos setIdPelicula() y getIdPelicula()
	 */
	@Test
	public void testIdPelicula() {
		Proyeccion proyeccion = new Proyeccion();

		int idPelicula = 1;
		proyeccion.setIdPelicula(idPelicula);

		assertEquals(proyeccion.getIdPelicula(), idPelicula);
	}

	/*
	 * Probar los metodos setIdSala() y getIdSala()
	 */
	@Test
	public void testIdSala() {
		Proyeccion proyeccion = new Proyeccion();

		int idSala = 1;
		proyeccion.setIdSala(idSala);

		assertEquals(proyeccion.getIdSala(), idSala);
	}

	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {
		int id = 1;
		int precio = 10;
		LocalDate fecha = LocalDate.of(2023, 02, 02);
		LocalTime hora = LocalTime.of(12, 0);
		int idPelicula = 1;
		int idSala = 1;

		Proyeccion proyeccion = new Proyeccion(id, precio, fecha, hora, idPelicula, idSala);

		String esperado = "Proyeccion [id=" + id + ", precio=" + precio + ", fecha=" + fecha + ", hora=" + hora
				+ ", idPelicula=" + idPelicula + ", idSala=" + idSala + "]";
		
		assertEquals(proyeccion.toString(), esperado);
	}
	
	/*
	 * Probar el metodo equals() con resultado verdadero
	 */
	@Test
	public void testEqualsTrue() {
		int id = 1;
		int precio = 10;
		LocalDate fecha = LocalDate.of(2023, 02, 02);
		LocalTime hora = LocalTime.of(12, 0);
		int idPelicula = 1;
		int idSala = 1;

		Proyeccion proyeccion1 = new Proyeccion(id, precio, fecha, hora, idPelicula, idSala);
		Proyeccion proyeccion2 = new Proyeccion(id, precio, fecha, hora, idPelicula, idSala);
		
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
		int precio = 10;
		LocalDate fecha = LocalDate.of(2023, 02, 02);
		LocalTime hora = LocalTime.of(12, 0);
		int idPelicula = 1;
		int idSala = 1;

		Proyeccion proyeccion1 = new Proyeccion(id, precio, fecha, hora, idPelicula, idSala);
		Proyeccion proyeccion2 = new Proyeccion(id2, precio, fecha, hora, idPelicula, idSala);
		
		assertFalse(proyeccion1.equals(proyeccion2));
		assertFalse(proyeccion2.equals(proyeccion1));
	}

}
