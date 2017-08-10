package gestionScolaireControlleur;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloCtrl {
	
	@RequestMapping("/")
	public String home(HttpServletRequest req) {
		if(req.getSession().getAttribute("user") != null)
			return "accueil";
		else
		return "redirect:login/login";
	}

}
