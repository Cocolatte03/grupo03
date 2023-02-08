package cine.vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import cine.controlador.Controlador;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;

public class SeleccionLogin {

	JFrame slFrame;
	private Controlador controlador = null;
	private JTextField slTextFielUsuario;
	private JTextField slTextFielContrasena;

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
		controlador = new Controlador();
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
		
		JLabel slLblCabecera = new JLabel("Identificación");
		slLblCabecera.setBounds(400, 43, 150, 56);
		slLblCabecera.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		slFrame.getContentPane().add(slLblCabecera);
		
		JButton slBtnAtras = new JButton("Atrás");
		slBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.volverAResumenCompra(slFrame);
			}
		});
		slBtnAtras.setBounds(24, 33, 85, 29);
		slFrame.getContentPane().add(slBtnAtras);
		
		JLabel slLblUsuario = new JLabel("Usuario");
		slLblUsuario.setFont(new Font("Dialog", Font.PLAIN, 20));
		slLblUsuario.setBounds(678, 114, 150, 56);
		slFrame.getContentPane().add(slLblUsuario);
		
		JLabel slLblContrasena = new JLabel("Contraseña");
		slLblContrasena.setFont(new Font("Dialog", Font.PLAIN, 20));
		slLblContrasena.setBounds(678, 226, 150, 56);
		slFrame.getContentPane().add(slLblContrasena);
		
		slTextFielUsuario = new JTextField();
		slTextFielUsuario.setBounds(678, 180, 150, 36);
		slFrame.getContentPane().add(slTextFielUsuario);
		slTextFielUsuario.setColumns(10);
		
		slTextFielContrasena = new JTextField();
		slTextFielContrasena.setColumns(10);
		slTextFielContrasena.setBounds(678, 292, 150, 36);
		slFrame.getContentPane().add(slTextFielContrasena);
		
		JButton slBtnIniciarSesion = new JButton("Iniciar Sesión");
		slBtnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 10));
		slBtnIniciarSesion.setForeground(Color.WHITE);
		slBtnIniciarSesion.setBackground(Color.RED);
		slBtnIniciarSesion.setBounds(678, 338, 150, 36);
		slFrame.getContentPane().add(slBtnIniciarSesion);
		
		JLabel slLblNoTengoCuenta = new JLabel("No tengo cuenta");
		slLblNoTengoCuenta.setFont(new Font("Dialog", Font.PLAIN, 20));
		slLblNoTengoCuenta.setBounds(678, 481, 200, 56);
		slFrame.getContentPane().add(slLblNoTengoCuenta);
		
		JButton slBtnRegistrarse = new JButton("Registrarse");
		slBtnRegistrarse.setForeground(Color.RED);
		slBtnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 10));
		slBtnRegistrarse.setBackground(Color.LIGHT_GRAY);
		slBtnRegistrarse.setBounds(678, 547, 150, 36);
		slFrame.getContentPane().add(slBtnRegistrarse);
	}
}
