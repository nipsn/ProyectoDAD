package es.urjc.computadores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
		Usuario des=new Usuario("pedro potro2","pedromolamucho123");
		usuarioRepo.save(des);
		usuarioRepo.save(new Usuario("pedro potro3","pedromolamucho123"));
		usuarioRepo.save(new Usuario("pedro potro4","pedromolamucho123"));
		
		List<Usuario> p = usuarioRepo.findByNombre("pedro potro");		
		Producto p1 = new Producto(2.0,"aaa","un item muy bonico",p.get(0));
		productoRepo.save(p1);	
		
		Pedido p2 = new Pedido(p1.getPrecio(),"calle falsa 123","Gran via 2",p1,des);
		pedidoRepo.save(p2);
		Usuario user = p1.getPropietario();
		//user.getProductosVendidos().add(p2);
		usuarioRepo.save(user);
		
	}
	
	@PostConstruct
	public void init() {
		
	}
	
	
	@GetMapping("/")
	public String greeting(Model model) {
		return "greeting_template";
	}
	
	@GetMapping("/inputuser")
	public String insertarDato(Model model, @RequestParam String nombre, String passwd) {
		usuarioRepo.save(new Usuario(nombre,passwd));
		return "SignUp";
	}
	
	@GetMapping("/inputproducto")
	public String insertarProducto(Model model, @RequestParam String precio, String categoria, String descripcion, String usuario) {

		List<Usuario> p = usuarioRepo.findByNombre(usuario);		
		Producto p1 = new Producto(Double.parseDouble(precio),categoria,descripcion,p.get(0));		
		productoRepo.save(p1);
		return "subirproducto";
	}
	
	
	
	@GetMapping("/producto/{num}")
	public String verProducto(Model model, @PathVariable Long num) {
		
		Producto elegido = productoRepo.findById(num).get();

		model.addAttribute("producto", elegido);

		return "producto";
	}
	
	@GetMapping("/peticion")
	public String devolverLista(Model model, @RequestParam String peticion) { 
		if(peticion.equals("usuarios")) {
			List<Usuario> lista = usuarioRepo.findAll();
			model.addAttribute("datos", lista);
			return "usuarios";
		} else if (peticion.equals("productos")) {
			List<Producto> lista = productoRepo.findAll();
			model.addAttribute("datos", lista);
			return "productos";
		}
		return "listadevuelta";
	}
	@GetMapping("/subirproducto")
	public String subirProducto(Model model) {
	return "subirproducto";
	}
	
	@GetMapping("/SignUp")
	public String SignUp(Model model) {
	return "SignUp";
	}
	
	@GetMapping("/buscadorpedidos")
	public String buscadorpedidos(Model model) {
	return "buscadorpedidos";
	}
	@GetMapping("/{id}/gestionenvios")
	public String gestionenvios(Model model,@PathVariable Long id) {
		Usuario u = usuarioRepo.findById(id).get();
		model.addAttribute("vendidos", u.getProductosVendidos());
		model.addAttribute("comprados", u.getProductosComprados());
		
		return "gestionenvios";
	}
}
