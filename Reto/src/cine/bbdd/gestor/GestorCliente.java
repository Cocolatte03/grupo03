package cine.bbdd.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cine.bbdd.pojos.Cliente;
import cine.bbdd.utils.DBUtils;

/**
 * Esta clase gestiona las consultas a la base de datos relacionadas con la
 * tabla cliente.
 */
public class GestorCliente {

	private final String ALL_CLIENTS = "SELECT * FROM cliente";

	public ArrayList<Cliente> getAllClientes() {
		ArrayList<Cliente> ret = null;
		String sql = ALL_CLIENTS;

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
					ret = new ArrayList<Cliente>();

				Cliente cliente = new Cliente();

				int id = resultSet.getInt("id");
				String dni = resultSet.getString("dni");
				String nombre = resultSet.getString("nombre");
				String apellidos = resultSet.getString("apellidos");
				String usuario = resultSet.getString("usuario");
				String contrasena = resultSet.getString("contrasena");
				String sexo = resultSet.getString("sexo");
				String direccion = resultSet.getString("direccion");

				cliente.setId(id);
				cliente.setDni(dni);
				cliente.setNombre(nombre);
				cliente.setApellidos(apellidos);
				cliente.setUsuario(usuario);
				cliente.setContrasena(contrasena);
				cliente.setSexo(sexo);
				cliente.setDireccion(direccion);

				ret.add(cliente);
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
	
	public void insertCliente(Cliente cliente) {
		Connection connection = null;
		
		Statement statement = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			statement = connection.createStatement();
			
			String sql = "insert into cliente (dni, nombre, apellidos, usuario, contrasena, sexo, direccion) VALUES ('" + 
					cliente.getDni() + "', '" + 
					cliente.getNombre() + "', '" + 
					cliente.getApellidos() + "', '" + 
					cliente.getUsuario() + "', '" + 
					cliente.getContrasena() + "', '" + 
					cliente.getSexo() + "', '" + 
					cliente.getDireccion() + "')";
			
			statement.executeUpdate(sql);
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
			};
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			};
		}
	}

}