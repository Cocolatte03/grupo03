package cine.vista;



import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bienvenida {

	public JFrame bFrame;

	/**
	 * Create the application.
	 */
	public Bienvenida() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		bFrame = new JFrame();
		bFrame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SeleccionCine seleccionCine = new SeleccionCine();
				seleccionCine.scFrame.setVisible(true);
				
				bFrame.dispose();
			}
		});
		bFrame.setBounds(100, 100, 1000, 700);
		bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bFrame.setLocationRelativeTo(null);
		bFrame.setTitle("Cines Elorrieta");
		bFrame.getContentPane().setLayout(null);
	}

}
