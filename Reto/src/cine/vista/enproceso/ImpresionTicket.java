package cine.vista.enproceso;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImpresionTicket {

	public JFrame itFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImpresionTicket window = new ImpresionTicket();
					window.itFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImpresionTicket() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		itFrame = new JFrame();
		itFrame.setBounds(100, 100, 1000, 700);
		itFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		itFrame.setLocationRelativeTo(null);
		itFrame.setTitle("Impresión de Tickets");
		itFrame.getContentPane().setLayout(null);
		
		JLabel itLblAgradecimiento = new JLabel("¡GRACIAS POR SU COMPRA!");
		itLblAgradecimiento.setForeground(Color.RED);
		itLblAgradecimiento.setFont(new Font("Dialog", Font.BOLD, 20));
		itLblAgradecimiento.setBounds(323, 41, 400, 56);
		itFrame.getContentPane().add(itLblAgradecimiento);
		
		JLabel itLblPregunta = new JLabel("¿Desea imprimir las entradas?");
		itLblPregunta.setFont(new Font("Dialog", Font.PLAIN, 20));
		itLblPregunta.setBounds(323, 369, 400, 56);
		itFrame.getContentPane().add(itLblPregunta);
		
		JButton itBtnSi = new JButton("SI");
		itBtnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		itBtnSi.setForeground(Color.WHITE);
		itBtnSi.setFont(new Font("Tahoma", Font.BOLD, 15));
		itBtnSi.setBackground(Color.RED);
		itBtnSi.setBounds(312, 435, 150, 36);
		itFrame.getContentPane().add(itBtnSi);
		
		JButton itBtnNo = new JButton("NO");
		itBtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		itBtnNo.setForeground(Color.WHITE);
		itBtnNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		itBtnNo.setBackground(Color.RED);
		itBtnNo.setBounds(472, 435, 150, 36);
		itFrame.getContentPane().add(itBtnNo);
	}
}
