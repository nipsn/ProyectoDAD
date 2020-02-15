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
	
	@OneToOne(cascade = CascadeType.ALL)
	private Producto producto;
	
	@OneToMany(mappedBy = "propietario")
	private List<Producto> productosEnVenta;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Producto> productosComprados;
	
	public Usuario() {}
	
	public Usuario(String nombre, String clave){
		this.nombre = nombre;
		this.clave = clave;
		productosEnVenta = new ArrayList<Producto>();
		productosComprados = new ArrayList<Producto>();
	}	
}
