package cine.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class SeleccionPelicula {

	private JFrame frame;

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
		
		JComboBox spComboTitulos = new JComboBox();
		spComboTitulos.setBounds(66, 162, 237, 27);
		frame.getContentPane().add(spComboTitulos);
		
		JPanel spPanelImg = new JPanel();
		spPanelImg.setBackground(new Color(254, 251, 0));
		spPanelImg.setBounds(577, 20, 321, 400);
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
	}
}
