package cine.bbdd.pojos;

import java.util.Date;
import java.util.Objects;

public class Entrada {
	int id = 0;
	String butaca = null;
	double precio = 0;
	Date fechaCompra = null;
	int idSala = 0;
	int idCliente = 0;
	int idPelicula = 0;
	
	public Entrada() {
		
	}
	
	public Entrada(int id, String butaca, double precio, Date fechaCompra, int idSala, int idCliente, int idPelicula) {
		super();
		this.id = id;
		this.butaca = butaca;
		this.precio = precio;
		this.fechaCompra = fechaCompra;
		this.idSala = idSala;
		this.idCliente = idCliente;
		this.idPelicula = idPelicula;
	}
	
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
