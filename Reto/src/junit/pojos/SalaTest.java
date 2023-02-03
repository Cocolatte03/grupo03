package junit.pojos;

import static org.junit.Assert.*;
import org.junit.Test;
import cine.bbdd.pojos.Sala;

/**
 * Esta test prueba la clase Sala.
 * @author vaain
 *
 */
public class SalaTest {

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Sala sala = new Sala();
		
		int id = 0;
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
	 * Probar los metodos setIdCine() y getIdCine()
	 */
	@Test
	public void testDireccion() {
		Sala sala = new Sala();
		
		int idCine = 1;
		sala.setIdCine(idCine);
		
		assertEquals(sala.getIdCine(), idCine);
	}
	
	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {
		int id = 1;
		String nombre = "Sala 1";
		int idCine = 1;
		
		Sala sala = new Sala();
		
		sala.setId(id);
		sala.setNombre(nombre);
		sala.setIdCine(idCine);

		String esperado = "Sala [id=" + id + ", nombre=" + nombre + ", idCine=" + idCine + "]";

		assertEquals(sala.toString(), esperado);
	}
	
	/*
	 * Probar el metodo equals() con resultado verdadero
	 */
	@Test
	public void testEqualsTrue() {
		int id = 1;
		String nombre = "Sala 1";
		int idCine = 1;

		Sala sala1 = new Sala();
		
		sala1.setId(id);
		sala1.setNombre(nombre);
		sala1.setIdCine(idCine);
		
		Sala sala2 = new Sala();
		
		sala2.setId(id);
		sala2.setNombre(nombre);
		sala2.setIdCine(idCine);

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
		int idCine = 1;

		Sala sala1 = new Sala();
		
		sala1.setId(id);
		sala1.setNombre(nombre);
		sala1.setIdCine(idCine);
		
		Sala sala2 = new Sala();
		
		sala2.setId(id2);
		sala2.setNombre(nombre);
		sala2.setIdCine(idCine);

		assertFalse(sala1.equals(sala2));
		assertFalse(sala2.equals(sala1));
	}

}