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

import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.LoginDao;
import gestionScolaire.metier.dao.PersonneDao;
import gestionScolaire.metier.dao.PersonneEtablissementDao;
import gestionScolaire.metier.model.Adresse;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Login;
import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.PersonneEtablissement;
import gestionScolaire.metier.model.StatusEnum;

@Controller
@RequestMapping("/utilisateur")
public class PersonneController {

	@Autowired
	private PersonneDao personneDao;
	@Autowired
	private EtablissementDao etablissementDao;
	@Autowired
	private PersonneEtablissementDao persEtabDao;
	@Autowired
	private LoginDao loginDao;

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isAdmin(session)) {
			List<Personne> users = personneDao.findAll();
			model.addAttribute("users", users);
			return "personne/list";
		}
		return "redirect:/";
	}

	@RequestMapping("/professeur")
	public String professeur(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isAdmin(session)) {
			List<Personne> profs = personneDao.findByStatus(StatusEnum.PROFESSEUR);
			model.addAttribute("profs", profs);
			return "personne/professeur";
		}
		return "redirect:/";
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isAdmin(session)) {
			List<Etablissement> etabs = etablissementDao.findAll();
			model.addAttribute("personne", new Personne());
			model.addAttribute("adresse", new Adresse());
			model.addAttribute("etabs", etabs);
			model.addAttribute("mode", "add");
			return "personne/edit";
		} else
		return "redirect:/";
	}

	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isAdmin(session)) {
			Personne user = personneDao.find(id);
			List<Etablissement> etabs = etablissementDao.findAll();
			Etablissement etab = user.getPersonneEtablissement().get(0).getEtablissement();

			model.addAttribute("personne", user);
			model.addAttribute("adresse", user.getAdresse());
			model.addAttribute("etabs", etabs);
			model.addAttribute("login", user.getLogin());
			model.addAttribute("etablissement", etab);
			model.addAttribute("mode", "edit");

			return "personne/edit";
		} else
		return "redirect:/";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, @RequestParam("etablissement_id") Long etabId,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@ModelAttribute("personne") Personne personne, BindingResult result, RedirectAttributes attr,
			HttpServletRequest req) throws ParseException {
		HttpSession session = req.getSession(false);
		if (VerifAdminUser.isAdmin(session)) {
			if (mode.equals("add")) {
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
			} else {
				Personne p = personneDao.find(personne.getId());
				Login l = loginDao.find(p.getLogin().getId());
				l.setPassword(password);
				l.setUsername(username);
				loginDao.update(l);
				personne.setLogin(l);

				Etablissement e = etablissementDao.find(etabId);
				PersonneEtablissement pe = persEtabDao.find(p.getPersonneEtablissement().get(0).getId());
				pe.setEtablissement(e);
				pe.setPersonne(personne);

				personneDao.update(personne);
				persEtabDao.update(pe);
			}

			attr.addFlashAttribute("typeMess", "success");
			attr.addFlashAttribute("message", "L'utilisateur à bien été édité");

			return "redirect:/utilisateur/list";
		} else
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isAdmin(session)) {
			Personne user = personneDao.find(id);

			personneDao.delete(user);

			attr.addFlashAttribute("typeMess", "success");
			attr.addFlashAttribute("message", "L'utilisateur à bien été supprimé");

			return "redirect:/utilisateur/list";
		} else
		return "redirect:/";
	}

}
