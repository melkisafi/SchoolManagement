package gestionScolaire.metier.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Status  {
	private Long id;
	private String nom;
	private List<Personne> personne;
	private int version;
	
	public Status(){
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique=true)
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@OneToMany(mappedBy="status")
	public List<Personne> getPersonne() {
		return personne;
	}

	public void setPersonne(List<Personne> personne) {
		this.personne = personne;
	}
	
	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
