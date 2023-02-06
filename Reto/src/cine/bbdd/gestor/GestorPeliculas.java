package cine.bbdd.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import cine.bbdd.pojos.Pelicula;
import cine.bbdd.utils.DBUtils;


public class GestorPeliculas {
	
	private final String FILM_BY_CINEMA = "SELECT * "
			+ "FROM cine C JOIN sala S ON C.id = S.idCine "
			+ "JOIN proyeccion PR ON S.id = PR.idSala "
			+ "JOIN pelicula P ON PR.idPelicula = P.id "
			+ "WHERE C.nombre = '";

	public ArrayList<Pelicula> getPeliculasPorCine(String nomCine) {
		ArrayList<Pelicula> ret = null;
		String sql = FILM_BY_CINEMA + nomCine + "'" + " GROUP BY P.id ORDER BY PR.fecha";

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
					ret = new ArrayList<Pelicula>();

				Pelicula pelicula = new Pelicula();

				int id = resultSet.getInt("id");
				String titulo = resultSet.getString("titulo");
				int duracion = resultSet.getInt("duracion");
				String genero = resultSet.getString("genero");
				int coste = resultSet.getInt("coste");
				String caratula = resultSet.getString("caratula");

				// Metemos los datos a Ejemplo
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
