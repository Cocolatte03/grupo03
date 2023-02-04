package junit.pojos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cine.bbdd.pojos.Cliente;
import cine.bbdd.pojos.Entrada;

/**
 * Este test prueba la clase Cliente
 * 
 * @author alexis
 *
 */

public class ClienteTest {

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Cliente cliente = new Cliente();

		int id = 1;
		cliente.setId(id);

		assertEquals(cliente.getId(), id);
	}

	/*
	 * Probar los metodos setDni() y getDni()
	 */
	@Test

	public void testDni() {
		Cliente cliente = new Cliente();

		String dni = "16066454N";
		cliente.setDni(dni);

		assertEquals(cliente.getDni(), dni);
	}

	/*
	 * Probar los metodos setNombre() y getNombre()
	 */
	@Test

	public void testNombre() {
		Cliente cliente = new Cliente();

		String nombre = "Maria";
		cliente.setNombre(nombre);

		assertEquals(cliente.getNombre(), nombre);
	}

	/*
	 * Probar los metodos setApellidos() y getApellidos()
	 */
	@Test

	public void testApellidos() {
		Cliente cliente = new Cliente();

		String apellidos = "Rojo Prieto";
		cliente.setApellidos(apellidos);

		assertEquals(cliente.getApellidos(), apellidos);
	}

	/*
	 * Probar los metodos setUsuario() y getUsuario()
	 */
	@Test

	public void testUsuario() {
		Cliente cliente = new Cliente();

		String usuario = "maria01";
		cliente.setUsuario(usuario);

		assertEquals(cliente.getUsuario(), usuario);
	}

	/*
	 * Probar los metodos setContrasena() y getContrasena()
	 */
	@Test

	public void testContrasena() {
		Cliente cliente = new Cliente();

		String contrasena = "blanco1996";
		cliente.setContrasena(contrasena);

		assertEquals(cliente.getContrasena(), contrasena);
	}

	/*
	 * Probar los metodos setSexo() y getSexo()
	 */
	@Test

	public void testSexo() {
		Cliente cliente = new Cliente();

		String sexo = "mujer";
		cliente.setSexo(sexo);

		assertEquals(cliente.getSexo(), sexo);
	}

	/*
	 * Probar los metodos setDireccion() y getDireccion()
	 */
	@Test

	public void testDireccion() {
		Cliente cliente = new Cliente();

		String direccion = "Calle Ercilla, 13";
		cliente.setDireccion(direccion);

		assertEquals(cliente.getDireccion(), direccion);
	}

	/*
	 * Probar los metodos setEntradas() y getEntradas()
	 */
	@Test

	public void testEntradas() {
		Cliente cliente = new Cliente();

		ArrayList<Entrada> entradas = null;
		cliente.setEntradas(entradas);

		assertNull(cliente.getEntradas());
	}

	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {

		int id = 1;
		String dni = "16066454N";
		String nombre = "Maria";
		String apellidos = "Rojo Prieto";
		String usuario = "maria01";
		String contrasena = "blanco1996";
		String sexo = "Mujer";
		String direccion = "Calle Ercilla, 13";
		ArrayList<Entrada> entradas = null;

		Cliente cliente = new Cliente();

		cliente.setId(id);
		cliente.setDni(dni);
		cliente.setNombre(nombre);
		cliente.setApellidos(apellidos);
		cliente.setUsuario(usuario);
		cliente.setContrasena(contrasena);
		cliente.setSexo(sexo);
		cliente.setDireccion(direccion);
		cliente.setEntradas(entradas);

		String esperado = "Cliente [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", usuario=" + usuario + ", contrasena=" + contrasena + ", sexo=" + sexo + ", direccion=" + direccion
				+ ", entradas=" + entradas + "]";

		assertEquals(cliente.toString(), esperado);
	}

	/*
	 * Probar el metodo equals() con resultado verdadero
	 */

	@Test
	public void testEqualsTrue() {
		int id = 1;
		String dni = "16066454N";
		String nombre = "Maria";
		String apellidos = "Rojo Prieto";
		String usuario = "maria01";
		String contrasena = "blanco1996";
		String sexo = "Mujer";
		String direccion = "Calle Ercilla, 13";
		ArrayList<Entrada> entradas = null;

		Cliente cliente1 = new Cliente();

		cliente1.setId(id);
		cliente1.setDni(dni);
		cliente1.setNombre(nombre);
		cliente1.setApellidos(apellidos);
		cliente1.setUsuario(usuario);
		cliente1.setContrasena(contrasena);
		cliente1.setSexo(sexo);
		cliente1.setDireccion(direccion);
		cliente1.setEntradas(entradas);

		Cliente cliente2 = new Cliente();

		cliente2.setId(id);
		cliente2.setDni(dni);
		cliente2.setNombre(nombre);
		cliente2.setApellidos(apellidos);
		cliente2.setUsuario(usuario);
		cliente2.setContrasena(contrasena);
		cliente2.setSexo(sexo);
		cliente2.setDireccion(direccion);
		cliente2.setEntradas(entradas);

		assertTrue(cliente1.equals(cliente2));
		assertTrue(cliente2.equals(cliente1));
	}

	/*
	 * Probar el metodo equals() con resultado falso
	 */
	@Test
	public void testEqualsFalse() {

		int id = 1;
		int id2 = 2;
		String dni = "16066454N";
		String nombre = "Maria";
		String apellidos = "Rojo Prieto";
		String usuario = "maria01";
		String contrasena = "blanco1996";
		String sexo = "Mujer";
		String direccion = "Calle Ercilla, 13";
		ArrayList<Entrada> entradas = null;

		Cliente cliente1 = new Cliente();

		cliente1.setId(id);
		cliente1.setDni(dni);
		cliente1.setNombre(nombre);
		cliente1.setApellidos(apellidos);
		cliente1.setUsuario(usuario);
		cliente1.setContrasena(contrasena);
		cliente1.setSexo(sexo);
		cliente1.setDireccion(direccion);
		cliente1.setEntradas(entradas);

		Cliente cliente2 = new Cliente();

		cliente2.setId(id2);
		cliente2.setDni(dni);
		cliente2.setNombre(nombre);
		cliente2.setApellidos(apellidos);
		cliente2.setUsuario(usuario);
		cliente2.setContrasena(contrasena);
		cliente2.setSexo(sexo);
		cliente2.setDireccion(direccion);
		cliente2.setEntradas(entradas);

		assertFalse(cliente1.equals(cliente2));
		assertFalse(cliente2.equals(cliente1));
	}

}
