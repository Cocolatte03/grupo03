package cine.bbdd.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cine.bbdd.pojos.Proyeccion;
import cine.bbdd.pojos.Sala;
import cine.bbdd.utils.DBUtils;

/**
 * Esta clase gestiona las consultas a la base de datos relacionadas con la
 * tabla sala.
 * 
 * @author leire
 *
 */
public class GestorSala {
	private final String SALA_BY_ID = "SELECT * "
			+ "FROM sala S JOIN proyeccion PR ON S.id = PR.idSala "
			+ "WHERE PR.id = ? ";

	public Sala getSalaPorProyeccion(Proyeccion proyeccion) {
		Sala ret = null;
		String sql = SALA_BY_ID;

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, proyeccion.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				if (null == ret)
					ret = new Sala();

				int id = resultSet.getInt("S.id");
				String nombre = resultSet.getString("S.nombre");

				// Metemos los datos a Ejemplo
				ret.setId(id);
				ret.setNombre(nombre);
				
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
			}
			;
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
		return ret;
	}
	
}
