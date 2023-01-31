package cine.bbdd.pojos;

import java.util.Objects;

public class Sala {
	int id = 0;
	String nombre = null;
	int idCine = 0;
	
	public Sala(){
		
	}
	
	public Sala(int id, String nombre, int idCine) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idCine = idCine;
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
	public int getIdCine() {
		return idCine;
	}
	public void setIdCine(int idCine) {
		this.idCine = idCine;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idCine, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return id == other.id && idCine == other.idCine && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", nombre=" + nombre + ", idCine=" + idCine + "]";
	}
	
	
}
