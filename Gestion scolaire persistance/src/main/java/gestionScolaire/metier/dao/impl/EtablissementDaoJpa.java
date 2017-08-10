package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.EtablissementDao;
import gestionScolaire.metier.model.Etablissement;

@Transactional
@Repository
public class EtablissementDaoJpa implements EtablissementDao {
	@PersistenceContext
	private EntityManager em;
	
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
	public void delete(Etablissement obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		Etablissement e = find(id);
		em.remove(e);
	}

}
