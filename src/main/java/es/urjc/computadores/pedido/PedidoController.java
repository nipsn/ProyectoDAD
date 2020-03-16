package es.urjc.computadores.pedido;

import javax.annotation.PostConstruct;

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
	@PostMapping("producto/inputpedido/{productid}")
	public String insertarPedido(Model model, @PathVariable Long productid, @RequestParam String destino) {
		
		if(usuario.getLoggedUser() != null) {
			Producto p = productoRepo.findById(productid).get();
			Pedido pedido = new Pedido(p,destino,usuario.getLoggedUser());
			pedidoRepo.save(pedido);
			model.addAttribute("producto", p); 
		}
		return "producto";
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





