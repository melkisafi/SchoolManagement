package gestionScolaire.metier.dao;

import java.util.List;

import gestionScolaire.metier.model.Salle;


public interface SalleDao extends Dao <Salle , Long>{
	List<Salle> findAllByEtab(Long idEtab);

}
