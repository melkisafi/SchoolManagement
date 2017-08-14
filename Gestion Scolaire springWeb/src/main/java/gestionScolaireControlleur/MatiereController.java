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

import gestionScolaire.metier.dao.MatiereDao;
import gestionScolaire.metier.model.Adresse;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Matiere;
import gestionScolaire.metier.model.TypeEtab;

@Controller
@RequestMapping("/matiere")
public class MatiereController {

	@Autowired
	private MatiereDao matiereDao;
	
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
//		if(isAdmin(session)){
			List<Matiere> matieres = matiereDao.findAll();
				
			model.addAttribute("matieres", matieres);
				
			return "matiere/list";	
//		} else return "redirect:/";
		
	
	}
	
	
	@RequestMapping("/voir/{id}")
	public String voir(@PathVariable("id") Long id, Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		//if(isAdmin(session) || isAutorized(session, id)){
			Matiere m = matiereDao.find(id);
			
			
			model.addAttribute("matiere", m);
			
			
			return "matiere/voir";
	//	} else return "redirect:/";
			
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			model.addAttribute("mode", "add");
			model.addAttribute("matiere", new Matiere());

			
			return "matiere/edit";
		}
		return "redirect:/";
	}
	
	@RequestMapping("edit/{idMatiere}")
	public String edit(@PathVariable("idMatiere") Long id, Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			Matiere m = matiereDao.find(id);
			
			model.addAttribute("matiere", m);
			model.addAttribute("mode", "edit");
			
			return "matiere/edit";
		} 
		return "redirect:/";		
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, 
			@ModelAttribute("matiere") Matiere matiere, 
			BindingResult result,
			RedirectAttributes attr) throws ParseException {
		if(mode.equals("add")){
			matiereDao.create(matiere);;
		} else {
			matiereDao.update(matiere);
		}
		
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "La matiére à bien été édité");
		
		return "redirect:/matiere/list";		
	}
	
	@RequestMapping("/delete/{idMatiere}")
	public String delete(@PathVariable("idMatiere") Long id, RedirectAttributes attr){
		Matiere m = matiereDao.find(id);
		matiereDao.delete(m);
		
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "La matiere à bien été supprimé");
		
		return "redirect:/matiere/list";
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

