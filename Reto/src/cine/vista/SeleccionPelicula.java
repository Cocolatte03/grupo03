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
		spPanelImg.setBounds(577, 20, 300, 400);
		frame.getContentPane().add(spPanelImg);
		spPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel spLblImg = new JLabel("");
		spPanelImg.add(spLblImg, BorderLayout.CENTER);
		
		JPanel spPanelInfo = new JPanel();
		spPanelInfo.setBounds(577, 452, 321, 201);
		frame.getContentPane().add(spPanelInfo);
		spPanelInfo.setLayout(null);
		
		JButton spBtnContinuar = new JButton("Continuar");
		spBtnContinuar.setBounds(359, 161, 117, 29);
		frame.getContentPane().add(spBtnContinuar);
		
		JComboBox<String> spComboTitulos = new JComboBox<String>();
		spComboTitulos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cambiarCaratulaPorSeleccion(spComboTitulos, spPanelImg, spLblImg);
			}
		});
		spComboTitulos.setBounds(66, 162, 237, 27);
		frame.getContentPane().add(spComboTitulos);
		
		anadirPeliculasAlCombo(spComboTitulos);
		cambiarCaratulaPorSeleccion(spComboTitulos, spPanelImg, spLblImg);
		
	}
	
	private void anadirPeliculasAlCombo(JComboBox<String> combo) {
		ArrayList<Pelicula> peliculas = gestorPeliculas.getPeliculasPorCine("Cine Elorrieta Bilbao");
		for (int i = 0; i < peliculas.size(); i++) {
			combo.addItem(peliculas.get(i).getTitulo());
			peliculasDelCine.add(peliculas.get(i));
		}
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
}
