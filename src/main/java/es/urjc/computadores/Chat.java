package es.urjc.computadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Usuario comprador;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Mensaje> mensajes;
	
	protected Chat() {}
	
	public Chat(Usuario comprador) {
		this.comprador = comprador;
		mensajes = new ArrayList<Mensaje>();
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	
	public void insertarMensaje(String mensaje) {
		this.mensajes.add(new Mensaje(mensaje, new Date()));
	}

	public long getId() {
		return id;
	}
	
	
	
}
