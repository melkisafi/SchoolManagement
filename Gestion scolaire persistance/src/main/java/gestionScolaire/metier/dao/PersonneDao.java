package gestionScolaire.metier.dao;

import java.util.List;

import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.Status;
import gestionScolaire.metier.model.StatusEnum;

public interface PersonneDao extends Dao<Personne, Long>{
	List<Personne> findByStatus(StatusEnum professeur);
	List<Personne> findProfByEtab(StatusEnum professeur, Long idEtab);
}
