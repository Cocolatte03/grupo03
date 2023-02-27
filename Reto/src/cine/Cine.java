package cine;

import java.awt.EventQueue;

import cine.vista.Menu;

/**
 * Clase que ejecuta la aplicación del Cine Elorrieta.
 */
public class Cine {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
