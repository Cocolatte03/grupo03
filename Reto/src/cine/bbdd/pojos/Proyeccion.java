package cine.bbdd.pojos;

import java.util.Date;
import java.util.Objects;

public class Proyeccion {
	int idSala = 0;
	int idPelicula = 0;
	Date fecha = null;
	
	public Proyeccion() {
		
	}
	
	public Proyeccion(int idSala, int idPelicula, Date fecha) {
		super();
		this.idSala = idSala;
		this.idPelicula = idPelicula;
		this.fecha = fecha;
	}
	
	public int getIdSala() {
		return idSala;
	}
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	public int getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, idPelicula, idSala);
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
		return Objects.equals(fecha, other.fecha) && idPelicula == other.idPelicula && idSala == other.idSala;
	}

	@Override
	public String toString() {
		return "Proyeccion [idSala=" + idSala + ", idPelicula=" + idPelicula + ", fecha=" + fecha + "]";
	}

	
}
