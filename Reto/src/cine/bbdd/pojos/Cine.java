package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.Objects;

/**
 * Este POJO describe la tabla t_cine
 * @author vaain
 *
 */
public class Cine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2927237207532070917L;
	private int id = 0;
	private String dni = null;
	private String nombre = null;
	private String direccion = null;
	private String telefono = null;
	
	public Cine() {
			
		}
	
	public Cine(int id, String dni, String nombre, String direccion, String telefono) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
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
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, dni, nombre, direccion, telefono);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre)
				&& Objects.equals(direccion, other.direccion)
				&& Objects.equals(telefono, other.telefono);
	}
	
	@Override
	public String toString() {
		return "Cine [id=" + id + 
				", dni=" + dni + 
				", nombre=" + nombre + 
				", direccion=" + direccion + 
				", telefono=" + telefono + "]";
	}

}