package cine.vista;



import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.controlador.Controlador;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionPelicula {
	public JFrame spFrame;
	private ArrayList<Pelicula> peliculas = null;
	private Cine cineSeleccionado = null;
	private Controlador controlador = null;

	/**
	 * Create the application.
	 */
	public SeleccionPelicula(Cine cineSeleccionado) {
		this.cineSeleccionado = cineSeleccionado;
		
		controlador = new Controlador();
		
		peliculas = controlador.guardarArrayListPeliculas(cineSeleccionado);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		spFrame = new JFrame();
		spFrame.setBounds(100, 100, 1000, 700);
		spFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spFrame.setLocationRelativeTo(null);
		spFrame.setTitle("Selección de la Película");
		spFrame.getContentPane().setLayout(null);
		
		JLabel spLblCabecera = new JLabel("Seleccione una película:");
		spLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		spLblCabecera.setForeground(new Color(72, 138, 246));
		spLblCabecera.setBounds(66, 43, 446, 56);
		spFrame.getContentPane().add(spLblCabecera);
		
		JPanel spPanelImg = new JPanel();
		spPanelImg.setBackground(new Color(254, 251, 0));
		spPanelImg.setBounds(551, 20, 426, 613);
		spFrame.getContentPane().add(spPanelImg);
		spPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel spLblImg = new JLabel("");
		spPanelImg.add(spLblImg, BorderLayout.CENTER);
		
		JPanel spPanelInfo = new JPanel();
		spPanelInfo.setBounds(74, 267, 411, 348);
		spFrame.getContentPane().add(spPanelInfo);
		spPanelInfo.setLayout(null);
		
		JLabel spLblTitulo = new JLabel("Titulo: ");
		spLblTitulo.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		spLblTitulo.setBounds(6, 25, 77, 16);
		spPanelInfo.add(spLblTitulo);
		
		JLabel spLblGenero = new JLabel("Género:");
		spLblGenero.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		spLblGenero.setBounds(6, 68, 77, 16);
		spPanelInfo.add(spLblGenero);
		
		JLabel spLblDuracion = new JLabel("Duración:");
		spLblDuracion.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		spLblDuracion.setBounds(6, 115, 80, 16);
		spPanelInfo.add(spLblDuracion);
		
		JLabel spLblTitulo1 = new JLabel("");
		spLblTitulo1.setBounds(103, 25, 284, 16);
		spPanelInfo.add(spLblTitulo1);
		
		JLabel spLblGenero1 = new JLabel("");
		spLblGenero1.setBounds(103, 68, 284, 16);
		spPanelInfo.add(spLblGenero1);
		
		JLabel spLblDuracion1 = new JLabel("");
		spLblDuracion1.setBounds(103, 115, 284, 16);
		spPanelInfo.add(spLblDuracion1);
		
		JComboBox<String> spComboTitulos = new JComboBox<String>();
		spComboTitulos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				controlador.cambiarCaratulaPorSeleccion(peliculas, spComboTitulos, spPanelImg, spLblImg);
				controlador.cambiarInformacionPorSeleccion(peliculas, spComboTitulos, spLblTitulo1, spLblGenero1, spLblDuracion1);
			}
		});
		spComboTitulos.setBounds(66, 162, 237, 27);
		spFrame.getContentPane().add(spComboTitulos);
		
		JButton spBtnContinuar = new JButton("Continuar");
		spBtnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pelicula peliSeleccionada = controlador.determinarPeliSeleccionada(spComboTitulos, peliculas);
				controlador.irASeleccionProyeccion(spComboTitulos, spFrame, cineSeleccionado, peliSeleccionada);
			}
		});
		spBtnContinuar.setBounds(359, 161, 117, 29);
		spFrame.getContentPane().add(spBtnContinuar);
		
		controlador.anadirPeliculasAlCombo(spComboTitulos, peliculas);
		controlador.cambiarCaratulaPorSeleccion(peliculas, spComboTitulos, spPanelImg, spLblImg);
		controlador.cambiarInformacionPorSeleccion(peliculas, spComboTitulos, spLblTitulo1, spLblGenero1, spLblDuracion1);
		
		JButton spBtnAtras = new JButton("Atrás");
		spBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.volverASeleccionCine(spFrame);
			}
		});
		spBtnAtras.setBounds(6, 6, 68, 29);
		spFrame.getContentPane().add(spBtnAtras);
		
	}
}
