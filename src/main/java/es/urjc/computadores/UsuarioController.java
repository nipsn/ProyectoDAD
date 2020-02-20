package es.urjc.computadores;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public void run(String... args) throws Exception {

	}
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/inputuser")
	public String insertarDato(Model model, @RequestParam String nombre, String passwd) {
		usuarioRepo.save(new Usuario(nombre,passwd));
		return "greeting_template";
	}
	
	@GetMapping("/SignUp")
	public String SignUp(Model model) {
		return "SignUp";
	}
	
	@GetMapping("/SignIn")
	public String SignIn(Model model) {
		return "SignIn";
	}
	
	@GetMapping("/usuario/{userid}")
	public String verDatosUsuario(Model model, @PathVariable Long userid) {
		Usuario elegido = usuarioRepo.findById(userid).get();
		model.addAttribute("usuario", elegido);
		model.addAttribute("productos", elegido.getProductosEnVenta());
		return "usuario";
	}
}
