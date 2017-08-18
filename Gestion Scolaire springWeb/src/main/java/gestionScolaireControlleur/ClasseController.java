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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gestionScolaire.metier.dao.ClasseDao;
import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.MatiereDao;
import gestionScolaire.metier.dao.PersonneClasseDao;
import gestionScolaire.metier.dao.PersonneDao;
import gestionScolaire.metier.dao.SalleDao;
import gestionScolaire.metier.model.Classe;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Matiere;
import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.PersonneClasse;
import gestionScolaire.metier.model.Salle;
import gestionScolaire.metier.model.StatusEnum;

@Controller
@RequestMapping("/classe")
public class ClasseController {

	@Autowired
	private ClasseDao classeDao;
	@Autowired
	private EtablissementDao etabDao;
	@Autowired
	private PersonneDao personneDao;
	@Autowired
	private PersonneClasseDao personneClasseDao;
	@Autowired
	private MatiereDao matiereDao;
	@Autowired
	private SalleDao salleDao;

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isConnected(session)) {
			Long idEtab = Long.parseLong(session.getAttribute("idEtab").toString());
			List<Classe> classe = VerifAdminUser.isAdmin(session) ? classeDao.findAll() : classeDao.findClasseByEtab(idEtab);
			model.addAttribute("classe", classe);
			return "classe/list";
		}
		return "redirect:../";
	}

	@RequestMapping("/voir/{id}")
	public String voir(@PathVariable("id") Long id, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
	
		if (VerifAdminUser.isConnected(session)) {
			Classe c = classeDao.find(id);
			List<Matiere> m = matiereDao.findAll();
			Etablissement e = etabDao.find(c.getEtablissement().getId());
			Personne p = c.getPersonneClasses().size() > 0 ? c.getPersonneClasses().get(0).getPersonne() : null;
			List<Personne> profs = VerifAdminUser.isAdmin(session) ? getProfsByEtab(c.getEtablissement().getId())
					: getProfsByEtab((Long) session.getAttribute("idEtab"));
			List<Salle> s = VerifAdminUser.isAdmin(session) ? salleDao.findAllByEtab(c.getEtablissement().getId())
					: salleDao.findAllByEtab((Long) session.getAttribute("idEtab"));

			model.addAttribute("classe", c);
			model.addAttribute("profs", profs);
			model.addAttribute("etab", e);
			model.addAttribute("matieres", m);
			model.addAttribute("prof", p);
			model.addAttribute("salles", s);

			return "classe/voir";
		}
		return "redirect:../";
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isConnected(session)) {
			int isAdmin = VerifAdminUser.isAdmin(session) ? 1 : 0;
			List<Etablissement> e = etabDao.findAll();
			List<Personne> profs = VerifAdminUser.isAdmin(session) ? null : getProfsByEtab((Long) session.getAttribute("idEtab"));

			model.addAttribute("mode", "add");
			model.addAttribute("isAdmin", isAdmin);
			model.addAttribute("etabs", e);
			model.addAttribute("profs", profs);
			model.addAttribute("classe", new Classe());

			return "classe/edit";
		}
		return "redirect:../";
	}

	@RequestMapping(value = "/getEtabProf", method = RequestMethod.GET)
	@ResponseBody
	public String getEtabProf(@RequestParam("idetab") Long idEtab) {
		String datas = "";
		List<Personne> profs = getProfsByEtab(idEtab);

		for (Personne p : profs) {
			if (!personneDao.isPrincipal(p.getId()))
				datas += "<option class='opts-pp'  value=" + p.getId() + ">" + p.getNom() + "</option>";
		}

		return datas;
	}

	public List<Personne> getProfsByEtab(Long idEtab) {
		List<Personne> profs = personneDao.findProfByEtab(StatusEnum.PROFESSEUR, idEtab);

		return profs;
	}

	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isConnected(session)) {
			int isAdmin = VerifAdminUser.isAdmin(session) ? 1 : 0;
			List<Etablissement> etabs = etabDao.findAll();
			Classe c = classeDao.find(id);
			Etablissement e = etabDao.find(c.getEtablissement().getId());
			PersonneClasse pp = personneClasseDao.findProfPrincipal(id);
			List<Personne> profs = VerifAdminUser.isAdmin(session) ? getProfsByEtab(c.getEtablissement().getId())
					: getProfsByEtab((Long) session.getAttribute("idEtab"));

			model.addAttribute("classe", c);
			model.addAttribute("mode", "edit");
			model.addAttribute("isAdmin", isAdmin);
			model.addAttribute("etabs", etabs);
			model.addAttribute("etab", e);
			model.addAttribute("profs", profs);
			model.addAttribute("pp", pp.getPersonne());

			return "classe/edit";
		}
		return "redirect:../";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, @RequestParam("etab_id") Long etabId,
			@RequestParam("personne_id") Long profId, @ModelAttribute("classe") Classe classe, BindingResult result,
			RedirectAttributes attr,HttpServletRequest req) throws ParseException {

		Personne p = personneDao.find(profId);
		Etablissement e = etabDao.find(etabId);

		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isConnected(session)) {
		if (mode.equals("add")) {
			PersonneClasse pc = new PersonneClasse();

			pc.setPrincipal(true);
			pc.setClasse(classe);
			pc.setPersonne(p);
			classe.setEtablissement(e);

			classeDao.create(classe);
			personneClasseDao.create(pc);
		} else {
			Classe c = classeDao.find(classe.getId());
			PersonneClasse pc = personneClasseDao.find(c.getPersonneClasses().get(0).getId());
			pc.setClasse(classe);
			pc.setPersonne(p);
			classe.setEtablissement(e);
			classeDao.update(classe);
			personneClasseDao.update(pc);
		}

		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "La classe à bien été édité");

		return "redirect:/classe/list";
		}
		return "redirect:../";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (VerifAdminUser.isConnected(session)) {
		
		Classe c = classeDao.find(id);
		classeDao.delete(c);

		attr.addFlashAttribute("typeMess", "success");
		attr.addFlashAttribute("message", "La classe à bien été supprimé");

		return "redirect:/classe/list";
		}
		return "redirect:../list";
	}
}
