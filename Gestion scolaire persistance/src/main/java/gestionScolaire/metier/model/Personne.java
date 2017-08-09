package gestionScolaire.metier.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
public class Personne {
	private Long id;
	private String civilite;
	private String nom;
	private String prenom;
	private Date datenaiss;
	//private Adresse adresse;
	private int version;
	
	
	public Personne(){
	}
}
