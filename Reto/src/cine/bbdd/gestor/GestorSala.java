package cine.bbdd.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Sala;
import cine.bbdd.utils.DBUtils;

public class GestorSala {
	private final String SALA_BY_ID = "SELECT * FROM sala WHERE id = '";
	private final String SALA_BY_CINE = "SELECT * FROM sala WHERE idCine = ?";

	public Sala getSalaPorId(int idSel) {
		Sala ret = null;
		String sql = SALA_BY_ID + idSel + "'";

		Connection connection = null;

		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				if (null == ret)
					ret = new Sala();

				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				int idCine = resultSet.getInt("idCine");

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
				if (statement != null)
					statement.close();
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
	
	public ArrayList<Sala> getSalasPorCine(Cine cine) {
		ArrayList<Sala> ret = null;
		String sql = SALA_BY_CINE;

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cine.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				if (null == ret)
					ret = new ArrayList<Sala>();
				
				Sala sala = new Sala();

				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");

				// Metemos los datos a Ejemplo
				sala.setId(id);
				sala.setNombre(nombre);
				
				ret.add(sala);
				
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
