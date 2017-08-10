package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.PersonneClasseDao;
import gestionScolaire.metier.model.PersonneClasse;

@Transactional
@Repository
public class PersonneClasseDaoJpa implements PersonneClasseDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public PersonneClasse find(Long id) {
		return em.find(PersonneClasse.class, id);
	}
	
	@Override
	public List<PersonneClasse> findAll() {
		Query query = em.createQuery("from PersonneClasse pc");
		return query.getResultList();
	}

	@Override
	public void create(PersonneClasse obj) {
		em.persist(obj);
	}

	@Override
	public PersonneClasse update(PersonneClasse obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(PersonneClasse obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		PersonneClasse pc = find(id);
		em.remove(pc);
	}

}
