package gestionScolaire.metier.dao;

import java.util.List;

import gestionScolaire.metier.model.Etablissement;
import gestionScolaire.metier.model.Evenement;

public interface EvenementDao extends Dao<Evenement,Long>{
	List<Evenement> findAllEvenementByClasse(Long classeId);
}
