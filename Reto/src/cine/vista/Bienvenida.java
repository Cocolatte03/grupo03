package cine.vista;



import javax.swing.JFrame;

import cine.controlador.Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Bienvenida {

	public JFrame bFrame;
	private Controlador controlador = null;

	/**
	 * Create the application.
	 */
	public Bienvenida() {
		controlador = new Controlador();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		bFrame = new JFrame();
		bFrame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.irASeleccionCine(bFrame);
			}
		});
		bFrame.setBounds(100, 100, 1000, 700);
		bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bFrame.setLocationRelativeTo(null);
		bFrame.setTitle("Cines Elorrieta");
		bFrame.getContentPane().setLayout(null);
		
		JLabel bLblCabecera = new JLabel("Bienvenid@ al Cine Elorrieta");
		bLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		bLblCabecera.setForeground(new Color(72, 138, 246));
		bLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		bLblCabecera.setBounds(0, 250, 1000, 60);
		bFrame.getContentPane().add(bLblCabecera);
		
		JPanel bPanelImg = new JPanel();
		bPanelImg.setBounds(0, 0, 1000, 672);
		bFrame.getContentPane().add(bPanelImg);
		bPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel bLblImg = new JLabel("");
		bPanelImg.add(bLblImg, BorderLayout.CENTER);
		
		controlador.anadirImagen(bPanelImg, bLblImg, "img/bbg.jpg");
	}

}

