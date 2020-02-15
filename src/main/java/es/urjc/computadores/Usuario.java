package es.urjc.computadores;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String clave;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Producto producto;
	
	public Usuario() {}
	
	public Usuario(String nombre, String clave){
		this.nombre = nombre;
		this.clave = clave;
	}	
}
