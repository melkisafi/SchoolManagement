package gestionScolaireControlleur;

import java.text.ParseException;
import java.util.ArrayList;
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

import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.MatiereDao;
import gestionScolaire.metier.dao.MatiereSalleDao;
import gestionScolaire.metier.dao.SalleDao;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Matiere;
import gestionScolaire.metier.model.MatiereSalle;
import gestionScolaire.metier.model.Salle;

@Controller
@RequestMapping("/salle")
public class SalleController {

	@Autowired
	private SalleDao salleDao;

	@Autowired
	private EtablissementDao etabDao;
	
	@Autowired
	private MatiereSalleDao msDao;
	
	@Autowired
	private MatiereDao matDao;
	

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		List<Salle> salles = new ArrayList<Salle>();

		if (VerifAdminUser.isAdmin(session)) {
			salles = salleDao.findAll();
			model.addAttribute("salles", salles);
			return "salle/list";

		} else if (VerifAdminUser.isAutorized(session, (Long) session.getAttribute("idEtab"))) {
			salles = salleDao.findAllByEtab((Long) session.getAttribute("idEtab"));
			model.addAttribute("salles", salles);
			return "salle/list";
		}
		return "redirect:../";

	}

	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		int isAdmin = VerifAdminUser.isAdmin(session) ? 1 : 0;
//		List<Etablissement>etabs =  etabDao.findAll() ;
		Long etabId= (Long) session.getAttribute("idEtab");

		if (VerifAdminUser.isConnected(session)) {
			Salle s = salleDao.find(id);
			model.addAttribute("salle", s);
			model.addAttribute("mode", "edit");
			model.addAttribute("isAdmin", isAdmin);
			model.addAttribute("etab", etabDao.findAll());
			model.addAttribute("etabId",etabId );
			
			return "salle/edit";
		}
		return "redirect:../list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		int isAdmin = VerifAdminUser.isAdmin(session) ? 1 : 0;
		Long etabId= (Long) session.getAttribute("idEtab");
		
		if (VerifAdminUser.isConnected(session)) {
			model.addAttribute("mode", "add");
			model.addAttribute("isAdmin", isAdmin);
			model.addAttribute("salle", new Salle());
			model.addAttribute("etab", etabDao.findAll());
			model.addAttribute("etabId",etabId );
			return "salle/edit";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, @ModelAttribute("salle") @Valid Salle salle,
			BindingResult result, RedirectAttributes attr, HttpServletRequest req, Model model) throws ParseException {
		HttpSession session = req.getSession(false);
		
		if (VerifAdminUser.isConnected(session)) {
			if (mode.equals("add")) {
				try {
					if (result.hasErrors()){model.addAttribute("mode", "add");return "salle/edit";}
					salleDao.create(salle);
				} catch (Exception e) {
					attr.addFlashAttribute("nomSal", salle.getNom());
					attr.addFlashAttribute("capSal", salle.getCapacite());
					attr.addFlashAttribute("typeMess", "danger");
					attr.addFlashAttribute("message","Merci de sélectionner l'établissement");
					return "redirect:/salle/add/";
				}
			} else {
				try {
					if (result.hasErrors()){
						model.addAttribute("mode", "edit");
						model.addAttribute("id",salle.getId());
						return "salle/edit";}
					salleDao.update(salle);
				} catch (Exception e) {
					attr.addFlashAttribute("nomSal",  salle.getNom());
					attr.addFlashAttribute("capSal", salle.getCapacite());
					attr.addFlashAttribute("typeMess", "danger");
					attr.addFlashAttribute("message","Le nom de la salle pour cet établissement existe déjà");
					return "redirect:/salle/edit/" + salle.getId();
				}
			}
			attr.addFlashAttribute("typeMess", "success");
			attr.addFlashAttribute("message", "La salle à bien été éditée");
			return "redirect:/salle/list";
		}
		return "redirect:../";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (VerifAdminUser.isConnected(session)) {
			Salle s = salleDao.find(id);
			salleDao.delete(s);

			attr.addFlashAttribute("typeMess", "success");
			attr.addFlashAttribute("message", "La matiere à bien été supprimé");

			return "redirect:/salle/list";
		}
		return "redirect:../list";
	}
	
}
