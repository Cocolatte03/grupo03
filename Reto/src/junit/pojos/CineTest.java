package junit.pojos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Sala;

/**
 * Este test prueba la clase Cine
 * 
 * @author ainhoa
 *
 */
public class CineTest {

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Cine cine = new Cine();

		int id = 1;
		cine.setId(id);

		assertEquals(cine.getId(), id);
	}

	/*
	 * Probar los metodos setNombre() y getNombre()
	 */
	@Test
	public void testNombre() {
		Cine cine = new Cine();

		String nombre = "Elorrieta Bilbao";
		cine.setNombre(nombre);

		assertEquals(cine.getNombre(), nombre);
	}

	/*
	 * Probar los metodos setDireccion() y getDireccion()
	 */
	@Test
	public void testDireccion() {
		Cine cine = new Cine();

		String direccion = "Av. Lehendakari Agirre, 184";
		cine.setDireccion(direccion);

		assertEquals(cine.getDireccion(), direccion);
	}

	/*
	 * Probar los metodos setSalas() y getSalas()
	 */
	@Test
	public void testSalas() {
		Cine cine = new Cine();

		ArrayList<Sala> salas = null;
		cine.setSalas(salas);

		assertNull(cine.getSalas());
	}

	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {
		int id = 1;
		String nombre = "Elorrieta Bilbao";
		String direccion = "Av. Lehendakari Agirre, 184";
		ArrayList<Sala> salas = null;

		Cine cine = new Cine();

		cine.setId(id);
		cine.setNombre(nombre);
		cine.setDireccion(direccion);
		cine.setSalas(salas);

		String esperado = "Cine [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", salas=" + salas
				+ "]";

		assertEquals(cine.toString(), esperado);
	}

	/*
	 * Probar el metodo equals() con resultado verdadero
	 */
	@Test
	public void testEqualsTrue() {
		int id = 1;
		String nombre = "Elorrieta Bilbao";
		String direccion = "Av. Lehendakari Agirre, 184";
		ArrayList<Sala> salas = null;

		Cine cine1 = new Cine();

		cine1.setId(id);
		cine1.setNombre(nombre);
		cine1.setDireccion(direccion);
		cine1.setSalas(salas);

		Cine cine2 = new Cine();

		cine2.setId(id);
		cine2.setNombre(nombre);
		cine2.setDireccion(direccion);
		cine2.setSalas(salas);

		assertTrue(cine1.equals(cine2));
		assertTrue(cine2.equals(cine1));
	}

	/*
	 * Probar el metodo equals() con resultado falso
	 */
	@Test
	public void testEqualsFalse() {
		int id = 1;
		int id2 = 2;
		String nombre = "Elorrieta Bilbao";
		String direccion = "Av. Lehendakari Agirre, 184";
		ArrayList<Sala> salas = null;

		Cine cine1 = new Cine();

		cine1.setId(id);
		cine1.setNombre(nombre);
		cine1.setDireccion(direccion);
		cine1.setSalas(salas);

		Cine cine2 = new Cine();

		cine2.setId(id2);
		cine2.setNombre(nombre);
		cine2.setDireccion(direccion);
		cine2.setSalas(salas);

		assertFalse(cine1.equals(cine2));
		assertFalse(cine2.equals(cine1));
	}

}