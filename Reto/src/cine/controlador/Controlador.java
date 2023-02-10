package cine.controlador;

import java.awt.Image;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePickerImpl;

import cine.bbdd.gestor.GestorCine;
import cine.bbdd.gestor.GestorPelicula;
import cine.bbdd.gestor.GestorProyeccion;
import cine.bbdd.gestor.GestorSala;
import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.vista.Bienvenida;
import cine.vista.ResumenCompra;
import cine.vista.SeleccionCine;
import cine.vista.SeleccionPelicula;
import cine.vista.SeleccionProyeccion;
import cine.vista.SeleccionLogin;
import cine.vista.SeleccionRegistro;

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
	private GestorSala gestorSala = null;
	private GestorProyeccion gestorProyeccion = null;

	public Controlador() {
		gestorCine = new GestorCine();
		gestorPelicula = new GestorPelicula();
		gestorSala = new GestorSala();
		gestorProyeccion = new GestorProyeccion();
	}

	// BIENVENIDA:
	public void irASeleccionCine(JFrame frame) {
		SeleccionCine seleccionCine = new SeleccionCine();
		seleccionCine.scFrame.setVisible(true);

		frame.dispose();
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

	/**
	 * Abre la pantalla de SeleccionPelicula y oculta el JFrame actual.
	 * 
	 * @param cine  Cine seleccionado en el apartado de Seleccion de Cine
	 * @param frame JFrame actual que se oculta
	 */
	public void irASeleccionPelicula(Cine cine, JFrame frame) {
		SeleccionPelicula seleccionPelicula = new SeleccionPelicula(cine);

		seleccionPelicula.spFrame.setVisible(true);

		frame.dispose();
	}

	public void irAFinalizarSesion(JFrame frame) {
		ResumenCompra resumenCompra = new ResumenCompra();
		resumenCompra.rcFrame.setVisible(true);
		frame.setVisible(false);
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

	public void volverASeleccionCine(JFrame frame) {
		SeleccionCine seleccionCine = new SeleccionCine();
		seleccionCine.scFrame.setVisible(true);

		frame.dispose();
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

	public void irASeleccionProyeccion(JComboBox<String> combo, JFrame frame, Cine cineSeleccionado,
			Pelicula peliSeleccionada) {
		SeleccionProyeccion seleccionProyeccion = new SeleccionProyeccion(cineSeleccionado, peliSeleccionada);

		seleccionProyeccion.sprFrame.setVisible(true);

		frame.dispose();
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

	public void anadirSesionesAlCombo(JComboBox<String> comboSesion, ArrayList<Proyeccion> proyecciones) {
		comboSesion.removeAllItems();

		if (null != proyecciones) {
			for (int i = 0; i < proyecciones.size(); i++) {
				Proyeccion proyeccion = proyecciones.get(i);

				String hora = proyeccion.getHora().toString();
				String nombreSala = proyeccion.getSala().getNombre();
				String precio = "" + proyeccion.getPrecio();

				comboSesion.addItem(hora + " - " + nombreSala + " - " + precio + " €");
			}
		}
	}

	public Proyeccion guardarProyeccionSeleccionada(JComboBox<String> comboSesion, ArrayList<Proyeccion> proyecciones) {
		Proyeccion ret = null;

		int index = comboSesion.getSelectedIndex();
		ret = proyecciones.get(index);

		return ret;
	}
	
	public int preguntarConfirmacionProyeccion(Proyeccion proyeccion) {
		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = "Cancelar";
		options[1] = "Confirmar";
		
		String titulo = "Confirmar Selección";
		
		String msg = "<html>Datos de la selección: <br><br>"
				+ "<b>Película: </b>" + proyeccion.getPelicula().getTitulo() + "<br>"
				+ "<b>Fecha: </b>" + proyeccion.getFecha().toString() + "<br>"
				+ "<b>Hora: </b>" + proyeccion.getHora().toString() + "<br>"
				+ "<b>Sala: </b>" + proyeccion.getSala().getNombre() + "<br>"
				+ "<b>Cine: </b>" + proyeccion.getSala().getCine().getNombre() + "<br><br>"
				+ "¿Desea confirmar la selección?</html>";

		int ret = JOptionPane.showOptionDialog(frame.getContentPane(), msg, titulo, 0, JOptionPane.INFORMATION_MESSAGE,
				null, options, null);
		
		return ret;
	}

	public void guardarSeleccionProyeccion(Proyeccion proyeccion, JPanel panelBienvenida, JPanel panelSeleccionProyeccion, ArrayList<Proyeccion> proyeccionesSeleccionadas) {
		int respuesta = preguntarConfirmacionProyeccion(proyeccion);
		if (respuesta == 1) {
			proyeccionesSeleccionadas.add(proyeccion);
			panelSeleccionProyeccion.setVisible(false);
			panelBienvenida.setVisible(true);
		}
		

	}

	public LocalDate confirmarFechaSeleccionada(JDatePickerImpl datePicker) {
		LocalDate ret = null;
		Date fechaSeleccionada = (Date) datePicker.getModel().getValue();

		ret = convertir(fechaSeleccionada);

		return ret;
	}

	public LocalDate convertir(Date date) {
		return date.toInstant().atZone(ZoneId.of("GMT+1")).toLocalDate();
	}

	// SELECCION LOGIN:

	public void volverAResumenCompra(JFrame frame) {
		ResumenCompra resumenCompra = new ResumenCompra();
		resumenCompra.rcFrame.setVisible(true);

		frame.dispose();
	}

	public void irASeleccionRegistro(JFrame frame) {
		SeleccionRegistro seleccionRegistro = new SeleccionRegistro();

		seleccionRegistro.srFrame.setVisible(true);

		frame.dispose();
	}

	// SELECCION REGISTRO:

	public void volverASeleccionLogin(JFrame frame) {
		SeleccionLogin seleccionLogin = new SeleccionLogin();
		seleccionLogin.slFrame.setVisible(true);

		frame.dispose();
	}

}
