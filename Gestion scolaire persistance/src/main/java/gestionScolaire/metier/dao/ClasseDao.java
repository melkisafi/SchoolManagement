package gestionScolaire.metier.dao;

import java.util.List;

import gestionScolaire.metier.model.Classe;

public interface ClasseDao extends Dao<Classe, Long> {
	List<Classe> findClasseByEtab(Long idEtab);
}
