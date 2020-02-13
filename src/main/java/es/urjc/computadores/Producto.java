package es.urjc.computadores;

import javax.persistence.*;

@Entity
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private double precio;
	private String categoria;
	private String descripcion;
	
	@OneToOne (cascade = CascadeType.ALL)
	private Pedido pedido;

	public Producto() {}
	
	public Producto(double precio,String categoria, String descripcion) {
		this.precio=precio;
		this.categoria=categoria;
		this.descripcion=descripcion;
	}
	

}