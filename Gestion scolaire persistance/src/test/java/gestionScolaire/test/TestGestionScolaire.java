package gestionScolaire.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Assert;
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
import gestionScolaire.metier.model.StatusEnum;
import gestionScolaire.metier.model.TypeEtab;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGestionScolaire {
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private PersonneDao personneDao;
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
		Calendar c = GregorianCalendar.getInstance();
		
		Adresse adrPers = new Adresse("29 rue des yebles", "77210", "Avon", "France"); 
		c.set(1988, 19, 8);
		Date datenaiss = c.getTime();
		
		Adresse adrPersMaj = new Adresse("26 Rue de Stalingrad", "68100", "Mulhouse", "France"); 
		c.set(1989, 04, 7);
		Date bDay = c.getTime();
		
		Adresse adrEtab = new Adresse("6 rue rougemont", "75009", "Paris", "France");
		Adresse adrEtabMaJ = new Adresse("11 Rue Alferd Werner","68093","Mulhouse","France");
		
		c.set(2014, Calendar.APRIL, 18, 15, 20, 00); 
		c.set(Calendar.MILLISECOND, 0);
		Date dateDebutEvenement = c.getTime();
		
		c.set(2015, Calendar.MAY, 4, 15, 35, 00);
		c.set(Calendar.MILLISECOND, 0);
		Date dateMiEvenement = c.getTime();
		
		c.set(2017, Calendar.AUGUST, 12, 18, 35, 00);
		c.set(Calendar.MILLISECOND, 0);
		Date dateFinEvenement = c.getTime();
		
		
		/*///////// LOGIN //////////*/
		Login login = new Login("admin","admin");
		loginDao.create(login);
		Login loginMaj = new Login("user","user");
		loginDao.create(loginMaj);
		Login loginFind = loginDao.find(loginMaj.getId());
		Assert.assertEquals(loginFind.getUsername(),loginMaj.getUsername());
		Assert.assertEquals(loginFind.getPassword(),loginMaj.getPassword());
		
		/*///////// PERSONNE //////////*/
		Personne admin = new Personne(civ.MR, role.ADMIN, StatusEnum.PROFESSEUR, login, "Maquaire", "Jérémy", datenaiss, adrPers);
		personneDao.create(admin);
		
		Personne qui = new Personne(civ.MME, role.USER,  StatusEnum.CHOMEUR,loginMaj, "FERRY", "Joanne", bDay, adrPersMaj);
		personneDao.create(qui);
		Personne personneFind=personneDao.find(qui.getId());
		Assert.assertEquals(personneFind.getCivilite(),qui.getCivilite());
		Assert.assertEquals(personneFind.getRole(),qui.getRole());
		Assert.assertEquals(personneFind.getLogin().getUsername(),qui.getLogin().getUsername());
		Assert.assertEquals(personneFind.getLogin().getPassword(),qui.getLogin().getPassword());
		Assert.assertEquals(personneFind.getStatusEnum(),qui.getStatusEnum());
		Assert.assertEquals(personneFind.getNom(),qui.getNom());
		Assert.assertEquals(personneFind.getPrenom(),qui.getPrenom());
		Assert.assertEquals(personneFind.getDatenaiss().getDate(),qui.getDatenaiss().getDate());
		Assert.assertEquals(personneFind.getAdresse().getAdresse(),qui.getAdresse().getAdresse());
		Assert.assertEquals(personneFind.getAdresse().getCodepostal(),qui.getAdresse().getCodepostal());
		Assert.assertEquals(personneFind.getAdresse().getVille(),qui.getAdresse().getVille());
		Assert.assertEquals(personneFind.getAdresse().getPays(),qui.getAdresse().getPays());
		
		/*///////// ETABLISSEMENT //////////*/
		Etablissement etab = new Etablissement("AJC", type.LYCEE, "0782546590", adrEtab);
		etabDao.create(etab);
		Etablissement etabMaJ = new Etablissement("ENSISA",type.COLLEGE,"0346106767",adrEtabMaJ);
		etabDao.create(etabMaJ);
		Etablissement etablissementFind=etabDao.find(etabMaJ.getId());
		Assert.assertEquals(etabMaJ.getNom(), etablissementFind.getNom());
		Assert.assertEquals(etabMaJ.getType(), etablissementFind.getType());
		Assert.assertEquals(etabMaJ.getTel(), etablissementFind.getTel());
		Assert.assertEquals(etabMaJ.getAdr().getAdresse(), etablissementFind.getAdr().getAdresse());
		Assert.assertEquals(etabMaJ.getAdr().getCodepostal(), etablissementFind.getAdr().getCodepostal());
		Assert.assertEquals(etabMaJ.getAdr().getVille(), etablissementFind.getAdr().getVille());
		Assert.assertEquals(etabMaJ.getAdr().getPays(), etablissementFind.getAdr().getPays());
		
		/*///////// CLASSE //////////*/
		Classe classe = new Classe("MaClasse");
		classeDao.create(classe);
		Classe classe2= new Classe("Seconde");
		classeDao.create(classe2);
		Classe classeFind = classeDao.find(classe2.getId());
		Assert.assertEquals(classe2.getNom(),classeFind.getNom());
		
		/*///////// SALLE //////////*/
		Salle salle = new Salle("Violette",20,etab);//création objet java
		salleDao.create(salle);//enregistrement ds la DB
		Salle salle2 = new Salle("Chez moi", 25, etab);
		salleDao.create(salle2);//enregistrement ds la DB
		Salle salleFind= salleDao.find(salle2.getId());
		Assert.assertEquals(salle2.getNom(), salleFind.getNom());
		Assert.assertEquals(salle2.getCapacite(), salleFind.getCapacite());
		Assert.assertEquals(salle2.getEtablissement().getNom(), salleFind.getEtablissement().getNom());
		Assert.assertEquals(salle2.getEtablissement().getType(), salleFind.getEtablissement().getType());
		Assert.assertEquals(salle2.getEtablissement().getTel(), salleFind.getEtablissement().getTel());
		Assert.assertEquals(salle2.getEtablissement().getAdr().getAdresse(), salleFind.getEtablissement().getAdr().getAdresse());
		Assert.assertEquals(salle2.getEtablissement().getAdr().getCodepostal(), salleFind.getEtablissement().getAdr().getCodepostal());
		Assert.assertEquals(salle2.getEtablissement().getAdr().getVille(), salleFind.getEtablissement().getAdr().getVille());
		Assert.assertEquals(salle2.getEtablissement().getAdr().getPays(), salleFind.getEtablissement().getAdr().getPays());
		
		/*/////////////// MATIERE //////////////////*/
		Matiere matiere = new Matiere("Java", "Rouge");//création objet java
		matiereDao.create(matiere);//enregistrement ds la DB
		Matiere matiere2 = new Matiere("SQL", "Gris");
		matiereDao.create(matiere2);//enregistrement ds la DB
		Matiere matiereFind = matiereDao.find(matiere2.getIdMatiere());//récupérer l'enregistrement de la DB
		Assert.assertEquals(matiere2.getNomMatiere(), matiereFind.getNomMatiere());
		Assert.assertEquals(matiere2.getCouleurMatiere(), matiereFind.getCouleurMatiere());
		
		/*///////// SALLE CLASSE //////////*/
		salleclasseDao.create(new SalleClasse(salle, classe));
		SalleClasse salleclasse2 = new SalleClasse(salle, classe);
		salleclasseDao.create(salleclasse2);
		SalleClasse salleclasse = new SalleClasse(salle, classe);
		salleclasseDao.create(salleclasse);
		SalleClasse salleclasseFind = salleclasseDao.find(salleclasse.getId());
		Assert.assertEquals(salleclasse.getSalle().getNom(),salleclasseFind.getSalle().getNom());
		Assert.assertEquals(salleclasse.getSalle().getCapacite(),salleclasseFind.getSalle().getCapacite());
		Assert.assertEquals(salleclasse.getClasse().getNom(),salleclasseFind.getClasse().getNom());
		
		/*///////// MATIERE SALLE //////////*/
		matieresalleDao.create(new MatiereSalle(salle, matiere));
		MatiereSalle matieresalle2 = new MatiereSalle(salle, matiere);
		matieresalleDao.create(matieresalle2);
		MatiereSalle matieresalle = new MatiereSalle(salle, matiere);
		matieresalleDao.create(matieresalle);
		MatiereSalle matieresalleFind = matieresalleDao.find(matieresalle.getId());
		Assert.assertEquals(matieresalle.getMatiere().getNomMatiere(),matieresalleFind.getMatiere().getNomMatiere());
		Assert.assertEquals(matieresalle.getMatiere().getCouleurMatiere(),matieresalleFind.getMatiere().getCouleurMatiere());
		Assert.assertEquals(matieresalle.getSalle().getNom(), matieresalleFind.getSalle().getNom());
		Assert.assertEquals(matieresalle.getSalle().getCapacite(), matieresalleFind.getSalle().getCapacite());
		
		/*///////// PERSONNE MATIERE //////////*/
		personnematiereDao.create(new PersonneMatiere(admin, matiere));
		PersonneMatiere personnematiere2 = new PersonneMatiere(admin, matiere);
		personnematiereDao.create(personnematiere2);
		PersonneMatiere personnematiere = new PersonneMatiere(admin, matiere);
		personnematiereDao.create(personnematiere);
		PersonneMatiere personnematiereFind = personnematiereDao.find(personnematiere.getId());
		Assert.assertEquals(personnematiere.getMatiere().getNomMatiere(), personnematiereFind.getMatiere().getNomMatiere());
		Assert.assertEquals(personnematiere.getMatiere().getCouleurMatiere(), personnematiereFind.getMatiere().getCouleurMatiere());
		Assert.assertEquals(personnematiere.getPersonne().getCivilite(), personnematiereFind.getPersonne().getCivilite());
		Assert.assertEquals(personnematiere.getPersonne().getRole(), personnematiereFind.getPersonne().getRole());
		Assert.assertEquals(personnematiere.getPersonne().getLogin().getUsername(),personnematiereFind.getPersonne().getLogin().getUsername());
		Assert.assertEquals(personnematiere.getPersonne().getLogin().getPassword(),personnematiereFind.getPersonne().getLogin().getPassword());
		Assert.assertEquals(personnematiere.getPersonne().getStatusEnum(), personnematiereFind.getPersonne().getStatusEnum());
		Assert.assertEquals(personnematiere.getPersonne().getNom(), personnematiereFind.getPersonne().getNom());
		Assert.assertEquals(personnematiere.getPersonne().getPrenom(), personnematiereFind.getPersonne().getPrenom());
		Assert.assertEquals(personnematiere.getPersonne().getDatenaiss().getDate(), personnematiereFind.getPersonne().getDatenaiss().getDate());
		Assert.assertEquals(personnematiere.getPersonne().getAdresse().getAdresse(), personnematiereFind.getPersonne().getAdresse().getAdresse());
		Assert.assertEquals(personnematiere.getPersonne().getAdresse().getCodepostal(), personnematiereFind.getPersonne().getAdresse().getCodepostal());
		Assert.assertEquals(personnematiere.getPersonne().getAdresse().getPays(), personnematiereFind.getPersonne().getAdresse().getPays());
		Assert.assertEquals(personnematiere.getPersonne().getAdresse().getVille(), personnematiereFind.getPersonne().getAdresse().getVille());
		
		/*///////// PERSONNE CLASSE //////////*/
		personneclasseDao.create(new PersonneClasse(admin, classe, true));
		PersonneClasse personneclasse2 = new PersonneClasse(admin, classe, true);
		personneclasseDao.create(personneclasse2);
		PersonneClasse personneclasse = new PersonneClasse(admin, classe, true);
		personneclasseDao.create(personneclasse);
		PersonneClasse personneClasseFind = personneclasseDao.find(personneclasse.getId());
		Assert.assertEquals(personneclasse.getClasse().getNom(), personneClasseFind.getClasse().getNom());
		Assert.assertEquals(personneclasse.isPrincipal(), personneClasseFind.isPrincipal());
		Assert.assertEquals(personneclasse.getPersonne().getCivilite(), personneClasseFind.getPersonne().getCivilite());
		Assert.assertEquals(personneclasse.getPersonne().getRole(), personneClasseFind.getPersonne().getRole());
		Assert.assertEquals(personneclasse.getPersonne().getLogin().getUsername(),personneClasseFind.getPersonne().getLogin().getUsername());
		Assert.assertEquals(personneclasse.getPersonne().getLogin().getPassword(),personneClasseFind.getPersonne().getLogin().getPassword());
		Assert.assertEquals(personneclasse.getPersonne().getStatusEnum(), personneClasseFind.getPersonne().getStatusEnum());
		Assert.assertEquals(personneclasse.getPersonne().getNom(), personneClasseFind.getPersonne().getNom());
		Assert.assertEquals(personneclasse.getPersonne().getPrenom(), personneClasseFind.getPersonne().getPrenom());
		Assert.assertEquals(personneclasse.getPersonne().getDatenaiss().getDate(), personneClasseFind.getPersonne().getDatenaiss().getDate());
		Assert.assertEquals(personneclasse.getPersonne().getAdresse().getAdresse(), personneClasseFind.getPersonne().getAdresse().getAdresse());
		Assert.assertEquals(personneclasse.getPersonne().getAdresse().getCodepostal(), personneClasseFind.getPersonne().getAdresse().getCodepostal());
		Assert.assertEquals(personneclasse.getPersonne().getAdresse().getPays(), personneClasseFind.getPersonne().getAdresse().getPays());
		Assert.assertEquals(personneclasse.getPersonne().getAdresse().getVille(), personneClasseFind.getPersonne().getAdresse().getVille());
		
		/*///////// ETABLISSEMENT PERSONNE //////////*/
		persEtabDao.create(new PersonneEtablissement(admin,etab));
		PersonneEtablissement pe2 = new PersonneEtablissement(admin,etab);
		persEtabDao.create(pe2);
		PersonneEtablissement pe = new PersonneEtablissement(admin,etab);
		persEtabDao.create(pe);
		PersonneEtablissement peFind=persEtabDao.find(pe.getId());
		Assert.assertEquals(pe.getEtablissement().getNom(), peFind.getEtablissement().getNom());
		Assert.assertEquals(pe.getEtablissement().getType(), peFind.getEtablissement().getType());
		Assert.assertEquals(pe.getEtablissement().getTel(), peFind.getEtablissement().getTel());
		Assert.assertEquals(pe.getEtablissement().getAdr().getAdresse(), peFind.getEtablissement().getAdr().getAdresse());
		Assert.assertEquals(pe.getEtablissement().getAdr().getCodepostal(), peFind.getEtablissement().getAdr().getCodepostal());
		Assert.assertEquals(pe.getEtablissement().getAdr().getVille(), peFind.getEtablissement().getAdr().getVille());
		Assert.assertEquals(pe.getEtablissement().getAdr().getPays(), peFind.getEtablissement().getAdr().getPays());
		Assert.assertEquals(pe.getPersonne().getCivilite(),peFind.getPersonne().getCivilite());
		Assert.assertEquals(pe.getPersonne().getRole(),peFind.getPersonne().getRole());
		Assert.assertEquals(pe.getPersonne().getLogin().getUsername(),peFind.getPersonne().getLogin().getUsername());
		Assert.assertEquals(pe.getPersonne().getLogin().getPassword(),peFind.getPersonne().getLogin().getPassword());
		Assert.assertEquals(pe.getPersonne().getStatusEnum(),peFind.getPersonne().getStatusEnum());
		Assert.assertEquals(pe.getPersonne().getNom(),peFind.getPersonne().getNom());
		Assert.assertEquals(pe.getPersonne().getPrenom(),peFind.getPersonne().getPrenom());
		Assert.assertEquals(pe.getPersonne().getDatenaiss().getDate(),peFind.getPersonne().getDatenaiss().getDate());
		Assert.assertEquals(pe.getPersonne().getAdresse().getAdresse(),peFind.getPersonne().getAdresse().getAdresse());
		Assert.assertEquals(pe.getPersonne().getAdresse().getCodepostal(),peFind.getPersonne().getAdresse().getCodepostal());
		Assert.assertEquals(pe.getPersonne().getAdresse().getVille(),peFind.getPersonne().getAdresse().getVille());
		Assert.assertEquals(pe.getPersonne().getAdresse().getPays(),peFind.getPersonne().getAdresse().getPays());

		/*///////// EVENEMENT //////////*/
		evenementDao.create(new Evenement(dateDebutEvenement, dateMiEvenement, classe, matiere, salle, admin, etab));
		Evenement evenement = new Evenement(dateDebutEvenement, dateMiEvenement, classe, matiere, salle, admin, etab);
		evenementDao.create(evenement);
		Evenement evenement2 = new Evenement(dateMiEvenement, dateMiEvenement, classe2, matiere2, salle2, qui, etabMaJ);
		evenementDao.create(evenement2);
		Evenement evenementFind = evenementDao.find(evenement2.getId());
		Assert.assertEquals(evenement2.getDateDebut().getDate(), evenementFind.getDateDebut().getDate());
		Assert.assertEquals(evenement2.getDateFin().getDate(), evenementFind.getDateFin().getDate());
		Assert.assertEquals(evenement2.getClasse().getNom(), evenementFind.getClasse().getNom());
		Assert.assertEquals(evenement2.getMatiere().getNomMatiere(), evenementFind.getMatiere().getNomMatiere());
		Assert.assertEquals(evenement2.getMatiere().getCouleurMatiere(), evenementFind.getMatiere().getCouleurMatiere());
		Assert.assertEquals(evenement2.getSalle().getNom(), evenementFind.getSalle().getNom());
		Assert.assertEquals(evenement2.getSalle().getCapacite(), evenementFind.getSalle().getCapacite());
		Assert.assertEquals(evenement2.getPersonne().getCivilite(), evenementFind.getPersonne().getCivilite());
		Assert.assertEquals(evenement2.getPersonne().getRole(), evenementFind.getPersonne().getRole());
		Assert.assertEquals(evenement2.getPersonne().getLogin().getUsername(),evenementFind.getPersonne().getLogin().getUsername());
		Assert.assertEquals(evenement2.getPersonne().getLogin().getPassword(),evenementFind.getPersonne().getLogin().getPassword());
		Assert.assertEquals(evenement2.getPersonne().getStatusEnum(), evenementFind.getPersonne().getStatusEnum());
		Assert.assertEquals(evenement2.getPersonne().getNom(), evenementFind.getPersonne().getNom());
		Assert.assertEquals(evenement2.getPersonne().getPrenom(), evenementFind.getPersonne().getPrenom());
		Assert.assertEquals(evenement2.getPersonne().getDatenaiss().getDate(), evenementFind.getPersonne().getDatenaiss().getDate());
		Assert.assertEquals(evenement2.getPersonne().getAdresse().getAdresse(), evenementFind.getPersonne().getAdresse().getAdresse());
		Assert.assertEquals(evenement2.getPersonne().getAdresse().getCodepostal(), evenementFind.getPersonne().getAdresse().getCodepostal());
		Assert.assertEquals(evenement2.getPersonne().getAdresse().getPays(), evenementFind.getPersonne().getAdresse().getPays());
		Assert.assertEquals(evenement2.getPersonne().getAdresse().getVille(), evenementFind.getPersonne().getAdresse().getVille());
		Assert.assertEquals(evenement2.getEtablissement().getNom(), evenementFind.getEtablissement().getNom());
		Assert.assertEquals(evenement2.getEtablissement().getType(), evenementFind.getEtablissement().getType());
		Assert.assertEquals(evenement2.getEtablissement().getTel(), evenementFind.getEtablissement().getTel());
		Assert.assertEquals(evenement2.getEtablissement().getAdr().getAdresse(), evenementFind.getEtablissement().getAdr().getAdresse());
		Assert.assertEquals(evenement2.getEtablissement().getAdr().getCodepostal(), evenementFind.getEtablissement().getAdr().getCodepostal());
		Assert.assertEquals(evenement2.getEtablissement().getAdr().getPays(), evenementFind.getEtablissement().getAdr().getPays());
		Assert.assertEquals(evenement2.getEtablissement().getAdr().getVille(), evenementFind.getEtablissement().getAdr().getVille());
		
		
		/*////////////////////////////////////////////////////////////////*/
		/*////////////////////// MISE A JOUR /////////////////////////////*/
		/*////////////////////////////////////////////////////////////////*/
		
		/*///////// LOGIN //////////*/
		loginFind.setUsername("admin2");
		loginFind.setPassword("admin2");
		Login loginUpdate = loginDao.update(loginFind);
		loginFind=loginDao.find(loginUpdate.getId());
		Assert.assertEquals(loginUpdate.getUsername(), loginFind.getUsername());
		Assert.assertEquals(loginUpdate.getPassword(), loginFind.getPassword());
		
		/*///////// PERSONNE //////////*/
		adrPersMaj = new Adresse("12 Rue Seré Depoin", "95300", "Pontoise", "France"); 
		c.set(1983, 19, 6);
		bDay = c.getTime();
		personneFind.setCivilite(civ.MR);
		personneFind.setRole(role.ADMIN);
		personneFind.setStatusEnum(StatusEnum.DIRECTEUR);
		personneFind.setLogin(loginFind);
		personneFind.setNom("MELKI");
		personneFind.setPrenom("Safi");
		personneFind.setDatenaiss(bDay);
		personneFind.setAdresse(adrPersMaj);
		Personne personneUpdate = personneDao.update(personneFind);
		personneFind=personneDao.find(personneUpdate.getId());
		Assert.assertEquals(personneFind.getCivilite(),personneUpdate.getCivilite());
		Assert.assertEquals(personneFind.getRole(),personneUpdate.getRole());
		Assert.assertEquals(personneFind.getLogin().getUsername(),personneUpdate.getLogin().getUsername());
		Assert.assertEquals(personneFind.getLogin().getPassword(),personneUpdate.getLogin().getPassword());
		Assert.assertEquals(personneFind.getStatusEnum(),personneUpdate.getStatusEnum());
		Assert.assertEquals(personneFind.getNom(),personneUpdate.getNom());
		Assert.assertEquals(personneFind.getPrenom(),personneUpdate.getPrenom());
		Assert.assertEquals(personneFind.getDatenaiss().getDate(),personneUpdate.getDatenaiss().getDate());
		Assert.assertEquals(personneFind.getAdresse().getAdresse(),personneUpdate.getAdresse().getAdresse());
		Assert.assertEquals(personneFind.getAdresse().getCodepostal(),personneUpdate.getAdresse().getCodepostal());
		Assert.assertEquals(personneFind.getAdresse().getVille(),personneUpdate.getAdresse().getVille());
		Assert.assertEquals(personneFind.getAdresse().getPays(),personneUpdate.getAdresse().getPays());
		
		/*///////// ETABLISSEMENT //////////*/
		adrEtabMaJ = new Adresse("13 Rue Louis Pasteur","92100","Boulogne-Billancourt","France");
		etablissementFind.setAdr(adrEtabMaJ);
		etablissementFind.setNom("AUBAY");
		etablissementFind.setType(type.LYCEE);
		etablissementFind.setTel("0146106767");
		Etablissement etablissementUpdate = etabDao.update(etablissementFind);
		etablissementFind=etabDao.find(etablissementUpdate.getId());
		Assert.assertEquals(etablissementUpdate.getNom(), etablissementFind.getNom());
		Assert.assertEquals(etablissementUpdate.getType(), etablissementFind.getType());
		Assert.assertEquals(etablissementUpdate.getTel(), etablissementFind.getTel());
		Assert.assertEquals(etablissementUpdate.getAdr().getAdresse(), etablissementFind.getAdr().getAdresse());
		Assert.assertEquals(etablissementUpdate.getAdr().getCodepostal(), etablissementFind.getAdr().getCodepostal());
		Assert.assertEquals(etablissementUpdate.getAdr().getVille(), etablissementFind.getAdr().getVille());
		Assert.assertEquals(etablissementUpdate.getAdr().getPays(), etablissementFind.getAdr().getPays());
		
		/*/////////////// CLASSE //////////////////*/
		classeFind.setNom("Terminale S");
		Classe classeUpdate = classeDao.update(classeFind);
		classeFind = classeDao.find(classeUpdate.getId());
		Assert.assertEquals(classeUpdate.getNom(),classeFind.getNom());
		
		/*///////// SALLE //////////*/
		salleFind.setNom("Marquise");
		salleFind.setCapacite(30);
		salleFind.setEtablissement(etabMaJ);
		Salle salleUpdate = salleDao.update(salleFind);
		salleFind = salleDao.find(salleUpdate.getId());
		Assert.assertEquals(salleUpdate.getNom(), salleFind.getNom());
		Assert.assertEquals(salleUpdate.getCapacite(), salleFind.getCapacite());
		Assert.assertEquals(salleUpdate.getEtablissement().getNom(), salleFind.getEtablissement().getNom());
		Assert.assertEquals(salleUpdate.getEtablissement().getType(), salleFind.getEtablissement().getType());
		Assert.assertEquals(salleUpdate.getEtablissement().getTel(), salleFind.getEtablissement().getTel());
		Assert.assertEquals(salleUpdate.getEtablissement().getAdr().getAdresse(), salleFind.getEtablissement().getAdr().getAdresse());
		Assert.assertEquals(salleUpdate.getEtablissement().getAdr().getCodepostal(), salleFind.getEtablissement().getAdr().getCodepostal());
		Assert.assertEquals(salleUpdate.getEtablissement().getAdr().getVille(), salleFind.getEtablissement().getAdr().getVille());
		Assert.assertEquals(salleUpdate.getEtablissement().getAdr().getPays(), salleFind.getEtablissement().getAdr().getPays());
		
		/*/////////////// MATIERE //////////////////*/
		matiereFind.setNomMatiere("HTML");
		matiereFind.setCouleurMatiere("Blanc");
		Matiere matiereUpdate = matiereDao.update(matiereFind);
		matiereFind=matiereDao.find(matiereUpdate.getIdMatiere());
		Assert.assertEquals(matiereUpdate.getNomMatiere(), matiereFind.getNomMatiere());
		Assert.assertEquals(matiereUpdate.getCouleurMatiere(), matiereFind.getCouleurMatiere());

		/*/////////////// SALLE CLASSE //////////////////*/
		salleclasseFind.setClasse(classe2);
		salleclasseFind.setSalle(salle2);
		SalleClasse salleclasseUpdate = salleclasseDao.update(salleclasseFind);
		salleclasseFind = salleclasseDao.find(salleclasseUpdate.getId());
		Assert.assertEquals(salleclasseUpdate.getClasse().getNom(), salleclasseFind.getClasse().getNom());
		Assert.assertEquals(salleclasseUpdate.getSalle().getNom(), salleclasseFind.getSalle().getNom());
		Assert.assertEquals(salleclasseUpdate.getSalle().getCapacite(), salleclasseFind.getSalle().getCapacite());
		
		/*///////// MATIERE SALLE //////////*/
		matieresalleFind.setMatiere(matiere2);
		matieresalleFind.setSalle(salle2);
		MatiereSalle matieresalleUpdate = matieresalleDao.update(matieresalleFind);
		matieresalleFind = matieresalleDao.find(matieresalleUpdate.getId());
		Assert.assertEquals(matieresalleUpdate.getMatiere().getNomMatiere(), matieresalleFind.getMatiere().getNomMatiere());
		Assert.assertEquals(matieresalleUpdate.getMatiere().getCouleurMatiere(), matieresalleFind.getMatiere().getCouleurMatiere());
		Assert.assertEquals(matieresalleUpdate.getSalle().getNom(), matieresalleFind.getSalle().getNom());
		Assert.assertEquals(matieresalleUpdate.getSalle().getCapacite(), matieresalleFind.getSalle().getCapacite());
		
		/*///////// PERSONNE MATIERE //////////*/
		personnematiereFind.setPersonne(qui);
		personnematiereFind.setMatiere(matiere2);
		PersonneMatiere personnematiereUpdate = personnematiereDao.update(personnematiereFind);
		personnematiereFind = personnematiereDao.find(personnematiereUpdate.getId());
		Assert.assertEquals(personnematiereUpdate.getMatiere().getNomMatiere(), personnematiereFind.getMatiere().getNomMatiere());
		Assert.assertEquals(personnematiereUpdate.getMatiere().getCouleurMatiere(), personnematiereFind.getMatiere().getCouleurMatiere());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getCivilite(), personnematiereFind.getPersonne().getCivilite());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getRole(), personnematiereFind.getPersonne().getRole());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getLogin().getUsername(),personnematiereFind.getPersonne().getLogin().getUsername());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getLogin().getPassword(),personnematiereFind.getPersonne().getLogin().getPassword());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getStatusEnum(), personnematiereFind.getPersonne().getStatusEnum());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getNom(), personnematiereFind.getPersonne().getNom());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getPrenom(), personnematiereFind.getPersonne().getPrenom());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getDatenaiss().getDate(), personnematiereFind.getPersonne().getDatenaiss().getDate());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getAdresse().getAdresse(), personnematiereFind.getPersonne().getAdresse().getAdresse());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getAdresse().getCodepostal(), personnematiereFind.getPersonne().getAdresse().getCodepostal());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getAdresse().getPays(), personnematiereFind.getPersonne().getAdresse().getPays());
		Assert.assertEquals(personnematiereUpdate.getPersonne().getAdresse().getVille(), personnematiereFind.getPersonne().getAdresse().getVille());
		
		/*///////// PERSONNE CLASSE //////////*/
		personneClasseFind.setClasse(classe2);
		personneClasseFind.setPersonne(qui);
		personneClasseFind.setPrincipal(false);
		PersonneClasse personneClasseUpdate = personneclasseDao.update(personneClasseFind);
		personneClasseFind=personneclasseDao.find(personneClasseUpdate.getId());
		Assert.assertEquals(personneClasseUpdate.getClasse().getNom(), personneClasseFind.getClasse().getNom());
		Assert.assertEquals(personneClasseUpdate.isPrincipal(), personneClasseFind.isPrincipal());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getCivilite(), personneClasseFind.getPersonne().getCivilite());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getRole(), personneClasseFind.getPersonne().getRole());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getLogin().getUsername(),personneClasseFind.getPersonne().getLogin().getUsername());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getLogin().getPassword(),personneClasseFind.getPersonne().getLogin().getPassword());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getStatusEnum(), personneClasseFind.getPersonne().getStatusEnum());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getNom(), personneClasseFind.getPersonne().getNom());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getPrenom(), personneClasseFind.getPersonne().getPrenom());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getDatenaiss().getDate(), personneClasseFind.getPersonne().getDatenaiss().getDate());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getAdresse().getAdresse(), personneClasseFind.getPersonne().getAdresse().getAdresse());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getAdresse().getCodepostal(), personneClasseFind.getPersonne().getAdresse().getCodepostal());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getAdresse().getPays(), personneClasseFind.getPersonne().getAdresse().getPays());
		Assert.assertEquals(personneClasseUpdate.getPersonne().getAdresse().getVille(), personneClasseFind.getPersonne().getAdresse().getVille());
	
		/*///////// ETABLISSEMENT PERSONNE //////////*/
		peFind.setPersonne(qui);
		peFind.setEtablissement(etabMaJ);
		PersonneEtablissement peUpdate = persEtabDao.update(peFind);
		peFind= persEtabDao.find(peUpdate.getId());
		Assert.assertEquals(peUpdate.getEtablissement().getNom(), peFind.getEtablissement().getNom());
		Assert.assertEquals(peUpdate.getEtablissement().getType(), peFind.getEtablissement().getType());
		Assert.assertEquals(peUpdate.getEtablissement().getTel(), peFind.getEtablissement().getTel());
		Assert.assertEquals(peUpdate.getEtablissement().getAdr().getAdresse(), peFind.getEtablissement().getAdr().getAdresse());
		Assert.assertEquals(peUpdate.getEtablissement().getAdr().getCodepostal(), peFind.getEtablissement().getAdr().getCodepostal());
		Assert.assertEquals(peUpdate.getEtablissement().getAdr().getVille(), peFind.getEtablissement().getAdr().getVille());
		Assert.assertEquals(peUpdate.getEtablissement().getAdr().getPays(), peFind.getEtablissement().getAdr().getPays());
		Assert.assertEquals(peUpdate.getPersonne().getCivilite(),peFind.getPersonne().getCivilite());
		Assert.assertEquals(peUpdate.getPersonne().getRole(),peFind.getPersonne().getRole());
		Assert.assertEquals(peUpdate.getPersonne().getLogin().getUsername(),peFind.getPersonne().getLogin().getUsername());
		Assert.assertEquals(peUpdate.getPersonne().getLogin().getPassword(),peFind.getPersonne().getLogin().getPassword());
		Assert.assertEquals(peUpdate.getPersonne().getStatusEnum(),peFind.getPersonne().getStatusEnum());
		Assert.assertEquals(peUpdate.getPersonne().getNom(),peFind.getPersonne().getNom());
		Assert.assertEquals(peUpdate.getPersonne().getPrenom(),peFind.getPersonne().getPrenom());
		Assert.assertEquals(peUpdate.getPersonne().getDatenaiss().getDate(),peFind.getPersonne().getDatenaiss().getDate());
		Assert.assertEquals(peUpdate.getPersonne().getAdresse().getAdresse(),peFind.getPersonne().getAdresse().getAdresse());
		Assert.assertEquals(peUpdate.getPersonne().getAdresse().getCodepostal(),peFind.getPersonne().getAdresse().getCodepostal());
		Assert.assertEquals(peUpdate.getPersonne().getAdresse().getVille(),peFind.getPersonne().getAdresse().getVille());
		Assert.assertEquals(peUpdate.getPersonne().getAdresse().getPays(),peFind.getPersonne().getAdresse().getPays());
		
		/*///////// EVENEMENT //////////*/
		evenementFind.setDateDebut(dateMiEvenement);;
		evenementFind.setDateFin(dateFinEvenement);
		evenementFind.setMatiere(matiere2);
		evenementFind.setSalle(salle2);
		evenementFind.setPersonne(qui);
		evenementFind.setEtablissement(etabMaJ);
		Evenement evenementUpdate = evenementDao.update(evenementFind);
		evenementFind=evenementDao.find(evenementUpdate.getId());
		
		Assert.assertEquals(evenementUpdate.getDateDebut().getDate(), evenementFind.getDateDebut().getDate());
		Assert.assertEquals(evenementUpdate.getDateFin().getDate(), evenementFind.getDateFin().getDate());
		Assert.assertEquals(evenementUpdate.getClasse().getNom(), evenementFind.getClasse().getNom());
		Assert.assertEquals(evenementUpdate.getMatiere().getNomMatiere(), evenementFind.getMatiere().getNomMatiere());
		Assert.assertEquals(evenementUpdate.getMatiere().getCouleurMatiere(), evenementFind.getMatiere().getCouleurMatiere());
		Assert.assertEquals(evenementUpdate.getSalle().getNom(), evenementFind.getSalle().getNom());
		Assert.assertEquals(evenementUpdate.getSalle().getCapacite(), evenementFind.getSalle().getCapacite());
		Assert.assertEquals(evenementUpdate.getPersonne().getCivilite(), evenementFind.getPersonne().getCivilite());
		Assert.assertEquals(evenementUpdate.getPersonne().getRole(), evenementFind.getPersonne().getRole());
		Assert.assertEquals(evenementUpdate.getPersonne().getLogin().getUsername(),evenementFind.getPersonne().getLogin().getUsername());
		Assert.assertEquals(evenementUpdate.getPersonne().getLogin().getPassword(),evenementFind.getPersonne().getLogin().getPassword());
		Assert.assertEquals(evenementUpdate.getPersonne().getStatusEnum(), evenementFind.getPersonne().getStatusEnum());
		Assert.assertEquals(evenementUpdate.getPersonne().getNom(), evenementFind.getPersonne().getNom());
		Assert.assertEquals(evenementUpdate.getPersonne().getPrenom(), evenementFind.getPersonne().getPrenom());
		Assert.assertEquals(evenementUpdate.getPersonne().getDatenaiss().getDate(), evenementFind.getPersonne().getDatenaiss().getDate());
		Assert.assertEquals(evenementUpdate.getPersonne().getAdresse().getAdresse(), evenementFind.getPersonne().getAdresse().getAdresse());
		Assert.assertEquals(evenementUpdate.getPersonne().getAdresse().getCodepostal(), evenementFind.getPersonne().getAdresse().getCodepostal());
		Assert.assertEquals(evenementUpdate.getPersonne().getAdresse().getPays(), evenementFind.getPersonne().getAdresse().getPays());
		Assert.assertEquals(evenementUpdate.getPersonne().getAdresse().getVille(), evenementFind.getPersonne().getAdresse().getVille());
		Assert.assertEquals(evenementUpdate.getEtablissement().getNom(), evenementFind.getEtablissement().getNom());
		Assert.assertEquals(evenementUpdate.getEtablissement().getType(), evenementFind.getEtablissement().getType());
		Assert.assertEquals(evenementUpdate.getEtablissement().getTel(), evenementFind.getEtablissement().getTel());
		Assert.assertEquals(evenementUpdate.getEtablissement().getAdr().getAdresse(), evenementFind.getEtablissement().getAdr().getAdresse());
		Assert.assertEquals(evenementUpdate.getEtablissement().getAdr().getCodepostal(), evenementFind.getEtablissement().getAdr().getCodepostal());
		Assert.assertEquals(evenementUpdate.getEtablissement().getAdr().getPays(), evenementFind.getEtablissement().getAdr().getPays());
		Assert.assertEquals(evenementUpdate.getEtablissement().getAdr().getVille(), evenementFind.getEtablissement().getAdr().getVille());
		
		
		/*////////////////////////////////////////////////////////////////*/
		/*///////////////////////// DELETE ///////////////////////////////*/
		/*////////////////////////////////////////////////////////////////*/
		
		/*///////// PERSONNE //////////*/
		List<Evenement> eventsPrPers = new ArrayList<Evenement>();
		Evenement eventDelFromPers = evenementDao.find(evenementUpdate.getId());
		if (eventDelFromPers!=null){eventsPrPers.add(eventDelFromPers);}
		personneFind.setEvenements(eventsPrPers);
		
		List<PersonneClasse> pCPrPers = new ArrayList<PersonneClasse>();
		PersonneClasse pCDelFromPers = personneclasseDao.find(personneClasseUpdate.getId());
		if (pCDelFromPers!=null){pCPrPers.add(pCDelFromPers);}
		personneFind.setPersonneClasses(pCPrPers);
		
		List<PersonneMatiere> pMPrPers = new ArrayList<PersonneMatiere>();
		PersonneMatiere pMDelFromPers = personnematiereDao.find(personnematiereUpdate.getId());
		if (pMDelFromPers!=null){pMPrPers.add(pMDelFromPers);}
		personneFind.setPersonneMatiere(pMPrPers);
		
		List<PersonneEtablissement> pEPrPers = new ArrayList<PersonneEtablissement>();
		PersonneEtablissement pEDelFromPers = persEtabDao.find(peUpdate.getId());
		if (pEDelFromPers!=null){pEPrPers.add(pEDelFromPers);}
		personneFind.setPersonneEtablissement(pEPrPers);
		
		List<Personne> pers = personneDao.findAll();
		personneDao.delete(personneFind);
		personneFind= personneDao.find(personneFind.getId());
		Assert.assertNull(personneFind);
		List<Personne> persPostDelete = personneDao.findAll();
		Assert.assertEquals(1, pers.size()-persPostDelete.size());
		
		/*///////// ETABLISSEMENT //////////*/
		List<Salle> sallesPrEtab = new ArrayList<Salle>();
		Salle salleDelFromEtab = salleDao.find(salleUpdate.getId());
		if (salleDelFromEtab!=null){sallesPrEtab.add(salleDelFromEtab);}
		etablissementFind.setSalles(sallesPrEtab);

		List<PersonneEtablissement> persEtabPrEtab = new ArrayList<PersonneEtablissement>();
		PersonneEtablissement persEtabDelFromEtab = persEtabDao.find(peUpdate.getId());
		if (persEtabDelFromEtab!=null){persEtabPrEtab.add(persEtabDelFromEtab);}
		etablissementFind.setPersonneEtablissement(persEtabPrEtab);
		
		List<Classe> clPrEtab = new ArrayList<Classe>();
		Classe clDelFromEtab = classeDao.find(classeUpdate.getId());
		if (persEtabDelFromEtab!=null){clPrEtab.add(clDelFromEtab);}
		etablissementFind.setClasses(clPrEtab);
		
		List<Etablissement> etabs = etabDao.findAll();
		etabDao.delete(etablissementFind);
		etablissementFind=etabDao.find(etablissementFind.getId());
		Assert.assertNull(etablissementFind);
		List<Etablissement> etabsPostDelete= etabDao.findAll();
		Assert.assertEquals(1, etabs.size()-etabsPostDelete.size());
		
		/*/////////////// CLASSE //////////////////*/
		List<Evenement> eventPrClasse = new ArrayList<Evenement>();
		Evenement evenDelFromClasse = evenementDao.find(evenementUpdate.getId());
		if (evenDelFromClasse!=null){eventPrClasse.add(evenDelFromClasse);}
		classeFind.setEvenements(eventPrClasse);
		
		List<PersonneClasse> personneClassePrClasse = new ArrayList<PersonneClasse>();
		PersonneClasse personneClasseDelFromClasse = personneclasseDao.find(personneClasseUpdate.getId());
		if (personneClasseDelFromClasse!=null){personneClassePrClasse.add(personneClasseDelFromClasse);}
		classeFind.setPersonneClasses(personneClassePrClasse);
		
		List<SalleClasse> salleClassePrClasse = new ArrayList<SalleClasse>();
		SalleClasse salleClasseDelFromClasse = salleclasseDao.find(personneClasseUpdate.getId());
		if (salleClasseDelFromClasse!=null){salleClassePrClasse.add(salleClasseDelFromClasse);}
		classeFind.setSalleClasses(salleClassePrClasse);
		
		
		List<Classe> classes = classeDao.findAll();
		classeDao.delete(classeFind);
		classeFind=classeDao.find(classeFind.getId());
		Assert.assertNull(classeFind);
		List<Classe> classesPsotDelete = classeDao.findAll();
		Assert.assertEquals(1, classes.size()-classesPsotDelete.size());
		
		/*///////// SALLE //////////*/
		salleFind=salleDao.find(salleFind.getId());
		if (salleFind!=null){
			List<MatiereSalle> mSPrSalle = new ArrayList<MatiereSalle>();
			MatiereSalle matiereSalleDelFromSalle = matieresalleDao.find(matieresalleUpdate.getId());
			if (matiereSalleDelFromSalle!=null){mSPrSalle.add(matiereSalleDelFromSalle);}
			salleFind.setMatiereSalles(mSPrSalle);
			
			List<SalleClasse>classePrSalle = new ArrayList<SalleClasse>();
			SalleClasse salleClasseDelFromSalle = salleclasseDao.find(salleclasseUpdate.getId());
			if (salleClasseDelFromSalle!=null){classePrSalle.add(salleClasseDelFromSalle);}
			salleFind.setSalleClasses(classePrSalle);
			
			List<Evenement> eventsPrSalle = new ArrayList<Evenement>();
			Evenement eventDelFromSalle=evenementDao.find(evenementUpdate.getId());
			if (eventDelFromSalle!=null){eventsPrSalle.add(eventDelFromSalle);}
			salleFind.setEvenements(eventsPrSalle);
			
			List<Salle> salles = salleDao.findAll();
			salleDao.delete(salleFind);
			salleFind=salleDao.find(salleFind.getId());
			Assert.assertNull(salleFind);
			List<Salle> sallesPostDelete = salleDao.findAll();
			Assert.assertEquals(1, salles.size()-sallesPostDelete.size());
		}
		
		/*/////////////// MATIERE //////////////////*/
		List<Evenement> events = new ArrayList<Evenement>();
		Evenement eventDelFromMatiere=evenementDao.find(evenementUpdate.getId());
		if (eventDelFromMatiere!=null){events.add(eventDelFromMatiere);}
		matiereFind.setEvenements(events);
		
		List<PersonneMatiere> persMat = new ArrayList<PersonneMatiere>();
		PersonneMatiere perMatDelFromMatiere = personnematiereDao.find(personnematiereUpdate.getId());
		if(perMatDelFromMatiere!=null){persMat.add(perMatDelFromMatiere);}
		matiereFind.setPersonneMatieres(persMat);
		
		List<MatiereSalle> matSalles = new ArrayList<MatiereSalle>();
		MatiereSalle matSalleDelFromMatiere = matieresalleDao.find(matieresalleUpdate.getId());
		if (matSalleDelFromMatiere!=null){matSalles.add(matSalleDelFromMatiere);}
		matiereFind.setMatiereSalles(matSalles);
		
		List<Matiere> matieres = matiereDao.findAll();
		matiereDao.delete(matiereFind);
		matiereFind=matiereDao.find(matiereFind.getIdMatiere());
		Assert.assertNull(matiereFind);
		List<Matiere> matieresPostDelete= matiereDao.findAll();
		Assert.assertEquals(1, matieres.size()-matieresPostDelete.size());
		
		/*/////////////// SALLE CLASSE //////////////////*/
		List<SalleClasse> salleclasses = salleclasseDao.findAll();
		salleclasseDao.delete(salleclasse2);
		salleclasseFind=salleclasseDao.find(salleclasse2.getId());
		Assert.assertNull(salleclasseFind);
		List<SalleClasse> salleclassesPostDelete = salleclasseDao.findAll();
		Assert.assertEquals(1, salleclasses.size() - salleclassesPostDelete.size());
		
		/*///////// MATIERE SALLE //////////*/
		List<MatiereSalle> matieresalles = matieresalleDao.findAll();
		matieresalleDao.delete(matieresalle2);
		matieresalleFind=matieresalleDao.find(matieresalle2.getId());
		Assert.assertNull(matieresalleFind);
		List<MatiereSalle> matieresallesPostDelete = matieresalleDao.findAll();
		Assert.assertEquals(1, matieresalles.size() - matieresallesPostDelete.size());
		
		/*///////// PERSONNE MATIERE //////////*/
		List<PersonneMatiere> personnematieres = personnematiereDao.findAll();
		personnematiereDao.delete(personnematiere2);
		personnematiereFind = personnematiereDao.find(personnematiere2.getId());
		Assert.assertNull(personnematiereFind);
		List<PersonneMatiere> personnematieresPostDelete = personnematiereDao.findAll();
		Assert.assertEquals(1, personnematieres.size() - personnematieresPostDelete.size());

		
		/*///////// PERSONNE CLASSE //////////*/
		List<PersonneClasse> personneclasses = personneclasseDao.findAll();
		personneclasseDao.delete(personneclasse2);
		personneClasseFind=personneclasseDao.find(personneclasse2.getId());
		Assert.assertNull(personneClasseFind);
		List<PersonneClasse> personneclassesPostDelete = personneclasseDao.findAll();
		Assert.assertEquals(1, personneclasses.size() - personneclassesPostDelete.size());
		
		/*///////// ETABLISSEMENT PERSONNE //////////*/
		List<PersonneEtablissement> etabpers = persEtabDao.findAll();
		persEtabDao.delete(pe2);
		peFind=persEtabDao.find(pe2.getId());
		Assert.assertNull(peFind);
		List<PersonneEtablissement> etabpersPostDelete = persEtabDao.findAll();
		Assert.assertEquals(1, etabpers.size() - etabpersPostDelete.size());
		
		/*///////// EVENEMENT //////////*/
		List<Evenement> eventss = evenementDao.findAll();
		evenementDao.delete(evenement);
		evenementFind=evenementDao.find(evenement.getId());
		Assert.assertNull(evenementFind);
		List<Evenement> eventsPostDelete= evenementDao.findAll();
		Assert.assertEquals(1, eventss.size()-eventsPostDelete.size());
		
	}
}
