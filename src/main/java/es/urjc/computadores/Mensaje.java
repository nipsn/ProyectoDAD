package es.urjc.computadores;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String cuerpo;
	private Date fecha;
	
	protected Mensaje(){}
	
	public Mensaje(String mensaje, Date fecha) {
		this.cuerpo = mensaje;
		this.fecha = fecha;
	}
}
