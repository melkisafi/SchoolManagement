package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.LoginDao;
import gestionScolaire.metier.model.Login;

@Transactional
@Repository
public class LoginDaoJpa implements LoginDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Login find(Long id) {
		return em.find(Login.class, id);
	}

	@Override
	public List<Login> findAll() {
		Query query = em.createQuery("from Login l");
		return query.getResultList();
	}

	@Override
	public void create(Login obj) {
		em.persist(obj);
	}

	@Override
	public Login update(Login obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Login obj) {
		em.remove(obj);
	}

	@Override
	public void delete(Long id) {
		Login l = find(id);
		em.remove(l);
	}
	
	@Override
	public Login checkLogin(String login, String password) {
		TypedQuery<Login> query = em.createQuery(
				"select l from Login as l where l.login = :login AND l.password=:password ", Login.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		// getSingleResult() throws an exception if no entity is found
		// Login loginEntity = query.getSingleResult();
		List<Login> list = query.getResultList();
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
