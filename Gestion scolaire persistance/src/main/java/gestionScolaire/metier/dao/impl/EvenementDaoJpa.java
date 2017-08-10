package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.EvenementDao;
import gestionScolaire.metier.model.Evenement;

@Transactional
@Repository
public class EvenementDaoJpa implements EvenementDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Evenement find(Long id) {
		return em.find(Evenement.class, id);
	}

	@Override
	public List<Evenement> findAll() {
		Query query = em.createQuery("from Evenement");
		return query.getResultList();
	}

	@Override
	public void create(Evenement obj) {
		em.persist(obj);
	}

	@Override
	public Evenement update(Evenement obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Evenement obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		Evenement evenement = find(id);
		em.remove(evenement);
	}

}
