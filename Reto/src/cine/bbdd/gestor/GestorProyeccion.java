package cine.bbdd.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import cine.bbdd.pojos.Proyeccion;
import cine.bbdd.utils.DBUtils;

public class GestorProyeccion {
	private final String SESION_BY = "SELECT * "
			+ "FROM cine C JOIN sala S ON C.id = S.idCine JOIN proyeccion PR ON S.id = PR.idSala "
			+ "JOIN pelicula P ON PR.idPelicula = P.id " + "WHERE C.nombre = '";

	public ArrayList<Proyeccion> getSesionPorCinePeliculaYFecha(String nomCine, String tituloPeli, String fechaSel) {
		ArrayList<Proyeccion> ret = null;
		String sql = SESION_BY + nomCine + "' AND P.titulo = '" + tituloPeli + "' AND PR.fecha = '" + fechaSel
				+ "' ORDER BY PR.hora ASC";

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
					ret = new ArrayList<Proyeccion>();

				Proyeccion proyeccion = new Proyeccion();

				int id = resultSet.getInt("id");
				double precio = resultSet.getDouble("precio");
				java.sql.Date sqlDate = resultSet.getDate("fecha");
				LocalDate fecha = sqlDate.toLocalDate();
				java.sql.Time sqlTime = resultSet.getTime("hora");
				LocalTime hora = sqlTime.toLocalTime();
				int idPelicula = resultSet.getInt("idPelicula");
				int idSala = resultSet.getInt("idSala");

				// Metemos los datos a Ejemplo
				proyeccion.setId(id);
				proyeccion.setPrecio(precio);
				proyeccion.setFecha(fecha);
				proyeccion.setHora(hora);
				proyeccion.setIdPelicula(idPelicula);
				proyeccion.setIdPelicula(idSala);
				

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
}
