package cine.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cine.controlador.Controlador;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionRegistro {

	public JFrame srFrame;
	private Controlador controlador = null;
	private JTextField srTextFieldNombre;
	private JTextField srTextFieldApellidos;
	private JTextField srTextFieldDNI;
	private JTextField srTextFieldDireccion;
	private JTextField srTextFieldCorreo;
	private JTextField srTextFieldUsuario;
	private JTextField srTextFieldContraseña;
	private JTextField srTextFieldRepContra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionRegistro window = new SeleccionRegistro();
					window.srFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SeleccionRegistro() {
		controlador = new Controlador();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		srFrame = new JFrame();
		srFrame.setBounds(100, 100, 1000, 700);
		srFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		srFrame.setLocationRelativeTo(null);
		srFrame.setTitle("Selección de Registro");
		srFrame.getContentPane().setLayout(null);
		
		JLabel srLblRegistro = new JLabel("Registro");
		srLblRegistro.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblRegistro.setBounds(387, 34, 150, 56);
		srFrame.getContentPane().add(srLblRegistro);
		
		JLabel srLblNombre = new JLabel("Nombre");
		srLblNombre.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblNombre.setBounds(94, 100, 300, 56);
		srFrame.getContentPane().add(srLblNombre);
		
		srTextFieldNombre = new JTextField();
		srTextFieldNombre.setColumns(10);
		srTextFieldNombre.setBounds(94, 166, 300, 36);
		srFrame.getContentPane().add(srTextFieldNombre);
		
		JLabel srLblApellidos = new JLabel("Apellidos");
		srLblApellidos.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblApellidos.setBounds(94, 212, 300, 56);
		srFrame.getContentPane().add(srLblApellidos);
		
		srTextFieldApellidos = new JTextField();
		srTextFieldApellidos.setColumns(10);
		srTextFieldApellidos.setBounds(94, 278, 300, 36);
		srFrame.getContentPane().add(srTextFieldApellidos);
		
		JLabel srLblDNI = new JLabel("DNI");
		srLblDNI.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblDNI.setBounds(94, 324, 300, 56);
		srFrame.getContentPane().add(srLblDNI);
		
		srTextFieldDNI = new JTextField();
		srTextFieldDNI.setColumns(10);
		srTextFieldDNI.setBounds(94, 390, 300, 36);
		srFrame.getContentPane().add(srTextFieldDNI);
		
		JLabel srLblDireccion = new JLabel("Dirección");
		srLblDireccion.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblDireccion.setBounds(94, 436, 150, 56);
		srFrame.getContentPane().add(srLblDireccion);
		
		srTextFieldDireccion = new JTextField();
		srTextFieldDireccion.setColumns(10);
		srTextFieldDireccion.setBounds(94, 502, 300, 36);
		srFrame.getContentPane().add(srTextFieldDireccion);
		
		JLabel srLblSexo = new JLabel("Sexo");
		srLblSexo.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblSexo.setBounds(94, 548, 150, 56);
		srFrame.getContentPane().add(srLblSexo);
		
		JComboBox<String> srComboSexo = new JComboBox<String>();
		srComboSexo.setBounds(94, 601, 191, 27);
		srFrame.getContentPane().add(srComboSexo);
		
		JLabel srLblCorreo = new JLabel("Correo Electronico");
		srLblCorreo.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblCorreo.setBounds(538, 100, 300, 56);
		srFrame.getContentPane().add(srLblCorreo);
		
		srTextFieldCorreo = new JTextField();
		srTextFieldCorreo.setColumns(10);
		srTextFieldCorreo.setBounds(538, 166, 300, 36);
		srFrame.getContentPane().add(srTextFieldCorreo);
		
		JLabel srLblUsuario = new JLabel("Usuario");
		srLblUsuario.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblUsuario.setBounds(538, 212, 300, 56);
		srFrame.getContentPane().add(srLblUsuario);
		
		srTextFieldUsuario = new JTextField();
		srTextFieldUsuario.setColumns(10);
		srTextFieldUsuario.setBounds(538, 278, 300, 36);
		srFrame.getContentPane().add(srTextFieldUsuario);
		
		JLabel srLblContraseña = new JLabel("Contraseña");
		srLblContraseña.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblContraseña.setBounds(538, 324, 300, 56);
		srFrame.getContentPane().add(srLblContraseña);
		
		srTextFieldContraseña = new JTextField();
		srTextFieldContraseña.setColumns(10);
		srTextFieldContraseña.setBounds(538, 390, 300, 36);
		srFrame.getContentPane().add(srTextFieldContraseña);
		
		JLabel srLblRepContra = new JLabel("Repetir Contraseña");
		srLblRepContra.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblRepContra.setBounds(538, 436, 300, 56);
		srFrame.getContentPane().add(srLblRepContra);
		
		srTextFieldRepContra = new JTextField();
		srTextFieldRepContra.setColumns(10);
		srTextFieldRepContra.setBounds(538, 502, 300, 36);
		srFrame.getContentPane().add(srTextFieldRepContra);
		
		JButton srBtnAtras = new JButton("Atrás");
		srBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.volverASeleccionLogin(srFrame);
			}
		});
		srBtnAtras.setBounds(835, 600, 85, 29);
		srFrame.getContentPane().add(srBtnAtras);
		
		JButton srBtnRegistrarme = new JButton("Registrarme");
		srBtnRegistrarme.setForeground(Color.WHITE);
		srBtnRegistrarme.setFont(new Font("Tahoma", Font.BOLD, 10));
		srBtnRegistrarme.setBackground(Color.RED);
		srBtnRegistrarme.setBounds(618, 596, 150, 36);
		srFrame.getContentPane().add(srBtnRegistrarme);
	}
}
