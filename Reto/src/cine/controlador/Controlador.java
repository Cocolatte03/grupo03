package cine.controlador;

import java.awt.Image;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cine.bbdd.gestor.GestorCine;
import cine.bbdd.gestor.GestorPelicula;
import cine.bbdd.gestor.GestorProyeccion;
import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;

/**
 * Esta clase lleva a cabo una relacion entre la clases del apartado de la vista
 * y el gestor. Gestiona la lógica.
 * 
 * @author leire
 *
 */
public class Controlador {

	private GestorCine gestorCine = null;
	private GestorPelicula gestorPelicula = null;
	private GestorProyeccion gestorProyeccion = null;

	public Controlador() {
		gestorCine = new GestorCine();
		gestorPelicula = new GestorPelicula();
		gestorProyeccion = new GestorProyeccion();
	}

	// BIENVENIDA:

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

		labelSubtotal.setText(subtotal + " €");
		labelDescuento.setText(descuento + " €");
		labelTotal.setText(total + " €");
	}

	// SELECCION CINE:

	/**
	 * Realiza una consulta a la BBDD para un ArrayList con todos los cines.
	 * 
	 * @return ArrayList de cines
	 */
	public ArrayList<Cine> guardarArrayListCines() {
		ArrayList<Cine> ret = gestorCine.getAllCines();
		return ret;
	}

	/**
	 * Añade el nombre de los cines a un JComboBox.
	 * 
	 * @param combo JComboBox al que se añaden los cines
	 * @param cines ArrayList que contiene los cines
	 */
	public void anadirCinesAlCombo(JComboBox<String> combo, ArrayList<Cine> cines) {

		combo.removeAllItems();

		for (int i = 0; i < cines.size(); i++) {
			combo.addItem(cines.get(i).getNombre());
		}
	}

	/**
	 * Añade una imagen a un JLabel que se encuentra dentro de un JPanel.
	 * 
	 * @param panel JPanel en el que se encuentra la JLabel
	 * @param label JLabel en el que se añade la imagen
	 * @param path  ruta de la imagen
	 */
	public void anadirImagen(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}

	/**
	 * Cambia la imagen de la localización del cine, dependiendo de la opcion
	 * seleccionada en el JComboBox.
	 * 
	 * @param combo JComboBox que contiene el nombre de los cines
	 * @param panel JPanel en el que se encuentra el JLabel
	 * @param label JLabel en el que se añade la imagen
	 */
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
		String nombreCine = combo.getSelectedItem().toString();
		for (int i = 0; i < cines.size(); i++) {
			Cine cine = cines.get(i);
			String nombre = cine.getNombre();
			if (nombreCine.equalsIgnoreCase(nombre)) {
				ret = cine;
			}
		}

		return ret;

	}

	// SELECCION PELICULA:

	public ArrayList<Pelicula> guardarArrayListPeliculas(Cine cine) {
		ArrayList<Pelicula> ret = gestorPelicula.getPeliculasPorCine(cine);
		for (int i = 0; i < ret.size(); i++) {
			ArrayList<Proyeccion> proyecciones = gestorProyeccion.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cine,
					ret.get(i));
			ret.get(i).setProyecciones(proyecciones);
		}

		return ret;
	}

	public void anadirPeliculasAlCombo(JComboBox<String> combo, ArrayList<Pelicula> peliculas) {
		combo.removeAllItems();

		if (null != peliculas) {
			for (int i = 0; i < peliculas.size(); i++) {
				combo.addItem(peliculas.get(i).getTitulo());
			}
		}
	}

	public void anadirInformacionPeli(JLabel label1, JLabel label2, JLabel label3, ArrayList<Pelicula> peliculas,
			int i) {

		String titulo = peliculas.get(i).getTitulo();
		String genero = peliculas.get(i).getGenero();
		int duracion = peliculas.get(i).getDuracion();

		label1.setText(titulo);
		label2.setText(genero);
		label3.setText(duracion + " min");
	}

	public void cambiarInformacionPorSeleccion(ArrayList<Pelicula> peliculas, JComboBox<String> combo, JLabel label1,
			JLabel label2, JLabel label3) {
		for (int i = 0; i < peliculas.size(); i++) {
			if (combo.getSelectedItem().toString().equalsIgnoreCase(peliculas.get(i).getTitulo())) {
				anadirInformacionPeli(label1, label2, label3, peliculas, i);
			}
		}
	}

	private String obtenerDireccionCaratula(ArrayList<Pelicula> peliculas, String titulo) {
		String ret = null;

		for (int i = 0; i < peliculas.size(); i++) {
			if (peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
				ret = peliculas.get(i).getCaratula();
			}
		}

		return ret;
	}

	public void cambiarCaratulaPorSeleccion(ArrayList<Pelicula> peliculas, JComboBox<String> combo, JPanel panel,
			JLabel label) {
		anadirImagen(panel, label, obtenerDireccionCaratula(peliculas, combo.getSelectedItem().toString()));
	}

	public Pelicula determinarPeliSeleccionada(JComboBox<String> combo, ArrayList<Pelicula> peliculas) {
		Pelicula ret = null;
		String nombrePeli = combo.getSelectedItem().toString();
		for (int i = 0; i < peliculas.size(); i++) {
			Pelicula pelicula = peliculas.get(i);
			String titulo = pelicula.getTitulo();
			if (nombrePeli.equalsIgnoreCase(titulo)) {
				ret = pelicula;
			}
		}

		return ret;

	}

	// SELECCION PROYECCION:

	public void anadirFechasAlCombo(JComboBox<String> combo, Cine cineSeleccionado, Pelicula peliSeleccionada) {

		ArrayList<Proyeccion> proyecciones = gestorProyeccion
				.getProyeccionesPorCineYPeliculaAgrupadasPorFecha(cineSeleccionado, peliSeleccionada);

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

		ret = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cineSeleccionado, peliSeleccionada, fecha);

		return ret;

	}

	public void cargarTablaConSesiones(DefaultTableModel tableModel, ArrayList<Proyeccion> proyecciones) {
		tableModel.setRowCount(0);

		for (int i = 0; i < proyecciones.size(); i++) {
			Proyeccion proyeccion = proyecciones.get(i);

			String hora = proyeccion.getHora().toString();
			String sala = proyeccion.getSala().getNombre();
			String precio = "" + proyeccion.getPrecio();

			tableModel.addRow(new String[] { hora, sala, precio });
		}
	}
	
	public boolean seHaSeleccionadoProyeccion(JTable table) {
		boolean ret = false;
		
		int index = table.getSelectedRow();
		
		if (index == -1) {
			JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna sesión",
				      "Selección de la sesión", JOptionPane.ERROR_MESSAGE);
			
		} else {
			ret = true;
		}
		
		return ret;
	}

	public Proyeccion guardarProyeccionSeleccionada(JTable table, ArrayList<Proyeccion> proyecciones) {
		Proyeccion ret = null;

		int index = table.getSelectedRow();
		
		ret = proyecciones.get(index);

		return ret;
	}

	public int preguntarConfirmacionProyeccion(Proyeccion proyeccion) {
		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = "Cancelar";
		options[1] = "Confirmar";

		String titulo = "Confirmar Selección";

		String msg = "<html>Datos de la selección: <br><br>" + "<b>Película: </b>"
				+ proyeccion.getPelicula().getTitulo() + "<br>" + "<b>Fecha: </b>" + proyeccion.getFecha().toString()
				+ "<br>" + "<b>Hora: </b>" + proyeccion.getHora().toString() + "<br>" + "<b>Sala: </b>"
				+ proyeccion.getSala().getNombre() + "<br>" + "<b>Cine: </b>"
				+ proyeccion.getSala().getCine().getNombre() + "<br>" + "<b>Precio: </b>" + proyeccion.getPrecio()
				+ " €" + "<br><br>" + "¿Desea confirmar la selección?</html>";

		int ret = JOptionPane.showOptionDialog(frame.getContentPane(), msg, titulo, 0, JOptionPane.INFORMATION_MESSAGE,
				null, options, null);

		return ret;
	}

	public void guardarSeleccionProyeccion(Proyeccion proyeccion, JPanel panelBienvenida,
			JPanel panelSeleccionProyeccion, ArrayList<Proyeccion> proyeccionesSeleccionadas) {
		int respuesta = preguntarConfirmacionProyeccion(proyeccion);
		if (respuesta == 1) {
			proyeccionesSeleccionadas.add(proyeccion);
			panelSeleccionProyeccion.setVisible(false);
			panelBienvenida.setVisible(true);
		}

	}

	// RESUMEN COMPRA:
	public double calcularSubtotal(JTable table, ArrayList<Proyeccion> proyecciones) {
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

}
