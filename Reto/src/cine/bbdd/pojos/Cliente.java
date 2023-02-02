package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase describe la tabla cliente.
 * 
 * Existe una relacion 1:N con la tabla entrada.
 * 
 * @author alexis
 *
 */

public class Cliente implements Serializable {

	private static final long serialVersionUID = -3267796040769875697L;
	
	//Clave primaria
	private int id = 0;
	
	//Atributos
	private String dni = null;
	private String nombre = null;
	private String apellidos = null;
	private String usuario = null;
	private String contrasena = null;
	private String sexo = null;
	private String direccion = null;
	
	//Relaciones
	private ArrayList<Entrada> entradas = null;
	
	/**
	 * Constructor vacio.
	 */
	public Cliente() {
		
	}

	/**
	 * Constructor sobrecargado.
	 */
	public Cliente(int id, String dni, String nombre, String apellidos, String usuario, String contrasena, String sexo,
			String direccion) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.sexo = sexo;
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Entrada> entradas) {
		this.entradas = entradas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, contrasena, direccion, dni, id, nombre, sexo, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(contrasena, other.contrasena)
				&& Objects.equals(direccion, other.direccion) && Objects.equals(dni, other.dni) && id == other.id
				&& Objects.equals(nombre, other.nombre) && Objects.equals(sexo, other.sexo)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", usuario="
				+ usuario + ", contrasena=" + contrasena + ", sexo=" + sexo + ", direccion=" + direccion + "]";
	}

}
