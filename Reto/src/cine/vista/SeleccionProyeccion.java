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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SeleccionProyeccion {
	public JFrame sprFrame;
	JDatePickerImpl datePicker;
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
		
		JLabel sprLblCabeceraSesion = new JLabel("Seleccione una sesión:");
		sprLblCabeceraSesion.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		sprLblCabeceraSesion.setForeground(new Color(72, 138, 246));
		sprLblCabeceraSesion.setBounds(66, 180, 370, 56);
		sprFrame.getContentPane().add(sprLblCabeceraSesion);
		
		JComboBox<String> sprComboSesion = new JComboBox<String>();
		sprComboSesion.setBounds(66, 258, 225, 27);
		sprFrame.getContentPane().add(sprComboSesion);
		
		JComboBox<String> sprComboFecha = new JComboBox<String>();
		sprComboFecha.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				controlador.anadirSesionesAlCombo(sprComboFecha, sprComboSesion, cineSeleccionado, peliSeleccionada);
			}
		});
		sprComboFecha.setBounds(66, 117, 225, 27);
		sprFrame.getContentPane().add(sprComboFecha);
		
		JButton sprBtnSesion = new JButton("Seleccionar");
		sprBtnSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sprBtnSesion.setBounds(303, 257, 117, 29);
		sprFrame.getContentPane().add(sprBtnSesion);
		
		controlador.anadirFechasAlCombo(sprComboFecha, cineSeleccionado, peliSeleccionada);
		
		crearPanelFondo();
		
	}
	
	private void crearSelectorFecha(JFrame frame, JPanel panel) {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
			}
		});
		datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(70, 120, 200, 25);
		model.setSelected(true);
		datePicker.setVisible(true);
		frame.getContentPane().add(datePicker);

	}
	
	private void crearPanelFondo() {
		JPanel sprPanelImgFondo = new JPanel();
		sprPanelImgFondo.setLayout(new BorderLayout(0, 0));
		sprPanelImgFondo.setBounds(0, 0, 1000, 700);
		
		JLabel sprLblImgFondo = new JLabel("");
		sprPanelImgFondo.add(sprLblImgFondo, BorderLayout.CENTER);
		sprFrame.getContentPane().add(sprPanelImgFondo);
		
		controlador.anadirImagen(sprPanelImgFondo, sprLblImgFondo, "img/sFecha.jpg");
	}
}
