package cine.bbdd.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.utils.DBUtils;

/**
 * Esta clase gestiona las consultas a la base de datos relacionadas con la
 * tabla pelicula.
 * 
 * @author leire
 *
 */
public class GestorPelicula {
	
	private final String FILM_BY_CINEMA = "SELECT * "
			+ "FROM cine C JOIN sala S ON C.id = S.idCine "
			+ "JOIN proyeccion PR ON S.id = PR.idSala "
			+ "JOIN pelicula P ON PR.idPelicula = P.id "
			+ "WHERE C.id = ? "
			+ "GROUP BY P.id "
			+ "ORDER BY PR.fecha, PR.hora";

	public ArrayList<Pelicula> getPeliculasPorCine(Cine cine) {
		ArrayList<Pelicula> ret = null;
		String sql = FILM_BY_CINEMA;

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
					ret = new ArrayList<Pelicula>();

				Pelicula pelicula = new Pelicula();

				int id = resultSet.getInt("P.id");
				String titulo = resultSet.getString("titulo");
				int duracion = resultSet.getInt("duracion");
				String genero = resultSet.getString("genero");
				int coste = resultSet.getInt("coste");
				String caratula = resultSet.getString("caratula");

				pelicula.setId(id);
				pelicula.setTitulo(titulo);
				pelicula.setDuracion(duracion);
				pelicula.setGenero(genero);
				pelicula.setCoste(coste);
				pelicula.setCaratula(caratula);
				
				ret.add(pelicula);
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
