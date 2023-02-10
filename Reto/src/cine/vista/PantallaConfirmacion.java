package cine.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class PantallaConfirmacion {

	public JFrame pcFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaConfirmacion window = new PantallaConfirmacion();
					window.pcFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaConfirmacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		pcFrame = new JFrame();
		pcFrame.setBounds(100, 100, 1000, 700);
		pcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pcFrame.setLocationRelativeTo(null);
		pcFrame.setTitle("Pantalla de Confirmación");
		pcFrame.getContentPane().setLayout(null);
		
		JLabel pcLblCabecera = new JLabel("¿Desea confirmar la selección?");
		pcLblCabecera.setForeground(Color.RED);
		pcLblCabecera.setFont(new Font("Dialog", Font.BOLD, 20));
		pcLblCabecera.setBounds(350, 34, 300, 56);
		pcFrame.getContentPane().add(pcLblCabecera);
		
		JLabel pcLblTitulo = new JLabel("Título:");
		pcLblTitulo.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblTitulo.setBounds(65, 125, 70, 40);
		pcFrame.getContentPane().add(pcLblTitulo);
		
		JLabel pcLblGenero = new JLabel("Género:");
		pcLblGenero.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblGenero.setBounds(65, 175, 75, 40);
		pcFrame.getContentPane().add(pcLblGenero);
		
		JLabel pcLblAno = new JLabel("Año:");
		pcLblAno.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblAno.setBounds(65, 225, 45, 40);
		pcFrame.getContentPane().add(pcLblAno);
		
		JLabel pcLblDuracion = new JLabel("Duración:");
		pcLblDuracion.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblDuracion.setBounds(65, 275, 90, 40);
		pcFrame.getContentPane().add(pcLblDuracion);
		
		JLabel pcLblFecha = new JLabel("Fecha:");
		pcLblFecha.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblFecha.setBounds(65, 325, 70, 40);
		pcFrame.getContentPane().add(pcLblFecha);
		
		JLabel pcLblSesion = new JLabel("Sesión:");
		pcLblSesion.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblSesion.setBounds(65, 375, 70, 40);
		pcFrame.getContentPane().add(pcLblSesion);
		
		JLabel pcLblSala = new JLabel("Sala:");
		pcLblSala.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblSala.setBounds(65, 425, 50, 40);
		pcFrame.getContentPane().add(pcLblSala);
		
		JLabel pcLblPrecio = new JLabel("Precio:");
		pcLblPrecio.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblPrecio.setBounds(65, 475, 70, 40);
		pcFrame.getContentPane().add(pcLblPrecio);
		
		JLabel pcLblCine = new JLabel("Cine:");
		pcLblCine.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblCine.setBounds(65, 525, 50, 40);
		pcFrame.getContentPane().add(pcLblCine);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(145, 129, 212, 40);
		pcFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(150, 175, 212, 40);
		pcFrame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(120, 227, 212, 40);
		pcFrame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(165, 275, 212, 40);
		pcFrame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(145, 325, 212, 40);
		pcFrame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(145, 375, 212, 40);
		pcFrame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(125, 425, 212, 40);
		pcFrame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(145, 475, 212, 40);
		pcFrame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(125, 525, 212, 40);
		pcFrame.getContentPane().add(lblNewLabel_8);
	}
}
