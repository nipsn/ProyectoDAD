package es.urjc.computadores;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
	@Autowired
	private MensajeRepository mensajeRepo;
	private ChatRepository chatRepo;
	
	@PostConstruct
	public void init() {
		
	}
	
	
	@GetMapping("/greeting")
	public String greeting(Model model) { 
		model.addAttribute("name", "Mundo");
		return "greeting_template";
	}
	
}
