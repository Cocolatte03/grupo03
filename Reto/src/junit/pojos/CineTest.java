package junit.pojos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Sala;

public class CineTest {
	private static Cine cine = null;

	/*
	 * Constructor vacío: comprueba que las variables se inicializan a nulo en caso
	 * de string o a cero si es numérico
	 */
	@Test
	public void testConstructorVacio() {
		cine = new Cine();
		assertEquals(cine.getId(), 0);
		assertEquals(cine.getNombre(), null);
		assertEquals(cine.getDireccion(), null);
		assertEquals(cine.getTelefono(), null);
		assertEquals(cine.getSalas(), null);
	}

	/*
	 * Constructor: comprueba que las variables se inicializan al valor asignado
	 */
	@Test
	public void testConstructor() {
		int id = 1;
		String nombre = "Cine Elorrieta";
		String direccion = "Gran Vía, Bilbao";
		String telefono = "940071212";
		ArrayList<Sala> salas = null;

		cine = new Cine(id, nombre, direccion, telefono, salas);

		assertEquals(cine.getId(), id);
		assertEquals(cine.getNombre(), nombre);
		assertEquals(cine.getDireccion(), direccion);
		assertEquals(cine.getTelefono(), telefono);
		
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNombre() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNombre() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDireccion() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDireccion() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTelefono() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTelefono() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
