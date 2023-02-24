package cine.controlador;

public class Utils {

	public static double calcularDescuento(int numeroProyecciones, double subtotal) {
		double ret = 0;
		if (numeroProyecciones == 1) {
			ret = 0;
		} else if (numeroProyecciones == 2) {
			ret = 0.2 * subtotal * (-1);
		} else if (numeroProyecciones == 3) {
			ret = 0.3 * subtotal * (-1);
		} else if (numeroProyecciones == 4) {
			ret = 0.4 * subtotal * (-1);
		} else if (numeroProyecciones >= 5) {
			ret = 0.5 * subtotal * (-1);
		}
		return ret;
	}
	
}
