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

		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		// Result set va a contener todo lo que devuelve la BBDD
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			// No es posible saber cuantas cosas nos ha devuelto el resultSet.
			// Hay que ir 1 por 1 y guardandolo todo en su objeto Ejemplo correspondiente
			while (resultSet.next()) {

				// Si es necesario, inicializamos la lista
				if (null == ret)
					ret = new ArrayList<Pelicula>();

				Pelicula pelicula = new Pelicula();

				// Sacamos las columnas del RS
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
				

				// Lo guardamos en ret
				ret.add(pelicula);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
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
		return ret;
	}
}
