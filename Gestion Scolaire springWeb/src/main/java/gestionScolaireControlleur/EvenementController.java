package gestionScolaireControlleur;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gestionScolaire.metier.dao.ClasseDao;
import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.EvenementDao;
import gestionScolaire.metier.dao.MatiereDao;
import gestionScolaire.metier.dao.PersonneDao;
import gestionScolaire.metier.dao.SalleDao;
import gestionScolaire.metier.model.Classe;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Evenement;
import gestionScolaire.metier.model.Matiere;
import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.Salle;

@Controller
@RequestMapping("/evenement")
public class EvenementController {
	@Autowired
	private EvenementDao eventDao;
	@Autowired
	private SalleDao salleDao;
	@Autowired
	private ClasseDao classeDao;
	@Autowired
	private PersonneDao personneDao;
	@Autowired
	private MatiereDao matiereDao;
	@Autowired
	private EtablissementDao etablissementDao;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	@ResponseBody
	public String test(@RequestParam("personne_id") Long profId,
			@RequestParam("salle_id") Long salleId,
			@RequestParam("matiere_id") Long matiereId,
			@RequestParam("classe_id") Long classeId,
			@RequestParam("etab_id") Long etabId,
			@RequestParam("dateD") Date dateD,
			@RequestParam("dateF") Date dateF){
		
		Evenement ev = new Evenement();
		Salle s = salleDao.find(salleId);
		Personne p = personneDao.find(profId);
		Matiere m = matiereDao.find(matiereId);
		Classe c = classeDao.find(classeId);
		Etablissement et = etablissementDao.find(etabId);
		
		ev.setClasse(c);
		ev.setMatiere(m);
		ev.setEtablissement(et);
		ev.setSalle(s);
		ev.setPersonne(p);
		ev.setDate(dateD);
		ev.setHeureDebut(dateD);
		ev.setHeureFin(dateF);
		
		eventDao.create(ev);
		
		return "ok";
	}
}
