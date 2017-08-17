package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.EvenementDao;
import gestionScolaire.metier.dao.MatiereSalleDao;
import gestionScolaire.metier.dao.SalleClasseDao;
import gestionScolaire.metier.dao.SalleDao;
import gestionScolaire.metier.model.Classe;
import gestionScolaire.metier.model.Evenement;
import gestionScolaire.metier.model.MatiereSalle;
import gestionScolaire.metier.model.Salle;
import gestionScolaire.metier.model.SalleClasse;

@Transactional
@Repository
public class SalleDaoJpa implements SalleDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private MatiereSalleDao matieresalleDao;

	@Autowired
	private EvenementDao evenementDao;

	@Autowired
	private SalleClasseDao salleClasseDao;

	@Override
	public Salle find(Long id) {
		return em.find(Salle.class, id);
	}

	@Override
	public List<Salle> findAll() {
		Query query = em.createQuery("from Salle s");
		return query.getResultList();
	}

	@Override
	public void create(Salle obj) {
		em.persist(obj);
	}

	@Override
	public Salle update(Salle obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Salle s) {

		for (MatiereSalle matSalle : s.getMatiereSalles()) {
			matieresalleDao.delete(em.merge(matSalle));
		}
		for (SalleClasse sClasses : s.getSalleClasses()) {
			salleClasseDao.delete(em.merge(sClasses));
		}
		for (Evenement sEvents : s.getEvenements()) {
			evenementDao.delete(em.merge(sEvents));
		}
		em.remove(em.merge(s));
	}

	@Override
	public void delete(Long id) {
		Salle s = find(id);
		for (MatiereSalle matSalle : s.getMatiereSalles()) {
			matieresalleDao.delete(em.merge(matSalle));
		}
		for (SalleClasse sClasses : s.getSalleClasses()) {
			salleClasseDao.delete(em.merge(sClasses));
		}
		for (Evenement sEvents : s.getEvenements()) {
			evenementDao.delete(em.merge(sEvents));
		}
		em.remove(s);
	}

	@Override
	public List<Salle> findAllByEtab(Long idEtablissement) {
		Query query1 = em.createQuery("from Salle s where s.etablissement.id = :id ");
		query1.setParameter("id", idEtablissement);
		List<Salle> s = query1.getResultList();
		
		return s.size() > 0 ? s : null;
	}



}
