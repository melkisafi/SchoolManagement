package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.PersonneMatiereDao;
import gestionScolaire.metier.model.PersonneMatiere;
@Transactional
@Repository
public class PersonneMatiereDaoJpa implements PersonneMatiereDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public PersonneMatiere find(Long id) {
		return em.find(PersonneMatiere.class, id);
	}
	
	@Override
	public List<PersonneMatiere> findAll() {
		Query query = em.createQuery("from PersonneMatiere pm");
		return query.getResultList();
	}

	@Override
	public void create(PersonneMatiere obj) {
		em.persist(obj);
	}

	@Override
	public PersonneMatiere update(PersonneMatiere obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(PersonneMatiere obj) {
		em.remove(em.merge(obj));
	}

	@Override
	public void delete(Long id) {
		PersonneMatiere pm = find(id);
		em.remove(pm);
	}

	
}
