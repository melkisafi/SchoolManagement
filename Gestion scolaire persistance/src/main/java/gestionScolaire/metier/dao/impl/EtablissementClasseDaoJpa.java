package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.EtablissementClasseDao;
import gestionScolaire.metier.model.EtablissementClasse;

@Transactional
@Repository
public class EtablissementClasseDaoJpa implements EtablissementClasseDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public EtablissementClasse find(Long id) {
		return em.find(EtablissementClasse.class, id);
	}

	@Override
	public List<EtablissementClasse> findAll() {
		Query query = em.createQuery("from EtablissementClasse");
		return query.getResultList();
	}

	@Override
	public void create(EtablissementClasse obj) {
		em.persist(obj);
	}

	@Override
	public EtablissementClasse update(EtablissementClasse obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(EtablissementClasse obj) {
		em.remove(em.merge(obj));
	}

	@Override
	public void delete(Long id) {
		EtablissementClasse etabClasse = find(id);
		em.remove(etabClasse);
	}

}
