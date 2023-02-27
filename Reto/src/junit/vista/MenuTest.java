package junit.vista;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import cine.bbdd.gestor.GestorCine;
import cine.bbdd.gestor.GestorCliente;
import cine.bbdd.gestor.GestorPelicula;
import cine.bbdd.gestor.GestorProyeccion;
import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Cliente;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.bbdd.pojos.Sala;
import cine.controlador.Controlador;
import cine.controlador.fichero.GestorDeFicheros;


public class MenuTest {

	private static GestorCine gestorCine = null;
	private static GestorPelicula gestorPelicula = null;
	private static GestorProyeccion gestorProyeccion = null;
	private static GestorCliente gestorCliente = null;
	private static Controlador controlador = null;

	@BeforeClass
	public static void setUpCines() throws AWTException {
		gestorCine = new GestorCine();
		gestorPelicula = new GestorPelicula();
		gestorProyeccion = new GestorProyeccion();
		gestorCliente = new GestorCliente();
		controlador = new Controlador();
	}

	/**
	 * Comprueba que el listado de cines se carga correctamente.
	 */
	@Test
	public void testCines() {

		ArrayList<Cine> cines = null;
		cines = gestorCine.getAllCines();

		assertNotNull(cines);

	}

	/**
	 * Comprueba que un cine existe en el listado.
	 */
	@Test
	public void testCineDisponible() {

		ArrayList<Cine> cines = gestorCine.getAllCines();

		String cineDisponible = "Cine Elorrieta Bilbao";
		boolean estaElCineEnElListado = false;

		for (int i = 0; i < cines.size(); i++) {
			Cine cine = cines.get(i);
			String nombre = cine.getNombre();
			if (nombre.equalsIgnoreCase(cineDisponible)) {
				estaElCineEnElListado = true;
			}
		}

		assertTrue(estaElCineEnElListado);

	}

	/**
	 * Comprueba que un cine no existe en el listado.
	 */
	@Test
	public void testCineNoDisponible() {

		ArrayList<Cine> cines = gestorCine.getAllCines();

		String cineNoDisponible = "Cine Elorrieta Gorliz";
		boolean estaElCineEnElListado = false;

		for (int i = 0; i < cines.size(); i++) {
			Cine cine = cines.get(i);
			String nombre = cine.getNombre();
			if (nombre.equalsIgnoreCase(cineNoDisponible)) {
				estaElCineEnElListado = true;
			}
		}

		assertFalse(estaElCineEnElListado);

	}

	/**
	 * Comprueba que el listado de películas del cine se carga correctamente.
	 */
	@Test
	public void testPeliculasCine() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = gestorPelicula.getPeliculasPorCine(cine);

		assertNotNull(peliculas);

	}

	/**
	 * Comprueba que una película no existe en el listado del cine.
	 */
	@Test
	public void testPeliculaNoDisponibleCine() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = gestorPelicula.getPeliculasPorCine(cine);

		String tituloNoDisponible = "La Cenicienta";
		boolean estaLaPeliculaEnElListado = false;

		for (int i = 0; i < peliculas.size(); i++) {
			Pelicula pelicula = peliculas.get(i);
			String titulo = pelicula.getTitulo();
			if (titulo.equalsIgnoreCase(tituloNoDisponible)) {
				estaLaPeliculaEnElListado = true;
			}
		}

		assertFalse(estaLaPeliculaEnElListado);

	}

	/**
	 * Comprueba que una película existe en el listado del cine.
	 */
	@Test
	public void testPeliculaDisponibleCine() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = gestorPelicula.getPeliculasPorCine(cine);

		String tituloDisponible = "Handia";
		boolean estaLaPeliculaEnElListado = false;

		for (int i = 0; i < peliculas.size(); i++) {
			Pelicula pelicula = peliculas.get(i);
			String titulo = pelicula.getTitulo();
			if (titulo.equalsIgnoreCase(tituloDisponible)) {
				estaLaPeliculaEnElListado = true;
			}
		}

		assertTrue(estaLaPeliculaEnElListado);

	}

	/**
	 * Comprueba que las películas están ordenadas según la fecha de emisión.
	 */
	@Test
	public void testPeliculasOrden() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = null;
		peliculas = gestorPelicula.getPeliculasPorCine(cine);

		boolean estaOrdenado = true;

		for (int i = 1; i < peliculas.size(); i++) {
			Pelicula pelicula = peliculas.get(i);
			ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
					pelicula);
			pelicula.setProyecciones(proyecciones);
			LocalDate fecha = pelicula.getProyecciones().get(0).getFecha();

			Pelicula pelicula2 = peliculas.get(i - 1);
			ArrayList<Proyeccion> proyecciones2 = gestorProyeccion
					.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine, pelicula2);
			pelicula2.setProyecciones(proyecciones2);
			LocalDate fecha2 = pelicula2.getProyecciones().get(0).getFecha();

			if (fecha2.isAfter(fecha)) {
				estaOrdenado = false;
			}
		}

		assertTrue(estaOrdenado);

	}

	/**
	 * Comprueba que el listado de proyecciones se carga correctamente, agrupándolo
	 * por fechas para que no salgan fechas repetidas en caso de que una película
	 * tenga diferentes horas de emisión en un día.
	 */
	@Test
	public void testProyecciones() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		boolean sonFechasDiferentes = true;

		for (int i = 1; i < proyecciones.size(); i++) {
			LocalDate fecha1 = proyecciones.get(i).getFecha();
			LocalDate fecha2 = proyecciones.get(i - 1).getFecha();
			if (fecha1.equals(fecha2)) {
				sonFechasDiferentes = false;
			}
		}

		assertNotNull(proyecciones);
		assertTrue(sonFechasDiferentes);

	}

	/**
	 * Comprueba que el listado de proyecciones se carga correctamente, mostrando
	 * los diferentes horarios de emisión ordenados.
	 */
	@Test
	public void testProyeccionesPorFecha() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones1 = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);
		LocalDate fecha = proyecciones1.get(0).getFecha();

		ArrayList<Proyeccion> proyecciones2 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha.toString());

		boolean estaOrdenado = true;

		for (int i = 1; i < proyecciones2.size(); i++) {
			LocalTime hora1 = proyecciones2.get(i).getHora();
			LocalTime hora2 = proyecciones2.get(i - 1).getHora();

			if (hora2.isAfter(hora1)) {
				estaOrdenado = false;
			}
		}

		assertNotNull(proyecciones2);
		assertTrue(estaOrdenado);

	}

	/**
	 * Comprueba que el listado de proyecciones se carga correctamente, con el
	 * precio correspondiente que puede ser diferente dependiendo del día y hora de
	 * emisión.
	 */
	@Test
	public void testProyeccionesConPrecioDiferentePorFecha() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		LocalDate fecha1 = proyecciones.get(0).getFecha();
		LocalDate fecha2 = proyecciones.get(1).getFecha();

		ArrayList<Proyeccion> proyecciones1 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha1.toString());
		ArrayList<Proyeccion> proyecciones2 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha2.toString());

		Proyeccion proyeccion1 = proyecciones1.get(0);
		Proyeccion proyeccion2 = proyecciones2.get(2);

		String titulo1 = proyeccion1.getPelicula().getTitulo();
		String titulo2 = proyeccion2.getPelicula().getTitulo();

		LocalTime hora1 = proyeccion1.getHora();
		LocalTime hora2 = proyeccion2.getHora();

		double precio1 = proyeccion1.getPrecio();
		double precio2 = proyeccion2.getPrecio();

		assertNotNull(precio1);
		assertNotNull(precio2);
		assertNotEquals(precio1, precio2);
		assertNotEquals(hora1, hora2);
		assertEquals(titulo1, titulo2);

	}

	/**
	 * Comprueba que se asignan correctamente las salas en las proyecciones.
	 */
	@Test
	public void testSalas() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		LocalDate fecha = proyecciones.get(0).getFecha();

		ArrayList<Proyeccion> proyecciones1 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha.toString());

		Sala sala = proyecciones1.get(0).getSala();

		assertNotNull(sala);

	}

	/**
	 * Comprueba el cálculo del subtotal de la compra.
	 */
	@Test
	public void testCalculoSubtotal() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		ArrayList<Proyeccion> proyeccionesSeleccionadas = new ArrayList<Proyeccion>();

		for (int i = 0; i < proyecciones.size(); i++) {
			Proyeccion proyeccion = proyecciones.get(i);
			proyeccionesSeleccionadas.add(proyeccion);
		}

		double subtotalEsperado = 11.9;
		/*
		 * for (int j = 0; j < proyeccionesSeleccionadas.size(); j++) { sumaEsperada +=
		 * proyeccionesSeleccionadas.get(j).getPrecio(); }
		 */

		double subtotal = controlador.calcularSubtotal(proyeccionesSeleccionadas);

		assertTrue(subtotalEsperado == subtotal);

	}

	/**
	 * Comprueba el cálculo del descuento de la compra.
	 */
	@Test
	public void testCalculoDescuento() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		ArrayList<Proyeccion> proyeccionesSeleccionadas = new ArrayList<Proyeccion>();

		for (int i = 0; i < proyecciones.size(); i++) {
			Proyeccion proyeccion = proyecciones.get(i);
			proyeccionesSeleccionadas.add(proyeccion);
		}

		double subtotal = controlador.calcularSubtotal(proyeccionesSeleccionadas);
		double descuento = controlador.calcularDescuento(proyeccionesSeleccionadas, subtotal);

		double descuentoEsperado = subtotal * 0.2 * (-1);

		assertTrue(descuentoEsperado == descuento);

	}

	/**
	 * Comprueba el cálculo del total de la compra, una vez aplicado el descuento.
	 */
	@Test
	public void testCalculoTotal() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		ArrayList<Proyeccion> proyeccionesSeleccionadas = new ArrayList<Proyeccion>();

		for (int i = 0; i < proyecciones.size(); i++) {
			Proyeccion proyeccion = proyecciones.get(i);
			proyeccionesSeleccionadas.add(proyeccion);
		}

		double subtotal = controlador.calcularSubtotal(proyeccionesSeleccionadas);
		double descuento = controlador.calcularDescuento(proyeccionesSeleccionadas, subtotal);
		double total = controlador.calcularTotal(subtotal, descuento);

		double totalEsperado = subtotal + descuento;

		assertTrue(totalEsperado == total);

	}

	/**
	 * Comprueba si un usuario existe y si el login es correcto.
	 */
	@Test
	public void testLogin() {

		ArrayList<Cliente> clientes = gestorCliente.getAllClientes();
		Cliente cliente = controlador.guardarCliente(clientes, "leiretxula");

		boolean esCorrectoLogin = controlador.coincidenUsuarioYContrasena(cliente, "patata");

		assertNotNull(clientes);
		assertNotNull(cliente);
		assertFalse(esCorrectoLogin);
	}

	/**
	 * Comprueba si el archivo que contiene las entradas se ha generado.
	 */
	@Test
	public void testGeneracionTicket() {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		LocalDate fecha = proyecciones.get(0).getFecha();

		ArrayList<Proyeccion> proyecciones1 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha.toString());

		ArrayList<Proyeccion> proyeccionesSeleccionadas = new ArrayList<Proyeccion>();
		Proyeccion proyeccionSeleccionada = proyecciones1.get(0);
		proyeccionesSeleccionadas.add(proyeccionSeleccionada);

		ArrayList<Cliente> clientes = gestorCliente.getAllClientes();
		Cliente cliente = controlador.guardarCliente(clientes, "leiretxula");
		int idCliente = cliente.getId();

		LocalDateTime fechaCompra = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		String fechaFormateada = fechaCompra.format(formatter);

		controlador.imprimirTicket(proyeccionesSeleccionadas, cliente, fechaCompra);

		File file = new File("src/cine/tickets/entradas_" + fechaFormateada + "_" + idCliente + ".txt");

		assertTrue(file.getAbsoluteFile().exists());
	}

	/**
	 * Comprueba si el contenido del archivo que contiene las entradas es correcto.
	 * @throws IOException 
	 */
	@Test
	public void testContenidoTicket() throws IOException {

		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		LocalDate fecha = proyecciones.get(0).getFecha();

		ArrayList<Proyeccion> proyecciones1 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha.toString());

		ArrayList<Proyeccion> proyeccionesSeleccionadas = new ArrayList<Proyeccion>();
		Proyeccion proyeccionSeleccionada = proyecciones1.get(0);
		proyeccionesSeleccionadas.add(proyeccionSeleccionada);

		ArrayList<Cliente> clientes = gestorCliente.getAllClientes();
		Cliente cliente = controlador.guardarCliente(clientes, "leiretxula");
		int idCliente = cliente.getId();

		LocalDateTime fechaCompra = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		String fechaFormateada = fechaCompra.format(formatter);

		controlador.imprimirTicket(proyeccionesSeleccionadas, cliente, fechaCompra);

		String esperado = "";

		String titulo = proyeccionSeleccionada.getPelicula().getTitulo();
		String hora = "" + proyeccionSeleccionada.getHora();
		String fechaSel = "" + proyeccionSeleccionada.getFecha();
		String sala = proyeccionSeleccionada.getSala().getNombre();
		String cineSel = proyeccionSeleccionada.getSala().getCine().getNombre();
		String precio = "" + proyeccionSeleccionada.getPrecio();
		String comprador = "" + cliente.getNombre() + " " + cliente.getApellidos() + "(" + cliente.getDni() + ")";
		
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String fechaFormateada2 = fechaCompra.format(formatter2);

		esperado += "--- ENTRADA 1 ---\n";

		esperado += "Película: " + titulo + "\n" + "Sesión: " + hora + "\n" + "Fecha: " + fechaSel + "\n" + "Sala: "
				+ sala + "\n" + "Cine: " + cineSel + "\n" + "Precio: " + precio + " €\n" + "Fecha compra: "
				+ fechaFormateada2 + "\n" + "Cliente: " + comprador + "\n\n";
		
		File file = new File("src/cine/tickets/entradas_" + fechaFormateada + "_" + idCliente + ".txt");
		
		GestorDeFicheros gestorFicheros = new GestorDeFicheros(file);
		String contenido = gestorFicheros.leer();

		assertEquals(esperado.trim(), contenido.trim());
	}
	
	/**
	 * Comprueba que los parametros se reinician con valores nulos.
	 */
	@Test
	public void testReseteo() {
		ArrayList<Cine> cines = gestorCine.getAllCines();
		Cine cine = cines.get(0);

		ArrayList<Pelicula> peliculas = gestorPelicula.getPeliculasPorCine(cine);
		Pelicula pelicula = peliculas.get(0);

		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
				pelicula);

		LocalDate fecha = proyecciones.get(0).getFecha();

		ArrayList<Proyeccion> proyecciones1 = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cine,
				pelicula, fecha.toString());

		ArrayList<Proyeccion> proyeccionesSeleccionadas = new ArrayList<Proyeccion>();
		Proyeccion proyeccionSeleccionada = proyecciones1.get(0);
		proyeccionesSeleccionadas.add(proyeccionSeleccionada);

		ArrayList<Cliente> clientes = gestorCliente.getAllClientes();
		Cliente clienteLogueado = clientes.get(0);
		
		controlador.reiniciarProyeccionesSeleccionadas(proyeccionesSeleccionadas);
		clienteLogueado = controlador.desloguearCliente();
		
		assertTrue(proyeccionesSeleccionadas.size() == 0);
		assertNull(clienteLogueado);
		
	}

}
