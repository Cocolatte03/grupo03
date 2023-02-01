package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
	
/**
 * Esta clase describe la tabla proyeccion
 * Existe una relacion de 1:N con la tabla entrada
 * Existe una relacion de N:1 con la tabla sala
 * Existe una relacion de N:1 con la tabla pelicula
 * 
 * @author leire
 *
 */
public class Proyeccion implements Serializable {

	private static final long serialVersionUID = 7177668438887100410L;

	private int id = 0;
	
	private int precio = 0;
	private Date fecha = null;
	private Date hora = null;
	
	private Sala sala = null;
	private Pelicula pelicula = null;
	private ArrayList<Entrada> entradas = null;
	
	public Proyeccion() {
		
	}
	
	public Proyeccion(int id, int precio, Date fecha, Date hora, Sala sala, Pelicula pelicula,
			ArrayList<Entrada> entradas) {
		super();
		this.id = id;
		this.precio = precio;
		this.fecha = fecha;
		this.hora = hora;
		this.sala = sala;
		this.pelicula = pelicula;
		this.entradas = entradas;
	}

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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}
	public void setEntradas(ArrayList<Entrada> entradas) {
		this.entradas = entradas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(entradas, fecha, hora, id, pelicula, precio, sala);
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
		return Objects.equals(entradas, other.entradas) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(hora, other.hora) && id == other.id && Objects.equals(pelicula, other.pelicula)
				&& precio == other.precio && Objects.equals(sala, other.sala);
	}

	@Override
	public String toString() {
		return "Proyeccion [id=" + id + ", precio=" + precio + ", fecha=" + fecha + ", hora=" + hora + ", sala=" + sala
				+ ", pelicula=" + pelicula + ", entradas=" + entradas + "]";
	}
	
}