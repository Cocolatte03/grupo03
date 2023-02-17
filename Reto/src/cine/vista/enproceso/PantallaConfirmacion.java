package cine.vista.enproceso;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;

public class PantallaConfirmacion {

	public JFrame pcFrame;
	private JLabel pcLblTitulo;

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
		pcLblCabecera.setBounds(145, 34, 300, 56);
		pcFrame.getContentPane().add(pcLblCabecera);
		
		pcLblTitulo = new JLabel("Título:");
		pcLblTitulo.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblTitulo.setBounds(65, 125, 70, 40);
		pcFrame.getContentPane().add(pcLblTitulo);
		
		JLabel pcLblTitulo1 = new JLabel("");
		pcLblTitulo1.setFont(new Font("Dialog", Font.PLAIN, 15));
		pcLblTitulo1.setBounds(145, 129, 212, 40);
		pcFrame.getContentPane().add(pcLblTitulo1);
		
		JLabel pcLblGenero = new JLabel("Género:");
		pcLblGenero.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblGenero.setBounds(65, 175, 75, 40);
		pcFrame.getContentPane().add(pcLblGenero);
		
		JLabel pcLblGenero1 = new JLabel("");
		pcLblGenero1.setFont(new Font("Dialog", Font.PLAIN, 15));
		pcLblGenero1.setBounds(150, 175, 212, 40);
		pcFrame.getContentPane().add(pcLblGenero1);
		
		JLabel pcLblAño = new JLabel("Año:");
		pcLblAño.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblAño.setBounds(65, 225, 45, 40);
		pcFrame.getContentPane().add(pcLblAño);
		
		JLabel pcLblAño1 = new JLabel("");
		pcLblAño1.setFont(new Font("Dialog", Font.PLAIN, 15));
		pcLblAño1.setBounds(120, 227, 212, 40);
		pcFrame.getContentPane().add(pcLblAño1);
		
		JLabel pcLblDuracion = new JLabel("Duración:");
		pcLblDuracion.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblDuracion.setBounds(65, 275, 90, 40);
		pcFrame.getContentPane().add(pcLblDuracion);
		
		JLabel pcLblDuracion1 = new JLabel("");
		pcLblDuracion1.setFont(new Font("Dialog", Font.PLAIN, 15));
		pcLblDuracion1.setBounds(165, 275, 212, 40);
		pcFrame.getContentPane().add(pcLblDuracion1);
		
		JLabel pcLblFecha = new JLabel("Fecha:");
		pcLblFecha.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblFecha.setBounds(65, 325, 70, 40);
		pcFrame.getContentPane().add(pcLblFecha);
		
		JLabel pcLblFecha1 = new JLabel("");
		pcLblFecha1.setFont(new Font("Dialog", Font.PLAIN, 15));
		pcLblFecha1.setBounds(145, 325, 212, 40);
		pcFrame.getContentPane().add(pcLblFecha1);
		
		JLabel pcLblSesion = new JLabel("Sesión:");
		pcLblSesion.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblSesion.setBounds(65, 375, 70, 40);
		pcFrame.getContentPane().add(pcLblSesion);
		
		JLabel pcLblSesion1 = new JLabel("");
		pcLblSesion1.setFont(new Font("Dialog", Font.PLAIN, 15));
		pcLblSesion1.setBounds(145, 375, 212, 40);
		pcFrame.getContentPane().add(pcLblSesion1);
		
		JLabel pcLblSala = new JLabel("Sala:");
		pcLblSala.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblSala.setBounds(65, 425, 50, 40);
		pcFrame.getContentPane().add(pcLblSala);
		
		JLabel pcLblSala1 = new JLabel("");
		pcLblSala1.setFont(new Font("Dialog", Font.PLAIN, 15));
		pcLblSala1.setBounds(125, 425, 212, 40);
		pcFrame.getContentPane().add(pcLblSala1);
		
		JLabel pcLblPrecio = new JLabel("Precio:");
		pcLblPrecio.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblPrecio.setBounds(65, 475, 70, 40);
		pcFrame.getContentPane().add(pcLblPrecio);
		
		JLabel pcLblPrecio1 = new JLabel("");
		pcLblPrecio1.setFont(new Font("Dialog", Font.PLAIN, 15));
		pcLblPrecio1.setBounds(145, 475, 212, 40);
		pcFrame.getContentPane().add(pcLblPrecio1);
		
		JLabel pcLblCine = new JLabel("Cine:");
		pcLblCine.setFont(new Font("Dialog", Font.PLAIN, 20));
		pcLblCine.setBounds(65, 525, 50, 40);
		pcFrame.getContentPane().add(pcLblCine);
		
		JLabel pcLblCine1 = new JLabel("");
		pcLblCine1.setFont(new Font("Dialog", Font.PLAIN, 15));
		pcLblCine1.setBounds(125, 525, 212, 40);
		pcFrame.getContentPane().add(pcLblCine1);
		
		JPanel pcPanelImg = new JPanel();
		pcPanelImg.setBackground(new Color(254, 251, 0));
		pcPanelImg.setBounds(551, 20, 426, 613);
		pcFrame.getContentPane().add(pcPanelImg);
		pcPanelImg.setLayout(new BorderLayout(0, 0));
		
		JLabel pcLblImg = new JLabel("");
		pcPanelImg.add(pcLblImg, BorderLayout.CENTER);
		
		JButton pcBtnCancelar = new JButton("Cancelar");
		pcBtnCancelar.setForeground(Color.WHITE);
		pcBtnCancelar.setFont(new Font("Tahoma", Font.BOLD, 15));
		pcBtnCancelar.setBackground(Color.RED);
		pcBtnCancelar.setBounds(88, 575, 150, 36);
		pcFrame.getContentPane().add(pcBtnCancelar);
		
		JButton pcBtnConfirmar = new JButton("Confirmar");
		pcBtnConfirmar.setForeground(Color.WHITE);
		pcBtnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 15));
		pcBtnConfirmar.setBackground(Color.RED);
		pcBtnConfirmar.setBounds(262, 575, 150, 36);
		pcFrame.getContentPane().add(pcBtnConfirmar);
	}
}
