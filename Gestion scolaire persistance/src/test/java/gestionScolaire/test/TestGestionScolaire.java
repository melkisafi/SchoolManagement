package gestionScolaire.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gestionScolaire.metier.dao.ClasseDao;
import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.EvenementDao;
import gestionScolaire.metier.dao.LoginDao;
import gestionScolaire.metier.dao.MatiereDao;
import gestionScolaire.metier.dao.MatiereSalleDao;
import gestionScolaire.metier.dao.PersonneClasseDao;
import gestionScolaire.metier.dao.PersonneDao;
import gestionScolaire.metier.dao.PersonneEtablissementDao;
import gestionScolaire.metier.dao.PersonneMatiereDao;
import gestionScolaire.metier.dao.SalleClasseDao;
import gestionScolaire.metier.dao.SalleDao;
import gestionScolaire.metier.dao.StatusDao;
import gestionScolaire.metier.model.Adresse;
import gestionScolaire.metier.model.Civilite;
import gestionScolaire.metier.model.Classe;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Evenement;
import gestionScolaire.metier.model.Login;
import gestionScolaire.metier.model.Matiere;
import gestionScolaire.metier.model.MatiereSalle;
import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.PersonneClasse;
import gestionScolaire.metier.model.PersonneEtablissement;
import gestionScolaire.metier.model.PersonneMatiere;
import gestionScolaire.metier.model.Role;
import gestionScolaire.metier.model.Salle;
import gestionScolaire.metier.model.SalleClasse;
import gestionScolaire.metier.model.Status;
import gestionScolaire.metier.model.TypeEtab;
import junit.framework.Assert;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGestionScolaire {
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private PersonneDao personneDao;
	@Autowired
	private StatusDao statusDao;
	@Autowired
	private EtablissementDao etabDao;
	@Autowired
	private PersonneEtablissementDao persEtabDao;
	@Autowired
	private SalleDao salleDao;
	@Autowired
	private ClasseDao classeDao;
	@Autowired
	private SalleClasseDao salleclasseDao;
	@Autowired
	private MatiereSalleDao matieresalleDao;
	@Autowired
	private MatiereDao matiereDao;
	@Autowired
	private PersonneMatiereDao personnematiereDao;
	@Autowired
	private PersonneClasseDao personneclasseDao;
	
	@Autowired
	private EvenementDao evenementDao;
	
	private Civilite civ;
	private Role role;
	private TypeEtab type;
	
	@Test
	public void apopulate(){
		
		/*////////////////////////////////////////////////////////////////*/
		/*///////////////////////// CREATE ///////////////////////////////*/
		/*////////////////////////////////////////////////////////////////*/
		
		/*///////// AdressePErsonne //////////*/
		Adresse adrPers = new Adresse();
		adrPers.setAdresse("29 rue des yebles");
		adrPers.setCodepostal("77210");
		adrPers.setPays("France");
		adrPers.setVille("Avon");
		
		/*///////// Login //////////*/
		Login login = new Login();
		login.setUsername("admin");
		login.setPassword("admin");
		
		/*///////// Status //////////*/
		Status status = new Status();
		status.setNom("Directeur");
		
		/*///////// Personne //////////*/
		Personne admin = new Personne();
		admin.setAdresse(adrPers);
		admin.setCivilite(civ.MR);
		Calendar c = GregorianCalendar.getInstance();
		c.set(1988, 19, 8);
		Date datenaiss = c.getTime();
		admin.setDatenaiss(datenaiss);
		admin.setLogin(login);
		admin.setNom("Maquaire");
		admin.setPrenom("Jérémy");
		admin.setRole(role.ADMIN);
		admin.setStatus(status);
	
		loginDao.create(login);
		statusDao.create(status);
		personneDao.create(admin);
		
		/*///////// Etablissement //////////*/
		Adresse adrEtab = new Adresse();
		adrEtab.setAdresse("6 rue rougemont");
		adrEtab.setCodepostal("75009");
		adrEtab.setPays("France");
		adrEtab.setVille("Paris");
		
		Etablissement etab = new Etablissement();
		etab.setNom("AJC");
		etab.setTel("0782546590");
		etab.setType(type.LYCEE);
		etab.setAdr(adrEtab);
		
		etabDao.create(etab);
		
		/*///////// Etablissement Personne //////////*/
		PersonneEtablissement pe = new PersonneEtablissement();
		pe.setPersonne(admin);
		pe.setEtablissement(etab);
		
		persEtabDao.create(pe);
		
		/*///////// Classe //////////*/
		Classe classe = new Classe();
		classe.setNom("AJC");
	
		classeDao.create(classe);
		
		/*///////// salle //////////*/
		Salle salle = new Salle("toto",15);//création objet java
		salleDao.create(salle);//enregistrement ds la DB
		
		Salle salleFind= salleDao.find(salle.getId());
		Assert.assertEquals("toto", salleFind.getNom());
		Assert.assertEquals(15, salleFind.getCapacite());
	
		
		
		
		/*///////// salleclasse//////////*/
		SalleClasse salleclasse = new SalleClasse();
		salleclasse.setClasse(classe);
		salleclasse.setSalle(salle);

		salleclasseDao.create(salleclasse);
		
		
		
		/*///////////////MATIERE//////////////////*/
		
					//Insertion
		Matiere matiere = new Matiere("Java", "Rouge");//création objet java
		matiereDao.create(matiere);//enregistrement ds la DB
		
		Matiere javaFind = matiereDao.find(matiere.getIdMatiere());//récupérer l'enregistrement de la DB
		Assert.assertEquals("Java", javaFind.getNomMatiere());
		Assert.assertEquals("Rouge", javaFind.getCouleurMatiere());
		
		/*///////// matieresalle//////////*/
		MatiereSalle matieresalle = new MatiereSalle();
		matieresalle.setSalle(salle);
		matieresalle.setMatiere(matiere);
		
		matieresalleDao.create(matieresalle);
		
		/*///////// personnematiere//////////*/
		PersonneMatiere personnematiere = new PersonneMatiere();
		personnematiere.setPersonne(admin);
		personnematiere.setMatiere(matiere);
		
		personnematiereDao.create(personnematiere);
		
		/*///////// personneclasse//////////*/
		PersonneClasse personneclasse = new PersonneClasse();
		personneclasse.setPersonne(admin);
		personneclasse.setClasse(classe);
		personneclasse.setPrincipal(true);
		
		personneclasseDao.create(personneclasse);
		
		
		/*///////// evenement//////////*/
		Evenement evenement = new Evenement(classe, matiere, salle, admin, etab);
		evenementDao.create(evenement);
		
	}
	

	}
