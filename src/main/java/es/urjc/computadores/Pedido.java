package es.urjc.computadores;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Pedido {

	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private double precio;
	private String DireccionOrigen;
	private String DireccionDestino;
	private Date fecha;
	
	@OneToOne
	private Producto producto;
	
	public Pedido() {}
	
	public Pedido(double precio,String DireccionOrigen,String DireccionDestino,Date fecha) {
		this.precio=precio;
		this.DireccionOrigen=DireccionOrigen;
		this.DireccionDestino=DireccionDestino;
		this.fecha=fecha;
	}

	public long getId() {
		return id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDireccionOrigen() {
		return DireccionOrigen;
	}

	public void setDireccionOrigen(String direccionOrigen) {
		DireccionOrigen = direccionOrigen;
	}

	public String getDireccionDestino() {
		return DireccionDestino;
	}

	public void setDireccionDestino(String direccionDestino) {
		DireccionDestino = direccionDestino;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}
