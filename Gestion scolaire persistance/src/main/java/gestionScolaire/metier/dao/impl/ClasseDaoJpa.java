package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.ClasseDao;
import gestionScolaire.metier.model.Classe;

@Transactional
@Repository
public class ClasseDaoJpa implements ClasseDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Classe find(Long id) {
		return em.find(Classe.class, id);
	}

	@Override
	public List<Classe> findAll() {
		Query query = em.createQuery("from Classe");
		return query.getResultList();
	}

	@Override
	public void create(Classe obj) {
		em.persist(obj);
	}

	@Override
	public Classe update(Classe obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Classe obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		Classe c = find(id);
		em.remove(c);
	}

}
