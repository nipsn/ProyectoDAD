package es.urjc.computadores.chat;

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

import es.urjc.computadores.chat.Chat;
import es.urjc.computadores.mensaje.Mensaje;
import es.urjc.computadores.usuario.Usuario;
import es.urjc.computadores.usuario.UsuarioRepository;
import es.urjc.computadores.chat.ChatRepository;

@Controller
public class ChatController implements CommandLineRunner{

	@Autowired
	private ChatRepository chatRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
	}
	
	@PostConstruct
	public void init() {
		
	}
	

	@PostMapping("/inputchat")
	public String insertarChat(Model model, @RequestParam String user1, String user2) {
		Usuario u1 = usuarioRepo.findByNombreInterno(user1);
		Usuario u2 = usuarioRepo.findByNombreInterno(user2);
		Chat chat = new Chat(u1,u2);
		chatRepo.save(chat);
		return "greeting_template";
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
		model.addAttribute("userid", elegido.getVendedor().getId());
		
		return "chat";
		
	}

}
