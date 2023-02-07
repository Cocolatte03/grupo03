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
		
		JLabel scLblCabecera = new JLabel("Seleccione un cine:");
		scLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		scLblCabecera.setFont(new Font("Dialog", Font.PLAIN, 20));
		scLblCabecera.setBounds(30, 43, 230, 56);
		scFrame.getContentPane().add(scLblCabecera);
		
		JPanel scPanelImg = new JPanel();
		scPanelImg.setBounds(186, 203, 660, 400);
		scFrame.getContentPane().add(scPanelImg);
		scPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel scLblImg = new JLabel("");
		scPanelImg.add(scLblImg, BorderLayout.CENTER);
		
		JComboBox<String> scComboCines = new JComboBox<String>();
		scComboCines.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
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
		anadirImagen(scPanelImg, scLblImg, "img/mapa.png");
		
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
}