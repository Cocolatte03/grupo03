package cine.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import cine.bbdd.gestor.GestorPeliculas;
import cine.bbdd.pojos.Pelicula;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SeleccionPelicula {

	private JFrame frame;
	private GestorPeliculas gestorPeliculas = null;
	private ArrayList<Pelicula> peliculasDelCine = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionPelicula window = new SeleccionPelicula();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SeleccionPelicula() {
		gestorPeliculas = new GestorPeliculas();
		peliculasDelCine = new ArrayList<Pelicula>();
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
		frame.setTitle("Selección de la Película");
		frame.getContentPane().setLayout(null);
		
		JLabel spLblCabecera = new JLabel("Seleccione una película:");
		spLblCabecera.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		spLblCabecera.setBounds(66, 43, 446, 56);
		frame.getContentPane().add(spLblCabecera);
		
		JPanel spPanelImg = new JPanel();
		spPanelImg.setBackground(new Color(254, 251, 0));
		spPanelImg.setBounds(551, 20, 426, 613);
		frame.getContentPane().add(spPanelImg);
		spPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel spLblImg = new JLabel("");
		spPanelImg.add(spLblImg, BorderLayout.CENTER);
		
		JPanel spPanelInfo = new JPanel();
		spPanelInfo.setBounds(74, 267, 411, 348);
		frame.getContentPane().add(spPanelInfo);
		spPanelInfo.setLayout(null);
		
		JLabel spLblTitulo = new JLabel("Titulo: ");
		spLblTitulo.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		spLblTitulo.setBounds(22, 25, 61, 16);
		spPanelInfo.add(spLblTitulo);
		
		JLabel spLblGenero = new JLabel("Género:");
		spLblGenero.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		spLblGenero.setBounds(22, 68, 61, 16);
		spPanelInfo.add(spLblGenero);
		
		JLabel spLblDuracion = new JLabel("Duración:");
		spLblDuracion.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		spLblDuracion.setBounds(22, 115, 61, 16);
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
		
		JButton spBtnContinuar = new JButton("Continuar");
		spBtnContinuar.setBounds(359, 161, 117, 29);
		frame.getContentPane().add(spBtnContinuar);
		
		JComboBox<String> spComboTitulos = new JComboBox<String>();
		spComboTitulos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cambiarCaratulaPorSeleccion(spComboTitulos, spPanelImg, spLblImg);
				cambiarInformacionPorSeleccion(spComboTitulos, spLblTitulo1, spLblGenero1, spLblDuracion1);
			}
		});
		spComboTitulos.setBounds(66, 162, 237, 27);
		frame.getContentPane().add(spComboTitulos);
		
		anadirPeliculasAlCombo(spComboTitulos);
		cambiarCaratulaPorSeleccion(spComboTitulos, spPanelImg, spLblImg);
		cambiarInformacionPorSeleccion(spComboTitulos, spLblTitulo1, spLblGenero1, spLblDuracion1);
		
	}
	
	private void anadirPeliculasAlCombo(JComboBox<String> combo) {
		ArrayList<Pelicula> peliculas = gestorPeliculas.getPeliculasPorCine("Cine Elorrieta Bilbao");
		for (int i = 0; i < peliculas.size(); i++) {
			combo.addItem(peliculas.get(i).getTitulo());
			peliculasDelCine.add(peliculas.get(i));
		}
	}
	
	private void anadirInformacionPeli(JLabel label1, JLabel label2, JLabel label3, ArrayList<Pelicula> peliculas, int i) {
		
		String titulo = peliculas.get(i).getTitulo();
		String genero = peliculas.get(i).getGenero();
		int duracion = peliculas.get(i).getDuracion();
		
		label1.setText(titulo);
		label2.setText(genero);
		label3.setText(duracion + "min");
	}
	
	private String obtenerDireccionCaratula(String titulo) {
		String ret = null;
		
		for (int i = 0; i < peliculasDelCine.size(); i++) {
			if (peliculasDelCine.get(i).getTitulo().equalsIgnoreCase(titulo)) {
				ret = peliculasDelCine.get(i).getCaratula();
			}
		}
		
		return ret;
	}
	
	private void anadirImagen(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
	
	private void cambiarCaratulaPorSeleccion(JComboBox<String> combo, JPanel panel, JLabel label) {
		anadirImagen(panel, label, obtenerDireccionCaratula(combo.getSelectedItem().toString()));
	}
	
	private void cambiarInformacionPorSeleccion(JComboBox<String> combo, JLabel label1, JLabel label2, JLabel label3) {
		for (int i = 0; i < peliculasDelCine.size(); i++) {
			if (combo.getSelectedItem().toString().equalsIgnoreCase(peliculasDelCine.get(i).getTitulo())) {
				anadirInformacionPeli(label1, label2, label3, peliculasDelCine, i);
			}
		}
	}
}
