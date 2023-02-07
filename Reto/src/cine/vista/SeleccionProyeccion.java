package cine.vista;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import cine.bbdd.gestor.GestorProyeccion;
import cine.bbdd.gestor.GestorSala;
import cine.bbdd.pojos.Proyeccion;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SeleccionProyeccion {

	JLabel sprLblPeliSel;
	JLabel sprLblCineSel;
	JFrame sprFrame;
	JDatePickerImpl datePicker;
	private GestorProyeccion gestorProyeccion = null;
	private GestorSala gestorSala = null;
	private ArrayList<Proyeccion> proyeccionesPorFecha = null;
	private String cineSeleccionado = null;
	private String peliSeleccionada = null;

	/**
	 * Create the application.
	 */
	public SeleccionProyeccion(String cineSeleccionado, String peliSeleccionada) {
		this.cineSeleccionado = cineSeleccionado;
		this.peliSeleccionada = peliSeleccionada;
		
		gestorProyeccion = new GestorProyeccion();
		gestorSala = new GestorSala();
		proyeccionesPorFecha = new ArrayList<Proyeccion>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		sprFrame = new JFrame();
		sprFrame.setBounds(100, 100, 1000, 700);
		sprFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sprFrame.setLocationRelativeTo(null);
		sprFrame.setTitle("Selección de la Proyección");
		sprFrame.getContentPane().setLayout(null);

		JButton sprBtnAtras = new JButton("Atrás");
		sprBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionPelicula seleccionPelicula = new SeleccionPelicula(cineSeleccionado);
				seleccionPelicula.spFrame.setVisible(true);

				sprFrame.dispose();
			}
		});
		sprBtnAtras.setBounds(6, 6, 70, 29);
		sprFrame.getContentPane().add(sprBtnAtras);
		
		JLabel sprLblCabecera = new JLabel("Seleccione una fecha:");
		sprLblCabecera.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		sprLblCabecera.setBounds(66, 43, 329, 56);
		sprFrame.getContentPane().add(sprLblCabecera);
		
		JPanel sprPanelSesion = new JPanel();
		sprPanelSesion.setBounds(509, 23, 455, 154);
		sprFrame.getContentPane().add(sprPanelSesion);
		sprPanelSesion.setLayout(null);
		sprPanelSesion.setVisible(false);
		
		JLabel sprLblCabeceraSesion = new JLabel("Seleccione una sesión:");
		sprLblCabeceraSesion.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		sprLblCabeceraSesion.setBounds(25, 20, 370, 56);
		sprPanelSesion.add(sprLblCabeceraSesion);
		
		JPanel sprPanelInfo = new JPanel();
		sprPanelInfo.setBounds(545, 199, 419, 176);
		sprFrame.getContentPane().add(sprPanelInfo);
		sprPanelInfo.setLayout(null);
		sprPanelInfo.setVisible(false);
		
		JLabel sprLblSesion = new JLabel("Sesión: ");
		sprLblSesion.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		sprLblSesion.setBounds(6, 25, 77, 16);
		sprPanelInfo.add(sprLblSesion);
		
		JLabel sprLblSala = new JLabel("Sala: ");
		sprLblSala.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		sprLblSala.setBounds(6, 68, 77, 16);
		sprPanelInfo.add(sprLblSala);
		
		JLabel sprLblPrecio = new JLabel("Precio: ");
		sprLblPrecio.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		sprLblPrecio.setBounds(6, 115, 80, 16);
		sprPanelInfo.add(sprLblPrecio);
		
		JLabel sprLblSesion1 = new JLabel("");
		sprLblSesion1.setBounds(103, 25, 284, 16);
		sprPanelInfo.add(sprLblSesion1);
		
		JLabel sprLblSala1 = new JLabel("");
		sprLblSala1.setBounds(103, 68, 284, 16);
		sprPanelInfo.add(sprLblSala1);
		
		JLabel sprLblPrecio1 = new JLabel("");
		sprLblPrecio1.setBounds(103, 115, 284, 16);
		sprPanelInfo.add(sprLblPrecio1);
		
		JComboBox<String> sprComboSesion = new JComboBox<String>();
		sprComboSesion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		sprComboSesion.setBounds(25, 95, 194, 27);
		sprPanelSesion.add(sprComboSesion);
		
		JButton sprBtnSesion = new JButton("Seleccionar");
		sprBtnSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sprPanelInfo.setVisible(true);
			}
		});
		sprBtnSesion.setBounds(263, 95, 117, 29);
		sprPanelSesion.add(sprBtnSesion);
		
		JButton sprBtnFecha = new JButton("Seleccionar");
		sprBtnFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarFechaSeleccionada();
				sprComboSesion.removeAllItems();
				anadirSesionesAlCombo(sprComboSesion);
				sprPanelSesion.setVisible(true);
			}
		});
		sprBtnFecha.setBounds(303, 116, 117, 29);
		sprFrame.getContentPane().add(sprBtnFecha);
		
		sprLblPeliSel = new JLabel("");
		sprLblPeliSel.setHorizontalAlignment(SwingConstants.CENTER);
		sprLblPeliSel.setBounds(551, 634, 426, 27);
		sprFrame.getContentPane().add(sprLblPeliSel);

		crearSelectorFecha(sprFrame);
		
	}
	
	private void crearSelectorFecha(JFrame frame) {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(70, 120, 200, 25);
		model.setSelected(true);
		datePicker.setVisible(true);
		frame.getContentPane().add(datePicker);
		
		sprLblCineSel = new JLabel("");
		sprLblCineSel.setHorizontalAlignment(SwingConstants.CENTER);
		sprLblCineSel.setBounds(551, 586, 426, 27);
		sprFrame.getContentPane().add(sprLblCineSel);

	}
	
	private LocalDate confirmarFechaSeleccionada() {
		LocalDate ret = null;
		Date fechaSeleccionada = (Date) datePicker.getModel().getValue();
		
		ret = convertir(fechaSeleccionada);
		
		return ret;
	}
	
	private static LocalDate convertir (Date date) {
	    return date.toInstant()
	      .atZone(ZoneId.of("GMT+1"))
	      .toLocalDate();
	}
	
	private void anadirSesionesAlCombo(JComboBox<String> combo) {
		String fecha = confirmarFechaSeleccionada().toString();
		ArrayList<Proyeccion> proyecciones = gestorProyeccion.getSesionPorCinePeliculaYFecha(cineSeleccionado, peliSeleccionada, fecha);
		if (null != proyecciones) {
			for (int i = 0; i < proyecciones.size(); i++) {
				Proyeccion proyeccion = proyecciones.get(i);
				proyeccion.setSala(gestorSala.getSalaPorId(proyeccion.getId()));
				proyeccionesPorFecha.add(proyeccion);
				
				String hora = proyeccion.getHora().toString();
				String nombreSala = proyeccion.getSala().getNombre();
				String precio = "" + proyeccion.getPrecio();
				
				combo.addItem(hora + " - " + nombreSala + " - " + precio + " €");
			}
		} else {
			combo.addItem("No hay sesiones");
		}
	}
	
}
