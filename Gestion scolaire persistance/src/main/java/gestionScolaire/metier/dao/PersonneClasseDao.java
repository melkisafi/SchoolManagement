package gestionScolaire.metier.dao;

import gestionScolaire.metier.model.Personne;
import gestionScolaire.metier.model.PersonneClasse;

public interface PersonneClasseDao extends Dao < PersonneClasse , Long> {
	PersonneClasse findProfPrincipal(Long idClasse);
}
