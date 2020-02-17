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
	
	@OneToMany(cascade =CascadeType.ALL)
	private List<Pedido> pedidosVendidos;
	
	@OneToMany(cascade =CascadeType.ALL)
	private List<Pedido> pedidosComprados;//distiguir con booleano o algo parecido el que ha llegado del que no
	
	@OneToMany(mappedBy = "comprador")
	private List<Chat> listaChatsEnLosQueEstoy;
	
	@OneToMany(mappedBy = "vendedor")
	private List<Chat> listaChatsMios;
	
	
	
	public Usuario() {}
	
	public Usuario(String nombre, String clave){
		this.nombre = nombre;
		this.clave = clave;
		productosEnVenta = new ArrayList<Producto>();
		listaChatsEnLosQueEstoy = new ArrayList<Chat>();
		listaChatsMios = new ArrayList<Chat>();
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

	public List<Pedido> getPedidosVendidos() {
		return pedidosVendidos;
	}

	public List<Pedido> getPedidosComprados() {
		return pedidosComprados;
	}

	public List<Chat> getListaChatsEnLosQueEstoy() {
		return listaChatsEnLosQueEstoy;
	}

	public List<Chat> getListaChatsMios() {
		return listaChatsMios;
	}

	
	
	
}
