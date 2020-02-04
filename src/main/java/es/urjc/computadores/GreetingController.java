package es.urjc.computadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
	@Autowired
	private Usuario u1;
	@GetMapping("/greeting")
	public String greeting(Model model) { 
		model.addAttribute("name", "Mundo");
		model.addAttribute("test", u1.getNombre());
		return "greeting_template";
	}
	
}
