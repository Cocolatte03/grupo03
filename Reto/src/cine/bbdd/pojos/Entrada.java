package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Este POJO describe la tabla t_entrada.<br> Relación N:1 con la tabla t_sala.
 * Relación 1:1 con la tabla t_pelicula. Relación N:1 con la tabla t_cliente.
 * 
 * @author in1dw3
 *
 */
public class Entrada implements Serializable {
	private static final long serialVersionUID = 1773284068804366159L;

	private int id = 0;

	private double precio = 0;
	private Date fechaCompra = null;
	private int idSala = 0;
	private int idCliente = 0;
	private int idPelicula = 0;

	private Pelicula pelicula = null;
	private Sala sala = null;

	public Entrada() {

	}

	public Entrada(int id, double precio, Date fechaCompra, int idSala, int idCliente, int idPelicula,
			Pelicula pelicula, Sala sala) {
		super();
		this.id = id;
		this.precio = precio;
		this.fechaCompra = fechaCompra;
		this.idSala = idSala;
		this.idCliente = idCliente;
		this.idPelicula = idPelicula;
		this.pelicula = pelicula;
		this.sala = sala;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
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
		return Objects.hash(fechaCompra, id, idCliente, idPelicula, idSala, pelicula, precio, sala);
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
				&& idPelicula == other.idPelicula && idSala == other.idSala && Objects.equals(pelicula, other.pelicula)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(sala, other.sala);
	}

	@Override
	public String toString() {
		return "Entrada [id=" + id + ", precio=" + precio + ", fechaCompra=" + fechaCompra + ", idSala=" + idSala
				+ ", idCliente=" + idCliente + ", idPelicula=" + idPelicula + ", pelicula=" + pelicula + ", sala="
				+ sala + "]";
	}

}
