package cine.bbdd.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Entrada implements Serializable {
	private static final long serialVersionUID = 1773284068804366159L;

	private int id = 0;
	
	private String butaca = null;
	private double precio = 0;
	private Date fechaCompra = null;
	private int idSala = 0;
	private int idCliente = 0;
	private int idPelicula = 0;
	
	private Pelicula pelicula = null;
	private Sala sala = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getButaca() {
		return butaca;
	}
	public void setButaca(String butaca) {
		this.butaca = butaca;
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
		return Objects.hash(butaca, fechaCompra, id, idCliente, idPelicula, idSala, precio);
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
		return Objects.equals(butaca, other.butaca) && Objects.equals(fechaCompra, other.fechaCompra) && id == other.id
				&& idCliente == other.idCliente && idPelicula == other.idPelicula && idSala == other.idSala
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}
	
	@Override
	public String toString() {
		return "Entrada [id=" + id + ", butaca=" + butaca + ", precio=" + precio + ", fechaCompra=" + fechaCompra
				+ ", idSala=" + idSala + ", idCliente=" + idCliente + ", idPelicula=" + idPelicula + "]";
	}
	
}
