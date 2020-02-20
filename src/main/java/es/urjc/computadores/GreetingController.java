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

	@Override
	public void run(String... args) throws Exception {

	}
	
	@PostConstruct
	public void init() {
		
	}
	
	
	@GetMapping("/")
	public String greeting(Model model) {
		return "greeting_template";
	}
	
	
	
	@GetMapping("/inputproducto")
	public String insertarProducto(Model model, @RequestParam String precio, String categoria, String descripcion, String usuario) {

		List<Usuario> p = usuarioRepo.findByNombre(usuario);		
		Producto p1 = new Producto(Double.parseDouble(precio),categoria,descripcion,p.get(0));		
		productoRepo.save(p1);
		return "subirproducto";
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
	

	
	
	
	
	
	
	//TODO: separar en varios controladores
	@GetMapping("/peticion")
	public String devolverLista(Model model, @RequestParam String peticion) { 
		if(peticion.equals("usuarios")) {
			
			
			
			
			// TODO: quita la parte del producto!!!
			
			
			
			
			
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
	

	
	@GetMapping("/{id}/gestionenvios")
	public String gestionenvios(Model model,@PathVariable Long id) {
		Usuario u = usuarioRepo.findById(id).get();
		model.addAttribute("vendidos", u.getProductosVendidos());
		model.addAttribute("comprados", u.getProductosComprados());
		model.addAttribute("userid", u.getId());
		
		return "gestionenvios";
	}
}
