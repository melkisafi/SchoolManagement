package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.ClasseDao;
import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.dao.EvenementDao;
import gestionScolaire.metier.dao.PersonneEtablissementDao;
import gestionScolaire.metier.dao.SalleDao;
import gestionScolaire.metier.model.Classe;
import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Evenement;
import gestionScolaire.metier.model.PersonneEtablissement;
import gestionScolaire.metier.model.Salle;

@Transactional
@Repository
public class EtablissementDaoJpa implements EtablissementDao {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EvenementDao eventDao;
	
	@Autowired
	private PersonneEtablissementDao persEtabDao;
	
	@Autowired
	private SalleDao salleDao;
	
	@Autowired
	private ClasseDao classeDao;
	
	@Override
	public Etablissement find(Long id) {
		return em.find(Etablissement.class, id);
	}

	@Override
	public List<Etablissement> findAll() {
		Query query = em.createQuery("from Etablissement e");
		return query.getResultList();
	}

	@Override
	public void create(Etablissement obj) {
		em.persist(obj);
	}

	@Override
	public Etablissement update(Etablissement obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Etablissement etab) {
		for (PersonneEtablissement pc : etab.getPersonneEtablissement()){
			persEtabDao.delete(em.merge(pc));
		}
		
		for (Salle salle : etab.getSalles()){
			salleDao.delete(em.merge(salle));
		}
		
		for (Classe e : etab.getClasses()){
			e.setEtablissement(null);
			
			classeDao.update(em.merge(e));
		}
		em.remove(em.merge(etab));
	}

	@Override
	public void delete(Long id) {
		Etablissement etab = find(id);
		for (PersonneEtablissement pc : etab.getPersonneEtablissement()){
			persEtabDao.delete(em.merge(pc));
		}
		
		for (Salle salle : etab.getSalles()){
			salleDao.delete(em.merge(salle));
		}
		
		for (Classe e : etab.getClasses()){
			e.setEtablissement(null);
			
			classeDao.update(em.merge(e));
		}
		em.remove(em.merge(etab));
	}

}
