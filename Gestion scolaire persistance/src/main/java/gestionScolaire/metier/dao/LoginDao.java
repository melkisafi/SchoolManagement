package gestionScolaire.metier.dao;

import gestionScolaire.metier.model.Login;

public interface LoginDao extends Dao<Login, Long>{
	Login findByName(String login);
	Login checkLogin(String login, String password);
}
