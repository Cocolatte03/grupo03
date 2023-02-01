package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Pelicula implements Serializable {

	/**Este POJO es la tabla pelicula
	 * Relacion N:M con la tabla sala
	 * Relacion 1:1 con la tabla entrada
	 * 
	 */
	private static final long serialVersionUID = -5258555826200509689L;

	private String titulo = null;
	private int id = 0;
	private char genero = ' ';
	private int ano= 0;
	private int duracion= 0;
	private int calificacion = 0;
	
	public Pelicula(String titulo, int id, char genero, int ano, int duracion, int calificacion) {
		super();
		this.titulo = titulo;
		this.id = id;
		this.genero = genero;
		this.ano = ano;
		this.duracion = duracion;
		this.calificacion = calificacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, calificacion, duracion, genero, id, titulo);
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
		return ano == other.ano && calificacion == other.calificacion && duracion == other.duracion
				&& genero == other.genero && id == other.id && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", id=" + id + ", genero=" + genero + ", ano=" + ano + ", duracion="
				+ duracion + ", calificacion=" + calificacion + "]";
	}
	
	
	
	
	
	

	
}
