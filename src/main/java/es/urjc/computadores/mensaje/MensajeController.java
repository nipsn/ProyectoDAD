package es.urjc.computadores.mensaje;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.computadores.chat.*;

@Controller
public class MensajeController implements CommandLineRunner {
	
	@Autowired 
	private ChatRepository chatRepo;
	
	@Override
	public void run(String... args) throws Exception {

	}
	
	@PostConstruct
	public void init() {
		
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
}
