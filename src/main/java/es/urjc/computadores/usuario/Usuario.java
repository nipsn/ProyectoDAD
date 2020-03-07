package es.urjc.computadores.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.urjc.computadores.chat.Chat;
import es.urjc.computadores.pedido.Pedido;
import es.urjc.computadores.producto.Producto;


@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Size(min=2, max=20)
	@Column(unique = true)
	private String nombreInterno;
	
	private String nombreReal;
	private String clave;
	
	@Column(unique = true)
	private String correo;
	
	@ElementCollection (fetch =FetchType.EAGER)
	private List<String> roles;
	
	@OneToMany(mappedBy = "propietario", cascade= CascadeType.ALL)
	private List<Producto> productosEnVenta;
	
	@OneToMany(mappedBy = "remitente")
	private List<Pedido> pedidosVendidos;
	
	@OneToMany(mappedBy = "destinatario")
	private List<Pedido> pedidosComprados;
	
	@OneToMany(mappedBy = "comprador",cascade= CascadeType.ALL)
	private List<Chat> listaChatsComoComprador;//malos nombres. hay que cambiar
	
	@OneToMany(mappedBy = "vendedor",cascade= CascadeType.ALL)
	private List<Chat> listaChatsComoVendedor;
	
	
	
	public Usuario() {}
	
	public Usuario(String nombreReal, String clave, String nombreInterno,String correo){
		this.nombreReal = nombreReal;
		this.nombreInterno=nombreInterno;
		this.clave =  new BCryptPasswordEncoder().encode(clave);
		this.correo=correo;
		productosEnVenta = new ArrayList<Producto>();
		listaChatsComoComprador = new ArrayList<Chat>();
		listaChatsComoVendedor = new ArrayList<Chat>();
		pedidosVendidos = new ArrayList<Pedido>();
		pedidosComprados = new ArrayList<Pedido>();
		roles = new ArrayList<String>();
		roles.add("USER");
	}
	public Usuario(String nombreReal, String clave, String nombreInterno,String correo,boolean admin){
		this.nombreReal = nombreReal;
		this.nombreInterno=nombreInterno;
		this.clave =  new BCryptPasswordEncoder().encode(clave);
		this.correo=correo;
		productosEnVenta = new ArrayList<Producto>();
		listaChatsComoComprador = new ArrayList<Chat>();
		listaChatsComoVendedor = new ArrayList<Chat>();
		pedidosVendidos = new ArrayList<Pedido>();
		pedidosComprados = new ArrayList<Pedido>();
		roles = new ArrayList<String>();
		roles.add("USER");
		if(admin)
			roles.add("ADMIN");
	}

	
	
	
	public List<String> getRoles() {
		return roles;
	}

	public long getId() {
		return id;
	}

	public String getNombreReal() {
		return nombreReal;
	}

	
	public String getNombreInterno() {
		return nombreInterno;
	}

	public void setNombreInterno(String nombreInterno) {
		this.nombreInterno = nombreInterno;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Chat> getListaChatsComoComprador() {
		return listaChatsComoComprador;
	}

	public void setListaChatsComoComprador(List<Chat> listaChatsComoComprador) {
		this.listaChatsComoComprador = listaChatsComoComprador;
	}

	public List<Chat> getListaChatsComoVendedor() {
		return listaChatsComoVendedor;
	}

	public void setListaChatsComoVendedor(List<Chat> listaChatsComoVendedor) {
		this.listaChatsComoVendedor = listaChatsComoVendedor;
	}

	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
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

	public List<Pedido> getPedidosVendidos() {
		return pedidosVendidos;
	}

	public List<Pedido> getPedidosComprados() {
		return pedidosComprados;
	}

	
	
	
}
