package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
	
/**
 * Esta clase describe la tabla proyeccion.
 * 
 * Existe una relacion de 1:N con la tabla entrada.
 * Existe una relacion de N:1 con la tabla sala.
 * Existe una relacion de N:1 con la tabla pelicula.
 * 
 * @author leire
 *
 */
public class Proyeccion implements Serializable {

	private static final long serialVersionUID = -1714643404022780329L;
	
	//Clave primaria
	private int id = 0;

	//Atributos
	private int precio = 0;
	private Date fecha = null;
	private Date hora = null;
	
	//Claves externas
	private int idPelicula = 0;
	private int idSala = 0;
	
	//Relaciones
	private ArrayList<Entrada> entradas = null;
	private Pelicula pelicula = null;
	private Sala sala = null;
	
	/**
	 * Constructor vacio.
	 */
	public Proyeccion() {
		
	}

	/**
	 * Constructor sobrecargado.
	 */
	public Proyeccion(int id, int precio, Date fecha, Date hora, int idPelicula, int idSala,
			ArrayList<Entrada> entradas, Pelicula pelicula, Sala sala) {
		super();
		this.id = id;
		this.precio = precio;
		this.fecha = fecha;
		this.hora = hora;
		this.idPelicula = idPelicula;
		this.idSala = idSala;
		this.entradas = entradas;
		this.pelicula = pelicula;
		this.sala = sala;
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
		return Objects.hash(entradas, fecha, hora, id, idPelicula, idSala, pelicula, precio, sala);
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
				&& Objects.equals(hora, other.hora) && id == other.id && idPelicula == other.idPelicula
				&& idSala == other.idSala && Objects.equals(pelicula, other.pelicula) && precio == other.precio
				&& Objects.equals(sala, other.sala);
	}

	@Override
	public String toString() {
		return "Proyeccion [id=" + id + ", precio=" + precio + ", fecha=" + fecha + ", hora=" + hora + ", idPelicula="
				+ idPelicula + ", idSala=" + idSala + ", entradas=" + entradas + ", pelicula=" + pelicula + ", sala="
				+ sala + "]";
	}

}