package es.urjc.computadores;

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
	
	
	
	@OneToMany(cascade =CascadeType.ALL)
	private List<Pedido> pedidosVendidos;
	@OneToMany(cascade =CascadeType.ALL)
	private List<Pedido> pedidosComprados;//distiguir con booleano o algo parecido el que ha llegado del que no
	@ManyToMany(cascade =CascadeType.ALL)
	private List<Chat> listaChats;
	
	
	
	public Usuario() {}
	
	public Usuario(String nombre, String clave){
		this.nombre = nombre;
		this.clave = clave;
	}
	
	
	
	
}
