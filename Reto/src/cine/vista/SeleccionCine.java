package cine.vista;



import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import cine.bbdd.gestor.GestorCine;
import cine.bbdd.pojos.Cine;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;

/**
 * 
 * @author vaain
 *
 */
public class SeleccionCine {

	JFrame scFrame;
	private GestorCine gestorCine = null;
	private ArrayList<Cine> cinesSeleccion = null;

	/**
	 * Create the application.
	 */
	public SeleccionCine() {
		gestorCine = new GestorCine();
		cinesSeleccion = new ArrayList<Cine>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		scFrame = new JFrame();
		scFrame.setBounds(100, 100, 1000, 700);
		scFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scFrame.setLocationRelativeTo(null);
		scFrame.setTitle("Selección del Cine");
		scFrame.getContentPane().setLayout(null);
		scFrame.setResizable(false);
		
		JLabel scLblCabecera = new JLabel("Seleccione un cine:");
		scLblCabecera.setForeground(new Color(72, 138, 246));
		scLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		scLblCabecera.setFont(new Font("Dialog", Font.BOLD, 20));
		scLblCabecera.setBounds(30, 43, 230, 56);
		scFrame.getContentPane().add(scLblCabecera);
		
		JPanel scPanelImg = new JPanel();
		scPanelImg.setBounds(160, 170, 700, 490);
		scFrame.getContentPane().add(scPanelImg);
		scPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel scLblImg = new JLabel("");
		scPanelImg.add(scLblImg, BorderLayout.CENTER);
		
		JComboBox<String> scComboCines = new JComboBox<String>();
		scComboCines.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cambiarImagen(scComboCines, scPanelImg, scLblImg);
			}
		});
		scComboCines.setBounds(53, 116, 230, 27);
		scFrame.getContentPane().add(scComboCines);
		
		JButton scBtnFin = new JButton("Finalizar Sesión");
		scBtnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResumenCompra resumenCompra = new ResumenCompra();
				resumenCompra.rcFrame.setVisible(true);
				scFrame.setVisible(false);
			}
		});
		scBtnFin.setBounds(869, 6, 125, 27);
		scFrame.getContentPane().add(scBtnFin);
		
		JButton scBtnConfirmar = new JButton("Confirmar");
		scBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cineSeleccionado = scComboCines.getSelectedItem().toString();
				SeleccionPelicula seleccionPelicula = new SeleccionPelicula(cineSeleccionado);
				
				seleccionPelicula.spLblCineSel.setText("Cine seleccionado: " + cineSeleccionado);
				seleccionPelicula.spFrame.setVisible(true);
				
				scFrame.dispose();
			}
		});
		scBtnConfirmar.setBounds(305, 116, 100, 27);
		scFrame.getContentPane().add(scBtnConfirmar);
		
		anadirCineAlCombo(scComboCines);
		cambiarImagen(scComboCines, scPanelImg, scLblImg);
		
	}
	
	private void anadirCineAlCombo(JComboBox<String> combo) {
		ArrayList<Cine> cines = gestorCine.getAllCines();
		for (int i = 0; i < cines.size(); i++) {
			combo.addItem(cines.get(i).getNombre());
			cinesSeleccion.add(cines.get(i));
		}
	}
	
	private void anadirImagen(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
	
	private void cambiarImagen(JComboBox<String> combo, JPanel panel, JLabel label) {
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
}