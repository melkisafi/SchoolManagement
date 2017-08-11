package gestionScolaire.metier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
@SequenceGenerator(name = "seq_personnematiere",sequenceName="seq_personnematiere", initialValue = 1, allocationSize = 1)
public class PersonneMatiere {

	private Long id;
	private Personne personne;
	private Matiere matiere;
	private int version;
	
	public PersonneMatiere() {
		super();
	}
	
	public PersonneMatiere(Personne personne, Matiere matiere) {
		super();
		this.personne = personne;
		this.matiere = matiere;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_personnematiere")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "personne_id")
	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	@ManyToOne
	@JoinColumn(name = "matiere_id")
	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	

	
	
}
