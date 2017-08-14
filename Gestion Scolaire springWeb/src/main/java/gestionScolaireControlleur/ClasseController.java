package gestionScolaireControlleur;

import java.text.ParseException;
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

import gestionScolaire.metier.dao.ClasseDao;
import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.PersonneClasseDao;
import gestionScolaire.metier.model.Classe;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Personne;

@Controller
@RequestMapping("/classe")
public class ClasseController {

	@Autowired
	private ClasseDao classeDao;
	@Autowired
	private EtablissementDao etabDao;
	
	
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			List<Classe> classe = classeDao.findAll();
		
			model.addAttribute("classe", classe);
				
			return "classe/list";	
		} else return "redirect:/";
	}
	
	@RequestMapping("/voir/{id}")
	public String voir(@PathVariable("id") Long id, Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		//if(isAdmin(session) || isAutorized(session, id)){
			Classe c = classeDao.find(id);
			
			
			model.addAttribute("classe", c);
			
			
			return "classe/voir";
	//	} else return "redirect:/";
			
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model){
		HttpSession session = req.getSession(false);
		int isAdmin = isAdmin(session) ? 1 : 0;
		List <Etablissement> e = etabDao.findAll();
			
		
		model.addAttribute("mode", "add");
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("etabs", e);
		model.addAttribute("classe", new Classe());
			
		return "classe/edit";		
	}
	
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			Classe c = classeDao.find(id);
			
			model.addAttribute("classe", c);
			model.addAttribute("mode", "edit");
			
			return "classe/edit";
		} 
		return "redirect:/";		
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, 
			@ModelAttribute("classe") Classe classe, 
			BindingResult result,
			RedirectAttributes attr) throws ParseException {
		if(mode.equals("add")){
			classeDao.create(classe);;
		} else {
			classeDao.update(classe);
		}
		
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "La classe à bien été édité");
		
		return "redirect:/classe/list";		
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr){
		Classe c = classeDao.find(id);
		classeDao.delete(c);
		
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "La classe à bien été supprimé");
		
		return "redirect:/classe/list";
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
