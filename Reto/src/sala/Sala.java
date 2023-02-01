package sala;

import java.io.Serializable;
import java.util.Objects;

public class Sala implements Serializable {

	 /**
	  * Este POJO es la tabla t_sala
	 	Relacion N:M con la tabla t_pelicula
	 	Relacion 1:N con la tabla t_entrada
	 	Relacion 1:N con la tabla t_cine
	  */
	
	private static final long serialVersionUID = -6070358253908309401L;

	private int id = 0;
	private String nombre = null;
	
	public Sala(int id, String nombre) {
		super()	;
	
	this.id = id;
	this.nombre = nombre;
	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
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
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", nombre=" + nombre + "]";
	}

	
	
	

}
