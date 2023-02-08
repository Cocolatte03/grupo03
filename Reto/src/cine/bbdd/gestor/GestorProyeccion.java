package cine.bbdd.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.bbdd.utils.DBUtils;

public class GestorProyeccion {
	
	private final String ALL_SESIONS_BY_CINE = "SELECT * "
			+ "FROM cine C JOIN sala S ON C.id = S.idCine JOIN proyeccion PR ON S.id = PR.idSala "
			+ "JOIN pelicula P ON PR.idPelicula = P.id " 
			+ "WHERE C.id = ? "
			+ "AND P.id = ? ";
	
	public ArrayList<Proyeccion> getProyeccionesPorCineYPelicula(Cine cine, Pelicula pelicula) {
		ArrayList<Proyeccion> ret = null;
		String sql = ALL_SESIONS_BY_CINE;

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cine.getId());
			preparedStatement.setInt(2, pelicula.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				if (null == ret)
					ret = new ArrayList<Proyeccion>();

				Proyeccion proyeccion = new Proyeccion();

				int id = resultSet.getInt("id");
				double precio = resultSet.getDouble("precio");
				java.sql.Date sqlDate = resultSet.getDate("fecha");
				LocalDate fecha = sqlDate.toLocalDate();
				java.sql.Time sqlTime = resultSet.getTime("hora");
				LocalTime hora = sqlTime.toLocalTime();

				// Metemos los datos a Ejemplo
				proyeccion.setId(id);
				proyeccion.setPrecio(precio);
				proyeccion.setFecha(fecha);
				proyeccion.setHora(hora);
				

				ret.add(proyeccion);
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
