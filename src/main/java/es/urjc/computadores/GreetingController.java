package es.urjc.computadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.computadores.producto.*;
import es.urjc.computadores.usuario.*;
import es.urjc.computadores.pedido.*;
import es.urjc.computadores.chat.*;
import es.urjc.computadores.mensaje.*;

@Controller
public class GreetingController implements CommandLineRunner{
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private ProductoRepository productoRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private UserComponent usuario;

	@Override
	public void run(String... args) throws Exception {

	}
	
	@PostConstruct
	public void init() {
		
	}
	
	
	@GetMapping("/")
	public String greeting(Model model) {
		List<Producto> lista = productoRepo.findAll();
		model.addAttribute("productos", lista);
		
		if(usuario.getLoggedUser() != null) {
			model.addAttribute("user",usuario.getLoggedUser());
			model.addAttribute("nombreUser",usuario.getLoggedUser().getNombreReal());
		}
		return "main";
	}

	

	
	
}
