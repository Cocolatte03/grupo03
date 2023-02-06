package cine.vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JFrame;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JButton;

public class SeleccionProyeccion {

	JFrame sprFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionProyeccion window = new SeleccionProyeccion();
					window.sprFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SeleccionProyeccion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		sprFrame = new JFrame();
		sprFrame.setBounds(100, 100, 1000, 700);
		sprFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sprFrame.setLocationRelativeTo(null);
		sprFrame.setTitle("Selección de la Proyección");
		sprFrame.getContentPane().setLayout(null);

		JButton sprBtnAtras = new JButton("Atrás");
		sprBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionPelicula seleccionPelicula = new SeleccionPelicula();
				seleccionPelicula.spFrame.setVisible(true);

				sprFrame.setVisible(false);
			}
		});
		sprBtnAtras.setBounds(6, 6, 70, 29);
		sprFrame.getContentPane().add(sprBtnAtras);

		crearSelectorFecha(sprFrame);
	}
	
	private void crearSelectorFecha(JFrame frame) {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(110, 100, 200, 25);
		model.setSelected(true);
		datePicker.setVisible(true);
		frame.add(datePicker);
	}
}
