package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase describe la tabla pelicula.
 */
public class Pelicula implements Serializable {

	private static final long serialVersionUID = -8262918258353500416L;
	
	//Clave primaria
	private int id = 0;
	
	//Atributos
	private String titulo = null;
	private int duracion = 0;
	private String genero = null;
	private int coste = 0;
	private String caratula = null;
	
	//Relaciones
	//Existe una relacion de 1:N con la tabla proyeccion.
	private ArrayList<Proyeccion> proyecciones = null;

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

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
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
		return Objects.hash(caratula, coste, duracion, genero, id, proyecciones, titulo);
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
		return Objects.equals(caratula, other.caratula) && coste == other.coste && duracion == other.duracion
				&& Objects.equals(genero, other.genero) && id == other.id
				&& Objects.equals(proyecciones, other.proyecciones) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero
				+ ", coste=" + coste + ", caratula=" + caratula + ", proyecciones=" + proyecciones + "]";
	}

}