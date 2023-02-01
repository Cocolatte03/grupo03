package pelicula;

import java.io.Serializable;
import java.util.Objects;

public class Pelicula implements Serializable {

	/**Este POJO es la tabla t_pelicula
	 * Relacion N:M con la tabla t_sala
	 * Relacion 1:1 con la tabla t_entrada
	 * 
	 */
	private static final long serialVersionUID = -5258555826200509689L;

	private String titulo = null;
	private int id = 0;
	private char genero = ' ';
	private int anio= 0;
	private int duracion= 0;
	private int calificacion = 0;
	
	public Pelicula() {
		
	}
	
	public Pelicula(String titulo, int id, char genero, int anio, int duracion, int calificacion) {
		super()	;
		
		this.titulo = titulo;
		this.id = id;
		this.genero = genero;
		this.anio = anio;
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

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
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
		return Objects.hash(anio, calificacion, duracion, genero, id, titulo);
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
		return anio == other.anio && calificacion == other.calificacion && duracion == other.duracion
				&& genero == other.genero && id == other.id && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", id=" + id + ", genero=" + genero + ", anio=" + anio + ", duracion="
				+ duracion + ", calificacion=" + calificacion + "]";
	}

	
}
