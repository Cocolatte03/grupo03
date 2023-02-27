package junit.pojos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Proyeccion;
import cine.bbdd.pojos.Sala;

/**
 * Este test prueba la clase Sala
 */
public class SalaTest {

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Sala sala = new Sala();

		int id = 1;
		sala.setId(id);

		assertEquals(sala.getId(), id);
	}

	/*
	 * Probar los metodos setNombre() y getNombre()
	 */
	@Test
	public void testNombre() {
		Sala sala = new Sala();

		String nombre = "Sala 1";
		sala.setNombre(nombre);

		assertEquals(sala.getNombre(), nombre);
	}
	
	/*
	 * Probar los metodos setCine() y getCine()
	 */
	@Test
	public void testCine() {
		Sala sala = new Sala();

		Cine cine = null;
		sala.setCine(cine);

		assertNull(sala.getCine());
	}

	/*
	 * Probar los metodos setProyecciones() y getProyecciones()
	 */
	@Test
	public void testProyecciones() {
		Sala sala = new Sala();

		ArrayList<Proyeccion> proyecciones = null;
		sala.setProyecciones(proyecciones);

		assertNull(sala.getProyecciones());
	}
	
	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {
		int id = 1;
		String nombre = "Sala 1";
		Cine cine = null;
		ArrayList<Proyeccion> proyecciones = null;

		Sala sala = new Sala();

		sala.setId(id);
		sala.setNombre(nombre);
		sala.setCine(cine);
		sala.setProyecciones(proyecciones);

		String esperado = "Sala [id=" + id + ", nombre=" + nombre + ", cine=" + cine 
				+ ", proyecciones=" + proyecciones + "]";

		assertEquals(sala.toString(), esperado);
	}
	
	/*
	 * Probar el metodo equals() con resultado verdadero
	 */
	@Test
	public void testEqualsTrue() {
		int id = 1;
		String nombre = "Sala 1";
		Cine cine = null;
		ArrayList<Proyeccion> proyecciones = null;

		Sala sala1 = new Sala();

		sala1.setId(id);
		sala1.setNombre(nombre);
		sala1.setCine(cine);
		sala1.setProyecciones(proyecciones);

		Sala sala2 = new Sala();

		sala2.setId(id);
		sala2.setNombre(nombre);
		sala2.setCine(cine);
		sala2.setProyecciones(proyecciones);

		assertTrue(sala1.equals(sala2));
		assertTrue(sala2.equals(sala1));
	}
	
	/*
	 * Probar el metodo equals() con resultado falso
	 */
	@Test
	public void testEqualsFalse() {
		int id = 1;
		int id2 = 2;
		String nombre = "Sala 1";
		Cine cine = null;
		ArrayList<Proyeccion> proyecciones = null;

		Sala sala1 = new Sala();

		sala1.setId(id);
		sala1.setNombre(nombre);
		sala1.setCine(cine);
		sala1.setProyecciones(proyecciones);

		Sala sala2 = new Sala();

		sala2.setId(id2);
		sala2.setNombre(nombre);
		sala2.setCine(cine);
		sala2.setProyecciones(proyecciones);

		assertFalse(sala1.equals(sala2));
		assertFalse(sala2.equals(sala1));
	}

}