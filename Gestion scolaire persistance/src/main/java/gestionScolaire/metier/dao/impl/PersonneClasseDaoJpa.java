package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.PersonneClasseDao;
import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.PersonneClasse;
import gestionScolaire.metier.model.StatusEnum;

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
	public PersonneClasse findProfPrincipal(Long idClasse) {
		Query query = em.createQuery("from PersonneClasse as pc left join fetch pc.personne  p where pc.principal = :principal and pc.classe.id = :id");
		query.setParameter("principal", true);
		query.setParameter("id", idClasse);
		List<PersonneClasse> p = query.getResultList();
		
		return p.size() > 0 ? p.get(0) : null;
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
		em.remove(em.merge(obj));
	}

	@Override
	public void delete(Long id) {
		PersonneClasse pc = find(id);
		em.remove(pc);
	}

}
