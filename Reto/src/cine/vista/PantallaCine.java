package cine.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

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
		
		JLabel cabecera = new JLabel("Seleccione un cine:");
		cabecera.setHorizontalAlignment(SwingConstants.CENTER);
		cabecera.setFont(new Font("Dialog", Font.PLAIN, 20));
		cabecera.setBounds(30, 43, 230, 56);
		frame.getContentPane().add(cabecera);
		
		JComboBox selectCine = new JComboBox();
		selectCine.setBounds(30, 100, 230, 27);
		frame.getContentPane().add(selectCine);
		
		JButton finalizar = new JButton("Finalizar Sesión");
		finalizar.setBounds(268, 207, 125, 27);
		frame.getContentPane().add(finalizar);
		
		JButton irASelectPeli = new JButton("Confirmar");
		irASelectPeli.setBounds(292, 103, 100, 27);
		frame.getContentPane().add(irASelectPeli);
	}
}
