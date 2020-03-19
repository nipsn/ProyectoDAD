package es.urjc.computadores.pedido;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLConnection;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.computadores.producto.Producto;
import es.urjc.computadores.producto.ProductoRepository;
import es.urjc.computadores.usuario.UserComponent;
import es.urjc.computadores.usuario.Usuario;
import es.urjc.computadores.usuario.UsuarioRepository;
import org.springframework.security.web.csrf.CsrfToken;



@Controller
public class PedidoController implements CommandLineRunner{
	
	public static final String OUTPUT_DIR = "src/main/resources/facturas/";
	
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
	@RequestMapping("/descargar/{fileName:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {

		File file = new File("/src/main/resources/facturas/" + fileName);
		System.out.print("/src/main/resources/facturas/" + fileName);
		if (file.exists()) {

			//get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				//unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}
	}
	@GetMapping("/generarfactura")
	public String generarFactura(Model model, @RequestParam int id) {
		new Thread(() -> {comunicarServicioInternoPDF(id);}).start();
		return "/";
	}
	
	private void comunicarServicioInternoPDF(int pedidoId) {
		Socket serverSocket;
		try {
            serverSocket = new Socket("localhost", 10000);
            DataOutputStream dos = new DataOutputStream(serverSocket.getOutputStream());
            dos.writeInt(pedidoId);

            InputStream is = serverSocket.getInputStream();
            OutputStream os = new FileOutputStream(OUTPUT_DIR + "factura" + pedidoId + ".pdf");

            copy(is,os);
            
            is.close();
            os.close();
            dos.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[512];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            System.out.println(len);
            out.write(buf, 0, len);
        }
    }

}





