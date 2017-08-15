package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.ClasseDao;
import gestionScolaire.metier.dao.EvenementDao;
import gestionScolaire.metier.dao.PersonneClasseDao;
import gestionScolaire.metier.dao.SalleClasseDao;
import gestionScolaire.metier.model.Classe;
import gestionScolaire.metier.model.Evenement;
import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.PersonneClasse;
import gestionScolaire.metier.model.SalleClasse;
import gestionScolaire.metier.model.StatusEnum;

@Transactional
@Repository
public class ClasseDaoJpa implements ClasseDao {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EvenementDao evenementDao;
	
	@Autowired
	private PersonneClasseDao persClaDao;
	
	@Autowired
	private SalleClasseDao salleClasseDao;

	@Override
	public Classe find(Long id) {
		return em.find(Classe.class, id);
	}

	@Override
	public List<Classe> findAll() {
		Query query = em.createQuery("from Classe");
		return query.getResultList();
	}
	
	@Override
	public List<Classe> findClasseByEtab(Long idEtab) {
		Query query = em.createQuery("from Classe as c left join fetch c.etablissement e where c.etablissement.id = :id");
		query.setParameter("id", idEtab);
		List<Classe> c = query.getResultList();
		
		return c.size() > 0 ? c : null;
	}
	
	@Override
	public void create(Classe obj) {
		em.persist(obj);
	}

	@Override
	public Classe update(Classe obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Classe c) {
		for (Evenement even : c.getEvenements()){
			evenementDao.delete(em.merge(even));
		}
		
		for (PersonneClasse pc : c.getPersonneClasses()){
			persClaDao.delete(em.merge(pc));
		}
		
		for (SalleClasse sc : c.getSalleClasses()){
			salleClasseDao.delete(em.merge(sc));
		}
		
		em.remove(em.merge(c));
	}

	@Override
	public void delete(Long id) {
		Classe c = find(id);
		for (Evenement even : c.getEvenements()){
			evenementDao.delete(em.merge(even));
		}
		
		for (PersonneClasse pc : c.getPersonneClasses()){
			persClaDao.delete(em.merge(pc));
		}
		
		for (SalleClasse sc : c.getSalleClasses()){
			salleClasseDao.delete(em.merge(sc));
		}
		
		em.remove(em.merge(c));
	}

}
