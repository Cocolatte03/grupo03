package cine.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class SeleccionLogin {

	JFrame slFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionLogin window = new SeleccionLogin();
					window.slFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SeleccionLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		slFrame = new JFrame();
		slFrame.setBounds(100, 100, 1000, 700);
		slFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		slFrame.getContentPane().setLayout(null);
		slFrame.setTitle("Seleccion de Login");
		slFrame.getContentPane().setLayout(null);
	}

}
