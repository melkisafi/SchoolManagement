package gestionScolaireControlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gestionScolaire.metier.dao.SalleDao;
import gestionScolaire.metier.model.Adresse;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Salle;
import gestionScolaire.metier.model.TypeEtab;

@Controller
@RequestMapping("/salle")
public class SalleController {

	@Autowired
	private SalleDao salleDao;

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (isAdmin(session)) {
			List<Salle> salles = salleDao.findAll();

			model.addAttribute("salles", salles);

			return "salle/list";
		} else if (isAutorized(session, session.getId())){
			
		}else
			return "redirect:/";
	}

	
	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			model.addAttribute("mode", "add");
			model.addAttribute("etablissement", new Salle());
			model.addAttribute("adresse", new Adresse());
			model.addAttribute("type", TypeEtab.values());
			
			return "etablissement/edit";
		}
		return "redirect:/";
	}
	
	public boolean isAdmin(HttpSession s){
		if(s != null){
			return s.getAttribute("role").equals("ADMIN") ? true : false;
		} else return false;
	}
	
	public boolean isAutorized(HttpSession s, Long id){
		if(s != null){
			if(s.getAttribute("role").equals("USER") && s.getAttribute("idEtab") == id){
				return true;
			} else return false;
		} else return false;
	}
}
