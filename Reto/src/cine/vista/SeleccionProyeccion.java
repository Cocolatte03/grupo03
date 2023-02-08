package cine.vista;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.controlador.Controlador;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Color;

public class SeleccionProyeccion {
	public JFrame sprFrame;
	JDatePickerImpl datePicker;
	private ArrayList<Proyeccion> proyecciones = null;
	private Cine cineSeleccionado = null;
	private Pelicula peliSeleccionada = null;
	private Controlador controlador = null;

	/**
	 * Create the application.
	 */
	public SeleccionProyeccion(Cine cineSeleccionado, Pelicula peliSeleccionada) {
		this.cineSeleccionado = cineSeleccionado;
		this.peliSeleccionada = peliSeleccionada;
		
		controlador = new Controlador();
		
		proyecciones = controlador.guardarArrayListProyecciones(cineSeleccionado, peliSeleccionada);
		System.out.println(proyecciones.toString());
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
		sprLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		sprLblCabecera.setForeground(new Color(72, 138, 246));
		sprLblCabecera.setBounds(66, 43, 329, 56);
		sprFrame.getContentPane().add(sprLblCabecera);
		
		JPanel sprPanelSesion = new JPanel();
		sprPanelSesion.setBounds(40, 320, 455, 154);
		sprFrame.getContentPane().add(sprPanelSesion);
		sprPanelSesion.setLayout(null);
		sprPanelSesion.setVisible(false);
		sprPanelSesion.setOpaque(false);
		
		JLabel sprLblCabeceraSesion = new JLabel("Seleccione una sesión:");
		sprLblCabeceraSesion.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		sprLblCabeceraSesion.setForeground(new Color(72, 138, 246));
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

			}
		});
		sprBtnFecha.setBounds(303, 116, 117, 29);
		sprFrame.getContentPane().add(sprBtnFecha);
		
		crearSelectorFecha(sprFrame);
		
		JPanel sprPanelImgFondo = new JPanel();
		sprPanelImgFondo.setLayout(new BorderLayout(0, 0));
		sprPanelImgFondo.setBounds(0, 0, 1000, 700);
		
		JLabel sprLblImgFondo = new JLabel("");
		sprPanelImgFondo.add(sprLblImgFondo, BorderLayout.CENTER);
		sprFrame.getContentPane().add(sprPanelImgFondo);

		controlador.anadirImagen(sprPanelImgFondo, sprLblImgFondo, "img/sFecha.jpg");
		
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

	}

}
