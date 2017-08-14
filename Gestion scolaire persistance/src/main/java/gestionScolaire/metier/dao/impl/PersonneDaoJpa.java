package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.EvenementDao;
import gestionScolaire.metier.dao.LoginDao;
import gestionScolaire.metier.dao.PersonneClasseDao;
import gestionScolaire.metier.dao.PersonneDao;
import gestionScolaire.metier.dao.PersonneEtablissementDao;
import gestionScolaire.metier.dao.PersonneMatiereDao;
import gestionScolaire.metier.dao.StatusDao;
import gestionScolaire.metier.model.Evenement;
import gestionScolaire.metier.model.Login;
import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.PersonneClasse;
import gestionScolaire.metier.model.PersonneEtablissement;
import gestionScolaire.metier.model.PersonneMatiere;
import gestionScolaire.metier.model.Status;
import gestionScolaire.metier.model.StatusEnum;

@Transactional
@Repository
public class PersonneDaoJpa implements PersonneDao {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EvenementDao eventDao;
	
	@Autowired
	private PersonneClasseDao persClaDao;
	
	@Autowired
	private PersonneMatiereDao persMatDao;
	
	@Autowired
	private PersonneEtablissementDao persEtabDao;
	
	@Autowired
	private StatusDao statusDao;
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public Personne find(Long id) {
		return em.find(Personne.class, id);
	}
	
	@Override
	public List<Personne> findByStatus(StatusEnum status) {
		Query query = em.createQuery("from Personne p where p.statusEnum = :status");
		query.setParameter("status", status);
		List<Personne> stat = query.getResultList();
		
		return stat.size() > 0 ? stat : null;
	}
	
	@Override
	public List<Personne> findProfByEtab(StatusEnum status, Long idEtab) {
		Query query = em.createQuery("from Personne as p left join fetch p.personneEtablissement  pe where p.statusEnum = :status ");
		query.setParameter("status", status);
		List<Personne> stat = query.getResultList();
		
		return stat.size() > 0 ? stat : null;
	}
	
	@Override
	public List<Personne> findAll() {
		Query query = em.createQuery("from Personne p left outer join fetch p.login");
		return query.getResultList();
	}

	@Override
	public void create(Personne obj) {
		em.persist(obj);
	}

	@Override
	public Personne update(Personne obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Personne p) {
		for (Evenement even : p.getEvenements()){
			eventDao.delete(em.merge(even));
		}
		for (PersonneClasse pc : p.getPersonneClasses()){
			persClaDao.delete(em.merge(pc));
		}
		for (PersonneMatiere pm : p.getPersonneMatiere()){
			persMatDao.delete(em.merge(pm));
		}
		for (PersonneEtablissement pe : p.getPersonneEtablissement()){
			persEtabDao.delete(em.merge(pe));
		}
		Login log = p.getLogin();
		loginDao.delete(em.merge(log));
		Status status = p.getStatus();
		statusDao.delete(em.merge(status));
		em.remove(em.merge(p));
	}

	@Override
	public void delete(Long id) {
		Personne p = find(id);
		for (Evenement even : p.getEvenements()){
			eventDao.delete(em.merge(even));
		}
		for (PersonneClasse pc : p.getPersonneClasses()){
			persClaDao.delete(em.merge(pc));
		}
		for (PersonneMatiere pm : p.getPersonneMatiere()){
			persMatDao.delete(em.merge(pm));
		}
		for (PersonneEtablissement pe : p.getPersonneEtablissement()){
			persEtabDao.delete(em.merge(pe));
		}
		Login log = p.getLogin();
		loginDao.delete(em.merge(log));
		Status status = p.getStatus();
		statusDao.delete(em.merge(status));
		em.remove(em.merge(p));
	}

}
