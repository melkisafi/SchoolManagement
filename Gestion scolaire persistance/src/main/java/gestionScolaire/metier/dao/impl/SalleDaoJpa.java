package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.SalleDao;
import gestionScolaire.metier.model.Salle;

@Transactional
@Repository
public class SalleDaoJpa implements SalleDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Salle find(Long id) {
		return em.find(Salle.class, id);
	}

	@Override
	public List<Salle> findAll() {
		Query query = em.createQuery("from Salle s");
		return query.getResultList();
	}

	@Override
	public void create(Salle obj) {
		em.persist(obj);
	}

	@Override
	public Salle update(Salle obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Salle obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		Salle s = find(id);
		em.remove(s);
	}

}
