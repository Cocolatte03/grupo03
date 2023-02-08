package cine.vista;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import cine.bbdd.pojos.Cine;
import cine.controlador.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.Color;

public class SeleccionCine {

	public JFrame scFrame;
	private Controlador controlador = null;
	private ArrayList<Cine> cines = null;

	/**
	 * Create the application.
	 */
	public SeleccionCine() {
		controlador = new Controlador();
		cines = controlador.guardarArrayListCines();
		
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
				controlador.cambiarImagen(scComboCines, scPanelImg, scLblImg);
			}
		});
		scComboCines.setBounds(53, 116, 230, 27);
		scFrame.getContentPane().add(scComboCines);
		
		JButton scBtnFin = new JButton("Finalizar Sesión");
		scBtnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.irAFinalizarSesion(scFrame);
			}
		});
		scBtnFin.setBounds(869, 6, 125, 27);
		scFrame.getContentPane().add(scBtnFin);
		
		JButton scBtnConfirmar = new JButton("Confirmar");
		scBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cine cineSeleccionado = controlador.determinarCineSeleccionado(scComboCines, cines);
				controlador.irASeleccionPelicula(cineSeleccionado, scFrame);
			}
		});
		scBtnConfirmar.setBounds(305, 116, 100, 27);
		scFrame.getContentPane().add(scBtnConfirmar);
		
		controlador.anadirCineAlCombo(scComboCines, cines);
		controlador.cambiarImagen(scComboCines, scPanelImg, scLblImg);
		
	}
	
}