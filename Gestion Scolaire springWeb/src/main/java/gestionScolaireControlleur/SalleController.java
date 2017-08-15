package gestionScolaireControlleur;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.SalleDao;
import gestionScolaire.metier.model.Salle;

@Controller
@RequestMapping("/salle")
public class SalleController {

	@Autowired
	private SalleDao salleDao;

	@Autowired
	private EtablissementDao etabDao;

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
	 public String add(HttpServletRequest req, Model model){
	 HttpSession session = req.getSession(false);
	
	 if(VerifAdminUser.isConnected(session)){
	 model.addAttribute("mode", "add");
	 model.addAttribute("salle", new Salle());
	 model.addAttribute("etab", etabDao.findAll());
	 return "salle/edit";
	 }
	 return "redirect:/";
	 }


}
