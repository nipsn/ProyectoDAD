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
	
	@OneToOne(mappedBy="pedido")
	private Producto producto;
	
	public Pedido() {}
	
	public Pedido(double precio,String DireccionOrigen,String DireccionDestino,Date fecha) {
		this.precio=precio;
		this.DireccionOrigen=DireccionOrigen;
		this.DireccionDestino=DireccionDestino;
		this.fecha=fecha;
	}
	

	
}
