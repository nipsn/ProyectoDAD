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
	
	@ManyToOne
	private Usuario propietario;

	public Producto() {}
	
	public Producto(double precio,String categoria, String descripcion) {
		this.precio=precio;
		this.categoria=categoria;
		this.descripcion=descripcion;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getPropietario() {
		return propietario;
	}
	
}
