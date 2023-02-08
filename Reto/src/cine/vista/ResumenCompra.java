package cine.vista;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class ResumenCompra {

	public JFrame rcFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResumenCompra window = new ResumenCompra();
					window.rcFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ResumenCompra() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		rcFrame = new JFrame();
		rcFrame.setBounds(100, 100, 1000, 700);
		rcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rcFrame.setLocationRelativeTo(null);
		rcFrame.setTitle("Resumen de Compra");
		rcFrame.getContentPane().setLayout(null);
		
		JLabel rcLblCabecera = new JLabel("Resumen de Compra");
		rcLblCabecera.setBounds(66, 43, 446, 56);
		rcLblCabecera.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rcFrame.getContentPane().add(rcLblCabecera);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionCine seleccionCine = new SeleccionCine();
				seleccionCine.scFrame.setVisible(true);
				
				rcFrame.setVisible(false);
			}
		});
		btnNewButton.setBounds(66, 569, 85, 27);
		rcFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Confirmar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionLogin seleccionLogin = new SeleccionLogin();
				seleccionLogin.slFrame.setVisible(true);
				
				rcFrame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(567, 569, 90, 27);
		rcFrame.getContentPane().add(btnNewButton_1);
		
		JList list = new JList();
		list.setBounds(70, 120, 680, 350);
		rcFrame.getContentPane().add(list);
	}
}
