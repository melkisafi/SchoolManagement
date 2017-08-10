package gestionScolaire.metier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Classe {
	private Long id;
	private String nom;
	
	
	public Classe(){	
	}
	
	public Classe(String nom) {
		super();
		this.nom = nom;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	

}
