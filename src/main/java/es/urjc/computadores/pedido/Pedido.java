package es.urjc.computadores.pedido;

import java.util.Date;

import javax.persistence.*;

import es.urjc.computadores.producto.Producto;
import es.urjc.computadores.usuario.Usuario;

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
	
	@ManyToOne
	private Usuario destinatario;
	
	@ManyToOne 
	private Usuario remitente;
	
	public Pedido() {}
	
	public Pedido(Producto producto, String DireccionOrigen,String DireccionDestino, Usuario remitente) {
		this.DireccionOrigen=DireccionOrigen;
		this.DireccionDestino=DireccionDestino;
		this.fecha=new Date();
		this.destinatario = producto.getPropietario();
		this.remitente = remitente;
		this.precio = producto.getPrecio();
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
