package cine.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import cine.bbdd.gestor.GestorCine;
import cine.bbdd.pojos.Cine;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * 
 * @author vaain
 *
 */
public class PantallaCine {

	JFrame pcFrame;
	private GestorCine gestorCine = null;
	private ArrayList<Cine> cinesSeleccion = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaCine window = new PantallaCine();
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
	public PantallaCine() {
		gestorCine = new GestorCine();
		cinesSeleccion = new ArrayList<Cine>();
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
		pcFrame.setTitle("Selección de la Película");
		pcFrame.getContentPane().setLayout(null);
		
		JLabel cabecera = new JLabel("Seleccione un cine:");
		cabecera.setHorizontalAlignment(SwingConstants.CENTER);
		cabecera.setFont(new Font("Dialog", Font.PLAIN, 20));
		cabecera.setBounds(30, 43, 230, 56);
		pcFrame.getContentPane().add(cabecera);
		
		JComboBox<String> selectCine = new JComboBox<String>();
		selectCine.setBounds(30, 100, 230, 27);
		pcFrame.getContentPane().add(selectCine);
		
		JButton finalizar = new JButton("Finalizar Sesión");
		finalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ResumenCompra newframe = new ResumenCompra();
//				newframe.main(null);
//				frame.setVisible(false);
			}
		});
		finalizar.setBounds(268, 207, 125, 27);
		pcFrame.getContentPane().add(finalizar);
		
		JButton irASelectPeli = new JButton("Confirmar");
		irASelectPeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionPelicula seleccionPelicula = new SeleccionPelicula();
				seleccionPelicula.spFrame.setVisible(true);
				pcFrame.setVisible(false);
			}
		});
		irASelectPeli.setBounds(292, 103, 100, 27);
		pcFrame.getContentPane().add(irASelectPeli);
		
		anadirCineAlCombo(selectCine);
	}
	
	private void anadirCineAlCombo(JComboBox<String> combo) {
		ArrayList<Cine> cines = gestorCine.getAllCines();
		for (int i = 0; i < cines.size(); i++) {
			combo.addItem(cines.get(i).getNombre());
			cinesSeleccion.add(cines.get(i));
		}
	}

}