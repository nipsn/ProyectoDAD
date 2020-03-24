package es.urjc.computadores.usuario;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private UserComponent usuariologueado;

	@Override
	public void run(String... args) throws Exception {

	}

	@PostConstruct
	public void init() {

	}

	@RequestMapping("/registrar")
	public String SignUp(Model model) {
		return "SignUp";
	}

	@PostMapping("/inputuser")
	public String insertarDato(Model model, @RequestParam String nombreRealIntroducido, String nombreInternoIntroducido,
			String correoIntroducido, String claveIntroducido) {
		usuarioRepo.save(new Usuario(nombreRealIntroducido, new BCryptPasswordEncoder().encode(claveIntroducido),
				nombreInternoIntroducido, correoIntroducido));
		return "registrar";
	}

	@GetMapping("/login")
	public String SignIn(Model model) {
		return "SignIn";
	}

	@GetMapping("/listausuarios")
	public String listarUsuarios(Model model) {
		List<Usuario> lista = usuarioRepo.findAll();
		model.addAttribute("datos", lista);
		return "usuarios";
	}

	@GetMapping("/usuario/{userid}")
	public String verDatosUsuario(Model model, @PathVariable Long userid) {
		Usuario elegido = usuarioRepo.findById(userid).get();
		model.addAttribute("usuario", elegido);
		model.addAttribute("productos", elegido.getProductosEnVenta());
		model.addAttribute("user", usuariologueado);
		return "usuario";
	}

	@GetMapping("/loginerror")
	public String SignInError(Model model) {
		return "SignIn";
	}
}
