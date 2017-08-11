package gestionScolaireControlleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gestionScolaire.metier.dao.LoginDao;
import gestionScolaire.metier.model.Login;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginDao loginDao;
	
	@RequestMapping("/login")
	public String signin(Model model, HttpServletRequest r){
		model.addAttribute("userid", r.getAttribute("userid"));
		
		return "login/login";
	}
	
	@RequestMapping(value="/signin", method = RequestMethod.POST)
	public String signin(@RequestParam("username") String username,
		@RequestParam("password") String password, 
		HttpServletRequest req,
		Model model){

		Login login = loginDao.checkLogin(username, password);
		
		if(login != null){
			Long idEtab = login.getPersonne().getPersonneEtablissement().get(0).getEtablissement().getId();
			Long uid = login.getPersonne().getId();
			String role = login.getPersonne().getRole().name();
			
			// reutilite la session existante ou 
			//en cree une si elle n'existe pas
			HttpSession session = req.getSession(true);
			
			session.setAttribute("userid", uid);
			session.setAttribute("idEtab", idEtab);
			session.setAttribute("role", role);
			session.setAttribute("username", login.getUsername());
			session.setAttribute("loginid", login.getId());
			
			session.setMaxInactiveInterval(300); //300 seconde de session
			
			return login.getVersion() == 0 ?  "redirect:changePassword" : "redirect:/";
			
		} else {
			return "redirect:login";
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(session != null){
			session.removeAttribute("userid");
			session.removeAttribute("idEtab");
			session.removeAttribute("role");
		}
		
		return "redirect:login";
	}
	
	@RequestMapping("/changePassword")
	public String edit(HttpServletRequest req, Model model){
		if(req.getSession(false).getAttribute("userid") != null){
			
			Login l = loginDao.find((Long) req.getSession(false).getAttribute("loginid"));
			model.addAttribute("login", l);
			model.addAttribute("mode", "edit");
			
			return "login/changePassword";
		} else return "redirect:login";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, 
			@RequestParam("disabled")int disabled, 
			@ModelAttribute("login") Login login, 
			HttpServletRequest req,
			BindingResult result) {
		
		if (mode.equals("add")) loginDao.create(login);
		else {
			if(disabled == 1) login.setUsername((String) req.getSession(false).getAttribute("username"));
			loginDao.update(login);
		}
		
		return "redirect:login";
	}
}
