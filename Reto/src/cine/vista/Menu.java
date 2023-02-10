package cine.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.controlador.Controlador;

public class Menu {

	public JFrame frame;
	
	private JPanel bPanel;
	private JPanel scPanel;
	private JPanel spPanel;
	private JPanel sprPanel;
	
	private JComboBox<String> scComboCines;
	private JComboBox<String> spComboTitulos;
	private JComboBox<String> sprComboFecha;
	
	private Controlador controlador = null;
	
	private ArrayList<Cine> cines = null;
	private ArrayList<Pelicula> peliculas = null;
	private ArrayList<Proyeccion> proyecciones = null;
	
	private ArrayList<Proyeccion> proyeccionesSeleccionadas = null;
	
	private Cine cineSeleccionado = null;
	private Pelicula peliSeleccionada = null;
	private Proyeccion proyeccionSeleccionada = null;

	/**
	 * Create the application.
	 */
	public Menu() {
		controlador = new Controlador();
		
		cines = controlador.guardarArrayListCines();
		peliculas = new ArrayList<Pelicula>();
		proyecciones = new ArrayList<Proyeccion>();
		
		proyeccionesSeleccionadas = new ArrayList<Proyeccion>();
		
		cineSeleccionado = new Cine();
		peliSeleccionada = new Pelicula();
		proyeccionSeleccionada = new Proyeccion();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Cine Elorrieta");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		crearPanelBienvenida();
		crearPanelSeleccionCine();
		crearPanelSeleccionPelicula();
		crearPanelSeleccionProyeccion();
		scPanel.setVisible(false);
		spPanel.setVisible(false);
		sprPanel.setVisible(false);
		
	}
	
	private void crearPanelBienvenida() {
		bPanel = new JPanel();
		bPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scPanel.setVisible(true);
				bPanel.setVisible(false);
				controlador.anadirCinesAlCombo(scComboCines, cines);
			}
		});
		bPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(bPanel);
		bPanel.setLayout(null);
		
		JLabel bLblCabecera = new JLabel("Bienvenid@ al Cine Elorrieta");
		bLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		bLblCabecera.setForeground(new Color(72, 138, 246));
		bLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		bLblCabecera.setBounds(281, 250, 439, 36);
		bPanel.add(bLblCabecera);
		
		JPanel bPanelImg = new JPanel();
		bPanelImg.setBounds(0, 0, 1000, 672);
		bPanel.add(bPanelImg);
		bPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel bLblImg = new JLabel("");
		bPanelImg.add(bLblImg, BorderLayout.CENTER);
		
		controlador.anadirImagen(bPanelImg, bLblImg, "img/bbg.jpg");
	}

	private void crearPanelSeleccionCine() {
		
		scPanel = new JPanel();
		scPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(scPanel);
		scPanel.setLayout(null);
		
		JLabel scLblCabecera = new JLabel("Seleccione un cine:");
		scLblCabecera.setForeground(new Color(72, 138, 246));
		scLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		scLblCabecera.setFont(new Font("Dialog", Font.BOLD, 20));
		scLblCabecera.setBounds(30, 43, 230, 56);
		scPanel.add(scLblCabecera);
		
		JPanel scPanelImg = new JPanel();
		scPanelImg.setBounds(160, 170, 700, 490);
		scPanel.add(scPanelImg);
		scPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel scLblImg = new JLabel("");
		scPanelImg.add(scLblImg, BorderLayout.CENTER);
		
		scComboCines = new JComboBox<String>();
		scComboCines.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != scComboCines.getSelectedItem()) {
					controlador.cambiarImagen(scComboCines, scPanelImg, scLblImg);
				}
			}
		});
		scComboCines.setBounds(53, 116, 230, 27);
		scPanel.add(scComboCines);
		
		JButton scBtnFin = new JButton("Finalizar Sesión");
		scBtnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		scBtnFin.setBounds(869, 6, 125, 27);
		scPanel.add(scBtnFin);
		
		JButton scBtnConfirmar = new JButton("Confirmar");
		scBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scPanel.setVisible(false);
				cineSeleccionado = controlador.determinarCineSeleccionado(scComboCines, cines);
				peliculas = controlador.guardarArrayListPeliculas(cineSeleccionado);
				controlador.anadirPeliculasAlCombo(spComboTitulos, peliculas);
				spPanel.setVisible(true);
			}
		});
		scBtnConfirmar.setBounds(305, 116, 100, 27);
		scPanel.add(scBtnConfirmar);
		
	}
	
	public void crearPanelSeleccionPelicula() {
		
		spPanel = new JPanel();
		spPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(spPanel);
		spPanel.setLayout(null);
		
		JLabel spLblCabecera = new JLabel("Seleccione una película:");
		spLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		spLblCabecera.setForeground(new Color(72, 138, 246));
		spLblCabecera.setBounds(66, 43, 446, 56);
		spPanel.add(spLblCabecera);
		
		JPanel spPanelImg = new JPanel();
		spPanelImg.setBackground(new Color(254, 251, 0));
		spPanelImg.setBounds(551, 20, 426, 613);
		spPanel.add(spPanelImg);
		spPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel spLblImg = new JLabel("");
		spPanelImg.add(spLblImg, BorderLayout.CENTER);
		
		JPanel spPanelInfo = new JPanel();
		spPanelInfo.setBounds(74, 267, 411, 348);
		spPanel.add(spPanelInfo);
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
		
		spComboTitulos = new JComboBox<String>();
		spComboTitulos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != spComboTitulos.getSelectedItem()) {
					controlador.cambiarCaratulaPorSeleccion(peliculas, spComboTitulos, spPanelImg, spLblImg);
					controlador.cambiarInformacionPorSeleccion(peliculas, spComboTitulos, spLblTitulo1, spLblGenero1, spLblDuracion1);
				}
			}
		});
		spComboTitulos.setBounds(66, 162, 237, 27);
		spPanel.add(spComboTitulos);
		
		JButton spBtnContinuar = new JButton("Continuar");
		spBtnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				peliSeleccionada = controlador.determinarPeliSeleccionada(spComboTitulos, peliculas);
				controlador.anadirFechasAlCombo(sprComboFecha, cineSeleccionado, peliSeleccionada);
				spPanel.setVisible(false);
				sprPanel.setVisible(true);
			}
		});
		spBtnContinuar.setBounds(359, 161, 117, 29);
		spPanel.add(spBtnContinuar);
		
		JButton spBtnAtras = new JButton("Atrás");
		spBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spPanel.setVisible(false);
				scPanel.setVisible(true);
			}
		});
		spBtnAtras.setBounds(6, 6, 68, 29);
		spPanel.add(spBtnAtras);
	}
	
	private void crearPanelSeleccionProyeccion() {
		sprPanel = new JPanel();
		sprPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(sprPanel);
		sprPanel.setLayout(null);

		JButton sprBtnAtras = new JButton("Atrás");
		sprBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spPanel.setVisible(true);
				sprPanel.setVisible(false);
			}
		});
		sprBtnAtras.setBounds(6, 6, 70, 29);
		sprPanel.add(sprBtnAtras);
		
		JLabel sprLblCabecera = new JLabel("Seleccione una fecha:");
		sprLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		sprLblCabecera.setForeground(new Color(72, 138, 246));
		sprLblCabecera.setBounds(66, 43, 329, 56);
		sprPanel.add(sprLblCabecera);
		
		JLabel sprLblCabeceraSesion = new JLabel("Seleccione una sesión:");
		sprLblCabeceraSesion.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		sprLblCabeceraSesion.setForeground(new Color(72, 138, 246));
		sprLblCabeceraSesion.setBounds(66, 180, 370, 56);
		sprPanel.add(sprLblCabeceraSesion);
		
		JComboBox<String> sprComboSesion = new JComboBox<String>();
		sprComboSesion.setBounds(66, 258, 225, 27);
		sprPanel.add(sprComboSesion);
		
		sprComboFecha = new JComboBox<String>();
		sprComboFecha.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != sprComboFecha.getSelectedItem()) {
					proyecciones = controlador.guardarArrayListProyecciones(sprComboFecha, cineSeleccionado, peliSeleccionada);
					controlador.anadirSesionesAlCombo(sprComboSesion, proyecciones);
				}
			}
		});
		sprComboFecha.setBounds(66, 117, 225, 27);
		sprPanel.add(sprComboFecha);
		
		JButton sprBtnSesion = new JButton("Seleccionar");
		sprBtnSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proyeccionSeleccionada = controlador.guardarProyeccionSeleccionada(sprComboSesion, proyecciones);
				controlador.guardarSeleccionProyeccion(proyeccionSeleccionada, bPanel, sprPanel, proyeccionesSeleccionadas);
			}
		});
		sprBtnSesion.setBounds(303, 257, 117, 29);
		sprPanel.add(sprBtnSesion);
		
		controlador.anadirFechasAlCombo(sprComboFecha, cineSeleccionado, peliSeleccionada);
		
		crearPanelFondoProyeccion();
	}
	
	private void crearPanelFondoProyeccion() {
		
		JPanel sprPanelImg = new JPanel();
		sprPanelImg.setBounds(0, 0, 1000, 672);
		sprPanel.add(sprPanelImg);
		sprPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel sprLblImg = new JLabel("");
		sprPanelImg.add(sprLblImg, BorderLayout.CENTER);
		
		controlador.anadirImagen(sprPanelImg, sprLblImg, "img/sFecha.jpg");
	}
}
