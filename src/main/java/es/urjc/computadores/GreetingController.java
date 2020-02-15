package es.urjc.computadores;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController implements CommandLineRunner{
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private ProductoRepository productoRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private MensajeRepository mensajeRepo;
	@Autowired
	private ChatRepository chatRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		usuarioRepo.save(new Usuario("pedro potro","pedromolamucho123"));
		usuarioRepo.save(new Usuario("pedro potro2","pedromolamucho123"));
		usuarioRepo.save(new Usuario("pedro potro3","pedromolamucho123"));
		usuarioRepo.save(new Usuario("pedro potro4","pedromolamucho123"));
		
		List<Usuario> p = usuarioRepo.findByNombre("pedro potro");
		for(Usuario u : p) {
			System.out.println(u.getNombre());
		}
		
		Producto p1 = new Producto(2.0,"aaa","un item muy bonico",p.get(0));
		productoRepo.save(p1);
	}
	
	@PostConstruct
	public void init() {
		
	}
	
	
	@GetMapping("/")
	public String greeting(Model model) { 
		model.addAttribute("name", "Mundo");
		return "greeting_template";
	}
	
}
