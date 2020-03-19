package es.urjc.computadores.pedido;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.computadores.producto.Producto;
import es.urjc.computadores.producto.ProductoRepository;
import es.urjc.computadores.usuario.UserComponent;
import es.urjc.computadores.usuario.Usuario;
import es.urjc.computadores.usuario.UsuarioRepository;
import org.springframework.security.web.csrf.CsrfToken;

@Controller
public class PedidoController implements CommandLineRunner{
	
	
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
	/*@PostMapping("/inputpedido/{productid}")
	public String insertarPedido(Model model, @PathVariable Long productid, @RequestParam String destino) {
		System.out.println("Producto:"+productid+ "Destino:"+destino);
		Usuario user = usuario.getLoggedUser();
		Producto p = productoRepo.findById(productid).get();
		Pedido pedido = new Pedido(p,destino,user);
		pedidoRepo.save(pedido);
		model.addAttribute("producto", p);
		return "producto";
	}*/
	/*@PostMapping("/inputpedido/{productid}")
	public String insertarPedido(Model model, Pedido pedido) {
		//if(usuario.getLoggedUser() != null) {
		
		//Pedido pedido = new Pedido(p,destino,usuario.getLoggedUser());
		model.addAttribute("pedido", pedido); 
	//}
		
			pedidoRepo.save(pedido);
			//model.addAttribute("producto", p); 
		
		return "";
	}*/
	
	/*@GetMapping("/inputpedido/{pruductid}")
	public String prepararPedido(Model model,@PathVariable Long productid, @RequestParam String destino,HttpServletRequest request) {
		
		if(usuario.getLoggedUser() != null) {
			Producto p = productoRepo.findById(productid).get();
			Pedido pedido = new Pedido(p,destino,usuario.getLoggedUser());
			model.addAttribute("pedido", pedido); 
		}
		return "inputpedido";
	}*/

	@GetMapping("/{id}/gestionenvios")
	public String gestionenvios(Model model,@PathVariable Long id) {
		Usuario u = usuarioRepo.findById(id).get();
		//model.addAttribute("vendidos", u.getProductosVendidos());
	//	model.addAttribute("comprados", u.getProductosComprados());
		model.addAttribute("userid", u.getId());
		
		return "gestionenvios";
	}
	

}





