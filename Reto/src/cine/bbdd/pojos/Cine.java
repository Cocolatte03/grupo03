package cine.bbdd.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Cine {

	int id = 0;
	String nombre = null;
	String direccion = null;
	String telefono = null;
	ArrayList<Sala> salas = null;
	
	public Cine() {
		
	}
	
	public Cine(int id, String nombre, String direccion, String telefono, ArrayList<Sala> salas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.salas = salas;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public ArrayList<Sala> getSalas() {
		return salas;
	}

	public void setSalas(ArrayList<Sala> salas) {
		this.salas = salas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(direccion, id, nombre, salas, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cine other = (Cine) obj;
		return Objects.equals(direccion, other.direccion) && id == other.id && Objects.equals(nombre, other.nombre)
				&& Objects.equals(salas, other.salas) && Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		return "Cine [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", salas=" + salas + "]";
	}
	
	
}
