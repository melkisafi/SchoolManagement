package gestionScolaireControlleur;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import gestionScolaire.metier.dao.ClasseDao;
import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.PersonneDao;
import gestionScolaire.metier.model.Adresse;
import gestionScolaire.metier.model.Classe;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.StatusEnum;
import gestionScolaire.metier.model.TypeEtab;

@Controller
@RequestMapping("/etablissement")
public class EtablissementController {
	
	@Autowired
	private EtablissementDao etabDao;
	@Autowired
	private PersonneDao personneDao;
	@Autowired
	private ClasseDao classeDao;
	
	
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			List<Etablissement> etabs = etabDao.findAll();
				
			model.addAttribute("etabs", etabs);
				
			return "etablissement/list";	
		} else return "redirect:/";
		
	}
	
	@RequestMapping("/voir/{id}")
	public String voir(@PathVariable("id") Long id, Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session) || isAutorized(session, id)){
			Etablissement e = etabDao.find(id);
			Adresse adr = e.getAdr();
			List <Personne> p = personneDao.findProfByEtab(StatusEnum.PROFESSEUR, id);
			List<Classe> c = classeDao.findClasseByEtab(id);
			int nbprof = p.size() > 0 ? p.size() : 0;
			int nbclasse = c == null ? 0: c.size();
			
			model.addAttribute("nbProf",nbprof);
			model.addAttribute("nbClasse", nbclasse);
			model.addAttribute("profs", p);
			model.addAttribute("etab", e);
			model.addAttribute("adr", adr);
			model.addAttribute("classes", c);
			
			return "etablissement/voir";
		} else return "redirect:/";
			
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			model.addAttribute("mode", "add");
			model.addAttribute("etablissement", new Etablissement());
			model.addAttribute("adresse", new Adresse());
			model.addAttribute("type", TypeEtab.values());
			
			return "etablissement/edit";
		}
		return "redirect:/";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			Etablissement e = etabDao.find(id);
			
			model.addAttribute("etablissement", e);
			model.addAttribute("adresse", e.getAdr());
			model.addAttribute("type", TypeEtab.values());
			model.addAttribute("mode", "edit");
			
			return "etablissement/edit";
		} 
		return "redirect:/";		
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, 
			@ModelAttribute("etablissement") @Valid Etablissement etab, 
			BindingResult result,
			RedirectAttributes attr) throws ParseException {
		if (result.hasErrors()){return "etablissement/edit";}
		if(mode.equals("add")){
			etabDao.create(etab);
		} else {
			etabDao.update(etab);
		}
		
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "L'etablissement à bien été édité");
		
		return "redirect:/etablissement/list";		
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr){
		Etablissement e = etabDao.find(id);
		etabDao.delete(e);
		
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "L'etablissement à bien été supprimé");
		
		return "redirect:/etablissement/list";
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
