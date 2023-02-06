package cine.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 
 * @author vaain
 *
 */
public class PantallaCine {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaCine window = new PantallaCine();
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
	public PantallaCine() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Selección del Cine");
		frame.getContentPane().setLayout(null);
		
		JLabel spLblCabecera = new JLabel("Seleccione un cine:");
		spLblCabecera.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		spLblCabecera.setBounds(66, 43, 446, 56);
		frame.getContentPane().add(spLblCabecera);
		
		JComboBox selectCine = new JComboBox();
		selectCine.setBounds(30, 100, 230, 27);
		frame.getContentPane().add(selectCine);
	}
}
