package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.StatusDao;
import gestionScolaire.metier.model.Status;

@Transactional
@Repository
public class StatusDaoJpa implements StatusDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Status find(Long id) {
		return em.find(Status.class, id);
	}

	@Override
	public List<Status> findAll() {
		Query query = em.createQuery("from Status s");
		return query.getResultList();
	}

	@Override
	public void create(Status obj) {
		em.persist(obj);
	}

	@Override
	public Status update(Status obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Status obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		Status s = find(id);
		em.remove(s);
	}

}
