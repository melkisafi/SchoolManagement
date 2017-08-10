package gestionScolaireControlleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloCtrl {
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/accueil";
	}
	
	@RequestMapping("/accueil")
	public String bonjour() {
		return "accueil";
	}

}
