package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.SalleClasseDao;
import gestionScolaire.metier.model.SalleClasse;
@Transactional
@Repository
public class SalleClasseDaoJpa implements SalleClasseDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SalleClasse find(Long id) {
		return em.find(SalleClasse.class, id);
	}
	
	@Override
	public List<SalleClasse> findAll() {
		Query query = em.createQuery("from SalleClasse sc");
		return query.getResultList();
	}

	@Override
	public void create(SalleClasse obj) {
		em.persist(obj);
	}

	@Override
	public SalleClasse update(SalleClasse obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(SalleClasse obj) {
		em.remove(em.merge(obj));
	}

	@Override
	public void delete(Long id) {
		SalleClasse sc = find(id);
		em.remove(sc);
	}

	
	
}
