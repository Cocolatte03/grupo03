package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase describe la tabla sala.
 */
public class Sala implements Serializable {

	private static final long serialVersionUID = 5160892600345699704L;

	// Clave primaria
	private int id = 0;

	// Atributos
	private String nombre = null;

	// Relaciones
	// Existe una relacion N:1 con la tabla cine.
	private Cine cine = null;
	// Existe una relacion 1:N con la tabla proyeccion.
	private ArrayList<Proyeccion> proyecciones = null;
	
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
	
	public Cine getCine() {
		return cine;
	}
	
	public void setCine(Cine cine) {
		this.cine = cine;
	}
	
	public ArrayList<Proyeccion> getProyecciones() {
		return proyecciones;
	}
	
	public void setProyecciones(ArrayList<Proyeccion> proyecciones) {
		this.proyecciones = proyecciones;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cine, id, nombre, proyecciones);
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
		return Objects.equals(cine, other.cine) && id == other.id && Objects.equals(nombre, other.nombre)
				&& Objects.equals(proyecciones, other.proyecciones);
	}
	
	@Override
	public String toString() {
		return "Sala [id=" + id + ", nombre=" + nombre + ", cine=" + cine + ", proyecciones=" + proyecciones + "]";
	}

}