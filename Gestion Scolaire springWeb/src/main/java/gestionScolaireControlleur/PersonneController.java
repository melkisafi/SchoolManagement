package gestionScolaireControlleur;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gestionScolaire.metier.dao.PersonneDao;
import gestionScolaire.metier.dao.PersonneEtablissementDao;
import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.LoginDao;
import gestionScolaire.metier.dao.StatusDao;
import gestionScolaire.metier.model.Adresse;
import gestionScolaire.metier.model.Civilite;
import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.PersonneEtablissement;
import gestionScolaire.metier.model.Status;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Login;

@Controller
@RequestMapping("/utilisateur")
public class PersonneController {

	@Autowired
	private PersonneDao personneDao;
	@Autowired
	private StatusDao statusDao;
	@Autowired
	private EtablissementDao etablissementDao;
	@Autowired
	private PersonneEtablissementDao persEtabDao;
	@Autowired
	private LoginDao loginDao;
	
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			List<Personne> users = personneDao.findAll();
			
			model.addAttribute("users", users);
			
			return "personne/list";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			List<Etablissement> etabs = etablissementDao.findAll();
			
			
			model.addAttribute("personne", new Personne());
			model.addAttribute("adresse", new Adresse());
			model.addAttribute("status", statusDao.findAll());
			model.addAttribute("etabs", etabs);
			model.addAttribute("mode", "add");
			
			return "personne/edit";
		}
		return "redirect:/";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			Personne user = personneDao.find(id);
			List<Etablissement> etabs = etablissementDao.findAll();
			Etablissement etab = user.getPersonneEtablissement().get(0).getEtablissement();
					
			model.addAttribute("personne", user);
			model.addAttribute("adresse", user.getAdresse());
			model.addAttribute("stat", user.getStatus());
			model.addAttribute("etabs", etabs);
			model.addAttribute("etablissement", etab);
			model.addAttribute("status", statusDao.findAll());
			model.addAttribute("civilites", Civilite.values());
			model.addAttribute("mode", "edit");
			
			return "personne/edit";
		} 
		return "redirect:/";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, 
			@RequestParam("status_id") Long statusId,
			@RequestParam("etablissement_id") Long etabId,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@ModelAttribute("personne") Personne personne, 
			BindingResult result,
			RedirectAttributes attr) throws ParseException {
		
		/*0if(mode.equals("add")){
		
		} else {
			personneDao.update(personne);
		}*/
		if(mode.equals("add")){
		
		Status s = statusDao.find(statusId);
		List<Personne> personnes = new ArrayList<Personne>();
		personnes.add(personne);
		s.setPersonne(personnes);
		statusDao.update(s);
		personne.setStatus(s);
		
		Login l = new Login();
		l.setPassword(password);
		l.setUsername(username);
		loginDao.create(l);
		personne.setLogin(l);
		
		Etablissement e = etablissementDao.find(etabId);
		PersonneEtablissement pe = new PersonneEtablissement();
		pe.setEtablissement(e);
		pe.setPersonne(personne);
	
		personneDao.create(personne);	
		persEtabDao.create(pe);
		}
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "L'utilisateur à bien été édité");
		
		return "redirect:/utilisateur/list";		
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr){
		Personne user = personneDao.find(id);
		
		personneDao.delete(user);
		
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "L'utilisateur à bien été supprimé");
		
		return "redirect:/utilisateur/list";
	}
	
	public boolean isAdmin(HttpSession s){
		if(s != null){
			return s.getAttribute("role").equals("ADMIN") ? true : false;
		} else return false;
	}
}
