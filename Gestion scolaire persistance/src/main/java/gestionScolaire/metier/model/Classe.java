package gestionScolaire.metier.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
@SequenceGenerator(name = "seq_classe",sequenceName="seq_classe", initialValue = 1, allocationSize = 1)
public class Classe {
	private Long id;
	private String nom;
	private List<Evenement> evenements;
	private int version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_classe")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@OneToMany(mappedBy="classe")
	public List<Evenement> getEvenements() {
		return evenements;
	}
	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}
	
	@Version
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Classe() {
		super();
	}
	public Classe(String nom) {
		super();
		this.nom = nom;
	}
	
	
}
