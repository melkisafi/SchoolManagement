package gestionScolaireControlleur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	
	@RequestMapping(value="/getEvents", method=RequestMethod.POST)
	@ResponseBody
	public String getEvents(@RequestParam("classe_id") Long classeId) {
		List<Evenement> events = eventDao.findAllEvenementByClasse(classeId);
		ArrayList arr = new ArrayList();
		HashMap datas = new HashMap();
		
		for(Evenement e : events){
			datas.put("start", convertDateHourToString(e.getDate(), e.getHeureDebut()));
			datas.put("end", convertDateHourToString(e.getDate(), e.getHeureFin()));
			
			datas.put("evenementid", e.getId());
			datas.put("prof", e.getPersonne().getNom());
			datas.put("matiere", e.getMatiere().getNomMatiere());
		
			arr.add(datas);
			datas = new HashMap();
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();	
		
		String json = gson.toJson(arr);
		return json;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	@ResponseBody
	public String add(@RequestParam("personne_id") Long profId,
			@RequestParam("salle_id") Long salleId,
			@RequestParam("matiere_id") Long matiereId,
			@RequestParam("classe_id") Long classeId,
			@RequestParam("etab_id") Long etabId,
			@RequestParam("dateD") String dateD,
			@RequestParam("dateF") String dateF) throws ParseException{
		Date dd = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateD);	
		Date df = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateF);	
		
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
		ev.setDate(dd);
		ev.setHeureDebut(dd);
		ev.setHeureFin(df);
		
		eventDao.create(ev);
		
		return "ok";
	}
	
	public String convertDateHourToString(Date d, Date h){
		Calendar c = GregorianCalendar.getInstance();
		SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat  hf = new SimpleDateFormat("HH:mm");
		String dateToString;
				
		c.setTime(d);
		dateToString = df.format(c.getTime());
		c.setTime(h);
		dateToString += " "+hf.format(c.getTime());
		
		return dateToString;
	}
}
