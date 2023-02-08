package cine.bbdd.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Esta clase describe la tabla entrada.
 * 
 * Existe una relacion N:1 con la tabla cliente. 
 * Existe una relacion N:1 con la tabla proyeccion.
 * 
 * @author alexis
 *
 */
public class Entrada implements Serializable {

	private static final long serialVersionUID = -7981210032729514915L;

	// Clave primaria
	private int id = 0;

	// Atributos
	private LocalDate fechaCompra = null;

	// Relaciones
	private Proyeccion proyeccion = null;
	private Cliente cliente = null;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	
	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	public Proyeccion getProyeccion() {
		return proyeccion;
	}
	
	public void setProyeccion(Proyeccion proyeccion) {
		this.proyeccion = proyeccion;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaCompra, id, proyeccion);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaCompra, other.fechaCompra)
				&& id == other.id && Objects.equals(proyeccion, other.proyeccion);
	}
	
	@Override
	public String toString() {
		return "Entrada [id=" + id + ", fechaCompra=" + fechaCompra + ", proyeccion=" + proyeccion + ", cliente="
				+ cliente + "]";
	}

}