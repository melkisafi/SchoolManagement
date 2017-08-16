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

import gestionScolaire.metier.dao.MatiereDao;
import gestionScolaire.metier.model.Matiere;

@Controller
@RequestMapping("/matiere")
public class MatiereController {

	@Autowired
	private MatiereDao matiereDao;

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isConnected(session)) {
			List<Matiere> matieres = matiereDao.findAll();
			model.addAttribute("matieres", matieres);
			return "matiere/list";
		}
		return "redirect:../";
	}

	@RequestMapping("/voir/{id}")
	public String voir(@PathVariable("id") Long id, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isConnected(session)) {
			Matiere m = matiereDao.find(id);
			model.addAttribute("matiere", m);
			return "matiere/voir";
		}
		return "redirect:../list";
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isConnected(session)) {
			model.addAttribute("mode", "add");
			model.addAttribute("matiere", new Matiere());
			return "matiere/edit";
		}
		return "redirect:../";

	}

	@RequestMapping("edit/{idMatiere}")
	public String edit(@PathVariable("idMatiere") Long id, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (VerifAdminUser.isConnected(session)) {
			Matiere m = matiereDao.find(id);
			model.addAttribute("matiere", m);
			model.addAttribute("mode", "edit");
			return "matiere/edit";
		}
		return "redirect:../list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, @ModelAttribute("matiere") @Valid Matiere matiere,
			BindingResult result, RedirectAttributes attr, HttpServletRequest req, Model model) throws ParseException {
		HttpSession session = req.getSession(false);

//		if (result.hasErrors()){return "matiere/edit";}
		
		if (VerifAdminUser.isConnected(session)) {
			if (mode.equals("add")) {
				try {
					if (result.hasErrors()){model.addAttribute("mode", "add");return "matiere/edit";}
					matiereDao.create(matiere);
				} catch (Exception e) {
					attr.addFlashAttribute("nomMat", matiere.getNomMatiere());
					attr.addFlashAttribute("colMat", matiere.getCouleurMatiere());
					attr.addFlashAttribute("typeMess", "danger");
					attr.addFlashAttribute("message",
							"La matière ou la couleur existe déjà. Merci de saisir autre chose");
					return "redirect:/matiere/add/";
				}
			} else {
				try {
					if (result.hasErrors()){
						model.addAttribute("mode", "edit");
						model.addAttribute("idMatiere",matiere.getIdMatiere() );
						return "matiere/edit";}
					matiereDao.update(matiere);
				} catch (Exception e) {
					attr.addFlashAttribute("nomMat", matiere.getNomMatiere());
					attr.addFlashAttribute("colMat", matiere.getCouleurMatiere());
					attr.addFlashAttribute("typeMess", "danger");
					attr.addFlashAttribute("message",
							"La matière ou la couleur existe déjà\n Merci de saisir autre chose");
					return "redirect:/matiere/edit/" + matiere.getIdMatiere();
				}
			}
			attr.addFlashAttribute("typeMess", "success");
			attr.addFlashAttribute("message", "La matiére à bien été édité");
			return "redirect:/matiere/list";
		}
		return "redirect:../";
	}

	@RequestMapping("/delete/{idMatiere}")
	public String delete(@PathVariable("idMatiere") Long id, RedirectAttributes attr, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (VerifAdminUser.isConnected(session)) {
			Matiere m = matiereDao.find(id);
			matiereDao.delete(m);

			attr.addFlashAttribute("typeMess", "success");
			attr.addFlashAttribute("message", "La matiere à bien été supprimé");

			return "redirect:/matiere/list";
		}
		return "redirect:../list";
	}
}
