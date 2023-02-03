package junit.pojos;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import cine.bbdd.pojos.Entrada;

public class EntradaTest {

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Entrada entrada = new Entrada();

		int id = 0;
		entrada.setId(id);

		assertEquals(entrada.getId(), id);
	}

	/*
	 * Probar los metodos setFechaCompra() y getFechaCompra()
	 */
	@Test
	public void testFechaCompra() {
		Entrada entrada = new Entrada();

		Date fechaCompra = null;
		entrada.setFechaCompra(fechaCompra);

		assertEquals(entrada.getFechaCompra(), fechaCompra);
	}

	/*
	 * Probar los metodos setIdProyeccion() y getIdProyeccion()
	 */
	@Test
	public void testIdProyeccion() {
		Entrada entrada = new Entrada();

		int idProyeccion = 0;
		entrada.setIdProyeccion(idProyeccion);

		assertEquals(entrada.getIdProyeccion(), idProyeccion);
	}

	/*
	 * Probar los metodos setClinte() y getCliente()
	 */
	@Test
	public void testIdCliente() {
		Entrada entrada = new Entrada();

		int idCliente = 0;
		entrada.setIdCliente(idCliente);

		assertEquals(entrada.getIdCliente(), idCliente);
	}

	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {

		int id = 2;
		Date fechaCompra = null;
		int idProyeccion = 12;
		int idCliente = 123;

		Entrada entrada = new Entrada();

		entrada.setId(id);
		entrada.setFechaCompra(fechaCompra);
		entrada.setIdProyeccion(idProyeccion);
		entrada.setIdCliente(idCliente);

		String esperado = "Entrada [id=" + id + ", fechaCompra=" + fechaCompra + ", idProyeccion=" + idProyeccion
				+ ", idCliente=" + idCliente + "]";

		assertEquals(entrada.toString(), esperado);

	}

	/*
	 * Probar el metodo equals() con resultado verdadero
	 */

	@Test
	public void testEqualsTrue() {
		int id = 2;
		Date fechaCompra = null;
		int idProyeccion = 12;
		int idCliente = 123;

		Entrada entrada1 = new Entrada();

		entrada1.setId(id);
		entrada1.setFechaCompra(fechaCompra);
		entrada1.setIdProyeccion(idProyeccion);
		entrada1.setIdCliente(idCliente);

		Entrada entrada2 = new Entrada();

		entrada2.setId(id);
		entrada2.setFechaCompra(fechaCompra);
		entrada2.setIdProyeccion(idProyeccion);
		entrada2.setIdCliente(idCliente);

		assertTrue(entrada1.equals(entrada2));
		assertTrue(entrada2.equals(entrada1));
	}

	/*
	 * Probar el metodo equals() con resultado falso
	 */
	@Test
	public void testEqualsFalse() {
		int id = 2;
		int id2 = 4;
		Date fechaCompra = null;
		int idProyeccion = 12;
		int idCliente = 123;

		Entrada entrada1 = new Entrada();

		entrada1.setId(id);
		entrada1.setFechaCompra(fechaCompra);
		entrada1.setIdProyeccion(idProyeccion);
		entrada1.setIdCliente(idCliente);

		Entrada entrada2 = new Entrada();

		entrada2.setId(id2);
		entrada2.setFechaCompra(fechaCompra);
		entrada2.setIdProyeccion(idProyeccion);
		entrada2.setIdCliente(idCliente);

		assertFalse(entrada1.equals(entrada2));
		assertFalse(entrada2.equals(entrada1));
	}

}
