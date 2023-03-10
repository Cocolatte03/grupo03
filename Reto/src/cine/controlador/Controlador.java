package cine.controlador;

import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cine.controlador.fichero.GestorDeFicheros;
import cine.bbdd.gestor.GestorCine;
import cine.bbdd.gestor.GestorCliente;
import cine.bbdd.gestor.GestorEntrada;
import cine.bbdd.gestor.GestorPelicula;
import cine.bbdd.gestor.GestorProyeccion;
import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Cliente;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;

/**
 * Clase de la capa Controlador. Recibe peticiones de la Vista y ejecuta
 * acciones contra la capa Modelo.
 */
public class Controlador {

	public void finalizarSesion(ArrayList<Proyeccion> proyecciones, JPanel panelCine, JPanel panelResumen, JFrame frame,
			DefaultTableModel tableModel, JLabel labelSubtotal, JLabel labelDescuento, JLabel labelTotal,
			double subtotal, double descuento, double total) {

		if (proyecciones.size() == 0) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		} else {
			panelCine.setVisible(false);
			panelResumen.setVisible(true);

			cargarTablaConProyeccionesSeleccionadas(tableModel, proyecciones);

			aplicarTotalesALabels(labelSubtotal, labelDescuento, labelTotal, subtotal, descuento, total);

		}
	}

	public void cargarTablaConProyeccionesSeleccionadas(DefaultTableModel tableModel,
			ArrayList<Proyeccion> proyecciones) {
		tableModel.setRowCount(0);

		for (int i = 0; i < proyecciones.size(); i++) {
			Proyeccion proyeccion = proyecciones.get(i);
			String titulo = proyeccion.getPelicula().getTitulo();
			String fecha = proyeccion.getFecha().toString();
			String hora = proyeccion.getHora().toString();
			String sala = proyeccion.getSala().getNombre();
			String cine = proyeccion.getSala().getCine().getNombre();
			String precio = "" + proyeccion.getPrecio();

			tableModel.addRow(new String[] { titulo, fecha, hora, sala, cine, precio });
		}
	}

	public void aplicarTotalesALabels(JLabel labelSubtotal, JLabel labelDescuento, JLabel labelTotal, double subtotal,
			double descuento, double total) {

		subtotal = Math.round(subtotal * 100.0) / 100.0;
		descuento = Math.round(descuento * 100.0) / 100.0;
		total = Math.round(total * 100.0) / 100.0;

		labelSubtotal.setText(subtotal + " ???");
		labelDescuento.setText(descuento + " ???");
		labelTotal.setText(total + " ???");
	}

	public ArrayList<Cine> guardarArrayListCines() {
		GestorCine gestorCine = new GestorCine();
		return gestorCine.getAllCines();
	}

	public void anadirCinesAlCombo(JComboBox<String> combo, ArrayList<Cine> cines) {
		combo.removeAllItems();
		for (int i = 0; i < cines.size(); i++) {
			combo.addItem(cines.get(i).getNombre());
		}
	}

	public void anadirImagen(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}

	public void cambiarImagen(JComboBox<String> combo, JPanel panel, JLabel label) {
		if (combo.getSelectedItem().toString().equalsIgnoreCase("Cine Elorrieta Bilbao")) {
			anadirImagen(panel, label, "img/cBilbao.png");
		} else if (combo.getSelectedItem().toString().equalsIgnoreCase("Cine Elorrieta Barakaldo")) {
			anadirImagen(panel, label, "img/cBarakaldo.png");
		} else if (combo.getSelectedItem().toString().equalsIgnoreCase("Cine Elorrieta Mungia")) {
			anadirImagen(panel, label, "img/cMungia.png");
		} else if (combo.getSelectedItem().toString().equalsIgnoreCase("Cine Elorrieta Durango")) {
			anadirImagen(panel, label, "img/cDurango.png");
		}
	}

	public Cine determinarCineSeleccionado(JComboBox<String> combo, ArrayList<Cine> cines) {
		Cine ret = null;

		int index = combo.getSelectedIndex();

		ret = cines.get(index);

		return ret;

	}

	public ArrayList<Pelicula> guardarArrayListPeliculas(Cine cine) {
		GestorPelicula gestorPelicula = new GestorPelicula();
		GestorProyeccion gestorProyeccion = new GestorProyeccion();

		ArrayList<Pelicula> ret = gestorPelicula.getPeliculasPorCine(cine);
		for (int i = 0; i < ret.size(); i++) {
			ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
					ret.get(i));
			ret.get(i).setProyecciones(proyecciones);
		}

		return ret;
	}

	public void cargarTablaConPeliculas(DefaultTableModel tableModel, ArrayList<Pelicula> peliculas) {
		tableModel.setRowCount(0);

		if (null != peliculas) {
			for (int i = 0; i < peliculas.size(); i++) {
				Pelicula pelicula = peliculas.get(i);

				String titulo = pelicula.getTitulo();
				String genero = pelicula.getGenero();
				String duracion = "" + pelicula.getDuracion();

				tableModel.addRow(new String[] { titulo, genero, duracion });
			}
		}

	}

	public Pelicula guardarPeliSeleccionada(JTable table, ArrayList<Pelicula> peliculas) {
		Pelicula ret = null;

		int index = table.getSelectedRow();

		ret = peliculas.get(index);

		return ret;
	}

	public ArrayList<Proyeccion> guardarArrayListProyeccionesAgrupadas(Cine cineSeleccionado,
			Pelicula peliSeleccionada) {
		ArrayList<Proyeccion> ret = null;

		GestorProyeccion gestorProyeccion = new GestorProyeccion();

		ret = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cineSeleccionado, peliSeleccionada);

		return ret;
	}

	public void anadirFechasAlCombo(JComboBox<String> combo, ArrayList<Proyeccion> proyecciones) {

		combo.removeAllItems();

		if (null != proyecciones) {
			for (int i = 0; i < proyecciones.size(); i++) {
				combo.addItem(proyecciones.get(i).getFecha().toString());
			}
		}
	}

	public ArrayList<Proyeccion> guardarArrayListProyecciones(JComboBox<String> comboFecha, Cine cineSeleccionado,
			Pelicula peliSeleccionada) {
		ArrayList<Proyeccion> ret = null;
		String fecha = comboFecha.getSelectedItem().toString();

		GestorProyeccion gestorProyeccion = new GestorProyeccion();

		ret = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cineSeleccionado, peliSeleccionada, fecha);

		return ret;

	}

	public void cargarTablaConSesiones(DefaultTableModel tableModel, ArrayList<Proyeccion> proyecciones,
			Pelicula pelicula) {
		tableModel.setRowCount(0);

		String titulo = pelicula.getTitulo();

		for (int i = 0; i < proyecciones.size(); i++) {
			Proyeccion proyeccion = proyecciones.get(i);

			String hora = proyeccion.getHora().toString();
			String sala = proyeccion.getSala().getNombre();
			String precio = "" + proyeccion.getPrecio();

			tableModel.addRow(new String[] { titulo, hora, sala, precio });
		}
	}

	public Proyeccion guardarProyeccionSeleccionada(JTable table, ArrayList<Proyeccion> proyecciones) {
		Proyeccion ret = null;

		int index = table.getSelectedRow();

		ret = proyecciones.get(index);

		return ret;
	}

	public void guardarProyeccionConfirmada(Proyeccion proyeccion, ArrayList<Proyeccion> proyeccionesSeleccionadas) {
		proyeccionesSeleccionadas.add(proyeccion);
	}

	public void asignarValoresALabelsSeleccion(Proyeccion proyeccion, JLabel lblCine, JLabel lblSala,
			JLabel lblPelicula, JLabel lblFecha, JLabel lblHora, JLabel lblPrecio) {

		String cine = proyeccion.getSala().getCine().getNombre();
		lblCine.setText(cine);

		String fecha = proyeccion.getFecha().toString();
		lblFecha.setText(fecha);

		String precio = "" + proyeccion.getPrecio();
		lblPrecio.setText(precio);

		String sala = proyeccion.getSala().getNombre();
		lblSala.setText(sala);

		String hora = proyeccion.getHora().toString();
		lblHora.setText(hora);

		String pelicula = proyeccion.getPelicula().getTitulo();
		lblPelicula.setText(pelicula);

	}

	public double calcularSubtotal(ArrayList<Proyeccion> proyecciones) {
		double ret = 0;

		int numeroEntradas = proyecciones.size();

		for (int i = 0; i < numeroEntradas; i++) {
			double precioEntrada = proyecciones.get(i).getPrecio();
			ret += precioEntrada;
		}

		return ret;
	}

	public double calcularDescuento(ArrayList<Proyeccion> proyecciones, double subtotal) {
		double ret = 0;

		int numeroProyecciones = proyecciones.size();

		if (numeroProyecciones == 1) {
			ret = 0;
		} else if (numeroProyecciones == 2) {
			ret = 0.2 * subtotal * (-1);
		} else if (numeroProyecciones == 3) {
			ret = 0.3 * subtotal * (-1);
		} else if (numeroProyecciones == 4) {
			ret = 0.4 * subtotal * (-1);
		} else if (numeroProyecciones >= 5) {
			ret = 0.5 * subtotal * (-1);
		}

		return ret;
	}

	public double calcularTotal(double subtotal, double descuento) {
		double ret = 0;

		ret = subtotal + descuento;

		return ret;
	}

	public int preguntarEliminarProyeccionSel() {
		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = "Cancelar";
		options[1] = "Confirmar";

		String titulo = "Eliminar Selecci??n";

		String msg = "??Desea eliminar la sesi??n?";

		int ret = JOptionPane.showOptionDialog(frame.getContentPane(), msg, titulo, 0, JOptionPane.INFORMATION_MESSAGE,
				null, options, null);

		return ret;
	}

	public void eliminarProyeccionSel(ArrayList<Proyeccion> proyeccionesSel, JTable table, DefaultTableModel tableModel,
			JLabel labelSubtotal, JLabel labelDescuento, JLabel labelTotal) {
		int respuesta = preguntarEliminarProyeccionSel();
		int index = table.getSelectedRow();
		if (respuesta == 1) {
			proyeccionesSel.remove(index);
			cargarTablaConProyeccionesSeleccionadas(tableModel, proyeccionesSel);

			double subtotal = calcularSubtotal(proyeccionesSel);
			double descuento = calcularDescuento(proyeccionesSel, subtotal);
			double total = calcularTotal(subtotal, descuento);

			aplicarTotalesALabels(labelSubtotal, labelDescuento, labelTotal, subtotal, descuento, total);
		}
	}

	public int preguntarCancelarCompra() {
		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = "Cancelar";
		options[1] = "Confirmar";

		String titulo = "Cancelar compra";

		String msg = "??Desea  cancelar la compra de las entradas?";

		int ret = JOptionPane.showOptionDialog(frame.getContentPane(), msg, titulo, 0, JOptionPane.INFORMATION_MESSAGE,
				null, options, null);

		return ret;
	}

	public void cancelarCompra(ArrayList<Proyeccion> proyecciones, JPanel bPanel, JPanel rcPanel) {
		int respuesta = preguntarCancelarCompra();
		if (respuesta == 1) {
			proyecciones.removeAll(proyecciones);
			rcPanel.setVisible(false);
			bPanel.setVisible(true);
		}
	}

	public ArrayList<Cliente> guardarArrayListClientes() {
		ArrayList<Cliente> ret = null;
		GestorCliente gestorCliente = new GestorCliente();

		ret = gestorCliente.getAllClientes();
		return ret;
	}

	public Cliente guardarCliente(ArrayList<Cliente> clientes, String usuario) {
		Cliente ret = null;

		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			String nomUsuario = cliente.getUsuario();
			if (usuario.equals(nomUsuario)) {
				ret = cliente;
			}
		}

		return ret;
	}

	public boolean coincidenUsuarioYContrasena(Cliente cliente, String contrasena) {
		boolean ret = false;

		String contrasenaBbdd = cliente.getContrasena();

		if (contrasena.equals(contrasenaBbdd)) {
			ret = true;
		}

		return ret;
	}

	public void reiniciarCamposInicio(JTextField txtUsuario, JPasswordField txtContrasena) {
		txtUsuario.setText("");
		txtContrasena.setText("");
	}

	public void iniciarSesion(Cliente cliente, String contrasena, JPanel lPanel, JPanel rcPanel, JTextField txtUsuario,
			JPasswordField txtContrasena) {
		if (cliente != null) {
			if (coincidenUsuarioYContrasena(cliente, contrasena)) {
				String nombre = cliente.getNombre();
				JOptionPane.showMessageDialog(null, ("??Bienvenid@ " + nombre + "!"));
				reiniciarCamposInicio(txtUsuario, txtContrasena);
				lPanel.setVisible(false);
				rcPanel.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Contrase??a incorrecta.", "Error en el inicio de sesi??n",
						JOptionPane.ERROR_MESSAGE);
				reiniciarCamposInicio(txtUsuario, txtContrasena);
			}
		} else {
			JOptionPane.showMessageDialog(null, "No existe el usuario.", "Error en el inicio de sesi??n",
					JOptionPane.ERROR_MESSAGE);
			reiniciarCamposInicio(txtUsuario, txtContrasena);
		}
	}

	public void crearEntradas(ArrayList<Proyeccion> proyecciones, Cliente cliente) {
		GestorEntrada gestorEntrada = new GestorEntrada();

		for (int i = 0; i < proyecciones.size(); i++) {
			Proyeccion proyeccion = proyecciones.get(i);
			gestorEntrada.insertEntrada(proyeccion, cliente);
		}
	}

	public String generarContenidoTicket(Proyeccion proyeccion, Cliente cliente, LocalDateTime fechaComp) {
		String ret = "";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String fechaFormateada = fechaComp.format(formatter);

		String titulo = proyeccion.getPelicula().getTitulo();
		String hora = "" + proyeccion.getHora();
		String fecha = "" + proyeccion.getFecha();
		String sala = proyeccion.getSala().getNombre();
		String cine = proyeccion.getSala().getCine().getNombre();
		String precio = "" + proyeccion.getPrecio();
		String comprador = "" + cliente.getNombre() + " " + cliente.getApellidos() + "(" + cliente.getDni() + ")";

		ret += "Pel??cula: " + titulo + "\n" + "Sesi??n: " + hora + "\n" + "Fecha: " + fecha + "\n" + "Sala: " + sala
				+ "\n" + "Cine: " + cine + "\n" + "Precio: " + precio + " ???\n" + "Fecha compra: " + fechaFormateada
				+ "\n" + "Cliente: " + comprador + "\n\n";

		return ret;
	}

	public void imprimirTicket(ArrayList<Proyeccion> proyecciones, Cliente cliente, LocalDateTime fechaComp) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		String fechaFormateada = fechaComp.format(formatter);

		File fichero = new File("src/cine/tickets/entradas_" + fechaFormateada + "_" + cliente.getId() + ".txt");
		GestorDeFicheros gestor = new GestorDeFicheros(fichero);

		String texto = "";

		for (int i = 0; i < proyecciones.size(); i++) {
			Proyeccion proyeccion = proyecciones.get(i);
			texto += "--- ENTRADA " + (i + 1) + " ---\n";
			texto += generarContenidoTicket(proyeccion, cliente, fechaComp);
		}

		try {
			gestor.escribir(texto);
		} catch (IOException e1) {

		}
	}

	public void reiniciarProyeccionesSeleccionadas(ArrayList<Proyeccion> proyeccionesSeleccionadas) {
		proyeccionesSeleccionadas.removeAll(proyeccionesSeleccionadas);
	}

	public Cliente desloguearCliente() {
		Cliente ret = null;

		return ret;
	}

	public void registrarCliente(Cliente cliente) {
		GestorCliente gestorCliente = new GestorCliente();
		gestorCliente.insertCliente(cliente);
	}

	public boolean estanVaciosLosCampos(JTextField nombre, JTextField apellidos, JTextField dni, JTextField direccion,
			JTextField usuario, JPasswordField passw1, JPasswordField passw2) {
		boolean ret = false;
		if ((nombre.getText().isBlank()) || (apellidos.getText().isBlank()) || (dni.getText().isBlank())
				|| (direccion.getText().isBlank()) || (usuario.getText().isEmpty())
				|| (passw1.getPassword().length == 0) || (passw2.getPassword().length == 0)) {
			ret = true;
		}

		return ret;
	}

	public boolean coincidenLasContrasenas(JPasswordField passw1, JPasswordField passw2) {
		boolean ret = false;

		String contrasena1 = String.valueOf(passw1.getPassword());
		String contrasena2 = String.valueOf(passw2.getPassword());

		if (contrasena1.equals(contrasena2)) {
			ret = true;
		}

		return ret;
	}

	public boolean esCorrectoElFormatoDni(JTextField dni) {
		boolean ret = false;

		int size = dni.getText().trim().length();

		Pattern pattern = Pattern.compile("^(\\d{8}[A-Z])$");
		Matcher matcher = pattern.matcher(dni.getText());
		if (matcher.matches() && (size == 9)) {
			ret = true;
		}

		return ret;
	}

	public boolean tieneElClienteCuenta(ArrayList<Cliente> clientes, JTextField dni) {
		boolean ret = false;

		for (int i = 0; i < clientes.size(); i++) {
			if (dni.getText().equals(clientes.get(i).getDni())) {
				ret = true;
			}
		}

		return ret;
	}

	public boolean existeNombreUsuario(ArrayList<Cliente> clientes, JTextField usuario) {
		boolean ret = false;

		for (int i = 0; i < clientes.size(); i++) {
			if (usuario.getText().equals(clientes.get(i).getUsuario())) {
				ret = true;
			}
		}

		return ret;
	}

	public boolean esCorrectoElFormatoNombreYApellidos(JTextField nombre, JTextField apellidos) {
		boolean ret = false;

		Pattern pattern = Pattern.compile("^([A-Za-z????????????????????????\\-\\s]*)$");
		Matcher matcher1 = pattern.matcher(nombre.getText());
		Matcher matcher2 = pattern.matcher(apellidos.getText());
		if (matcher1.matches() && matcher2.matches()) {
			ret = true;
		}

		return ret;
	}

	public boolean esCorrectoRegistro(ArrayList<Cliente> clientes, JTextField nombre, JTextField apellidos,
			JTextField dni, JTextField direccion, JTextField usuario, JPasswordField passw1, JPasswordField passw2) {
		boolean ret = true;

		if (estanVaciosLosCampos(nombre, apellidos, dni, direccion, usuario, passw1, passw2)) {
			ret = false;
		} else if (!(coincidenLasContrasenas(passw1, passw2))) {
			ret = false;
		} else if (!(esCorrectoElFormatoDni(dni))) {
			ret = false;
		} else if (tieneElClienteCuenta(clientes, dni)) {
			ret = false;
		} else if (existeNombreUsuario(clientes, usuario)) {
			ret = false;
		} else if (!(esCorrectoElFormatoNombreYApellidos(nombre, apellidos))) {
			ret = false;
		}

		return ret;
	}

	public Cliente nuevoCliente(JComboBox<String> comboSexo, JTextField nombre, JTextField apellidos, JTextField dni,
			JTextField direccion, JTextField usuario, JPasswordField passw1) {

		Cliente ret = new Cliente();
		ret.setDni(dni.getText());
		ret.setNombre(nombre.getText());
		ret.setApellidos(apellidos.getText());
		ret.setUsuario(usuario.getText());
		ret.setContrasena(String.valueOf(passw1.getPassword()));
		String sexo = comboSexo.getSelectedItem().toString();
		ret.setSexo(sexo);
		ret.setDireccion(direccion.getText());

		return ret;

	}

	public void registrarNuevoCliente(ArrayList<Cliente> clientes, JComboBox<String> comboSexo, JTextField nombre,
			JTextField apellidos, JTextField dni, JTextField direccion, JTextField usuario, JPasswordField passw1,
			JPasswordField passw2) {
		if (!(esCorrectoRegistro(clientes, nombre, apellidos, dni, direccion, usuario, passw1, passw2))) {
			JOptionPane.showMessageDialog(null, "Registro no v??lido, revise los campos.", "Error en el registro",
					JOptionPane.ERROR_MESSAGE);
		} else {
			Cliente cliente = nuevoCliente(comboSexo, nombre, apellidos, dni, direccion, usuario, passw1);

			registrarCliente(cliente);

			JOptionPane.showMessageDialog(null, ("??Gracias por su registro, " + nombre.getText() + "!"));

			reiniciarCampos(comboSexo, nombre, apellidos, dni, direccion, usuario, passw1, passw2);
		}
	}

	public void reiniciarCampos(JComboBox<String> comboSexo, JTextField nombre, JTextField apellidos, JTextField dni,
			JTextField direccion, JTextField usuario, JPasswordField passw1, JPasswordField passw2) {
		comboSexo.setSelectedIndex(0);
		nombre.setText("");
		apellidos.setText("");
		dni.setText("");
		direccion.setText("");
		usuario.setText("");
		passw1.setText("");
		passw2.setText("");
	}

}