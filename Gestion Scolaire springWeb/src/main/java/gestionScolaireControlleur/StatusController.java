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

import gestionScolaire.metier.dao.StatusDao;
import gestionScolaire.metier.dao.PersonneDao;
import gestionScolaire.metier.model.Status;
import gestionScolaire.metier.model.Personne;

@Controller
@RequestMapping("/status")
public class StatusController {
	
	@Autowired
	private StatusDao statusDao;
	@Autowired
	private PersonneDao persDao;
	
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			List<Status> status = statusDao.findAll();
			
			model.addAttribute("status", status);
			
			return "status/list";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			model.addAttribute("mode", "add");
			model.addAttribute("status", new Status());
			
			return "status/edit";
		}
		return "redirect:/";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest req){
		HttpSession session = req.getSession(false);
		
		if(isAdmin(session)){
			Status status = statusDao.find(id);
			
			model.addAttribute("status", status);
			model.addAttribute("mode", "edit");
			
			return "status/edit";
		} 
		return "redirect:/";		
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, 
			@ModelAttribute("etablissement") Status status, 
			BindingResult result,
			RedirectAttributes attr) throws ParseException {
		if(mode.equals("add")){
			statusDao.create(status);
		} else {
			statusDao.update(status);
		}
		
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "Le status à bien été édité");
		
		return "redirect:/status/list";		
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr){
		Status status = statusDao.find(id);
		
		for(Personne p : status.getPersonne()){
			p.setStatus(null);
			persDao.update(p);
		}
		
		statusDao.delete(status);
		
		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "Le status à bien été supprimé");
		
		return "redirect:/status/list";
	}
	
	public boolean isAdmin(HttpSession s){
		if(s != null){
			return s.getAttribute("role").equals("ADMIN") ? true : false;
		} else return false;
	}
}
