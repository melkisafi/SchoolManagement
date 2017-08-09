package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionScolaire.metier.dao.PersonneDao;
import gestionScolaire.metier.model.Personne;


public class PersonneDaoJpa implements PersonneDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Personne find(Long id) {
		return em.find(Personne.class, id);
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
	public void delete(Personne obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		Personne p = find(id);
		em.remove(p);
	}

}
