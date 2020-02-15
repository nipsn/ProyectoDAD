package es.urjc.computadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Mensaje> mensajes;
	
	protected Chat() {}
	
	public Chat(Mensaje mensaje) {
		mensajes = new ArrayList<Mensaje>();
		mensajes.add(mensaje);
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	
}
