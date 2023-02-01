package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase describe la tabla pelicula
 * Existe una relacion de 1:N con la tabla proyeccion
 * @author leire
 *
 */

public class Pelicula implements Serializable {

	private static final long serialVersionUID = -8262918258353500416L;
	
	private int id = 0;
	
	private String titulo = null;
	private int duracion = 0;
	private String genero = null;
	
	ArrayList<Proyeccion> proyecciones = null;
	
	public Pelicula() {
		
	}

	public Pelicula(int id, String titulo, int duracion, String genero, ArrayList<Proyeccion> proyecciones) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.duracion = duracion;
		this.genero = genero;
		this.proyecciones = proyecciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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
		return Objects.hash(duracion, genero, id, proyecciones, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return duracion == other.duracion && Objects.equals(genero, other.genero) && id == other.id
				&& Objects.equals(proyecciones, other.proyecciones) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero
				+ ", proyecciones=" + proyecciones + "]";
	}

}
