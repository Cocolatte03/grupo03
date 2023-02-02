package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase describe la tabla cine.
 * 
 * Existe una relacion 1:N con la tabla sala.
 * 
 * @author ainhoa
 *
 */
public class Cine implements Serializable {

	private static final long serialVersionUID = 9039805776548704768L;

	//Clave primaria
	private int id = 0;

	//Atributos
	private String nombre = null;
	private String direccion = null;

	//Relaciones
	private ArrayList<Sala> salas = null;

	/**
	 * Constructor vacio.
	 */
	public Cine() {

	}

	/**
	 * Constructor sobrecargado.
	 */
	public Cine(int id, String nombre, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
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

	public ArrayList<Sala> getSalas() {
		return salas;
	}

	public void setSalas(ArrayList<Sala> salas) {
		this.salas = salas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(direccion, id, nombre);
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
		return Objects.equals(direccion, other.direccion) && id == other.id && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Cine [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}

}