package junit.pojos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

import cine.bbdd.pojos.Cliente;
import cine.bbdd.pojos.Entrada;
import cine.bbdd.pojos.Proyeccion;

/**
 * Este test prueba la clase Entrada
 */
public class EntradaTest {

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Entrada entrada = new Entrada();

		int id = 1;
		entrada.setId(id);

		assertEquals(entrada.getId(), id);
	}

	/*
	 * Probar los metodos setFechaCompra() y getFechaCompra()
	 */
	@Test
	public void testFechaCompra() {
		Entrada entrada = new Entrada();

		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();

		LocalDateTime fechaCompra = LocalDateTime.of(hoy, ahora);
		entrada.setFechaCompra(fechaCompra);

		assertEquals(entrada.getFechaCompra(), fechaCompra);
	}

	/*
	 * Probar los metodos setProyeccion() y getProyeccion()
	 */
	@Test
	public void testProyeccion() {
		Entrada entrada = new Entrada();

		Proyeccion proyeccion = null;
		entrada.setProyeccion(proyeccion);

		assertNull(entrada.getProyeccion());
	}

	/*
	 * Probar los metodos setCliente() y getCliente()
	 */
	@Test
	public void testCliente() {
		Entrada entrada = new Entrada();

		Cliente cliente = null;
		entrada.setCliente(cliente);

		assertNull(entrada.getCliente());
	}

	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {
		int id = 1;
		LocalDateTime fechaCompra = LocalDateTime.now();
		Proyeccion proyeccion = null;
		Cliente cliente = null;

		Entrada entrada = new Entrada();

		entrada.setId(id);
		entrada.setFechaCompra(fechaCompra);
		entrada.setProyeccion(proyeccion);
		entrada.setCliente(cliente);

		String esperado = "Entrada [id=" + id + ", fechaCompra=" + fechaCompra + ", proyeccion=" + proyeccion
				+ ", cliente=" + cliente + "]";

		assertEquals(entrada.toString(), esperado);

	}

	/*
	 * Probar el metodo equals() con resultado verdadero
	 */

	@Test
	public void testEqualsTrue() {
		int id = 1;
		LocalDateTime fechaCompra = LocalDateTime.now();
		Proyeccion proyeccion = null;
		Cliente cliente = null;

		Entrada entrada1 = new Entrada();

		entrada1.setId(id);
		entrada1.setFechaCompra(fechaCompra);
		entrada1.setProyeccion(proyeccion);
		entrada1.setCliente(cliente);

		Entrada entrada2 = new Entrada();

		entrada2.setId(id);
		entrada2.setFechaCompra(fechaCompra);
		entrada2.setProyeccion(proyeccion);
		entrada2.setCliente(cliente);

		assertTrue(entrada1.equals(entrada2));
		assertTrue(entrada2.equals(entrada1));
	}

	/*
	 * Probar el metodo equals() con resultado falso
	 */
	@Test
	public void testEqualsFalse() {
		int id = 1;
		int id2 = 2;
		LocalDateTime fechaCompra = LocalDateTime.now();
		Proyeccion proyeccion = null;
		Cliente cliente = null;

		Entrada entrada1 = new Entrada();

		entrada1.setId(id);
		entrada1.setFechaCompra(fechaCompra);
		entrada1.setProyeccion(proyeccion);
		entrada1.setCliente(cliente);

		Entrada entrada2 = new Entrada();

		entrada2.setId(id2);
		entrada2.setFechaCompra(fechaCompra);
		entrada2.setProyeccion(proyeccion);
		entrada2.setCliente(cliente);

		assertFalse(entrada1.equals(entrada2));
		assertFalse(entrada2.equals(entrada1));
	}

}