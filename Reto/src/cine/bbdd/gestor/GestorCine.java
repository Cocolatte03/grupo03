package cine.bbdd.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cine.bbdd.pojos.Cine;
import cine.bbdd.utils.DBUtils;

public class GestorCine {

	private final String ALL_CINEMAS = "SELECT * "
			+ "FROM cine";
	
	private final String CINEMA_BY_NAME = "SELECT * "
			+ "FROM cine "
			+ "WHERE nombre = ?";
	
	public ArrayList<Cine> getAllCines() {
		ArrayList<Cine> ret = null;
		String sql = ALL_CINEMAS;

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
					ret = new ArrayList<Cine>();

				Cine cine = new Cine();

				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String direccion = resultSet.getString("direccion");

				// Metemos los datos a Ejemplo
				cine.setId(id);
				cine.setNombre(nombre);
				cine.setDireccion(direccion);
				
				ret.add(cine);
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
	
	public Cine getCineByName(String cinemaName) {
		Cine ret = null;
		String sql = CINEMA_BY_NAME;

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cinemaName);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				if (null == ret)
					ret = new Cine();

				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String direccion = resultSet.getString("direccion");

				// Metemos los datos a Ejemplo
				ret.setId(id);
				ret.setNombre(nombre);
				ret.setDireccion(direccion);
		
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
	
	public ArrayList<Cine> getCinesWithSalas(){
		ArrayList<Cine> ret = getAllCines();
		GestorSala gestorSala = new GestorSala();
		
		for (int i = 0; i < ret.size(); i++) {
			Cine cine = ret.get(i);
			cine.setSalas(gestorSala.getSalasPorCine(cine));
		}
		
		return ret;

	}

}