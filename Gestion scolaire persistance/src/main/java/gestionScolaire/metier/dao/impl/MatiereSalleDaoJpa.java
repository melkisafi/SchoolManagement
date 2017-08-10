package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.MatiereSalleDao;
import gestionScolaire.metier.model.MatiereSalle;

@Transactional
@Repository
public class MatiereSalleDaoJpa implements MatiereSalleDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public MatiereSalle find(Long id) {
		return em.find(MatiereSalle.class, id);
	}
	
	@Override
	public List<MatiereSalle> findAll() {
		Query query = em.createQuery("from MatiereSalle ms");
		return query.getResultList();
	}

	@Override
	public void create(MatiereSalle obj) {
		em.persist(obj);
	}

	@Override
	public MatiereSalle update(MatiereSalle obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(MatiereSalle obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		MatiereSalle ms = find(id);
		em.remove(ms);
	}

	
}
