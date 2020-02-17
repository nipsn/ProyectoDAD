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
		Usuario p1 = new Usuario("pedro potro","pedromolamucho123");
		Usuario p2 = new Usuario("pedro potro2","pedromolamucho123");
		usuarioRepo.save(p1);
		usuarioRepo.save(p2);
		usuarioRepo.save(new Usuario("pedro potro3","pedromolamucho123"));
		usuarioRepo.save(new Usuario("pedro potro4","pedromolamucho123"));
		
		List<Usuario> lista = usuarioRepo.findByNombre("pedro potro");		
		Producto p = new Producto(2.0,"aaa","un item muy bonico",lista.get(0));
		productoRepo.save(p);
		
		
		Mensaje m1 = new Mensaje("Esto es un mensaje",new Date());
		
		Chat c1 = new Chat(p1,p2);
		c1.getMensajes().add(m1);
		chatRepo.save(c1);
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
		return "greeting_template";
	}
	
	@GetMapping("/subirproducto")
	public String pantallainsertarproducto(Model model) {
		return "subirproducto";
	}
	
	@GetMapping("/inputproducto")
	public String insertarProducto(Model model, @RequestParam String precio, String categoria, String descripcion, String usuario) {

		List<Usuario> p = usuarioRepo.findByNombre(usuario);		
		Producto p1 = new Producto(Double.parseDouble(precio),categoria,descripcion,p.get(0));		
		productoRepo.save(p1);
		return "subirproducto";
	}
	
	@GetMapping("/inputchat")
	public String insertarChat(Model model, @RequestParam String user1, String user2) {
		Usuario u1 = usuarioRepo.findByNombre(user1).get(0);
		Usuario u2 = usuarioRepo.findByNombre(user2).get(0);
		Chat chat = new Chat(u1,u2);
		chatRepo.save(chat);
		return "greeting_template";
	}
	
	@GetMapping("producto/inputpedido/{productid}")
	public String insertarPedido(Model model, @PathVariable Long productid, @RequestParam String origen,String destino, String remitente) {
		Usuario user = usuarioRepo.findByNombre(remitente).get(0);
		Producto p = productoRepo.findById(productid).get();
		Pedido pedido = new Pedido(p,origen,destino,user);
		pedidoRepo.save(pedido);
		model.addAttribute("producto", p);
		return "producto";
	}
	
	@GetMapping("/producto/{num}")
	public String verProducto(Model model, @PathVariable Long num) {
		
		Producto elegido = productoRepo.findById(num).get();

		model.addAttribute("producto", elegido);

		return "producto";
	}
	
	@GetMapping("/usuario/{userid}")
	public String verDatosUsuario(Model model, @PathVariable Long userid) {
		Usuario elegido = usuarioRepo.findById(userid).get();
		model.addAttribute("usuario", elegido);
		model.addAttribute("productos", elegido.getProductosEnVenta());
		return "usuario";
	}
	
	@GetMapping("/{userid}/chats")
	public String verChatDeUsuario(Model model, @PathVariable Long userid) {
		Usuario elegido = usuarioRepo.findById(userid).get();
		List<Chat> listaChat = chatRepo.findByComprador(elegido);
		listaChat.addAll(chatRepo.findByVendedor(elegido));
		model.addAttribute("datos", listaChat);
		model.addAttribute("userid", userid);
		
		return "listachats";
	}
	
	@GetMapping("/chats/{id}")
	public String verChat(Model model, @PathVariable Long id) {
		Chat elegido = chatRepo.findById(id).get();
		List<Mensaje> mensajes = elegido.getMensajes();
		
		model.addAttribute("mensajes", mensajes);
		model.addAttribute("userid", elegido.getComprador().getId());
		
		return "chat";
		
	}
	
	@GetMapping("/chats/inputmensaje")
	public String insertarMensaje(Model model, @RequestParam String mensaje, String chatid) {
		Long id = Long.parseLong(chatid);
		Chat elegido = chatRepo.findById(id).get();
		elegido.insertarMensaje(mensaje);
		chatRepo.save(elegido);
		model.addAttribute("mensajes", elegido.getMensajes());
		model.addAttribute("userid", elegido.getVendedor().getId());
		
		return "chat";
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
	
}
