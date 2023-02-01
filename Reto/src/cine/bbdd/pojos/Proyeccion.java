package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.Objects;

/**
 * Este PJP describe la tabla t_proyeccion
 * @author vaain
 *
 */
public class Proyeccion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5235740695129578150L;
	private int idSala = 0;
	private int idPelicula = 0;
	private String fecha = null;
	private String horaInicio = null;
	private String horaFin = null;
	
	public Proyeccion() {
		
	}
	
	public Proyeccion(int idSala, int idPelicula, String fecha, String horaInicio, String horaFin) {
		super();
		this.idSala = idSala;
		this.idPelicula = idPelicula;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
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
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public String getHoraFin() {
		return horaFin;
	}
	
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fecha, horaFin, horaInicio, idPelicula, idSala);
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
		return Objects.equals(fecha, other.fecha) && Objects.equals(horaFin, other.horaFin)
				&& Objects.equals(horaInicio, other.horaInicio) && idPelicula == other.idPelicula
				&& idSala == other.idSala;
	}
	
	@Override
	public String toString() {
		return "Proyeccion [idSala=" + idSala + ", idPelicula=" + idPelicula + ", fecha=" + fecha + ", horaInicio="
				+ horaInicio + ", horaFin=" + horaFin + "]";
	}

}