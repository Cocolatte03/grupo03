package cine.vista;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Bienvenida extends JFrame {

	private JPanel contentPane;	

	/**
	 * Create the frame.
	 */
	public Bienvenida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 583);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel bLblBienvenida = new JLabel("BIENVENID@S A CINE ELORRIETA");
		bLblBienvenida.setForeground(new Color(128, 0, 0));
		bLblBienvenida.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
		bLblBienvenida.setBounds(336, 236, 351, 107);
		contentPane.add(bLblBienvenida);
		
		JLabel bLblImgBienvenida = new JLabel();
		ImageIcon img = new ImageIcon("img/imgBienvenida.png");		
		bLblImgBienvenida.setBounds(0, 0, 791,544);
		bLblImgBienvenida.setIcon(img);
		getContentPane().add(bLblImgBienvenida);
		
		
	}

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenida frame = new Bienvenida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
