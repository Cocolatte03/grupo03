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
 * y el gestor, gestionando el apartado lógico.
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

	public ArrayList<Cine> guardarArrayListCines() {
		ArrayList<Cine> ret = gestorCine.getAllCines();
		return ret;
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

		ret = gestorProyeccion.getProyeccionesPorFechaConSesionPeliculaYCine(cineSeleccionado, peliSeleccionada, fecha);

		return ret;

	}

	public void cargarTablaConSesiones(DefaultTableModel tableModel, ArrayList<Proyeccion> proyecciones, Pelicula pelicula) {
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

	public int preguntarEliminarProyeccionSel() {
		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = "Cancelar";
		options[1] = "Confirmar";

		String titulo = "Eliminar Selección";

		String msg = "¿Desea eliminar la sesión?";

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

			double subtotal = calcularSubtotal(table, proyeccionesSel);
			double descuento = calcularDescuento(proyeccionesSel, subtotal);
			double total = calcularTotal(subtotal, descuento);

			aplicarTotalesALabels(labelSubtotal, labelDescuento, labelTotal, subtotal, descuento, total);
		}
	}

}
