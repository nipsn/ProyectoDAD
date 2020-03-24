package es.urjc.computadores;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.urjc.computadores.producto.*;
import es.urjc.computadores.usuario.*;

@Controller
public class GreetingController implements CommandLineRunner {

	@Autowired
	private ProductoRepository productoRepo;

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

		if (usuario.getLoggedUser() != null) {
			model.addAttribute("user", usuario.getLoggedUser());
			model.addAttribute("nombreUser", usuario.getLoggedUser().getNombreReal());
		}
		return "main";
	}

}
