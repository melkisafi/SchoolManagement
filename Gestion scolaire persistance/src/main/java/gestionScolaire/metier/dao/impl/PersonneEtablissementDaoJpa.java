package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.PersonneEtablissementDao;
import gestionScolaire.metier.model.PersonneEtablissement;

@Transactional
@Repository
public class PersonneEtablissementDaoJpa implements PersonneEtablissementDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public PersonneEtablissement find(Long id) {
		return em.find(PersonneEtablissement.class, id);
	}
	
	@Override
	public List<PersonneEtablissement> findAll() {
		Query query = em.createQuery("from PersonneEtablissement pe");
		return query.getResultList();
	}

	@Override
	public void create(PersonneEtablissement obj) {
		em.persist(obj);
	}

	@Override
	public PersonneEtablissement update(PersonneEtablissement obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(PersonneEtablissement obj) {
		em.remove(em.merge(obj));
	}

	@Override
	public void delete(Long id) {
		PersonneEtablissement pe = find(id);
		em.remove(pe);
	}

}
