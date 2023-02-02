package cine.bbdd.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase describe la tabla proyeccion. Cabe recalcar el formato de la fecha
 * y hora, cuya libreria es java.time.
 * 
 * Existe una relacion de 1:N con la tabla entrada. Existe una relacion de N:1
 * con la tabla sala. Existe una relacion de N:1 con la tabla pelicula.
 * 
 * @author leire
 *
 */
public class Proyeccion implements Serializable {

	private static final long serialVersionUID = -1714643404022780329L;

	// Clave primaria
	private int id = 0;

	// Atributos
	private int precio = 0;
	private LocalDate fecha = null;
	private LocalTime hora = null;

	// Claves externas
	private int idPelicula = 0;
	private int idSala = 0;

	// Relaciones
	private ArrayList<Entrada> entradas = null;
	private Pelicula pelicula = null;
	private Sala sala = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Entrada> entradas) {
		this.entradas = entradas;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, hora, id, idPelicula, idSala, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proyeccion other = (Proyeccion) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora) && id == other.id
				&& idPelicula == other.idPelicula && idSala == other.idSala && precio == other.precio;
	}

	@Override
	public String toString() {
		return "Proyeccion [id=" + id + ", precio=" + precio + ", fecha=" + fecha + ", hora=" + hora + ", idPelicula="
				+ idPelicula + ", idSala=" + idSala + "]";
	}

}