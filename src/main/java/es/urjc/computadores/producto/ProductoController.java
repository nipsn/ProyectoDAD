package es.urjc.computadores.producto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.computadores.pedido.Pedido;
import es.urjc.computadores.pedido.PedidoRepository;
import es.urjc.computadores.usuario.UserComponent;
import es.urjc.computadores.usuario.Usuario;
import es.urjc.computadores.usuario.UsuarioRepository;


@Controller
public class ProductoController implements CommandLineRunner{
	
	@Autowired
	private ProductoRepository productoRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private UserComponent usuario;

	@Override
	public void run(String... args) throws Exception {

	}
	
	@PostConstruct
	public void init() {
		
	}
	

	@PostMapping("/inputproducto")
		public String insertarProducto(Model model, @RequestParam String precioIntroducido, String tituloIntroducido,String categoriaIntroducido, String descripcionIntroducido) {

		Usuario p = usuarioRepo.findByNombreInterno(usuario.getLoggedUser().getNombreInterno());		
			Producto p1 = new Producto(Double.parseDouble(precioIntroducido),categoriaIntroducido,tituloIntroducido,descripcionIntroducido,p);		
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
		model.addAttribute("id",usuario.getLoggedUser().getId());//para tener la varibale id del usuario que ha inicio sesion en la vista de subir producto
		return "subirproducto";
	}
	
	@GetMapping("/listaproductos")
	public String listarproductos(Model model) {
		List<Producto> lista = productoRepo.findAll();
		model.addAttribute("datos", lista);
		return "productos";
	}
	@RequestMapping("/comprarproducto/{num}")
	public String comprarProducto(Model model, @PathVariable Long num) {
		

		Producto elegido = productoRepo.findById(num).get();
		model.addAttribute("producto", elegido);

		return "comprarproducto";
	}
	@PostMapping("/comprarproducto/inputpedido")
	public String confirmarCompra(Model model, @RequestParam String destino, String productid) {
		System.out.println("Producto:"+productid+ "Destino:"+destino);
		Usuario user = usuario.getLoggedUser();
		
		Producto p = productoRepo.findById(Long.parseLong(productid)).get();
		Pedido pedido = new Pedido(p,destino,user);
		
		pedidoRepo.save(pedido);
		//model.addAttribute("producto", p);
		return "pedidorealizado";
	}
}

