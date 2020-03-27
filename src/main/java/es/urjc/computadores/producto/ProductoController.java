package es.urjc.computadores.producto;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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

@Controller
public class ProductoController implements CommandLineRunner {

	@Autowired
	private ProductoRepository productoRepo;

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
	public String insertarProducto(Model model, @RequestParam String precioIntroducido, String tituloIntroducido,
			String categoriaIntroducido, String descripcionIntroducido) {
		Producto p1 = new Producto(Double.parseDouble(precioIntroducido), categoriaIntroducido, tituloIntroducido,
				descripcionIntroducido, usuario.getLoggedUser());
		productoRepo.save(p1);
		model.addAttribute("id", usuario.getLoggedUser().getId());
		return "subirproducto";
	}

	@GetMapping("/producto/{num}")
	public String verProducto(Model model, @PathVariable Long num) {
		Producto elegido = productoRepo.findById(num).get();
		model.addAttribute("producto", elegido);
		if(usuario.getLoggedUser() != null) {
			model.addAttribute("user", usuario.getLoggedUser());
			model.addAttribute("userid", usuario.getLoggedUser().getId());
		}		
		return "producto";
	}

	@GetMapping("/subirproducto")
	public String subirProducto(Model model) {
		System.out.println("usuario logueado: " + usuario.getLoggedUser().getId() + " "
				+ usuario.getLoggedUser().getNombreInterno());
		model.addAttribute("id", usuario.getLoggedUser().getId());// para tener la varibale id del usuario que ha inicio
																	// sesion en la vista de subir producto
		return "subirproducto";
	}

	@RequestMapping("/comprarproducto/{num}")
	public String comprarProducto(Model model, @PathVariable Long num) {

		Producto elegido = productoRepo.findById(num).get();
		model.addAttribute("producto", elegido);

		return "comprarproducto";
	}

	@PostMapping("/comprarproducto/inputpedido")
	public String confirmarCompra(Model model, @RequestParam String destino, String productid) {
		System.out.println("Producto:" + productid + "Destino:" + destino);
		Usuario user = usuario.getLoggedUser();

		Producto p = productoRepo.findById(Long.parseLong(productid)).get();
		Pedido pedido = new Pedido(p, destino, user);

		pedidoRepo.save(pedido);
		
		comunicarSenderEmails((int) pedido.getId());

		return "pedidorealizado";
	}
	@GetMapping("/eliminarproducto/{productoid}")
	public String borrarProducto(Model model, @PathVariable long productoid, HttpServletRequest request) {
		Producto p = productoRepo.findById(productoid).get();
		Pedido pedidoEncontrado = pedidoRepo.findByProducto(p);
		if(pedidoEncontrado == null) {
		productoRepo.deleteById(productoid);
		model.addAttribute("productoBorrado", p);
		}
		return "productdelete";
	}
	
	
	private void comunicarSenderEmails(int pedidoId) {
		Socket serverSocket = null;
        try {
            serverSocket = new Socket("localhost", 10001);
            DataOutputStream dos = new DataOutputStream(serverSocket.getOutputStream());
            dos.writeInt(pedidoId);
            dos.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
