package es.urjc.computadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Component;


@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String clave;
		
	@OneToMany(mappedBy = "propietario")
	private List<Producto> productosEnVenta;
	
	@OneToMany(mappedBy="remitente")
	private List<Pedido> pedidosVendidos;
	
	@OneToMany(mappedBy="destinatario")
	private List<Pedido> pedidosComprados;//distiguir con booleano o algo parecido el que ha llegado del que no
	
	@ManyToMany(cascade =CascadeType.ALL)
	private List<Chat> listaChats;
	
	
	
	
	
	public Usuario() {}
	
	public Usuario(String nombre, String clave){
		this.nombre = nombre;
		this.clave = clave;
		this.productosEnVenta = new ArrayList<Producto>();
		this.pedidosVendidos=new ArrayList<Pedido>();
		this.pedidosComprados= new ArrayList<Pedido>();
		
	}

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public List<Producto> getProductosEnVenta() {
		return productosEnVenta;
	}
	public List<Pedido> getProductosVendidos() {
		return pedidosVendidos;
	}
	public List<Pedido> getProductosComprados() {
		return pedidosComprados;
	}
	public void setProductosVendidos(Pedido nuevo) {
		this.pedidosVendidos.add(nuevo);
	}

	
}
