package cine.bbdd.pojos;

import java.util.Objects;

public class Pelicula {
	int id = 0;
	String titulo = null;
	int duracion = 0;
	String genero = null;
	
	public Pelicula() {
		
	}
	
	public Pelicula(int id, String titulo, int duracion, String genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.duracion = duracion;
		this.genero = genero;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(duracion, genero, id, titulo);
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
				&& Objects.equals(titulo, other.titulo);
	}
	
	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero + "]";
	}
	
	

}
