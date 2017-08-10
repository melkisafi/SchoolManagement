package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.MatiereDao;
import gestionScolaire.metier.model.Matiere;

@Transactional
@Repository
public class MatiereDaoJpa implements MatiereDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Matiere find(Long id) {
		return em.find(Matiere.class, id);
	}

	@Override
	public List<Matiere> findAll() {
		Query query = em.createQuery("from Matiere");
		return query.getResultList();
	}

	@Override
	public void create(Matiere obj) {
		em.persist(obj);
	}

	@Override
	public Matiere update(Matiere obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Matiere obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		Matiere m = find(id);
		em.remove(m);
	}

}
