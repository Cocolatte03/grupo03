package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.Date;
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
	
	//Clave primaria
	private int id = 0;
	
	//Atributos
	private Date fechaCompra = null;
	
	//Claves externas
	private int idProyeccion = 0;
	private int idCliente = 0;
	
	//Relaciones
	private Proyeccion proyeccion = null;
	private Cliente cliente = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getIdProyeccion() {
		return idProyeccion;
	}

	public void setIdProyeccion(int idProyeccion) {
		this.idProyeccion = idProyeccion;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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
		return Objects.hash(fechaCompra, id, idCliente, idProyeccion);
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
		return Objects.equals(fechaCompra, other.fechaCompra) && id == other.id && idCliente == other.idCliente
				&& idProyeccion == other.idProyeccion;
	}

	@Override
	public String toString() {
		return "Entrada [id=" + id + ", fechaCompra=" + fechaCompra + ", idProyeccion=" + idProyeccion + ", idCliente="
				+ idCliente + "]";
	}

}
