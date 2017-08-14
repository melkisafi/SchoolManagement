package gestionScolaire.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gestionScolaire.metier.dao.EvenementDao;
import gestionScolaire.metier.dao.MatiereDao;
import gestionScolaire.metier.dao.MatiereSalleDao;
import gestionScolaire.metier.dao.PersonneMatiereDao;
import gestionScolaire.metier.model.Evenement;
import gestionScolaire.metier.model.Matiere;
import gestionScolaire.metier.model.MatiereSalle;
import gestionScolaire.metier.model.PersonneMatiere;

@Transactional
@Repository
public class MatiereDaoJpa implements MatiereDao {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PersonneMatiereDao personneMatiereDao;
	
	@Autowired
	private MatiereSalleDao matiereSalleDao;
	
	@Autowired
	private EvenementDao evenementDao;
	
	@Override
	public Matiere find(Long id) {
		return em.find(Matiere.class, id);
	}

	@Override
	public List<Matiere> findAll() {
		Query query = em.createQuery("from Matiere");
		return query.getResultList();
	}

	@Override
	public void create(Matiere obj) {
		em.persist(obj);
	}

	@Override
	public Matiere update(Matiere obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Matiere matiere) {
		for (MatiereSalle matiereSalle : matiere.getMatiereSalles()){
			matiereSalleDao.delete(em.merge(matiereSalle));
		}
		
		for (PersonneMatiere personneMatiere : matiere.getPersonneMatieres()){
			personneMatiereDao.delete(em.merge(personneMatiere));
		}
		
		for (Evenement matiereEvement : matiere.getEvenements()){
			evenementDao.delete(em.merge(matiereEvement));
		}
		
		em.remove(em.merge(matiere));
	}

	@Override
	public void delete(Long id) {
		Matiere m = find(id);
		for (MatiereSalle matiereSalle : m.getMatiereSalles()){
			matiereSalleDao.delete(em.merge(matiereSalle));
		}
		
		for (PersonneMatiere personneMatiere : m.getPersonneMatieres()){
			personneMatiereDao.delete(em.merge(personneMatiere));
		}
		
		for (Evenement matiereEvement : m.getEvenements()){
			evenementDao.delete(em.merge(matiereEvement));
		}
		
		em.remove(em.merge(m));
	}

}
