package gestionScolaireControlleur;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(HttpServletRequest req) {
		return req.getSession().getAttribute("userid") != null ? "home" : "redirect:login/login";
	}
}
