package es.urjc.computadores.producto;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.computadores.usuario.Usuario;
import es.urjc.computadores.usuario.UsuarioRepository;


@Controller
public class ProductoController implements CommandLineRunner{
	
	@Autowired
	private ProductoRepository productoRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;


	@Override
	public void run(String... args) throws Exception {

	}
	
	@PostConstruct
	public void init() {
		
	}
	

	@PostMapping("/inputproducto")
	public String insertarProducto(Model model, @RequestParam String precioIntroducido, String tituloIntroducido,String categoriaIntroducido, String descripcionIntroducido, String usuario) {

		List<Usuario> p = usuarioRepo.findByNombreInterno(usuario);		
		Producto p1 = new Producto(Double.parseDouble(precioIntroducido),categoriaIntroducido,tituloIntroducido,descripcionIntroducido,p.get(0));		
		productoRepo.save(p1);
		return "subirproducto";
	}
	@GetMapping("/producto/{num}")
	public String verProducto(Model model, @PathVariable Long num) {
		
		
		Producto elegido = productoRepo.findById(num).get();

		model.addAttribute("producto", elegido);

		return "producto";
	}
	@GetMapping("/subirproducto")
	public String subirProducto(Model model) {
		return "subirproducto";
	}
	
	@GetMapping("/listaproductos")
	public String listarproductos(Model model) {
		List<Producto> lista = productoRepo.findAll();
		model.addAttribute("datos", lista);
		return "productos";
	}
	
}

