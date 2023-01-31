package cine.bbdd.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente {
	int id = 0;
	String dni = null;
	String nombre = null;
	String apellidos = null;
	String sexo = null;
	String direccion = null;
	String usuario = null;
	String password = null;
	ArrayList<Entrada> entradas = null;
	
	public Cliente() {
		
	}
	
	public Cliente(int id, String dni, String nombre, String apellidos, String sexo, String direccion, String usuario,
			String password) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sexo = sexo;
		this.direccion = direccion;
		this.usuario = usuario;
		this.password = password;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Entrada> entradas) {
		this.entradas = entradas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, direccion, dni, entradas, id, nombre, password, sexo, usuario);
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
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(dni, other.dni) && Objects.equals(entradas, other.entradas) && id == other.id
				&& Objects.equals(nombre, other.nombre) && Objects.equals(password, other.password)
				&& Objects.equals(sexo, other.sexo) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", sexo="
				+ sexo + ", direccion=" + direccion + ", usuario=" + usuario + ", password=" + password + ", entradas="
				+ entradas + "]";
	}
	
	

}
