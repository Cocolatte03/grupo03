package cine.vista.enproceso;



import java.awt.EventQueue;

/**
 * Esta clase ejecuta la aplicación del Cine Elorrieta.
 * 
 * @author leire
 *
 */
public class Cine2 {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu2 window = new Menu2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
