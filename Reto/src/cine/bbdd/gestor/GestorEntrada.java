package cine.bbdd.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import cine.bbdd.pojos.Cliente;
import cine.bbdd.pojos.Proyeccion;
import cine.bbdd.utils.DBUtils;

/**
 * Esta clase gestiona las consultas a la base de datos relacionadas con la
 * tabla entrada.
 */
public class GestorEntrada {

	public void insertEntrada(Proyeccion proyeccion, Cliente cliente) {

		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			LocalDateTime fechaCompra = LocalDateTime.now();
			int idProyeccion = proyeccion.getId();
			int idCliente = cliente.getId();
			String fechaStr = fechaCompra.toString();

			String sql = "insert into entrada (fechaCompra, idProyeccion, idCliente) VALUES ('" 
					+ fechaStr + "', '"
					+ idProyeccion + "', '" 
					+ idCliente + "')";

			statement.executeUpdate(sql);

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
		}
	}
}
