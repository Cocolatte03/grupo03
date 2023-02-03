package junit.pojos;

import static org.junit.Assert.*;

import org.junit.Test;

import cine.bbdd.pojos.Cliente;

public class ClienteTest {

	/*
	 * Probar los metodos setId() y getId()
	 */
	@Test
	public void testId() {
		Cliente cliente = new Cliente();

		int id = 0;
		cliente.setId(id);

		assertEquals(cliente.getId(), id);
	}

	/*
	 * Probar los metodos setDni() y getDni()
	 */
	@Test

	public void testDni() {
		Cliente cliente = new Cliente();

		String dni = null;
		cliente.setDni(dni);

		assertEquals(cliente.getDni(), dni);
	}

	/*
	 * Probar los metodos setNombre() y getNombre()
	 */
	@Test

	public void testNombre() {
		Cliente cliente = new Cliente();

		String nombre = null;
		cliente.setNombre(nombre);

		assertEquals(cliente.getNombre(), nombre);
	}

	/*
	 * Probar los metodos setApellidos() y getApellidos()
	 */
	@Test

	public void testApellidos() {
		Cliente cliente = new Cliente();

		String apellidos = null;
		cliente.setApellidos(apellidos);

		assertEquals(cliente.getApellidos(), apellidos);
	}

	/*
	 * Probar los metodos setUsuario() y getUsuario()
	 */
	@Test

	public void testUsuario() {
		Cliente cliente = new Cliente();

		String usuario = null;
		cliente.setUsuario(usuario);

		assertEquals(cliente.getUsuario(), usuario);
	}

	/*
	 * Probar los metodos setContrasena() y getContrasena()
	 */
	@Test

	public void testContrasena() {
		Cliente cliente = new Cliente();

		String contrasena = null;
		cliente.setContrasena(contrasena);

		assertEquals(cliente.getContrasena(), contrasena);
	}

	/*
	 * Probar los metodos setSexo() y getSexo()
	 */
	@Test

	public void testSexo() {
		Cliente cliente = new Cliente();

		String sexo = null;
		cliente.setSexo(sexo);

		assertEquals(cliente.getSexo(), sexo);
	}

	/*
	 * Probar los metodos setDireccion() y getDireccion()
	 */
	@Test

	public void testDireccion() {
		Cliente cliente = new Cliente();

		String direccion = null;
		cliente.setDireccion(direccion);

		assertEquals(cliente.getDireccion(), direccion);
	}

	/*
	 * Probar el metodo toString()
	 */
	@Test
	public void testToString() {

		int id = 2;
		String dni = "567L";
		String nombre = "Fernando";
		String apellidos = "Gutierrez";
		String usuario = "user";
		String contrasena = "password";
		String sexo = "Hombre";
		String direccion = "Bilbao";

		Cliente cliente = new Cliente();

		cliente.setId(id);
		cliente.setDni(dni);
		cliente.setNombre(nombre);
		cliente.setApellidos(apellidos);
		cliente.setUsuario(usuario);
		cliente.setContrasena(contrasena);
		cliente.setSexo(sexo);
		cliente.setDireccion(direccion);

		String esperado = "Cliente [id=2, dni=567L, nombre=Fernando, apellidos=Gutierrez, usuario=user, contrasena=password, sexo=Hombre, direccion=Bilbao]";

		assertEquals(cliente.toString(), esperado);
	}

	/*
	 * Probar el metodo equals() con resultado verdadero
	 */

	@Test
	public void testEqualsTrue() {
		int id = 2;
		String dni = "567L";
		String nombre = "Fernando";
		String apellidos = "Gutierrez";
		String usuario = "user";
		String contrasena = "password";
		String sexo = "Hombre";
		String direccion = "Bilbao";

		Cliente cliente1 = new Cliente();

		cliente1.setId(id);
		cliente1.setDni(dni);
		cliente1.setNombre(nombre);
		cliente1.setApellidos(apellidos);
		cliente1.setUsuario(usuario);
		cliente1.setContrasena(contrasena);
		cliente1.setSexo(sexo);
		cliente1.setDireccion(direccion);

		Cliente cliente2 = new Cliente();

		cliente1.setId(id);
		cliente1.setDni(dni);
		cliente1.setNombre(nombre);
		cliente1.setApellidos(apellidos);
		cliente1.setUsuario(usuario);
		cliente1.setContrasena(contrasena);
		cliente1.setSexo(sexo);
		cliente1.setDireccion(direccion);

		assertTrue(cliente1.equals(cliente1));
		assertTrue(cliente2.equals(cliente2));
	}

	/*
	 * Probar el metodo equals() con resultado falso
	 */
	@Test
	public void testEqualsFalse() {

		int id = 2;
		int id2 = 3;
		String dni = "567L";
		String nombre = "Fernando";
		String apellidos = "Gutierrez";
		String usuario = "user";
		String contrasena = "password";
		String sexo = "Hombre";
		String direccion = "Bilbao";

		Cliente cliente1 = new Cliente();

		cliente1.setId(id);
		cliente1.setDni(dni);
		cliente1.setNombre(nombre);
		cliente1.setApellidos(apellidos);
		cliente1.setUsuario(usuario);
		cliente1.setContrasena(contrasena);
		cliente1.setSexo(sexo);
		cliente1.setDireccion(direccion);

		Cliente cliente2 = new Cliente();

		cliente1.setId(id2);
		cliente1.setDni(dni);
		cliente1.setNombre(nombre);
		cliente1.setApellidos(apellidos);
		cliente1.setUsuario(usuario);
		cliente1.setContrasena(contrasena);
		cliente1.setSexo(sexo);
		cliente1.setDireccion(direccion);

		assertFalse(cliente1.equals(cliente2));
		assertFalse(cliente2.equals(cliente1));
	}

}
