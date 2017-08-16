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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.MatiereDao;
import gestionScolaire.metier.dao.MatiereSalleDao;
import gestionScolaire.metier.dao.SalleDao;
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

	@RequestMapping("/add")
	public String add(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);

		// if (VerifAdminUser.isAdmin(session)) {
		// salles = salleDao.findAll();
		// model.addAttribute("salles", salles);
		// return "salle/list";
		//
		// } else if (VerifAdminUser.isAutorized(session, (Long)
		// session.getAttribute("idEtab"))) {
		// salles = salleDao.findAllByEtab((Long)
		// session.getAttribute("idEtab"));
		// model.addAttribute("salles", salles);
		// return "salle/list";
		// }
		// return "redirect:../";

		if (VerifAdminUser.isConnected(session)) {
			model.addAttribute("mode", "add");
			model.addAttribute("salle", new Salle());
			model.addAttribute("etab", etabDao.findAll());
			model.addAttribute("mats",matDao.findAll());
			model.addAttribute("SM", new MatiereSalle());
			return "salle/edit";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("mode") String mode, @ModelAttribute("salle") @Valid Salle salle,
			BindingResult result, RedirectAttributes attr, HttpServletRequest req, Model model) throws ParseException {
		HttpSession session = req.getSession(false);
//		if (salle.getEtablissement().getId()!=null){
//			salle.setEtablissement(etabDao.find(salle.getEtablissement().getId()));
//		}else{
//			System.out.println(salle.getEtablissement().getId());
//			System.out.println(salle.getEtablissement().getNom());
//			System.out.println(new Etablissement());
//		}
		
		if (result.hasErrors()) {
			return "salle/edit";
		} else {
			return "redirect:../";
		}
	}

}
